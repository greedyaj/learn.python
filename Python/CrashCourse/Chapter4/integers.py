for i in range(0, 20):
    print(i)

integers = []
for i in range(0, 100000):
    integers.append(i)

print("\n\nMin:"+ str(min(integers)))
print("\n\nMin:"+ str(max(integers)))
print("\n\nSum:" + str(sum(integers)))

for odd in range(1, 20, 2):
    print(odd)

for part3 in range(3, 31, 3):
    print(part3)

for cube in range(1, 10):
    print(cube ** 3)

cubes = [cube ** 3 for cube in range(1, 10)]
print(cubes)

print("The first three items in the list are:" + str(cubes[:3]))

print("The middle three items in the list are:" + str(cubes[3:6]))

print("The last three items in the list are:" + str(cubes[-3:]))
