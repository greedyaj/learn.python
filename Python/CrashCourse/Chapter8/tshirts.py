def make_shirt(size, msg = "i love python"):
    print(f"T-Shirt size is {size} and message is {msg.title()}.")

make_shirt("medium", "Jai Shri Ram")
make_shirt(msg = "Docker rocks", size = "large")
make_shirt(size = "medium")
make_shirt(size = "large")