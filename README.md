# 🎧 AI-Powered Audio Transcriber

A full-stack web application that transcribes audio files into text using **OpenAI's Whisper-1** model. Built using **Spring Boot**, **Spring AI**, **React + Vite**, and supports both API-based and local Whisper inference.

---

# 🚀 Features

- Upload audio files and get transcriptions
- AI integration using Whisper-1 (OpenAI's speech recognition model)
- Option to run Whisper locally to avoid API costs
- Full-stack architecture with clean separation between frontend and backend
- Responsive and modern UI built with React + Vite

---

# 🛠️ Tech Stack

# Frontend
- React + Vite
- Axios for API requests

# Backend
- Spring Boot (Java 17+)
- Spring AI
- OpenAI Whisper-1 Integration
- File Upload Handling

---

# 📦 Running the Project Locally

# Prerequisites
- Java 17+
- npm
- Python (for running Whisper locally)
- Git

# 1. Clone the repository

# bash
git clone https://github.com/yourusername/audio-transcriber.git
cd audio-transcriber

# folder structure
audio-transcriber/
├── backend/        # Spring Boot Backend
├── frontend/       # React + Vite Frontend
└── README.md

# React + Vite

This template provides a minimal setup to get React working in Vite with HMR and some ESLint rules.

Currently, two official plugins are available:

- [@vitejs/plugin-react](https://github.com/vitejs/vite-plugin-react/blob/main/packages/plugin-react) uses [Babel](https://babeljs.io/) for Fast Refresh
- [@vitejs/plugin-react-swc](https://github.com/vitejs/vite-plugin-react/blob/main/packages/plugin-react-swc) uses [SWC](https://swc.rs/) for Fast Refresh

## Expanding the ESLint configuration

#  Learnings
Integration of AI models (OpenAI Whisper) into real-world apps

Handling audio uploads and file processing

Running heavy ML models locally to save costs

Efficient full-stack development using Spring Boot and React

If you are developing a production application, we recommend using TypeScript with type-aware lint rules enabled. Check out the [TS template](https://github.com/vitejs/vite/tree/main/packages/create-vite/template-react-ts) for information on how to integrate TypeScript and [`typescript-eslint`](https://typescript-eslint.io) in your project.
