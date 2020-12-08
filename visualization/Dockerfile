FROM ubuntu:latest

ARG KMEANS_BACKEND_HOST
ARG KMEANS_BACKEND_PORT
ENV ENV_KMEANS_BACKEND_HOST=$KMEANS_BACKEND_HOST
ENV ENV_KMEANS_BACKEND_PORT=$KMEANS_BACKEND_PORT

RUN apt-get update && apt-get -y update
RUN apt-get install -y build-essential python3.6 python3-pip python3-dev
RUN pip3 -q install pip --upgrade

RUN mkdir src
WORKDIR src/
COPY clustering.ipynb .
COPY requirements.txt .
RUN pip3 install -r requirements.txt
RUN pip3 install jupyter

CMD ["jupyter", "notebook", "--port=8888", "--no-browser", "--ip=0.0.0.0", "--allow-root"]
