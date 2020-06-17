def make_sandwitch(*args):
    for arg in args:
        print(f"Sandwitch created with {arg.title()}")

make_sandwitch("browen bread", "cheese", "sauce", "chicken")
make_sandwitch("browen bread", "sauce", "chicken")