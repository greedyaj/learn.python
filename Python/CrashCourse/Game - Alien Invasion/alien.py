import pygame

class Alien(pygame.sprite.Sprite):
    """A class to represent a single alien in the fleet."""

    def __init__(self, ai_game):
        super().__init__()

        self.screen = ai_game.screen
        self.settings = ai_game.settings

        # Load the alien image and set its rect attribute.
        self.image = pygame.image.load(self.settings.alien_image)
        self.rect = self.image.get_rect()

        # Start each new alien near the top left of the screen.
        self.rect.x = self.rect.width
        self.rect.y = self.rect.height

        # Store the alien's exact horizontal position.
        self.x_position = float(self.rect.x)
        # Store the alien's exact vertical position.
        self.y_position = float(self.rect.y)
    
    def update(self):
        """Move the alien fleet to the right."""
        self.x_position += (self.settings.alien_speed * self.settings.fleet_direction) 
        self.rect.x = self.x_position

    def _check_edges(self):
        """Check if any of the alien has hit the edge of the screen"""
        if self.rect.right >= self.screen.get_rect().right or self.rect.left <= 0:
            return True
        else:
            return False
    