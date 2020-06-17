def make_album(name, singer, numOfSongs = None):
    album = {"name" : name, "singer" : singer}
    if(numOfSongs != None):
        album["songs"] = numOfSongs
    return album

print(make_album(singer = "KK", name = "Bas ek Pal"))
print(make_album("Doorie", "Atif", 11))
print(make_album("Fireworks", "Ketty Perry"))