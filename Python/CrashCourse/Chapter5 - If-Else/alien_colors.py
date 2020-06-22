import random

alien_colors = ["green", "yellow", "red"]
alien_color = random.choice(alien_colors)

if alien_color == "yellow":
    score = 5
elif alien_color == "red":
    score = 10
else:
    score = 0

print(f"{score} points erned.")    

