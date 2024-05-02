package com.im2back.github.registrationmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.im2back.github.registrationmicroservice.model.vingadores.Avengers;

public interface AvengersRepository extends JpaRepository<Avengers, Long> {

}
