got_result = False
while got_result == False:
    number_of_personals = input("How many people are there in your dinner group?\n->")
    try:
        number_of_personals = int(number_of_personals)
        got_result = True
    except:
        print("Please give numeric answer")

if number_of_personals > 8:
    print(f"For group of {number_of_personals}, you will have wait.")
else:
    print("Your table is ready.")    
