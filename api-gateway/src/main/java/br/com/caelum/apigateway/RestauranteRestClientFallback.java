package br.com.caelum.apigateway;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
class RestauranteRestClientFallback implements RestauranteRestClient {

	@Override
	public Map<String, Object> porId(Long id) {
		//TODO: colocar num cache de longa vida
		Map<String, Object> dados = new HashMap<>();
		dados.put("id", id);
		return dados;
	}

}
