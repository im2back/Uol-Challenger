package com.im2back.github.playermicroservice.model.player;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.im2back.github.playermicroservice.model.group.Group;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="tb_player")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Player {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;
	
	private String email;
	
	private String phone;
	
	private String alias;
	
	@Enumerated(EnumType.STRING)
	private Group group;

}
