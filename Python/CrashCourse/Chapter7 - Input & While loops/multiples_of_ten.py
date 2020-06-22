got_result = False

while got_result == False:
    number = input("Give me a number?\n->")
    try:
        number = int(number)
        got_result = True
    except:
        print("Please give numeric input")   

if number % 10 == 0:
    print(f"Number {number} is in multiples of 10")
else:
    print(f"Number {number} is not in multiples of 10")
   