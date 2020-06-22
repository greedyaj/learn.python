from lottery import Lottery

def my_ticket(shriLakshmiLottery):
    return shriLakshmiLottery.generate_lottery_number()

shriLakshmiLottery = Lottery()
winning_lotter_number  = shriLakshmiLottery.generate_lottery_number()
print(f"Lottery number {winning_lotter_number} is a winning number!")

number_of_tickets = 0
found = False
while found == False:
    my_ticket_num = my_ticket(shriLakshmiLottery)
    number_of_tickets += 1
    if my_ticket_num == winning_lotter_number:
        found = True
        print(f"Won a lottery after {number_of_tickets} tickets")
