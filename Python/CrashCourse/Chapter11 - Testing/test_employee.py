import unittest
import employee as emp

class TestEmployee(unittest.TestCase):

    def setUp(self):
        self.employee = emp.Employee("Rus", "Pet", 10000)
    
    def test_give_raise(self):
        self.employee.give_raise()
        self.assertEqual(self.employee.salary, 15000)
    
    def test_give_custom_raise(self):
        self.employee.give_raise(3000)
        self.assertEqual(self.employee.salary, 13000)

if __name__ == '__main__':
    unittest.main()
