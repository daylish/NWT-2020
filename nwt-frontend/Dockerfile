FROM node:10 as builder

RUN mkdir frontend
COPY . ./frontend

WORKDIR /frontend

RUN npm install
RUN npm run-script build --prod


FROM httpd:2.4

COPY --from=builder /frontend/dist/nwt-frontend /usr/local/apache2/htdocs/

EXPOSE 80