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
        self.ship_limit = 3

        # Bullet settings
        self.bullet_speed = 3
        self.bullet_width = 3
        self.bullet_height = 15
        self.bullet_color = (60, 60, 60)
        self.bullets_allowed = 3

        # Alien settings
        self.alien_image = "Game - Alien Invasion/images/alien.bmp"
        self.alien_speed = 1
        self.alien_points = 50
        self.fleet_drop_speed = 10
        # fleet_direction of 1 represents right; -1 represents left.
        self.fleet_direction = 1
        
        # Button settings
        self.button_width = 200
        self.button_height = 50
        self.button_color = (60, 60, 60)
        self.button_text_color = (255, 255, 255)

        # Level up speech increase
        self.speedup_scale = 1.1

        # Scoreboard settings
        self.scoreboard_text_color = (60, 60, 60)
        self.scoreboard_text_size = 23
    
    def initialize_dynamic_settings(self):
        """Initialize settings that change throughout the game."""
        self.ship_speed = 1.5
        self.bullet_speed = 3.0
        self.alien_speed = 1.0

        # fleet_direction of 1 represents right; -1 represents left.
        self.fleet_direction = 1

        self.alien_points = 50

    def levle_up(self):
        """Increase speed settings."""
        self.ship_speed *= self.speedup_scale
        self.bullet_speed *= self.speedup_scale
        self.alien_speed *= self.speedup_scale
        
        # Increase points..
        self.alien_points += 5
        


