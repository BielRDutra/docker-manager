package com.lonelystar.docker_manager.services;

import java.util.List;

import org.jvnet.hk2.annotations.Service;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.Image;

@Service
public class DockerService {

    private final DockerClient dockerClient;

    public DockerService(DockerClient dockerClient) {
        this.dockerClient = dockerClient;
    }

    public List<Container> ListContainers(boolean all) {
        return this.dockerClient.listContainersCmd().withShowAll(all).exec();
    }

    public List<Image> ListImages() {
        return this.dockerClient.listImagesCmd().exec();
    }

    public List<Image> filterImages(String filterName){
        return dockerClient.listImagesCmd().withImageNameFilter(filterName).exec();
    }

    public void startContainer(String containerId) {
        this.dockerClient.startContainerCmd(containerId).exec();
    }

    public void stopContainer(String containerId) {
        this.dockerClient.stopContainerCmd(containerId).exec();
    }

    public void deleteContainer(String containerId) {
        this.dockerClient.removeContainerCmd(containerId).exec();
    }

    public void createContainer(String imageId) {
        this.dockerClient.createContainerCmd(imageId).exec();
    }


}
