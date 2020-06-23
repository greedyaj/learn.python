guests = []
guests_book = []
while True:
    guest = input("Please enter guest name... ")
    guest = guest.lower()
    if guest == 'q' or guest == 'quit':
        break
    guest = guest.title()
    guests.append(guest)
    
    print(f"Hello and Welcome, {guest}")

    guests_book.append(f"Guest {guest} has visited the crash course training room.\n")

with open("Chaprter10 - Files and Exceptions/guest.txt", "w") as f:
    for guest in guests:
        f.write(guest)
        f.write("\n")

with open("Chaprter10 - Files and Exceptions/guest_book.txt", "w") as f:
    [f.write(line) for line in guests_book]