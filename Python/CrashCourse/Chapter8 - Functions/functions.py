def display_message(msg):
    """Display input msg"""
    print(msg)

def display_simple_message():
    """Display simple msg"""
    display_message("Hello, I am learning about Python Functions.")

def favorite_book(book, author):
    """Display msg with input book and author"""
    display_message(f"One of my favorite book is {book.title()} by {author.title()}.")

def my_favorite_book():
    """Display msg of favrite book"""
    favorite_book("jaya", "devdutt patnaik")

display_simple_message()
my_favorite_book()
