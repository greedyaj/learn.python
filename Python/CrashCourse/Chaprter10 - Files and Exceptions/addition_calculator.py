while True:
    try:
        num1 = input("Enter first number: ")
        if num1 == 'q' or num1 == 'Q':
            break

        num2 = input("Enter second number: ")
        if num2 == 'q' or num2 == 'Q':
            break

        num1 = int(num1)
        num2 = int(num2)
    except ValueError:
        print("Enter the numeric input, please.")
    else:    
        result = num1 + num2
        print("Addition is: " + str(result))