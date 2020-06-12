fruits = ["Mango", "Apple", "Banana"]
fruit = input("Give me a fruit name, that you think I like: ").title()

if fruit in fruits:
    print(f"yup, {fruit} is my favorite.")
else:
    print(f"nope, {fruit} is not my favorite")    

