import json

file = "Chaprter10 - Files and Exceptions\\favorite_number.json"

def get_favorite_number():
    try:
        with open(file, "r") as f:
            favorite_number = json.load(f)
    except FileNotFoundError:
        return None        
    else:
        return favorite_number

def store_favorite_number(num):
    with open(file, "w") as f:
        json.dump(num, f)


def ask_favorite_number():
    return input("Whats your favorite number?-> ")

def favorite_number():
    favorite_number = get_favorite_number()
    if favorite_number != None:
        print(f"{favorite_number} is your favorite number.")
    else:
        favorite_number = ask_favorite_number()
        store_favorite_number(favorite_number)
        print("Next time we will remember your favorite number.")

favorite_number()
