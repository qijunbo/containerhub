package com.example.docker.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ContainerRepository extends CrudRepository<Container, Long> {

    List<Container> findByName(String lastName);
}
