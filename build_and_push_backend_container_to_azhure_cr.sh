#!/bin/bash
container_registry=kmeanscr.azurecr.io
printf "Building backend image..\n"
docker build -t kmeans/backend .
docker tag kmeans/backend kmeanscr.azurecr.io/kmeans/backend
docker push kmeanscr.azurecr.io/kmeans/backend


