package br.com.caelum.eats.restaurante;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties.StubsMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import br.com.caelum.eats.administrativo.TipoDeCozinha;

@AutoConfigureStubRunner(stubsMode = StubsMode.LOCAL, ids="br.com.caelum:eats-distancia-service:+:stubs:9992")
@SpringBootTest
@RunWith(SpringRunner.class)
public class DistanciaRestClientTest {
	
	private DistanciaRestClient distanciaRestClient;

	@Before
	public void before () {
		RestTemplate restTemplate = new RestTemplate();
		distanciaRestClient = new DistanciaRestClient(restTemplate, "http://localhost:9992");
	}

	@Test
	public void deveAdicionarNovoRestaurante() {
		TipoDeCozinha tipoDeCozinha = new TipoDeCozinha();
		tipoDeCozinha.setId(1L);

		Restaurante restaurante = new Restaurante();
		restaurante.setId(2L);
		restaurante.setCep("71501-010");
		restaurante.setTipoDeCozinha(tipoDeCozinha);
		distanciaRestClient.novoRestauranteAprovado(restaurante);
	}
	
}
