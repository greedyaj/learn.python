class Employee:

    def __init__(self, fname, lname, salary):
        super().__init__()
        self.fname = fname
        self.lname = lname
        self.salary = salary

    def give_raise(self, increment=5000):
        self.salary += increment

if __name__ == '__main__':
    e = Employee("AB", "Jr", 100000)
    print(e)
    
