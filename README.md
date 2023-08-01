# TechNews Chatbot API
<img width="122" alt="robot" src="https://github.com/V-Bala/ai-news-spring-boot-app/assets/10373409/078c7979-4ab8-4b6b-96c6-7011ae4d1a84">

This project is a Java Spring Boot application that uses the US News API to fetch the latest technology news, allows users to save interesting articles to a "read-later" list, and employs OpenAI's GPT-3.5-Turbo to generate a full article body based on a headline. It demonstrates the use of several Spring Boot features including REST controllers, services, request mapping, and dependency injection.

Designed with a template ArticleRepository to support easily swapping in a real database instead of the in-memory one. Spring Data JPA could be used to generate the boilerplate code.

## Endpoints

The application provides the following REST API endpoints:

1. `GET /tech-news`: Fetches the latest technology news articles.
2. `POST /read-later/{id}`: Adds an article with the given ID to the "read-later" list.
3. `GET /read-later`: Retrieves the list of articles saved to the "read-later" list.
4. `DELETE /read-later/{id}`: Removes an article with the given ID from the "read-later" list.
5. `GET /openai-request`: Generates a full article using OpenAI's GPT-4 based on a given article headline in the "read-later" list.

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

## Test Run
```
$ curl -X GET http://localhost:8080/tech-news
"id":1,"title":"Hasbro's Newest Partnership May Bring AI to Dungeons & Dragons - Gizmodo"},{"id":2,"title":"Does the Samsung Galaxy Watch 6 Classic use standard bands? - Android Police"},{"id":3,"title":"Hasbro wants Xbox to bring back Activision's Transformers games via Game Pass | VGC - Video Games Chronicle"},{"id":4,"title":"Snag the best way to experience Half-Life for $5 while you still can - PCGamesN"},{"id":5,"title":"Asmongold comments on Path of Exile 2 being a Diablo 4 killer - Dexerto"},{"id":6,"title":"Call of Duty: Warzone Ban Wave Removes 14,000 Cheaters - GameRant"},{"id":7,"title":"PlayStation Classic flops on Steam despite strong reviews - Dexerto"},{"id":8,"title":"Jake Beardsley Wins Pro Tour The Lord of the Rings - Hipsters of the Coast"},{"id":9,"title":"iPhone 15 May Get Thinner Bezels, Higher Price - CNET"},{"id":10,"title":"‘Remnant 2’ Hid The Entire Archon Class Behind Datamining And Outfit-Selecting - Forbes"},{"id":11,"title":"Skyrim Artifact Tier List - GameRant"},{"id":12,"title":"Apple confirms bug stops Screen Time limits from sticking for kids - The Verge"},{"id":13,"title":"iPhone 15 Pro Said to Be 'Easier to Repair' Like iPhone 14 and 14 Plus - MacRumors"},{"id":14,"title":"Random: \"Ultimate Console Database\" Secures Ultra Rare Nintendo Labo Switch - Nintendo Life"},{"id":15,"title":"14 Exciting New Games Coming to Nintendo Switch - August 2023 - Nintendo Life"},{"id":16,"title":"Play the SNES Classic That Launched the Cozy Sim Trend on Switch Online ASAP - Inverse"},{"id":17,"title":"Samsung's Galaxy Z Flip 5 doesn't have DeX, but why? - XDA Developers"},{"id":18,"title":"\"We Have To Do This Now\" - Sonic Mania Devs On Creating Chaotic Yo-Yo Platformer Penny's Big Breakaway - Nintendo Life"},{"id":19,"title":"The PS Plus Free Games for August 2023 Solidify a Years-Long Pattern - GameRant"},{"id":20,"title":"Assassin's Creed Mirage Nowhere Near the Length of Origins, Odyssey, Valhalla - Push Square"

$ curl -X POST http://localhost:8080/read-later/5
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
  0     0    0     0    0     0      0      0 --:--:-- --:--:-- --:--:--     0

$ curl -X GET http://localhost:8080/openai-request
Title: "Gaming Titans Clash: Call of Duty's Publisher and Transformers Fuel Frustration in the Toy Industry!"
In a jaw-dropping turn of events, the toy industry is buzzing with adrenaline as the battlefield between Call of Duty's publisher and the iconic Transformers franchise becomes the ultimate showdown of the century. Tensions have reached a fever pitch as these gaming titans engage in a head-to-head conflict, leaving fans and industry insiders alike on the edge of their seats. What sparked this epic rivalry, and how will it impact the future of our beloved action figures and gaming adventures? As the storm gathers, join us as we unveil the thrilling tale of frustration, ambition, and the clash of two unstoppable forces in the world of toys and entertainment!
```
