[![Build Status](https://travis-ci.org/SergiySobolev/kmeans-clustering-service.svg?branch=master)](https://travis-ci.org/SergiySobolev/kmeans-clustering-service)
[![Total alerts](https://img.shields.io/lgtm/alerts/g/SergiySobolev/kmeans-clustering-service.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/SergiySobolev/kmeans-clustering-service/alerts/)
[![Language grade: Java](https://img.shields.io/lgtm/grade/java/g/SergiySobolev/kmeans-clustering-service.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/SergiySobolev/kmeans-clustering-service/context:java)

<!-- Badges -->
<img src="https://img.shields.io/badge/java-%23ED8B00.svg?&style=for-the-badge&logo=java&logoColor=white"/>
<img src="https://img.shields.io/badge/azure%20-%230072C6.svg?&style=for-the-badge&logo=azure-devops&logoColor=white"/>
<br>

<!-- TABLE OF CONTENTS -->
## Table of Contents

* [About the project](#about-the-project)
* [Clustering visualization example](#clustering-visualization-example)
* [Build and run guidance](#build-and-run-guidance)
* [Generate data](#generate-data)

<!-- ABOUT THE PROJECT -->
# About the project
Service, that implements clustering technique using K-means algorithm. 

# Clustering visualization example
TBD

# Build and run guidance
1. Clone project:  *git clone https://github.com/SergiySobolev/kmeans-clustering-service.git*
2. Go to the root of the project directory: *cd kmeans-clustering-service*
3. Build backend: *./gradlew build*
4. Run backend: *java -jar build/libs/kmeans-clustering-service*.jar . By default, backend service will start on port 11111.

#Generate data
Backend provides endpoint to generate synthetic data that could be divided into clusters:

**URL**
/generatedata

**METHOD**
GET

**HEADERS**
Content-Type: application/json

**DATA PARAMS**

 clusterNum: int,
  
 bounds: 2d array of ints 
 
**RESPONSE**

data: 2d array of ints

**EXAMPLE**

Generate data that could be divided into 3 clusters

_Request:_

GET http://{host}:11111/generatedata

Content-Type: application/json

{"clusterNum": 3, "bounds": [[100, 200],[100, 200],[350, 550],[350, 550],[2000, 2500],[2000, 2500]]}

_Response visualization:_
![Generated data response visualization](https://github.com/SergiySobolev/kmeans-clustering-service/blob/master/visualization/screenshots/generatedata.png)



