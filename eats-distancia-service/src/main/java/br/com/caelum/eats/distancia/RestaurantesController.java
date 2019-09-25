package br.com.caelum.eats.distancia;

import javax.management.RuntimeErrorException;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@Slf4j
public class RestaurantesController {

	private RestauranteRepository repo;
	
	@PostMapping("/restaurantes")
	ResponseEntity<Restaurante> adiciona(@RequestBody Restaurante novoRestaurante, UriComponentsBuilder uriBuilder)  {
		Restaurante salvo = repo.insert(novoRestaurante);
		log.info("Insere restaurante: " + novoRestaurante);
		UriComponents uriComponents = uriBuilder.path("/restaurantes/{id}").buildAndExpand(salvo.getId());
		return ResponseEntity.created(uriComponents.toUri()).contentType(MediaType.APPLICATION_JSON).body(salvo);
	}
	
	@PutMapping("/restaurantes/{id}")
	Restaurante atualiza(@PathVariable("id") Long id, @RequestBody Restaurante restaurante) {
		throw new RuntimeException();
		
//		if (!repo.existsById(id)) {
//			throw new ResourceNotFoundException();
//		}
//		log.info("Atualiza restaurante: " + restaurante);
//		return repo.save(restaurante);
	}
	
}


















