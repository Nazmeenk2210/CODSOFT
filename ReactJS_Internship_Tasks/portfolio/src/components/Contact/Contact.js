// components/Contact/Contact.js
import React, { useState } from 'react';
import emailjs from 'emailjs-com';
import './Contact.css';

function Contact() {
  const [formData, setFormData] = useState({ name: '', email: '', phone: '', linkedin: '', message: '' });
  const [isSending, setIsSending] = useState(false);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    setIsSending(true);

    emailjs
      .send('service_o4gw514', 'template_rk2fnrs', formData, 'LKY8LREjO5ye9MW7C')
      .then(() => {
        alert('Message sent successfully! Nazmeen Khan will respond to you within 48 hours.');
        setFormData({ name: '', email: '', phone: '', linkedin: '', message: '' });
      })
      .catch(() => alert('An error occurred. Please try again.'))
      .finally(() => setIsSending(false));
  };

  return (
    <div className="contact">
      <h2>Contact Me</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="name">Full Name :</label>
          <input
            id="name"
            type="text"
            name="name"
            value={formData.name}
            onChange={handleChange}
            placeholder="Your Full Name"
            required
          />
        </div>

        <div className="form-group">
          <label htmlFor="email">Email Address :</label>
          <input
            id="email"
            type="email"
            name="email"
            value={formData.email}
            onChange={handleChange}
            placeholder="Your Email Address"
            required
          />
        </div>

        <div className="form-group">
          <label htmlFor="phone">Phone Number :</label>
          <input
            id="phone"
            type="text"
            name="phone"
            value={formData.phone}
            onChange={handleChange}
            placeholder="Your Phone Number"
          />
        </div>

        <div className="form-group">
          <label htmlFor="linkedin">LinkedIn :</label>
          <input
            id="linkedin"
            type="text"
            name="linkedin"
            value={formData.linkedin}
            onChange={handleChange}
            placeholder="Your LinkedIn Profile"
          />
        </div>

        <div className="form-group">
          <label htmlFor="message">Message :</label>
          <textarea
            id="message"
            name="message"
            value={formData.message}
            onChange={handleChange}
            placeholder="Your Message"
            required
          />
        </div>

        <button type="submit" disabled={isSending}>
          {isSending ? 'Sending...' : 'SEND'}
        </button>
      </form>
    </div>
  );
}

export default Contact;
