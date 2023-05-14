# e-commerce-app-test

Repo for e-commerce application

## Notes

**NOTE: Before running any commands in the terminal, ensure you are in the correct working file directory.**

**e.g. if you clone the repo to `/Users/James.Dunn/Documents/hackathon/ecpHackathon/e-commerce-app`
ensure you use the following command first to change to your working directory:**

```shell
#This is only an example, change this path to whatever path you are using
cd /Users/James.Dunn/Documents/hackathon/ecpHackathon/e-commerce-app
```

## App Entry Points

This application has a few entry points which will be useful to know

Please run the backend and go to -> http://localhost:8080/api/v1/swagger-ui/index.html for a 
helpful UI that shows all the entry points for the backend and what each entry point accepts and 
returns.

### Backend

- The app backend is hosted on `http://localhost:8080`
- The API endpoint is `http://localhost:8080/api/v1/<specific-endpoint>`
    - `e.g. http://localhost:8080/api/v1/products`

#### Authentication Entry Points

- `/authenticate` - entrypoint to authenticate a user
- `/register` - entrypoint to register a user

#### Product Entry Points

- `/products` - entrypoint to interact with products

#### Basket Entry Points

- `/basket` - entrypoint to interact with baskets
- `/basket/checkout` - entrypoint to checkout basket

### Frontend

- The app frontend is hosted on `http://localhost:3000`
- You can access this by typing this into any browser

### Database

- If using the `dev` profile then a h2 in-memory database will be used, to access this you can go to the following
  endpoint: `http://localhost:8080/api/v1/h2-ui`
- Ensure you have inputted the following details on the login page:
    - Driver class - `org.h2.Driver`
    - JDBC URL - `jdbc:h2:file:./testdb`
    - username - `sa`
    - password - **leave this blank**


- If using the `prod` profile then a MySql database will be used, to access this you can use the following JDBC url to
  connect: `jdbc:mysql://localhost:3308`
- The username is `root` and the password is `password`
- I would suggest either using [MySql workbench](https://www.mysql.com/products/workbench/) or if using IntelliJ you can
  use the [built-in database management panel](https://www.jetbrains.com/help/idea/mysql.html)

## Java 17 and SDKMAN

This project uses Java 17, if you have Java 17 as your
default [JDK](https://www.simplilearn.com/tutorials/java-tutorial/jdk-in-java#:~:text=JDK%20in%20Java%20is%20an%20abbreviation%20for%20Java%20Development%20Kit,Java%20Virtual%20Machine%20(JVM).)
(Java Software Developer Kit) then ignore this section. Otherwise, this section will help you set up SDKMAN to install
and use Java 17 for this project.

Here is a link to install SDKMAN to your local machine -> [link here](https://sdkman.io/install).

Once you have SDKMAN installed run the following commands in your terminal:

```shell
#Install Java 17 JDK
sdk install java 17.0.5-tem 

#Use Java 17 in your current terminal
#Run this each time you start a new terminal to run this project in the terminal
sdk use java 17.0.5-tem

```

If you are using IntelliJ, you can then set the Default JDK used for gradle which is used to build and run
the project. Go to the following settings path:

`Preferences | Build, Execution, Deployment | Build Tools | Gradle`

Then select the new version of java you have just installed.

Then go to:

`File | Project Structure | Project | Project SDK`

To set the SDK used for the project.

## Docker

### Dockerfile Structure

There are 3 main files for running this application in docker:

- Frontend [Dockerfile](frontend/Dockerfile) - This is a Dockerfile to run the frontend in docker
- Backed [Dockerfile](backend/Dockerfile) - This is a Dockerfile to run the backend in docker
- [docker-compose.yml](docker-compose.yml) - This is the file that runs all the docker files, starts the backend,
  frontend and database

### Running in Docker

To run this application in docker you can use the following commands in the terminal:

```shell
#Run the app
docker-compose up 

#Re-create all the docker images with any app changes, then run the app
docker-compose up -d --force-recreate
```

```shell
#Stop the app
docker-compose down

#Remove all the docker images for the app and stop the app
docker-compose down --rmi all
```