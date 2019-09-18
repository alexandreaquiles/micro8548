package br.com.caelum.eats.distancia;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

interface RestauranteRepository extends MongoRepository<Restaurante, Long> {

	Page<Restaurante> findAllByTipoDeCozinhaId(Long tipo, Pageable limit);

	Page<Restaurante> findAll(Pageable limit);

}
