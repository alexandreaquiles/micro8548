package br.com.caelum.eats.restaurante;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
class DistanciaRestClient {

	private RestTemplate restTemplate;
	private String distanciaServiceUrl;
	
	DistanciaRestClient(RestTemplate restTemplate, 
							@Value("${configuracao.distancia.service.url}") String distanciaServiceUrl) {
		this.restTemplate = restTemplate;
		this.distanciaServiceUrl = distanciaServiceUrl;
	}

	void novoRestauranteAprovado(Restaurante restaurante) {
		String url = distanciaServiceUrl + "/restaurantes";
		RestauranteParaServiceDeDistancia restauranteParaDistancia = new RestauranteParaServiceDeDistancia(restaurante);
		ResponseEntity<RestauranteParaServiceDeDistancia> responseEntity = restTemplate.postForEntity(url, restauranteParaDistancia, RestauranteParaServiceDeDistancia.class);
		HttpStatus statusCode = responseEntity.getStatusCode();
		if (!HttpStatus.CREATED.equals(statusCode)) {
			throw new RuntimeException("Status diferente do esperado: " + statusCode);
		}
	}
	
	void restauranteAtualizado(Restaurante restaurante) {
		RestauranteParaServiceDeDistancia restauranteParaDistancia = 
					new RestauranteParaServiceDeDistancia(restaurante);
		
		String url = distanciaServiceUrl + "/restaurantes/" + restaurante.getId();
		restTemplate.put(url, restauranteParaDistancia);
	}
}
