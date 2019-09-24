package br.com.caelum.apigateway;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class DistanciaRestClient {

	private RestTemplate restTemplate;
	private String distanciaServiceUrl;
	
	public DistanciaRestClient(RestTemplate restTemplate, @Value("${configuracao.distancia.service.url}") String distanciaServiceUrl) {
		this.restTemplate = restTemplate;
		this.distanciaServiceUrl = distanciaServiceUrl;
	}
	
	@HystrixCommand(fallbackMethod="restauranteSemDistancia")
	@SuppressWarnings("unchecked")
	Map<String, Object> porCepEId(String cep, Long restauranteId) {
		String url = distanciaServiceUrl + "/restaurantes/" + cep + "/restaurante/" + restauranteId;
		return restTemplate.getForObject(url, Map.class);
	}
	
	Map<String, Object> restauranteSemDistancia(String cep, Long restauranteId) {
		Map<String, Object> dados = new HashMap<>();
		dados.put("cep", cep);
		dados.put("restauranteId", restauranteId);
		return dados;
	}
}








