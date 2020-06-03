FROM httpd:2.4

RUN npm run-script build --prod

COPY ./dist/nwt-frontend /usr/local/apache2/htdocs/

EXPOSE 80


