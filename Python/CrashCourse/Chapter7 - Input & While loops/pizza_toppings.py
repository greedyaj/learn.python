pizza_toppings = []
topping = ""
while topping.lower() != 'quit':
    topping = input("Tell, the toppings you want on your pizza?\n->")
    if(topping.lower() != "quit"):
        pizza_toppings.append(topping)

print(f"\nYour toppings are {pizza_toppings}")