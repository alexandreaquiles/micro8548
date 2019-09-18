package br.com.caelum.eats.distancia;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="restaurantes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restaurante {

	@Id
	private Long id;

	@NotBlank @Size(max=9)
	private String cep;

	private Long tipoDeCozinhaId;
	
}
