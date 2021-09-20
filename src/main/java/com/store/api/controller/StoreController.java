package com.store.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.store.api.model.Articulo;
import com.store.api.model.Productos;
import com.store.api.model.Unidades;
import com.store.api.repository.ArticuloRepository;
import com.store.api.repository.UnidadesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class StoreController {

  @Autowired
  ArticuloRepository storeRepository;
  @Autowired
  UnidadesRepository unidadesRepository;
  @GetMapping("/productos")
  public ResponseEntity<List<Productos>> getAllTutorials(@RequestParam(required = false) String nombre) {
    try {
      List<Productos> stores = new ArrayList<Productos>();

      if (nombre == null)
        storeRepository.findAll().forEach(stores::add);
      else
        storeRepository.findByNombre(nombre).forEach(stores::add);

      if (stores.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(stores, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/productos/{id}")
  public ResponseEntity<Productos> getTutorialById(@PathVariable("id") String id) {
    Optional<Productos> tutorialData = storeRepository.findById(id);

    if (tutorialData.isPresent()) {
      return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/productos")
  public ResponseEntity<Productos> createTutorial(@RequestBody Productos producto) {
    try {
      System.out.println("producto-->"+producto);
      Productos _tutorial = storeRepository.save(new Productos(producto.getClave(),producto.getNombre(), producto.getUnidad(),producto.getPrecio()));
      return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/productos/{id}")
  public ResponseEntity<Productos> updateTutorial(@PathVariable("id") String id, @RequestBody Productos store) {
    Optional<Productos> optionalStore = storeRepository.findById(id);

    if (optionalStore.isPresent()) {
      Productos store1 = optionalStore.get();
      store1.setNombre(store.getNombre());
      store1.setUnidad(store.getUnidad());
      store1.setClave(store.getClave());
      store1.setPrecio(store.getPrecio());
      return new ResponseEntity<>(storeRepository.save(store1), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/productos/{id}")
  public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") String id) {
    try {
      storeRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/productos")
  public ResponseEntity<HttpStatus> deleteAllTutorials() {
    try {
      storeRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/unidades")
  public ResponseEntity<List<Unidades>> getUnidades() {
    try {
      List<Unidades> unidades = new ArrayList<Unidades>();

      unidadesRepository.findAll().forEach(unidades::add);

      if (unidades.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(unidades, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
