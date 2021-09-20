package com.store.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "articulos")
@Data
public class Articulo {
  @Id
  private String id;

  private String nombre;
  private String sku;

  public Articulo(String nombre, String sku){
    this.nombre=nombre;
    this.sku=sku;
  }
  @Override
  public String toString() {
    return "Articulos [id=" + id + ", nombre=" + nombre + ", sku=" + sku  +"]";
  }
}
