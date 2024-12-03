// components/About/About.js
import React from 'react';
import './About.css';
import myPhoto from '../naaz.jpg'; // Replace with actual path to your photo

function About() {
  return (
    <div className="about">
      <h2>About Me</h2>
      <div className="about-content">
        <div className="about-image">
          <img src={myPhoto} alt="My Photo" />
        </div>
        <div className="about-text">
          <p>
            I am a passionate <span className="highlight">ReactJS developer</span> with an interest in creating dynamic and user-friendly web applications. I thrive on solving complex problems and delivering high-quality solutions. 
          </p>
          <p>
            With a strong foundation in web development technologies such as JavaScript, ReactJS, Node.js, and MongoDB, I specialize in building full-stack applications that are scalable, efficient, and easy to maintain. I am constantly exploring new technologies and improving my skills to stay ahead in this ever-evolving field.
          </p>
          <p>
            Outside of coding, I enjoy working on personal projects, collaborating with others, and learning about new advancements in technology. I believe in continuous learning and always strive to apply new concepts to my work. My goal is to create meaningful software solutions that make a positive impact on users' lives.
          </p>
        </div>
      </div>
      <div className="about-footer">
        <p>I'm always looking for new opportunities to collaborate and grow. Feel free to connect!</p>
      </div>
    </div>
  );
}

export default About;
