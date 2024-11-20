import re
import tkinter as tk
from tkinter import scrolledtext

# Define the chatbot responses with regex pattern matching
def chatbot_response(user_input):
    user_input = user_input.lower()  # Convert input to lowercase for easier matching

    # Define responses using regex
    if re.search(r'\bhello\b|\bhi\b|\bhey\b', user_input):
        return "Hi! How can I help you today?"
    elif re.search(r'\bwhat is your name\b', user_input):
        return "I am Aerona, a simple chatbot created to assist you."
    elif re.search(r'\bhow are you\b|\bhow you doing\b', user_input):
        return "I'm just a chatbot, but thanks for asking! How can I assist you?"
    elif re.search(r'\bbye\b|\bgoodbye\b', user_input):
        return "Goodbye! Have a great day!"
    elif re.search(r'\bhelp\b|\bcan you help me\b', user_input):
        return "Sure! I can help you with information. Ask me anything!"
    elif re.search(r'\bwhat can you do\b|\bwhat do you do\b|\bwhat you do\b', user_input):
        return "I can respond to your questions based on predefined rules. Try asking me something!"
    elif re.search(r'\btime\b|\bdate\b', user_input):
        from datetime import datetime
        return f"The current date and time is {datetime.now().strftime('%Y-%m-%d %H:%M:%S')}."
    elif re.search(r'\bjoke\b', user_input):
        return "Why did the scarecrow win an award? Because he was outstanding in his field!"
    elif re.search(r'\bwhat is your favorite color\b', user_input):
        return "I like blue, the color of calm and logic! what about you?"
    elif re.search(r'\bmy favorite color is pink\b', user_input):
        return "oh wow, great choice!"
    elif re.search(r'\bmy favorite color is blue\b', user_input):
        return "nice!"
    elif re.search(r'\bmy favorite color is yellow\b', user_input):
        return "oh i see!"
    elif re.search(r'\bmy favorite color is red\b', user_input):
        return "great choice!"
    elif re.search(r'\bmy favorite color is black\b', user_input):
        return "awesome!"
    elif re.search(r'\bmy favorite color is pink\b', user_input):
        return "fabulous!"
    elif re.search(r'\bthanks\b|\bthank you\b', user_input):
        return "you're welcome !"
    else:
        return "I'm sorry, I didn't understand that. Can you rephrase your question?"

# Function to handle user input and display chatbot response
def send_message():
    user_input = entry.get()  # Get user input from the entry box
    if user_input.strip():  # Check if the input is not empty
        response = chatbot_response(user_input)  # Get the chatbot's response
        chat_area.config(state='normal')  # Enable the chat area for editing
        chat_area.insert(tk.END, "You: " + user_input + "\n", "user_text")  # Display user input
        chat_area.insert(tk.END, "Chatbot: " + response + "\n", "bot_text")  # Display chatbot response
        chat_area.config(state='disabled')  # Disable the chat area for editing
        entry.delete(0, tk.END)  # Clear the input field

# Placeholder functionality for the entry box
def on_entry_click(event):
    if entry.get() == "Type your message here...":
        entry.delete(0, tk.END)
        entry.config(fg="black")

def on_focus_out(event):
    if not entry.get():
        entry.insert(0, "Type your message here...")
        entry.config(fg="grey")

# Setting up the GUI
root = tk.Tk()
root.title("Simple Chatbot")
root.geometry("800x600")
root.resizable(True, True)

# Add a heading
heading = tk.Label(
    root,
    text="Aerona Welcomes You :)",
    font=("Comic Sans MS", 24, "bold"),
    bg="lightgrey",
    fg="darkblue"
)
heading.grid(row=0, column=0, columnspan=2, pady=10, sticky="nsew")

# Create a text area for chat history
chat_area = scrolledtext.ScrolledText(
    root,
    wrap=tk.WORD,
    state='disabled',
    width=60,
    height=20,
    bg="lightblue",
    fg="black"
)
chat_area.grid(row=1, column=0, columnspan=2, padx=10, pady=10)
chat_area.tag_config("user_text", foreground="blue", font=("Arial", 15, "italic"))
chat_area.tag_config("bot_text", foreground="green", font=("Arial", 15, "bold"))

# Create an entry box for user input with a placeholder
entry = tk.Entry(root, width=50, font=("Arial", 16))
entry.insert(0, "Type your message here...")
entry.bind("<FocusIn>", on_entry_click)
entry.bind("<FocusOut>", on_focus_out)
entry.config(fg="grey")
entry.grid(row=2, column=0, padx=10, pady=10, sticky="e")

# Create a send button
send_button = tk.Button(root, text="SEND", font=("Arial", 14), bg="lightblue", fg="black", command=send_message)
send_button.grid(row=2, column=1, padx=10, pady=10, sticky="w")

# Adjust column weights to center the widgets
root.grid_columnconfigure(0, weight=1)
root.grid_columnconfigure(1, weight=1)

# Start the GUI loop
root.mainloop()
