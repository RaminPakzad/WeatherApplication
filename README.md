# Weather Application

This application provides an API for getting and storing weather of a city. The weather information came
form [OpenWeather](https://openweathermap.org/) and stores in your local database.

## Run and use the application

### System requirements

* [Docker](https://www.docker.com/) You need Docker for running the application container and the database container.
* [Java JDK - Version 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
* [Maven](https://maven.apache.org/download.cgi)

### How to run

As this application uses [OpenWeather](https://openweathermap.org/), first you need to generate an API key for yourself.

- Generate a OpenWeather API key on [OpenWeather](https://openweathermap.org/appid)
- Place the generated API key in .env file as the value for WEATHER_API_ID, like this:

  WEATHER_API_ID={your api key}

Run the following command in your terminal to generate the jar file

    mvn clean install

Then in order to run the application, run the below command in your terminal

    docker-compose up -d

### How to test the application

Open a web browser, put the below url on the address bar

    http://localhost:8085/v1/weather/{city}

Change the {city} with your favorite one, like this example:

    http://localhost:8085/v1/weather/amsterdam

Press Enter to see the result.
The response will be a JSON like this:

`{ 
  "id":1,
  "city":"Amsterdam",
  "country":"NL",
  "temperature":114.66 
}`

- id : the id of this record in your database
- city : the city name comes from [OpenWeather](https://openweathermap.org/)
- country : the country of the city comes from [OpenWeather](https://openweathermap.org/)
- temperature: current temperature of the city

### API documentation
By the following URLs you can see API docume# Weather Application

This application provides an API for getting and storing weather of a city. The weather information came
form [OpenWeather](https://openweathermap.org/) and stores in your local database.

## Run and use the application

### System requirements

* [Docker](https://www.docker.com/) You need Docker for running the application container and the database container.
* [Java JDK - Version 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
* [Maven](https://maven.apache.org/download.cgi)

### How to run

As this application uses [OpenWeather](https://openweathermap.org/), first you need to generate an API key for yourself.

- Generate a OpenWeather API key on [OpenWeather](https://openweathermap.org/appid)
- Place the generated API key in docker-variables.env file as the value for WEATHER_API_ID, like this:

  WEATHER_API_ID={your api key}

Run the following command in your terminal to generate the jar file

    mvn clean install

Then in order to run the application, run the below command in your terminal

    docker-compose up -d

### How to test the application

Open a web browser, put the below url on the address bar

    http://localhost:8085/v1/weather/{city}

Change the {city} with your favorite one, like this example:

    http://localhost:8085/v1/weather/amsterdam

Press Enter to see the result.
The response will be a JSON like this:

`{ 
  "id":1,
  "city":"Amsterdam",
  "country":"NL",
  "temperature":114.66 
}`

- id : the id of this record in your database
- city : the city name comes from [OpenWeather](https://openweathermap.org/)
- country : the country of the city comes from [OpenWeather](https://openweathermap.org/)
- temperature: current temperature of the city

### API documentation
By the following URLs you can see API documentation.

Web Format:

    http://localhost:8085/docs-ui.html

JSON Format:

    http://localhost:8085/docs