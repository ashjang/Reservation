docker run -d \
--name rv-mysql \
-e MYSQL_ROOT_PASSWORD="rootpw123" \
-e MYSQL_USER="reservation" \
-e MYSQL_PASSWORD="reservation" \
-e MYSQL_DATABASE="reservation" \
-p 3306:3306 \
-d mysql