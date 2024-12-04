package com.lonelystar.docker_manager.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DefaultDockerClientConfig.Builder;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;

@Configuration
public class DockerClientConfig {

    @Value("${docker.socket.path}")
    private String dockerSocketPath;

    public DockerClient buiDockerClient(){
        Builder dockerClientConfig = DefaultDockerClientConfig.createDefaultConfigBuilder();
        if(this.dockerSocketPath != null && this.dockerSocketPath.startsWith("unix://")) {
            dockerClientConfig.withDockerHost(this.dockerSocketPath).withDockerTlsVerify(false);
        }

    DefaultDockerClientConfig config = dockerClientConfig.build();

    ApacheDockerHttpClient dockerHttpClient = new ApacheDockerHttpClient.Builder()
        .dockerHost(config.getDockerHost())
        .sslConfig(config.getSSLConfig())
        .maxConnections(100)
        .connectionTimeout(java.time.Duration.ofSeconds(30))
        .responseTimeout(java.time.Duration.ofSeconds(45))
        .build();

    return DockerClientBuilder.getInstance().withDockerHttpClient(dockerHttpClient).build();

    }
    
}
