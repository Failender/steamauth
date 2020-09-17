# SteamAuth

This is a basic example for integrating Steam authentication in a web app

## Description

This project shows a minimal example of the steam openId authentication in a simple webpage, using a Spring Boot backend.
An example of this in life usage is viewable here http://failender.de:9090

## Getting Started

This project is getting build and published using docker. 

You will need a steam api key, which will later be used to fetch the current user and retrieve his information, get one here https://steamcommunity.com/dev/apikey

The container needs two configuration options, which are shown in the example docker-compose

```yaml
version: '3'
services:
  backend:
    image: failender/steamauth:latest
    ports:
      - <target port on host machine>:8080
    environment:
      - de.failender.steamauth.steam.api.key=<your api key>
      - de.failender.steamauth.redirect.uri=<the adress that be called after authentication is done>
      
      
```

Using that compose file you can use
```
docker-compose up backend
```

to start the backend - a basic index.html for testing will be accessible under

```
http://localhost:<port>
```

## Authors


[@Failender](https://github.com/failender)

## License

This project is licensed under the WTFPL License
