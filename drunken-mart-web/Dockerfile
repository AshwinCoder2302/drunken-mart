FROM node:20.16.0-alpine

WORKDIR /app

RUN npm cache clean -f

RUN npm install -g npm@10.8.1

COPY package*.json ./

RUN npm update

COPY . .

RUN npm install

EXPOSE 4200

CMD ["npm", "start"]
