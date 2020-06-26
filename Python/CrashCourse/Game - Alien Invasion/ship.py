import pygame

class Ship(pygame.sprite.Sprite):
    """A class to manage the ship."""
    def __init__(self, ai_game):
        """Initializes the ship and set it's starting position."""
        super().__init__()

        self.screen = ai_game.screen
        self.settings = ai_game.settings
        self.screen_rec = self.screen.get_rect()

        # Load the ship the image and get it's rectangle
        self.image = pygame.image.load(self.settings.ship_image)
        self.rect = self.image.get_rect()

        # Start the new ship at the bottom center of the screen
        self.rect.midbottom = self.screen_rec.midbottom

        # movement flags
        self.move_left = False
        self.move_right = False

        self.x_position = float(self.rect.x)
    
    def blitme(self):
        """Draw the ship at it's current location."""
        self.screen.blit(self.image, self.rect)

    def update(self):
        if self.move_left == True and self.rect.left > 0:
            self.x_position -= self.settings.ship_speed
            self.rect.x -= 1
        if self.move_right == True and self.rect.right < self.screen_rec.right:
            self.x_position += self.settings.ship_speed

        self.rect.x = self.x_position   

    def center_ship(self):
        """Center the ship on the screen."""
        self.rect.midbottom = self.screen_rec.midbottom
        self.x_position = float(self.rect.x)


