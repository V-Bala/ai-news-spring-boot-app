# TechNews Chatbot API

This project is a Java Spring Boot application that uses the US News API to fetch the latest technology news, allows users to save interesting articles to a "read-later" list, and employs OpenAI's GPT-3.5-Turbo to generate a full article body based on a headline. It demonstrates the use of several Spring Boot features including REST controllers, services, and dependency injection.

## Endpoints

The application provides the following REST API endpoints:

1. `GET /tech-news`: Fetches the latest technology news articles.
2. `POST /read-later/{id}`: Adds an article with the given ID to the "read-later" list.
3. `GET /read-later`: Retrieves the list of articles saved to the "read-later" list.
4. `GET /openai-request`: Generates a full article using OpenAI's GPT-4 based on a given article headline in the "read-later" list.

## Project Structure

The project is structured into several key classes:

- `ApiController`: This is the main REST controller for the application. It handles HTTP requests and calls the appropriate service methods.
- `NewsApiService`: This service fetches the latest technology news articles from the News API.
- `ArticleService`: This service manages the "read-later" list and provides methods to add and retrieve articles.
- `OpenAIService`: This service interacts with OpenAI's GPT-4 to generate a full article based on a given article headline.

## How to Run

To run this project, clone the repository and use Maven to start the application:
```
git clone https://github.com/your-username/tech-news-chatbot.git
cd tech-news-chatbot
mvn spring-boot:run
```

The application will start and listen for HTTP requests on port 8080.
