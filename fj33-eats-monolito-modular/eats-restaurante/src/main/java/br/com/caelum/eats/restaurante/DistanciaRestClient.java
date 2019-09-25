package br.com.caelum.eats.restaurante;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
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
	
	@Retryable(maxAttempts = 5, backoff = @Backoff(delay = 2000, multiplier = 2))
	void restauranteAtualizado(Restaurante restaurante) {
		RestauranteParaServiceDeDistancia restauranteParaDistancia = 
					new RestauranteParaServiceDeDistancia(restaurante);
		log.info("monólito tentando chamar servico de distancia");
		String url = distanciaServiceUrl + "/restaurantes/" + restaurante.getId();
		restTemplate.put(url, restauranteParaDistancia);
	}
}
