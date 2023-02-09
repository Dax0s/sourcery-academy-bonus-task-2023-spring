FROM node:16.18.1

WORKDIR /app

COPY package*.json .

RUN npm install

COPY . .

# Changes localhost to backend in package.json, because localhost in a docker container points to the container and not the host machine
RUN sed -i 's/localhost/backend/g' package.json

EXPOSE 3000

ENTRYPOINT [ "npm", "run", "start" ]
