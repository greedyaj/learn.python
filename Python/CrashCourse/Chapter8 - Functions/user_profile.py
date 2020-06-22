def build_profile(first_name, last_name, **user_info):
    user_info["first_name"] = first_name
    user_info["last_name"] = last_name
    return user_info

user = build_profile("Ajit", "Pawar", field = "Computer Science", location="India")
print(user)
