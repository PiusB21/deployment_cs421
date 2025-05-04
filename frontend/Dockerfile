FROM node:18-alpine as build-stage
WORKDIR /app
COPY package*.json ./
COPY .env .env
RUN npm install
COPY . .
RUN npm run build

# Production stage using Nginx
FROM nginx:alpine
COPY --from=build-stage /app/dist /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf
