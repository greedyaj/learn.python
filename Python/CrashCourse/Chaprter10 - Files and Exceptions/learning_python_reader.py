with open("Chaprter10 - Files and Exceptions/learning_python.txt") as f:
    content = f.read()

print(content)

with open("Chaprter10 - Files and Exceptions/learning_python.txt") as f:
    for line in f:
        print(line)

with open("Chaprter10 - Files and Exceptions/learning_python.txt") as f:
    lines = f.readlines()

print(lines)

print(content.replace("Python", "Java"))

