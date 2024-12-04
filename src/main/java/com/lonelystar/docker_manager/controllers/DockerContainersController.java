package com.lonelystar.docker_manager.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.dockerjava.api.model.Container;
import com.lonelystar.docker_manager.services.DockerService;


public class DockerContainersController {

    @RestController
@RequestMapping("/api/images")
public class DockerImagesController {

    private final DockerService dockerService;

    public DockerImagesController(DockerService dockerService) {
        this.dockerService = dockerService;
    }

    @GetMapping("/filter")
    public List<Container> listContainers(@RequestParam(required = true,defaultValue= "true") boolean  showAll) {
        return dockerService.ListContainers(showAll);
    }

    @PostMapping("/{id}/start")
    public void startContainer(String containerId) {
        dockerService.startContainer(containerId);
    }
    @PostMapping("/{id}/stop")
    public void stopContainer(String containerId) {
        dockerService.stopContainer(containerId);
    }
    @DeleteMapping("/{id}")
    public void deleteContainer(String containerId) {
        dockerService.deleteContainer(containerId);
    }
    @PostMapping("")
    public void createContainer(@RequestParam String imageName) {
        dockerService.createContainer(imageName);
    }

}

}
