package com.im2back.github.playermicroservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.im2back.github.playermicroservice.model.group.Group;
import com.im2back.github.playermicroservice.model.player.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
	
	@Query("SELECT p.alias FROM Player p WHERE p.group = :groupParam")
    List<String> listCodinomesByGroup(@Param("groupParam") Group groupParam);
}
