import json

file = "Chaprter10 - Files and Exceptions\\username.json"

def get_username():
    try:
        with open(file, "r") as f:
            username = json.load(f)
    except FileNotFoundError:
        return None        
    else:
        return username

def store_username(num):
    with open(file, "w") as f:
        json.dump(num, f)


def ask_username():
    return input("What's your username?-> ")

def username():
    username = get_username()
    if username != None:
        print(f"{username} is your username.")
        new_username = input("Do you want to change it? (y/n)-> ")
        if new_username == 'y' or new_username == 'Y':
            new_username = ask_username()
            store_username(new_username)
            print("Next time we will remember your username.")
    else:
        username = ask_username()
        store_username(username)
        print("Next time we will remember your username.")

username()
