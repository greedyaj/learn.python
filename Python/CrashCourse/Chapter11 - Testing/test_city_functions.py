import unittest
import city_functions as cf

class TestCityFunctions(unittest.TestCase):

    def test_city_country(self):
        result = cf.city_country("mosco", "russia")
        self.assertEqual(result, "Mosco, Russia")
    
    def test_city_country_population(self):
        result = cf.city_country("riyad", "saudi arebia", 20000000)
        self.assertEqual(result, "Riyad, Saudi Arebia - Population 20000000.")

if __name__ == '__main__':
    unittest.main()
