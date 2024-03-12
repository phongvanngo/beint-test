FROM node:lts-alpine as build-stage

ARG API_URL
ENV VITE_API_URL=${API_URL}

WORKDIR /app

COPY package*.json ./

RUN npm install

COPY . .

RUN npm run build.prod

FROM nginx:stable-alpine as production-stage

COPY --from=build-stage /app/nginx.conf /etc/nginx/conf.d/default.conf

COPY --from=build-stage /app/dist /usr/share/nginx/html

EXPOSE 80