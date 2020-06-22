import random as rd

class Die:

    def __init__(self, sides=6):
        super().__init__()
        self.sides = sides
    
    def roll(self):
        print("Rolling a die...,value is: " + str(rd.randint(1, self.sides)))

die1 = Die()
die1.roll()

die2 = Die(10)
die2.roll()
