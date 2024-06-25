SpringBoot Project Commands / Troubleshooting:

Linux:

	1. starting up development server
		> sudo docker compose up -d
		> sudo ./mvnw spring-boot:run
		
	2. building project .jar file ('deployment')
		> sudo ./mvnw clean install
		> cd target
		> java -jar *target_name.jar
	
	3. cleaning dependency workspace
		> sudo ./mvnw clean
		
	4. build the docker image
		> docker build -t backtrader-backend:v# .
		> docker images
		> docker run -p host_port:container_port image_id
