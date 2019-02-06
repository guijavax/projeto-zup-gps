package com.gps.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gps.api.entities.CoordenatesEntitie;

public interface CoordenateRepositorie extends JpaRepository<CoordenatesEntitie, Long>{

@Query(value="select name_coordenates from coordenates "
		+ "where "
		+ 	":dmax >= sqrt(((:coordenate_x - coordenate_x)^2) + ((:coordenate_y - coordenate_y)^2))"
		, nativeQuery = true)
		List<String> coordenates(
				@Param(value="coordenate_x") Long coordenateX, 
				@Param(value="coordenate_y") Long coordenateY,
				@Param (value="dmax") Long dMax);
			   
}