package com.im2back.github.playermicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.im2back.github.playermicroservice.model.player.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

	
}
