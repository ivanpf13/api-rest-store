package com.store.api.repository;

import java.util.List;

import com.store.api.model.Articulo;
import com.store.api.model.Productos;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface ArticuloRepository extends MongoRepository<Productos, String> {
  List<Productos> findByPrecio(String sku);
  List<Productos> findByNombre(String nombre);
}
