while (True):
    age = input("What's your age?\n->")
    if age.lower() != 'quit':
        try:
            age = int(age)
        except:
            print("Please provide numeric input")
            continue    
    else:
        break

    if age < 3:
        ticket_price = "free"
    elif age >=3 and age < 12:
        ticket_price = "$10"
    else:
        ticket_price = "$15"        
    
    print(f"\nThe ticket is {ticket_price}")