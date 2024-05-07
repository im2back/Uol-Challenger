package com.im2back.github.registrationmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.im2back.github.registrationmicroservice.model.entities.ligadajustica.JusticeLeague;

public interface JusticeRepository extends JpaRepository<JusticeLeague, Long> {

}
