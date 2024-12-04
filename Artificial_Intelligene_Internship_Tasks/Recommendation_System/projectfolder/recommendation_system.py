import pandas as pd
from sklearn.metrics.pairwise import cosine_similarity
import tkinter as tk
from tkinter import messagebox, ttk

# Step 1: Load Data (Movies and Ratings)
movies = pd.read_csv('movies.csv')
ratings = pd.read_csv('ratings.csv')

# Step 2: Merge data
movie_ratings = pd.merge(ratings, movies, on='movieId')

# Step 3: Create a Pivot Table (User-Item Matrix)
user_movie_matrix = movie_ratings.pivot_table(index='userId', columns='title', values='rating')

# Step 4: Handle Missing Values
user_movie_matrix = user_movie_matrix.fillna(0)

# Step 5: Compute Cosine Similarity
cosine_sim = cosine_similarity(user_movie_matrix)

# Step 6: Recommend Movies for a Specific User
def recommend_movies(user_id, top_n=5):
    user_index = user_id - 1  # Adjusting for zero-indexing
    user_similarities = cosine_sim[user_index]

    similar_users = user_similarities.argsort()[-(top_n + 1):-1][::-1]

    recommended_movies = []
    for sim_user in similar_users:
        similar_user_ratings = user_movie_matrix.iloc[sim_user]
        for movie, rating in similar_user_ratings.items():
            if rating > 3 and movie not in recommended_movies:
                recommended_movies.append(movie)

        if len(recommended_movies) >= top_n:
            break

    return recommended_movies[:top_n]

# Step 7: Create Tkinter Window
def show_recommendations():
    try:
        # Get user ID and number of recommendations from input fields
        user_id = int(user_id_entry.get())
        top_n = int(recommendations_number_slider.get())

        # Get movie recommendations
        recommended_movies = recommend_movies(user_id, top_n)

        # Show recommendations in the text box
        recommendations_text.delete(1.0, tk.END)
        recommendations_text.insert(tk.END, f"Recommended Movies for User {user_id}:\n\n")
        for idx, movie in enumerate(recommended_movies, start=1):
            recommendations_text.insert(tk.END, f"{idx}. {movie}\n")

    except ValueError:
        messagebox.showerror("Invalid Input", "Please enter valid numeric values for both User ID and number of recommendations.")

# Step 8: Set up Tkinter Interface
window = tk.Tk()
window.title("Movie Recommendation System")
window.geometry("700x700")  # Set a fixed window size

# Center the window on the screen
window_width = 600
window_height = 600
screen_width = window.winfo_screenwidth()
screen_height = window.winfo_screenheight()

position_top = int(screen_height / 2 - window_height / 2)
position_left = int(screen_width / 2 - window_width / 2)

window.geometry(f'{window_width}x{window_height}+{position_left}+{position_top}')  # Position the window at the center
window.configure(bg="#f0f8ff")  # Light cyan background color

# Header label with a customized font and color
header_label = tk.Label(window, text="Movie Recommendation System", font=("Courier New", 24, "bold"), bg="#f0f8ff", fg="#333366")
header_label.pack(pady=20)

# Label for User ID
user_id_label = tk.Label(window, text="Enter User ID:", font=("Arial", 14), bg="#f0f8ff", fg="#333366")
user_id_label.pack(pady=10)

# Entry field for User ID
user_id_entry = tk.Entry(window, font=("Arial", 14), width=20, bd=2, relief="solid")
user_id_entry.pack(pady=5)

# Label for number of recommendations
recommendations_number_label = tk.Label(window, text="Number of Recommendations:", font=("Arial", 14), bg="#f0f8ff", fg="#333366")
recommendations_number_label.pack(pady=10)

# Slider for number of recommendations
recommendations_number_slider = tk.Scale(window, from_=1, to=10, orient="horizontal", length=200, font=("Arial", 12), bg="#f0f8ff", fg="#333366")
recommendations_number_slider.set(5)  # Default value is 5 recommendations
recommendations_number_slider.pack(pady=5)

# Label for Movie Genre (optional feature)
genre_label = tk.Label(window, text="Select Movie Genre:", font=("Arial", 14), bg="#f0f8ff", fg="#333366")
genre_label.pack(pady=10)

# Dropdown menu for genre selection (just for show, not affecting recommendations in this example)
genre_options = ['All', 'Action', 'Comedy', 'Drama', 'Horror', 'Romance']
genre_combobox = ttk.Combobox(window, values=genre_options, font=("Arial", 12), width=18)
genre_combobox.set('All')  # Default selection
genre_combobox.pack(pady=5)

# Button to get recommendations with a colorful design
recommend_button = tk.Button(window, text="Get Recommendations", font=("Arial", 14), bg="#4CAF50", fg="white", width=20, height=2, command=show_recommendations)
recommend_button.pack(pady=20)

# Text box to display recommendations with larger font and padding
recommendations_text = tk.Text(window, width=50, height=10, font=("Arial", 12), wrap="word", bd=2, relief="solid", padx=10, pady=10)
recommendations_text.pack(pady=10)

# Run the Tkinter event loop
window.mainloop()
