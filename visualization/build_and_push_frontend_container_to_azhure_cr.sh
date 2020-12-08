#!/bin/bash
printf "Building frontend image..\n"
docker build -t kmeans/frontend \
  --build-arg KMEANS_BACKEND_HOST=kmeans-backend.westeurope.azurecontainer.io \
  --build-arg KMEANS_BACKEND_PORT=11111 .
docker tag kmeans/frontend kmeanscr.azurecr.io/kmeans/frontend
docker push kmeanscr.azurecr.io/kmeans/frontend


