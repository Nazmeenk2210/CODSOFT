import React, { useState } from 'react';
import './App.css';
import useTaskManager from './useTaskManager';

function App() {
  const { tasks, addTask, deleteTask, toggleComplete, updateTask } =
    useTaskManager();

  const [editTaskId, setEditTaskId] = useState(null);
  const [taskInput, setTaskInput] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    if (taskInput.trim() === '') return;

    if (editTaskId) {
      updateTask(editTaskId, taskInput);
      setEditTaskId(null);
    } else {
      addTask(taskInput);
    }

    setTaskInput('');
  };

  return (
    <div className="app">
      <h1>Task Tracker</h1>

      <form onSubmit={handleSubmit}>
        <input
          type="text"
          value={taskInput}
          onChange={(e) => setTaskInput(e.target.value)}
          placeholder="Enter a task"
        />
        <button type="submit">{editTaskId ? 'Update Task' : 'Add Task'}</button>
      </form>

      <TaskList
        tasks={tasks}
        deleteTask={deleteTask}
        toggleComplete={toggleComplete}
        setEditTaskId={setEditTaskId}
        setTaskInput={setTaskInput}
      />
    </div>
  );
}

function TaskList({ tasks, deleteTask, toggleComplete, setEditTaskId, setTaskInput }) {
  return (
    <ul>
      {tasks.map((task) => (
        <li key={task.id} className={task.completed ? 'completed' : ''}>
          <span onClick={() => toggleComplete(task.id)}>{task.text}</span>
          <div>
            <button
              onClick={() => {
                setEditTaskId(task.id);
                setTaskInput(task.text);
              }}
            >
              Edit
            </button>
            <button onClick={() => deleteTask(task.id)}>Delete</button>
          </div>
        </li>
      ))}
    </ul>
  );
}

export default App;
