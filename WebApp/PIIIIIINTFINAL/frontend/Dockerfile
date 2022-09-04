FROM node:18.7-alpine3.15 as build

WORKDIR /APP/react-roombooker
COPY /package.json ./
COPY /package-lock.json ./
RUN npm ci --silent
COPY ./ ./
RUN npm run build

FROM nginx:stable-alpine
COPY --from=build /APP/react-roombooker/build /usr/share/nginx/react
CMD ["nginx", "-g", "daemon off;"]