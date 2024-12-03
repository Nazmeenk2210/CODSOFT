import React from 'react';
import './Projects.css';

function Projects() {
  const projectList = [
    { 
      name: 'Snake Game', 
      description: 'Developed a classic Snake game in Java, utilizing Swing and AWT for the user interface and game board. Implemented key features like snake movement, growth mechanics, collision detection, and a game-over screen. Applied object-oriented programming principles, abstract classes, and exception handling for a modular and stable game experience. This project enhanced my understanding of game logic, user input handling, and Java programming, providing a solid foundation in game development.' 
    },
    { 
      name: 'Notepad Clone', 
      description: 'Built my first Java desktop application—a Notepad Clone using Java Swing and JFrame in NetBeans IDE. This project allowed me to explore Java’s graphical interface capabilities, enabling users to create, edit, save, and open text files. It was a valuable learning experience, enhancing my skills in GUI design and desktop application development.' 
    },
    { 
      name: 'Student Management System', 
      description: 'Developed a Student Management System using Java, Swing, and NetBeans IDE. The system allows for efficient management of student data, including adding, editing, and removing records. I implemented object-oriented programming principles, file handling for data storage using serialization, and data validation through regular expressions. The project also utilized Swing and JFrame to create a user-friendly, interactive interface, and incorporated event-driven programming to handle user actions effectively. This project sharpened my skills in Java development and problem-solving.' 
    },
    { 
      name: 'ATM Interface', 
      description: 'Developed an ATM Interface as part of my Java Programming Internship at CodSoft. The project features a user-friendly interface with functions like Deposit, Withdraw, Check Balance, and Transaction History. I implemented secure PIN-based authentication and real-time error handling using Java, Swing, and JFrame. This experience deepened my understanding of Java programming, GUI design, and software development.' 
    },
    { 
      name: 'Number Guessing Game', 
      description: 'Developed a Number Guessing Game in Java using IntelliJ IDEA, where players are challenged to guess a randomly generated number. The game provides helpful hints to guide users toward the correct answer. This project strengthened my Java programming skills, focusing on logic implementation, random number generation, and user interaction.' 
    },
    { 
      name: 'Tic Tac Toe', 
      description: 'Developed a Python-based Tic-Tac-Toe game featuring an AI opponent powered by the Minimax algorithm for optimal gameplay. The game includes a dynamic, interactive GUI built with Tkinter, ensuring a smooth and responsive user experience. The AI makes intelligent decisions to provide a challenging gameplay experience. I enhanced my skills in Python, AI algorithms, and GUI development, tackling complex problems in game logic and state management.'
    },
    { 
      name: 'Chatbot', 
      description: 'Developed a simple rule-based chatbot using Python and Tkinter. The chatbot utilizes regex patterns to process user inputs and provide appropriate responses. The project focused on building a user-friendly GUI, enhancing interaction through an engaging interface, and solving problems related to AI-driven communication. This task sharpened my skills in Python, Tkinter, and basic AI implementation.' 
    },
    { 
      name: 'Task Tracker', 
      description: 'Developed a Task Tracker application as part of my ReactJS Developer Internship at CodSoft. The app allows users to add, update, delete, and edit tasks. I used React state management and hooks to handle task operations, with a responsive and intuitive interface for seamless task management. The project enhanced my skills in component design, form handling, and dynamic updates to improve user experience.' 
    },
    { 
      name: 'Calculator', 
      description: 'Built a basic calculator using HTML, CSS, and JavaScript, featuring an interactive interface with buttons for mathematical operations like addition, subtraction, multiplication, and division. I utilized CSS grid for button layout and JavaScript to manage user inputs, perform calculations, and handle operations using event listeners and loops, improving my skills in front-end development and JavaScript programming.' 
    },
    { 
      name: 'Typing Speed Game', 
      description: 'Developed a Typing Speed Game in Python using PyCharm and executed via Command Prompt. The game tests typing speed in an engaging way, offering real-time feedback for improvement. It was an excellent opportunity to hone my Python skills, enhance problem-solving abilities, and improve user experience design while focusing on performance optimization.' 
    },
  ];

  return (
    <div className="projects">
      <h2 className="title">My Projects</h2>
      <p className="subtitle">A showcase of my projects that highlight my skills and learning journey. 🎮💻</p>
      <div className="project-list">
        {projectList.map((project, index) => (
          <div
            key={index}
            className={`project-container ${index % 2 === 0 ? 'left-align' : 'right-align'}`}
          >
            <div className="project-title">
              <h3>{project.name}</h3>
            </div>
            <div className="project-card">
              {typeof project.description === 'string' ? (
                <p>{project.description}</p>
              ) : (
                project.description
              )}
            </div>
          </div>
        ))}
      </div>
      <p className="footer-text">Many more exciting projects are coming soon! Stay tuned! 🚀</p>
    </div>
  );
}

export default Projects;
