#!/bin/bash
printf "Building backend image..\n"
docker build -t kmeans/backend .
docker tag kmeans/backend kmeanscr.azurecr.i/kmeans/backend
docker push kmeanscr.azurecr.io/kmeans/backend


