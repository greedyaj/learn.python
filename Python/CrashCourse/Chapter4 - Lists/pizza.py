pizzas = ["margarita", "corn", "chicken", "vegie"]

for pizza in pizzas:
    print("I like " + pizza.title() + " pizza.")
print("I really love pizza!")

friend_pizza = pizzas[:]
pizzas.append("double cheese")
friend_pizza.append("mutton")

print("\nMy favorit Pizza are: " + str(pizzas))
print("\nFriends's favorit Pizza are: " + str(friend_pizza))
