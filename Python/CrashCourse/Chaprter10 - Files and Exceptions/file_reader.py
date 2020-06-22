#pi_file = "c:\MyData\Study\Github\learn.programming\Python\CrashCourse\Chaprter10 - Files and Exceptions\pi_digits.txt"
pi_file = "Chaprter10 - Files and Exceptions/pi_digits.txt"
with open(pi_file) as file_object:
    content = file_object.read()

print(content.rstrip())