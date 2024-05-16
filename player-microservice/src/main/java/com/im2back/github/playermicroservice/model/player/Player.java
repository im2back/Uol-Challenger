package com.im2back.github.playermicroservice.model.player;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.im2back.github.playermicroservice.model.group.Group;
import com.im2back.github.playermicroservice.model.player.dto.PlayerUpdateRequestDto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_player")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String email;

	private String phone;

	private String alias;

	@Enumerated(EnumType.STRING)
	@Column(name = "group_list")
	private Group group;

	public Player(String name, String email, String phone, String alias, Group group) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.alias = alias;
		this.group = group;
	}

	public void update(PlayerUpdateRequestDto dtoParam) {

		if (dtoParam.getName() != null) {
			this.setName(dtoParam.getName());
		}
		if (dtoParam.getEmail() != null) {
			this.setEmail(dtoParam.getEmail());
		}
		if (dtoParam.getPhone() != null) {
			this.setPhone(dtoParam.getPhone());
		}
	}

}
