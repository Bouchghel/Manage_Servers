package com.example.server.repos;

import com.example.server.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerRepos extends JpaRepository<Server,Long> {
    Server findByIpAddress(String ipAddress);
}
