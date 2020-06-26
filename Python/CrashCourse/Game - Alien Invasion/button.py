import pygame

class Button:

    def __init__(self, ai_game, msg):
        super().__init__()
        self.screen = ai_game.screen
        self.settings = ai_game.settings

        # Set the dimensions and properties of the button.
        self.font = pygame.font.SysFont(None, 58)

        # Build the button's rect object and center it.
        self.rect = pygame.Rect(0, 0, self.settings.button_width, self.settings.button_height)
        self.rect.center = self.screen.get_rect().center

        # The button message needs to be prepped only once.
        self._prep_msg(msg)

    def _prep_msg(self, msg):
        """Turn msg into a rendered image and center text on the button."""
        self.msg_image = self.font.render(msg, True, self.settings.button_text_color, self.settings.button_color)
        self.msg_image_rect = self.msg_image.get_rect()
        self.msg_image_rect.center = self.rect.center
    
    def draw_button(self):
        """Draw button"""
        self.screen.blit(self.msg_image, self.msg_image_rect)