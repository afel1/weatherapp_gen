# Weather App Technical Challenge

This project is a solution to the Genesys backend technical challenge. It consists of a backend API developed using Spring Boot in Java, and it stores data in a PostgreSQL database. The application is containerized using Docker for easy deployment.

## Getting Started

To start the application, follow these steps:

### Prerequisites

- Docker

### Installation

Clone this repository to your local machine.

   ```console shell
   git clone https://github.com/afel1/weatherapp_gen.git
   ```

Navigate to the project directory.

    ```console
    cd weatherapp_gen
    ```
Build and run the Docker containers using docker-compose.

```console
docker-compose up --build
```
This command will start both the backend API and the PostgreSQL database.

## Usage

Once the application is up and running, you can access the API at ```http://localhost:8080```. Here are some example API endpoints:

- ####  Create Sensor: (POST) /createSensor

To create a new sensor in the application, send a POST request with the following JSON object:
```console
   {
    "name": "name of the sensor",
    "location": "location of the sensor"
    }
```
If the request is successful, you will receive a true boolean indicating that the sensor has been added.
```console
   curl -X POST -H "Content-Type: application/json" -d '{"name":"Sensor1", "location":"Office"}' http://localhost:8080/createSensor
```
This will create a sensor with the name "Sensor1" and location "Office" in the application.

- ####  Get Sensors List: (GET) /getSensorsList
To retrieve the list of all sensors in the application along with their information (name, location, and id), make a GET request to this endpoint. By default, this endpoint will return all sensors if no sensorIds parameter is provided.

Example:
```console
   curl http://localhost:8080/getSensorsList
```
To get specific sensor details, add their IDs as query parameters using the key sensorIds. For example, to retrieve details for sensors with IDs 1001, 1002, and 1003:
```console
   curl http://localhost:8080/getSensorsList?sensorIds=1001,1002,1003

```
This will return the details of the specified sensors.

- ####  Record Metrics: (POST) /recordMetrics
To add weather metrics to the application (temperature, humidity, wind speed, precipitation), send a POST request with the following JSON object:

```console
   {
    "temperature": float,
    "humidity": float,
    "windSpeed": float,
    "precipitation": float,
    "sensorId": long
    }

```
If the metrics are added to the specified sensor, the API will return a true boolean.

Example using curl:
```console
   curl -X POST -H "Content-Type: application/json" -d '{"temperature": 25.5, "humidity": 50.0, "windSpeed": 3.0, "precipitation": 0.0, "sensorId": 1001}' http://localhost:8080/recordMetrics

```
This will add weather metrics to the sensor with ID 1001 and return true if successful.
- #### Get Metrics Data: (GET) /getMetricsData

To retrieve metrics data and statistics within a specified date range, use this endpoint. Pass the following parameters:

- **metricsTypes:** Types of metrics for which you want statistics **(TEM, HUM, PRE, WIND)**. You can provide a list of metric types. If no value is specified, the API will return statistics for all these metrics.

- **statistic:** The type of statistic you are looking for (average, sum, min, or max). The API accepts only these four codes **(AVG, SUM, MIN, MAX)**. If nothing is specified, the API will send the average of the specified metrics by default.

- **sensorIds:** A list of IDs of sensors to calculate metrics based on their data. If you want metrics to be calculated based on specific sensors, pass their IDs. If not specified, the API will return statistics for metrics from all sensors.

- **startDate and endDate:** A date range for the metrics you want to use in your calculations. The range must be between one day and one month, or an error will be thrown. If no parameters are passed, the app will calculate statistics based on the data of the last month.
Example:
```console
   curl "http://localhost:8080/getMetricsData?metricsTypes=TEM,HUM&statistic=AVG&sensorIds=1001,1002&startDate=2023-07-05&endDate=2023-07-31"
```
This will retrieve the average temperature and humidity statistics for sensors **1001** and **1002** between **2023-07-05** and **2023-07-31**.

