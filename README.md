# FoodBuzz:

This is a sample application demonstrating the containerisation of spring boot application in development environment. In this project we will build a very basic rest service, fetching data from mysql database. Rest application service and database both will be running within a container build on docker.

```pre
	|---------|			|----------|
	|		  |			|		   |
	|FoodBuzz |			|	Mysql  |
	|Container|			|Container |
	|		  |			|		   |
	|---------|			|----------|
```

## Goal:

This repository is for two achieve two main goals

  - Debugging a java application running inside a container.
  - Instantly reloading the foodbuzz application using spring boot-devtools.


## Running the starter application:

Before running the application, clone the application in your local system

```bash
git clone https://github.com/ratulSharker/FoodBuzz
```

Get into the `Starter` project folder

```bash
cd FoodBuzz/Starter
```

We use `maven` as a build tool, so make sure `maven` is installed in your system. To install maven in your system please follow these [instructions](https://maven.apache.org/install.html).

Now build the **FoodBuzz** using `maven`

```bash
mvn clean package
```

We can find the jar file inside the `target` directory.

Now we are able to run the `docker-compose` to `up` the whole application as following

```bash
cd ../ 	# move to the parent `FoodBuzz` directory
docker-compose up -d
```

doing `docker-compose up -d` will respond with following

```bash
Creating network "foodbuzz_db-net" with the default driver
Creating db ... done
Creating fb ... done
```

Here **db** is our containerized mysql db service. **fb** is FoodBuzz service. Both these service is connected inside **db-net** network declared in `docker-compose` file.

Once these two services are up, it can be accessed via `cURL` as follows

```bash
curl -X GET http://localhost:8080/foods
```

(NB: **fb** service is dependent upon **db** service. To up and running **db** service may take longer time than usual, so it accessing the service may require some time even both services are up and running. To check if both service are actually up or not omit `-d` in the `docker-compose up -d` to see the logs of these services.)

## Remote debugging the FoodBuzz application:

To debug the **FoodBuzz** application remotely, we need to pass some additional parameters while running the **FoodBuzz** jar inside the container. For more information please reffer to this [guide](https://www.baeldung.com/java-application-remote-debugging)

Open the `Dockerfile` inside the `Starter` directory and change below line

```dockerfile
ENTRYPOINT ["java", "-jar", "/food-buzz.jar"]
```

with following

```dockerfile
ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8000", "-jar", "/food-buzz.jar"]
```

Doing so, our **FoodBuzz** jar will run in debug mode. We use port `8000` for debugging commuinication. So we also need to `expose 8000` in `dockerfile`.

```dockerfile
EXPOSE 8000
```

besides this, also update the `docker-compose` to expose `8000` port into the host machine

```dockerfile
services:
 foodbuzz:
  build: ./Starter
  image: foodbuzz
  container_name: fb
  ports:
   - "8080:8080"
   - "8000:8000"		<---- debugging port mapping to host machine
  networks:
   - db-net
  depends_on:
   - db
```

Now stop the previously running `docker-compose` by following (if it is still running)

```bash
docker-compose down
```

Now re-run the `docker-compose` specifying that it should rebuild the **FoodBuzz** image by running following. (Make sure you are in the `FoodBuzz` directory before running `docker-compose` commmand.)

```bash
docker-compose up -d --build		# --build option will rebuild the FoodBuzz image.
```

Now we are ready to connect the ide (eclipse, inteliJ, Visual studio code) to this debugger.

### Connect eclipse remote debugger to FoodBuzz:

To connect remote debugger in eclipse right on the **FoodBuzz** project. Navigate to

```
'FoodBuzz Project' > Debug As > Debug configurations...
```

