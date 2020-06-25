class Settings:
    """A class to store all the settigns of alien invasion game."""
    def __init__(self):
        """Initialize the game settings"""
        self.screen_width = 1200
        self.screen_height = 800
        self.bg_color = (230, 230, 230)

        # Ship settings
        self.ship_image = "Game - Alien Invasion/images/ship.bmp"
        self.ship_speed = 1.5

        # Bullet settings
        self.bullet_speed = 1.0
        self.bullet_width = 3
        self.bullet_height = 15
        self.bullet_color = (60, 60, 60)
        self.bullets_allowed = 10

        # Alien settings
        self.alien_image = "Game - Alien Invasion/images/alien.bmp"
        


