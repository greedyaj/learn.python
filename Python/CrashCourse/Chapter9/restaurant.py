class Restaurant:

    def __init__(self, name, cuisine, number_served = 0):
        self.name = name
        self.cuisine = cuisine
        self.number_served = number_served
    
    def describe(self):
        print(f"Restaurant is {self.name.title()} and it has {self.cuisine.title()} cuisine, and its serving {self.number_served} customers")
    
    def is_open(self):
        return True
    
    def set_number_served(self, number_served):
        self.number_served = number_served

    def increment_number_served(self):
        self.number_served += 1


restaurant = Restaurant("Agtya", "North Indian")
restaurant.describe()
is_open = restaurant.is_open()
if is_open == True:
    is_open = "open"
else:
    is_open = "closed"    
print(f"Restaurant {restaurant.name.title()} is {is_open}")

restaurant = Restaurant("Girija", "Maharashtrian")
restaurant.describe()

restaurant = Restaurant("Little Itly", "Italian")
restaurant.describe()

restaurant.set_number_served(21)
restaurant.describe()

restaurant.increment_number_served()
restaurant.describe()

class IceCreamStand(Restaurant):

    def __init__(self, name, cuisine, number_served=0, flavors=["vanilla", "chocolate", "lichi", "strawberry"]):
        super().__init__(name, cuisine, number_served=number_served)
        self.flavors = flavors
    
    def describe(self):
        super().describe()
        print("It has ice creams as " + str(self.flavors))

restaurant = IceCreamStand("giani", "indian")
restaurant.describe()
