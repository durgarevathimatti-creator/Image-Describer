# Image Describer – AI Powered Image Description using Google Gemini

A beautiful and simple Spring Boot web application that lets you **upload an image or paste an image URL** and get a **detailed, intelligent description** instantly — powered by **Google Gemini 1.5 Flash (Vision)**.

Live Demo: http://localhost:8080 (when you run it)

## Features

- Upload image files (JPG, PNG, GIF, JPEG)
- Describe image from direct URL
- Clean & responsive JSP-based frontend (just like your Text-Summarizer)
- Powered by **Spring AI** + **Google Gemini Vision**
- Fast, accurate, and detailed descriptions in bullet points
- Zero external APIs — everything runs locally with your Gemini API key

## Tech Stack

- Spring Boot 3.x (with Spring Boot 4.0 parent)
- Spring AI (Google Gemini integration)
- Google Gemini 1.5 Flash (multimodal – text + vision)
- JSP + Vanilla JavaScript frontend
- Maven

## Screenshots

![Image Describer Demo](https://via.placeholder.com/800x450.png?text=Image+Describer+Demo+-+Upload+or+URL)  
*Upload or paste URL → Get smart description instantly*

## How to Run

### 1. Prerequisites
- Java 17+
- Maven
- Google Gemini API Key (free at https://aistudio.google.com/app/apikey)

### 2. Clone & Setup
```bash
git clone https://github.com/yourusername/Image-Describer.git
cd Image-Describer
