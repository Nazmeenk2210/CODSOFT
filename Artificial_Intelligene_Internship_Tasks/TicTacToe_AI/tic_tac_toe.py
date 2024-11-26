import tkinter as tk
from tkinter import messagebox

# Initialize the main window
root = tk.Tk()
root.title("Tic-Tac-Toe AI")
root.geometry("700x700")  # Increased size of the window

# Initialize the board with placeholders
board = [[str(i * 3 + j) for j in range(3)] for i in range(3)]
buttons = {}  # Dictionary to hold button widgets for easy access

# Function to reset the board
def reset_board():
    global board
    board = [[str(i * 3 + j) for j in range(3)] for i in range(3)]
    for i in range(3):
        for j in range(3):
            buttons[(i, j)].config(text=str(i * 3 + j), state=tk.NORMAL, fg="lightgray")  # Light gray placeholder text

# Function to check for a winner
def check_winner():
    for i in range(3):
        # Check rows and columns
        if board[i][0] == board[i][1] == board[i][2] in {"X", "O"}:
            return board[i][0]
        if board[0][i] == board[1][i] == board[2][i] in {"X", "O"}:
            return board[0][i]

    # Check diagonals
    if board[0][0] == board[1][1] == board[2][2] in {"X", "O"}:
        return board[0][0]
    if board[0][2] == board[1][1] == board[2][0] in {"X", "O"}:
        return board[0][2]

    return None

# Check if the board is full (draw)
def is_draw():
    return all(board[i][j] in {"X", "O"} for i in range(3) for j in range(3))

# Minimax algorithm for AI decision making
def minimax(board, is_maximizing):
    winner = check_winner()
    if winner == "X":
        return 1
    elif winner == "O":
        return -1
    elif is_draw():
        return 0

    if is_maximizing:
        best_score = -float('inf')
        for move in available_moves(board):
            board[move[0]][move[1]] = "X"
            score = minimax(board, False)
            board[move[0]][move[1]] = str(move[0] * 3 + move[1])
            best_score = max(score, best_score)
        return best_score
    else:
        best_score = float('inf')
        for move in available_moves(board):
            board[move[0]][move[1]] = "O"
            score = minimax(board, True)
            board[move[0]][move[1]] = str(move[0] * 3 + move[1])
            best_score = min(score, best_score)
        return best_score

# Function to find the best move for AI
def best_move():
    best_score = -float('inf')
    move = None
    for i in range(3):
        for j in range(3):
            if board[i][j] not in {"X", "O"}:
                board[i][j] = "X"
                score = minimax(board, False)
                board[i][j] = str(i * 3 + j)
                if score > best_score:
                    best_score = score
                    move = (i, j)
    return move

# Function for player click
def player_move(i, j):
    if board[i][j] not in {"X", "O"}:
        board[i][j] = "O"
        buttons[(i, j)].config(text="O", state=tk.DISABLED)
        if check_winner() == "O":
            messagebox.showinfo("Game Over", "Congratulations! You win!")
            disable_all_buttons()
            return
        elif is_draw():
            messagebox.showinfo("Game Over", "It's a draw!")
            disable_all_buttons()
            return

        # AI move
        ai_move = best_move()
        if ai_move:
            board[ai_move[0]][ai_move[1]] = "X"
            buttons[ai_move].config(text="X", state=tk.DISABLED)
            if check_winner() == "X":
                messagebox.showinfo("Game Over", "AI wins! Better luck next time.")
                disable_all_buttons()
            elif is_draw():
                messagebox.showinfo("Game Over", "It's a draw!")
                disable_all_buttons()

# Disable all buttons
def disable_all_buttons():
    for button in buttons.values():
        button.config(state=tk.DISABLED)

# Get available moves
def available_moves(board):
    return [(i, j) for i in range(3) for j in range(3) if board[i][j] not in {"X", "O"}]

# Create buttons for the board with placeholders
frame = tk.Frame(root)  # Create a frame to hold the buttons, for centering
frame.pack(expand=True)

for i in range(3):
    for j in range(3):
        btn = tk.Button(frame, text=str(i * 3 + j), width=10, height=3, font=("Helvetica", 24),
                        command=lambda i=i, j=j: player_move(i, j), fg="lightgray")  # Light gray placeholder text
        btn.grid(row=i, column=j, padx=10, pady=10)
        buttons[(i, j)] = btn

# Add Reset Button
reset_btn = tk.Button(root, text="Reset", command=reset_board, font=("Helvetica", 14))
reset_btn.pack(pady=20)

# Run the main loop
root.mainloop()
