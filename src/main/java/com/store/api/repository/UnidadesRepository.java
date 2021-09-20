package com.store.api.repository;

import com.store.api.model.Productos;
import com.store.api.model.Unidades;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UnidadesRepository extends MongoRepository<Unidades, String> {
}
