import random

users = ["admin", "ab", "bc", "cd"]

for index in range(0, 4):
    user = random.choice(users)
    if user == "admin":
        print("Hello admin, would you like to see a status report?")
    else:
        print(f"Hello {user}, thank you for logging in again.")

if users:
    print("List of users is not empty, its: " + str(users))
else:
    print("List of users is empty")

users.clear()
if users:
    print("List of users is not empty, its: " + str(users))
else:
    print("List of users is empty. We need to find some users!")

current_users = ["admin", "superuser"]
new_users = ["ab", "superuser", "bc", "cd", "ADmin"]

for new_user in new_users:
    if new_user.lower() in current_users:
        print(f"New User name {new_user} rejected.")
    else:
         print(f"New User name {new_user} accepted.")    

ordinal_numbers = range(1, 10)
ordinal_numbers_readout = []
for ordinal_number in ordinal_numbers:
    if ordinal_number == 1:
        ordinal_numbers_readout.append("1st")
    elif ordinal_number == 2:
        ordinal_numbers_readout.append("2nd")
    elif ordinal_number == 3:
        ordinal_numbers_readout.append("3rd")
    else:
        ordinal_numbers_readout.append(str(ordinal_number) + "th")

print(ordinal_numbers_readout)
