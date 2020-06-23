file = "Chaprter10 - Files and Exceptions/common_words.txt"

with open(file, "r") as f:
    content = f.read()

print("Word count: ", str(len(content.split())))
print("Word count for 'the': ", str(content.lower().count("the")))