file = "cats.txt"
try:
    with open(file) as f:
        cats = f.read()
except FileNotFoundError:
    print(f"{file} not found!")
else: 
    print(cats)