// components/Home/Home.js
import React from 'react';
import './Home.css';

function Home() {
  return (
    <div className="home">
      <div className="intro">
        <h1 className="title">Welcome to My Portfolio</h1>
        <p className="sub">
          Hi, I'm Nazmeen Khan, a developer with a passion for building interactive web applications. 
          I enjoy creating seamless user experiences and turning ideas into reality using modern technologies.
        </p>
        <p className="additional-info">
          I am enthusiastic about learning new technologies and staying up-to-date with industry trends. 
          Whether it's frontend, backend, or full-stack development, I love exploring the potential of software to make lives easier.
        </p>
      </div>

      <div className="details">
        <h2>Who Am I?</h2>
        <p>
          I am a self-motivated developer specializing in ReactJS, with a strong interest in creating dynamic and responsive web applications.
          With hands-on experience in modern frontend development, I strive to deliver high-quality user experiences.
        </p>
      </div>

      <div className="quote">
        <h2>Quote I Live By</h2>
        <p>" The only way to do great work is to love what you do. " - Steve Jobs</p>
      </div>

      <div className="cta">
        <p>Explore my portfolio to learn more about my journey, projects, and skills!</p>
      </div>
    </div>
  );
}

export default Home;
