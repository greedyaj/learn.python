from user import User, Admin, Previleges

user1 = User("Aarvi", "Kumar", 2, "Delhi")
user1.login_attempts = 3
user1.describe()

admin1 = Admin("admin1", "", 31, "Mumbai")
admin1.login_attempts = 1
admin1.describe()
admin1.show_previleges()