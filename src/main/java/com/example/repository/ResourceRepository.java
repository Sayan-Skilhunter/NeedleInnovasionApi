package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.NetflixTitle;

@Repository
public interface ResourceRepository extends JpaRepository<NetflixTitle, String> {

}
