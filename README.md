Local AI Chatbot Documentation
Project Overview
This project is a chatbot application with a UI interface that runs AI models locally using Ollama. It's built with Java Spring Boot for the backend and provides a seamless way to interact with AI models without relying on cloud services.


Setup Instructions
-Prerequisites
-Java JDK 17 or higher
-Maven 3.6+
-Ollama (for local AI model hosting)
-Git

Installing Ollama
Install Ollama based on your operating system:

# For Linux/WSL2
curl https://ollama.ai/install.sh | sh

# For MacOS
brew install ollama

# For Windows
# Download the installer from https://ollama.ai/download

#Start Ollama service:
ollama serve

Pull your preferred model:
ollama pull llama2
# or
ollama pull mistral

Setting up the Application
Clone the repository:

git clone [your-repository-url]
cd [project-directory]

#Configure application properties:
Build the project:
mvn clean install
Run the application:
mvn spring-boot:run

Architecture
High-Level Architecture

┌─────────────┐     ┌──────────────┐     ┌─────────────┐
│   Frontend  │ ──► │ Spring Boot  │ ──► │   Ollama    │
│    (UI)     │ ◄── │   Backend    │ ◄── │   Server    │
└─────────────┘     └──────────────┘     └─────────────┘

#Components
 Frontend Layer
-User interface for chat interactions
-WebSocket connection for real-time messaging
-Message history display

#Backend Layer (Spring Boot)
-REST API endpoints
-WebSocket handler
-Ollama client service
-Message processing

#Ollama Integration
-Local model hosting
-API communication
-Model management

#API Documentation
REST Endpoints
Chat Completion
POST /api/chat/completion

Request body:
{
    "prompt": "Your question here",
    "model": "llama2",
    "stream": false
}

Response:
{
    "response": "AI generated response",
    "model": "llama2",
    "timestamp": "2024-03-20T10:00:00Z"
}

Model Management
GET /api/models

Response:
{
    "models": [
        {
            "name": "llama2",
            "status": "ready"
        }
    ]
}

**Configuration Guide**
Application Properties
| Property | Description | Default |
|----------|-------------|---------|
| ollama.base.url | Ollama server URL | http://localhost:11434 |
| ollama.model.name | Default AI model | llama2 |
| server.port | Application port | 8080 |
