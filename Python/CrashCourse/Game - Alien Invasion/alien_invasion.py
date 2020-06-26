import sys
import pygame
import settings
from ship import Ship
from bullets import Bullet
from alien import Alien
from game_state import GameStats
from button import Button
from scoreboard import Scoreboard
from time import sleep

class AlienInvasion:
    """Overall class to manage the gave assets and their behavior."""

    def __init__(self):
        """Initializes the game and create game resources."""
        pygame.init()

        self.settings = settings.Settings()
        self.game_stats = GameStats(self)
        #self.screen = pygame.display.set_mode((0, 0), pygame.FULLSCREEN)
        #self.settings.screen_width = self.screen.get_rect().width
        #self.settings.screen_height = self.screen.get_rect().height
        
        self.screen = pygame.display.set_mode((self.settings.screen_width, self.settings.screen_height))
        pygame.display.set_caption("Alien Invasion")

        self.ship = Ship(self)
        self.bullets = pygame.sprite.Group()
        self.aliens = pygame.sprite.Group()

        self._create_fleet()

        # Play button
        self.play_button = Button(self, "Play")

        # Scoreboard
        self.scoreboard = Scoreboard(self)
    
    def run_game(self):
        """Start teh main loop for the game."""
        while True:
            self._check_update()
            
            if self.game_stats.game_active == True:
                self.ship.update()
                self._update_bullets()
                self._update_fleet()
                
            self._update_screen()
    
    def _check_update(self):
        """Listen for keboard and mouse events."""
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                self.quit()
            elif event.type == pygame.KEYDOWN:
                self._check_keydown_events(event)
            elif event.type == pygame.KEYUP:
                self._check_keyup_events(event)
            elif event.type == pygame.MOUSEBUTTONDOWN:
                mouse_pos = pygame.mouse.get_pos()
                self._check_play_button(mouse_pos)  

    def _check_keydown_events(self, event):
        """Respond to keypresses."""
        if event.key == pygame.K_LEFT:
            self.ship.move_left = True
        elif event.key == pygame.K_RIGHT:
            self.ship.move_right = True
        elif event.key == pygame.K_SPACE:
            self._fire_bullet()  
        elif event.key == pygame.K_q or event.key == pygame.K_ESCAPE:
            self.quit()

    def _fire_bullet(self):
        """Create a new bullet and add it to the bullets group."""
        if len(self.bullets) < self.settings.bullets_allowed:
            new_bullet = Bullet(self)
            self.bullets.add(new_bullet)

    def _check_play_button(self, mouse_pos):
        """Start a new game when the player clicks Play."""
        if self.play_button.rect.collidepoint(mouse_pos) and not self.game_stats.game_active:
            # Reset the game statistics.
            self.game_stats.reset_stats()    
            self.game_stats.game_active = True
            self.scoreboard.prep_score()
            self.scoreboard.prep_high_score()
            self.scoreboard.prep_ships()

            # Get rid of any remaining aliens and bullets.
            self.aliens.empty()
            self.bullets.empty()

            # Create a new fleet and center the ship.
            self._create_fleet()
            self.ship.center_ship()

    def _check_keyup_events(self, event):
        """Respond to key releases."""
        if event.key == pygame.K_LEFT:
            self.ship.move_left = False
        elif event.key == pygame.K_RIGHT:
            self.ship.move_right = False

    def _update_bullets(self):
        """Update position of bullets and get rid of old bullets."""
        self.bullets.update()
        # Get rid of bullets that have disappeared.
        for bullet in self.bullets.copy():
            if bullet.rect.bottom <= 0:
                self.bullets.remove(bullet)
        
        self._check_bullet_alien_collision()
    
    def _check_bullet_alien_collision(self):
        # Check for any bullets that have hit aliens. If so, get rid of the bullet and the alien.
        collisions = pygame.sprite.groupcollide(self.bullets, self.aliens, True, True)
        
        # Update number of aliens killed count
        if collisions:
            [self._add_score(shot_aliens) for shot_aliens in collisions.values()]

        if not self.aliens:
            # Destroy existing bullets and create new fleet.
            self.bullets.empty()
            self._create_fleet()
            self.settings.levle_up()
    
    def _create_fleet(self):
        """Create the fleet of aliens."""
        # Create an alien and find the number of aliens in a row.
        # Spacing between each alien is equal to one alien width.
        alien = Alien(self)
        alien_width = alien.rect.width
        alien_height = alien.rect.height

        available_height = self.settings.screen_height // 1.9
        number_of_alien_rows = int(available_height // alien_height)
        available_space_on_row = self.settings.screen_width 

        for row_number in range(1, number_of_alien_rows + 1):
            available_space_on_row = available_space_on_row - (2 * alien_width)
            number_of_aliens_on_row = available_space_on_row // (2* alien_width)
            for alien_number in range(number_of_aliens_on_row):
                self._create_alien((row_number * alien_width) + (2 * alien_width * alien_number), row_number * alien_height)

    def _create_alien(self, x_position, y_position):
        """Create's the alien object and add's it to the fleet of aliens."""
        alien = Alien(self)
        alien.x_position = x_position
        alien.y_position = y_position
        alien.rect.x = alien.x_position
        alien.rect.y = alien.y_position
        self.aliens.add(alien)

    def _add_score(self, shot_aliens):
        self.game_stats.score += len(shot_aliens) * self.settings.alien_points
        self.scoreboard.prep_score()

    def _update_fleet(self):
        """Check if the fleet is at an edge, then update the positions of all aliens in the fleet."""
        self._check_fleet_edges()
        self.aliens.update()

        # Look for alien-ship collisions.
        if pygame.sprite.spritecollideany(self.ship, self.aliens):
            self._ship_hit()

        # Look for aliens hitting the bottom of the screen.
        self._check_alien_bottom()

    def _check_fleet_edges(self):
        """Respond appropriately if any aliens have reached an edge."""
        for alien in self.aliens.sprites():
            if alien._check_edges() == True:
                self._change_fleet_direction()
                break

    def _change_fleet_direction(self):
        """Drop the entire fleet and change the fleet's direction."""
        self.settings.fleet_direction *= -1
        for alien in self.aliens.sprites():
            alien.y_position += self.settings.fleet_drop_speed 
            alien.rect.y = int(alien.y_position)

    def _check_alien_bottom(self):
        """Check if any aliens have reached the bottom of the screen."""
        screen_rec = self.screen.get_rect()
        for alien in self.aliens.sprites():
            if alien.rect.bottom >= screen_rec.bottom:
                # Treat this the same as if the ship got hit.
                self._ship_hit()
                break

    def _ship_hit(self):
        """Respond to the ship being hit by an alien."""    
        if self.game_stats.ships_left > 0:
            # Decrement ships_left.
            self.game_stats.ships_left -= 1

            # Get rid of any remaining aliens and bullets.
            self.bullets.empty()
            self.aliens.empty()

            # Display ship count
            self.scoreboard.prep_ships()

            # Create a new fleet and center the ship.
            self._create_fleet()
            self.ship.center_ship()

            # Pause.
            sleep(1)
        else:
            self.game_stats.game_active = False
            self.settings.initialize_dynamic_settings()
            self.scoreboard.check_and_update_high_score()

    def _update_screen(self):
        """Redraw the scrern upon each pass through the loop."""

        self.screen.fill(self.settings.bg_color)
        self.ship.blitme()
        for bullet in self.bullets.sprites():
            bullet.draw_bullet()
        
        self.aliens.draw(self.screen)

        if not self.game_stats.game_active:
            self.play_button.draw_button()

        self.scoreboard.show_score()

        # Make the most recent screen visible
        pygame.display.flip()

    def quit(self):
        self.scoreboard.check_and_update_high_score()
        sys.exit()

if __name__ == '__main__':
    ai = AlienInvasion()
    ai.run_game()

