import pygame
from ship import Ship

class Scoreboard:
    """A class to report scoring information."""

    def __init__(self, ai_game):
        super().__init__()
        self.ai_game = ai_game
        self.screen = ai_game.screen
        self.screen_rect = self.screen.get_rect()
        self.settings = ai_game.settings
        self.game_stats = ai_game.game_stats

        # Font settings for scoring information.
        self.font = pygame.font.SysFont(None, self.settings.scoreboard_text_size)
        
        self.high_score = self.get_high_score()

        # Prepare the initial score image.
        self.prep_score()
        self.prep_high_score()
        self.prep_ships()
    
    def prep_score(self):
        """Turn the score into a rendered image."""
        rounded_score = round(self.game_stats.score, -1)
        score_str = "{:,}".format(rounded_score)
        score_str = f"Your Score {score_str}"
        self.score_image = self.font.render(score_str, True, self.settings.scoreboard_text_color, self.settings.bg_color)
        
        # Display the score at the top right of the screen.
        self.score_rect = self.score_image.get_rect()
        self.score_rect.left = self.screen_rect.left
        self.score_rect.top = 35
    
    def prep_high_score(self):
        """Turn the score into a rendered image."""
        score = self.high_score
        #if self.high_score < self.game_stats.score:
        #    score = self.game_stats.score

        rounded_score = round(score, -1)
        score_str = "{:,}".format(rounded_score)
        score_str = f"High Score {score_str}"
        self.high_score_image = self.font.render(score_str, True, self.settings.scoreboard_text_color, self.settings.bg_color)
        
        # Display the score at the top right of the screen.
        self.high_score_rect = self.score_image.get_rect()
        self.high_score_rect.left = self.screen_rect.left
        self.high_score_rect.top = 10
    
    def prep_ships(self):
        """Turn the ships score into a rendered image."""

        self.ships = pygame.sprite.Group()
        right = 1
        for ship_number in range(self.game_stats.ships_left):
            ship = Ship(self.ai_game)
            ship.rect.right = self.screen_rect.right - (ship_number * ship.rect.width)
            ship.rect.top = 10
            self.ships.add(ship)
            right *= -1

    def show_score(self):
        """Draw score to the screen."""
        self.screen.blit(self.score_image, self.score_rect)
        self.screen.blit(self.high_score_image, self.high_score_rect)
        self.ships.draw(self.screen)

    def get_high_score(self):
        try:
            with open("Game - Alien Invasion/highscore.txt") as f:
                return int(f.read())
        except FileNotFoundError:
            print("No one played yet")
        except:
            print("Highscore file currupted")
        return 0

    def save_high_score(self):
        try:
            with open("Game - Alien Invasion/highscore.txt", "w") as f:
                f.write(str(self.high_score))
        except:
            print("Failed to add high score")

    def check_and_update_high_score(self):
        self.high_score = self.get_high_score()
        if self.high_score < self.game_stats.score:
            self.high_score = self.game_stats.score
            self.save_high_score()
            self.prep_high_score()