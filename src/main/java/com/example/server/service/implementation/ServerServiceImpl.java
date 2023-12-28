package com.example.server.service.implementation;

import com.example.server.Enumerations.Status;
import com.example.server.model.Server;
import com.example.server.repos.ServerRepos;
import com.example.server.service.ServerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Random;

import static com.example.server.Enumerations.Status.SERVER_DOWN;
import static com.example.server.Enumerations.Status.SERVER_UP;
import static java.lang.Boolean.TRUE;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServerServiceImpl implements ServerService {
    private final ServerRepos serverRepos;
    @Override
    public Server create(Server server) {
        log.info("Saving new Server",server.getName());
        server.setImageUrl(setServerImageUrl());
        return serverRepos.save(server);
    }



    @Override
    public Server ping(String ipAddress) throws IOException {
        log.info("Pinging Server IP ",ipAddress);
        Server server=serverRepos.findByIpAddress(ipAddress);
        InetAddress address=InetAddress.getByName(ipAddress);
        server.setStatus(address.isReachable(10000)? SERVER_UP: SERVER_DOWN);
        serverRepos.save(server);
        return server;
    }

    @Override
    public Collection<Server> List(int Limit) {
        log.info("Fetching all servers");
        return serverRepos.findAll(PageRequest.of(0,Limit)).toList();
    }

    @Override
    public Server get(Long id) {
        log.info("Fetching server by Id : {}",id);
        return serverRepos.findById(id).get();
    }

    @Override
    public Server update(Server server) {
        log.info("Updating server : {}" , server.getName());
        return serverRepos.save(server);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("deleting servers :{}",id);
        serverRepos.deleteById(id);
        return TRUE;
    }

    private String setServerImageUrl() {
        String[] imageNames={"server1.png","server2.png","server3.png","server4.png"};
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/server/images/"+imageNames[new Random().nextInt(4)]).toUriString();
    }
}
