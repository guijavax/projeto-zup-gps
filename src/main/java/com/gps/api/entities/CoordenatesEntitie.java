package com.gps.api.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.context.MessageSource;
import org.springframework.validation.annotation.Validated;


@Entity
@Table(name="coordenates")
public class CoordenatesEntitie {
	
	@Id
	@Column(name="id_coordenate")
	@GeneratedValue(generator="id_coordenate_sequence")
	@SequenceGenerator(name="id_coordenate_sequence", sequenceName="sequence_id_coordenate")
	private Long idCoordenate;
	
	@Column(name="name_coordenates")
	private String nameCoordenate;
	
	@Column(name="coordenate_x")
	@Min(value=0, message="Coordenada deve ser positiva!")
	private Long coordenateX;
	
	@Column(name="coordenate_y")
	@Min(value=0, message="Coordenada deve ser positiva!")
	private Long coordenateY;
	
	public Long getIdCoordenate() {
		return idCoordenate;
	}

	public void setId_coordenates(Long idCoordenate) {
		this.idCoordenate = idCoordenate;
	}

	public String getNameCoodernate() {
		return nameCoordenate;
	}

	public void setNameCoodernate(String nameCoordenate) {
		this.nameCoordenate = nameCoordenate;
	}

	public Long getcoordenateX() {
		return coordenateX;
	}

	public void setcoordenateX(Long coordenateX) {
		this.coordenateX = coordenateX;
	}

	public Long getCoordenateY() {
		return coordenateY;
	}

	public void setCoordenateY(Long coordenateY) {
		this.coordenateY = coordenateY;
	}
}
