package com.gps.api.controlers;

import java.net.URI;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gps.api.entities.CoordenatesEntitie;
import com.gps.api.repositories.CoordenateRepositorie;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value="coordenates")
@RestController
@RequestMapping("/coodernates")
public class CoordenateController {

	@Autowired
	private CoordenateRepositorie repositorie;
	
	@ApiOperation(
			value="Buscar todos os pois", 
			response=CoordenatesEntitie.class)
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Retorna Lista de todos os pois",
					response=ResponseEntity.class
					),
			@ApiResponse(
					code=500, 
					message="Caso tenhamos algum erro vamos retornar a Exception",
					response=ResponseEntityExceptionHandler.class
					)
 
	})
	
	@GetMapping
	public ResponseEntity<?> findAll() {
		List<CoordenatesEntitie> coordenates = repositorie.findAll();
		return coordenates.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(coordenates);
	}
	
	@ApiOperation(
			value="Cadastrar um poi", 
			response=CoordenatesEntitie.class)
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Retorna o poi cadastrado",
					response=ResponseEntity.class
					),
			@ApiResponse(
					code=500, 
					message="Caso tenhamos algum erro vamos retornar a Exception",
					response=ResponseEntityExceptionHandler.class
					)
 
	})
	
	@PostMapping(value="/insertCoordenates")
	public ResponseEntity<CoordenatesEntitie> insertCoordenate(@RequestBody CoordenatesEntitie coodernates, HttpServletResponse resp) {
		CoordenatesEntitie newCoordenates = repositorie.save(coodernates);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/findById/{id}")
				.buildAndExpand(newCoordenates.getIdCoordenate()).toUri();
		resp.setHeader("Location", uri.toASCIIString());
		return ResponseEntity.created(uri).body(newCoordenates);
	}
	
	@GetMapping("/findById/[id}")
	public ResponseEntity<CoordenatesEntitie> findById(@PathVariable(name="id") Long id) {
		CoordenatesEntitie coordenates = repositorie.findOne(id);
		return coordenates != null ? ResponseEntity.ok(coordenates) : ResponseEntity.noContent().build();
	}
	
	@ApiOperation(
			value="Buscar poi por proximidade", 
			response=CoordenatesEntitie.class)
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Retorna todos os pois de acordo com uma coordenada e uma distância maxima",
					response=ResponseEntity.class
					),
			@ApiResponse(
					code=500, 
					message="Caso tenhamos algum erro vamos retornar a Exception",
					response=ResponseEntityExceptionHandler.class
					)
 
	})
	
	
	@GetMapping("/findReferencesPoint/{limit}")
	public ResponseEntity<?>pointsReferences (@PathVariable(name="limit") Long limit,  @RequestBody Map<String, Object> coordenates) {
		List<String> response = repositorie.coordenates(Long.valueOf(coordenates.get("coordenateX").toString()), Long.valueOf(coordenates.get("coordenateY").toString()), limit);
		if (response.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(response);
	}
}
