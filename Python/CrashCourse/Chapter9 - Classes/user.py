class User:

    def __init__(self, fname, lname, age, location):
        self.fname = fname
        self.lname = lname
        self.age = age
        self.location = location
        self.login_attempts = 0
    
    def describe(self):
        print(f"User's name is {self.fname.title()} {self.lname.title()}, age is {self.age} and is from {self.location.title()}. Login attempts are {self.login_attempts}")
    
    def greet(self):
        print(f"Hello, {self.fname.title()} {self.lname.title()}")
    
    def increment_login_attempts(self):
        self.login_attempts += 1
    
    def reset_login_attempts(self):
        self.login_attempts = 0

user = User("sushant", "singh", 34, "mumbai")
user.greet()
user.describe()

user.increment_login_attempts()
user.describe()

user.reset_login_attempts()
user.describe()

class Admin(User):

    def __init__(self, fname, lname, age, location):
        super().__init__(fname, lname, age, location)
        self.previleges = Previleges(["can add", "can delete", "can modify"])
    
    def show_previleges(self):
        self.previleges.show_previleges()

    def describe(self):
        super().describe()
        self.show_previleges()

class Previleges:

    def __init__(self, previleges):
        self.previleges = previleges
    
    def show_previleges(self):
        print("Previleges are " + str(self.previleges))


user = Admin("ajit", "pawar", "32", "pune")
user.show_previleges()
user.describe()
