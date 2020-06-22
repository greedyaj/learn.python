def show_messages(msgs):
    [print(msg) for msg in msgs]

original_msgs = ["Namaste", 'Hi', "Hello", "Walha"]
show_messages(original_msgs)

def send_messages(msgs):
    sent_messages = []
    for msg in msgs:
        print(msg)
        sent_messages.append(msg)
    msgs.clear()
    print(msgs)
    print(sent_messages)

send_messages(original_msgs[:])
print(original_msgs)