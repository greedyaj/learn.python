rivers = {
    "nile" : "egypt",
    "sindhu" : "india",
    "amazon" : "brazil",
    "mississippi" : "u.s."
}

for river, country in rivers.items():
    print(f"{river.title()} is in {country.title()}")

for river in rivers.keys():
    print(river.title())

for country in rivers.values():
    print(country.title())