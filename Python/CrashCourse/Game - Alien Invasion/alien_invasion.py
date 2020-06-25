import sys
import pygame
import settings
from ship import Ship
from bullets import Bullet
from alien import Alien

class AlienInvasion:
    """Overall class to manage the gave assets and their behavior."""

    def __init__(self):
        """Initializes the game and create game resources."""
        pygame.init()
        self.settings = settings.Settings()

        #self.screen = pygame.display.set_mode((0, 0), pygame.FULLSCREEN)
        #self.settings.screen_width = self.screen.get_rect().width
        #self.settings.screen_height = self.screen.get_rect().height
        
        self.screen = pygame.display.set_mode((self.settings.screen_width, self.settings.screen_height))
        pygame.display.set_caption("Alien Invasion")

        self.ship = Ship(self)
        self.bullets = pygame.sprite.Group()
        self.aliens = pygame.sprite.Group()

        self._create_fleet()
    
    def run_game(self):
        """Start teh main loop for the game."""
        while True:
            self._check_update()
            self.ship.update()
            self._update_bullets()
            self._update_screen()
    
    def _check_update(self):
        """Listen for keboard and mouse events."""
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                sys.exit()
            elif event.type == pygame.KEYDOWN:
                self._check_keydown_events(event)
            elif event.type == pygame.KEYUP:
                self._check_keyup_events(event)    

    def _update_bullets(self):
        """Update position of bullets and get rid of old bullets."""
        self.bullets.update()
        # Get rid of bullets that have disappeared.
        for bullet in self.bullets.copy():
            if bullet.rect.bottom <= 0:
                self.bullets.remove(bullet)

    def _check_keydown_events(self, event):
        """Respond to keypresses."""
        if event.key == pygame.K_LEFT:
            self.ship.move_left = True
        elif event.key == pygame.K_RIGHT:
            self.ship.move_right = True
        elif event.key == pygame.K_SPACE:
            self._fire_bullet()  
        elif event.key == pygame.K_q or event.key == pygame.K_ESCAPE:
            sys.exit()
    
    def _check_keyup_events(self, event):
        """Respond to key releases."""
        if event.key == pygame.K_LEFT:
            self.ship.move_left = False
        elif event.key == pygame.K_RIGHT:
            self.ship.move_right = False

    def _create_fleet(self):
        """Create the fleet of aliens."""
        # Make an alien.
        alien = Alien(self)
        self.aliens.add(alien)

    def _update_screen(self):
        """Redraw the scrern upon each pass through the loop."""
        self.screen.fill(self.settings.bg_color)
        self.ship.blitme()
        for bullet in self.bullets.sprites():
            bullet.draw_bullet()
        
        self.aliens.draw(self.screen)

        # Make the most recent screen visible
        pygame.display.flip()
    
    def _fire_bullet(self):
        """Create a new bullet and add it to the bullets group."""
        if len(self.bullets) < self.settings.bullets_allowed:
            new_bullet = Bullet(self)
            self.bullets.add(new_bullet)

if __name__ == '__main__':
    ai = AlienInvasion()
    ai.run_game()

