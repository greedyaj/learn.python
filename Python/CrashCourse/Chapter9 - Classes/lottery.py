import random as rd

class Lottery:

    def __init__(self):
        super().__init__()
        self.data = ("a", "b", "c", "d", 0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
    
    def generate_lottery_number(self, size=4):
        num = ""
        for index in range(0, size):
            num += str(rd.choice(self.data))
        return num

shriLakshmiLottery = Lottery()
print(f"Lottery number {shriLakshmiLottery.generate_lottery_number()} is a winning number!")