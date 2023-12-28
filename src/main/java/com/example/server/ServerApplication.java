package com.example.server;

import com.example.server.Enumerations.Status;
import com.example.server.model.Server;
import com.example.server.repos.ServerRepos;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.example.server.Enumerations.Status.SERVER_UP;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	@Bean
	CommandLineRunner run(ServerRepos serverRepos){
		return args -> {
			serverRepos.save(new Server(null,"192.168.1.168","Ubuntu Linux","16GB","Personal PC",
					"http://localhost:8080/server/images/server1.png", SERVER_UP));
			serverRepos.save(new Server(null,"192.168.1.169","Fedora Linux","32GB","DELL",
					"http://localhost:8080/server/images/server2.png", SERVER_UP));
			serverRepos.save(new Server(null,"192.168.1.170","RedHat Linux","64GB","Server",
					"http://localhost:8080/server/images/server3.png", SERVER_UP));
			serverRepos.save(new Server(null,"192.168.1.171","Ms 2012","128GB","Web Server",
					"http://localhost:8080/server/images/server4.png", SERVER_UP));
		};
	}
}
