def make_car(manufacturer, model, **optionalInfo):
    car = {}
    car["manufacturer"] = manufacturer
    car["model"] = model
    car.update(optionalInfo)
    return car

car = make_car("Tata", "Nexon", color = "black", varient = "XZA")
print(car)