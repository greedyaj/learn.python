def city_country(city, country, population = 0):
    if population != 0:
        return f"{city}, {country} - population {population}.".title()
    else:
        return f"{city}, {country}".title()    

if __name__ == '__main__':
    print(city_country("tokiyo", "jpana", 20000000))