package br.com.caelum.apigateway;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
class RestauranteComDistanciaController {

	private DistanciaRestClient distanciaRestClient;
	private RestauranteRestClient restauranteRestClient;
	
	@GetMapping("/restaurantes-com-distancia/{cep}/restaurante/{restauranteId}")
	Map<String, Object> porCepEIdComDistancia(@PathVariable("cep") String cep,
												@PathVariable("restauranteId") Long restauranteId) {
		Map<String, Object> dadosDeDistancia = distanciaRestClient.porCepEId(cep, restauranteId);
		Map<String, Object> dadosDoRestaurante = restauranteRestClient.porId(restauranteId);
		dadosDoRestaurante.putAll(dadosDeDistancia);
		return dadosDoRestaurante;
	}
	
}
