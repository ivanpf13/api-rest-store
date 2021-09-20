package com.store.api.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "productos")
@Data
public class Productos {
  @Id
  private String id;
  private String clave;
  private String nombre;

  @DBRef
  private Unidades unidad;

  private Double precio;

  public Productos(String clave,String nombre, Unidades unidad,Double precio){
    this.clave=clave;
    this.nombre=nombre;
    this.unidad=unidad;
    this.precio=precio;

  }
  @Override
  public String toString() {
    return "Articulos [id=" + id + ", nombre=" + nombre + ", unidad=" + unidad + ", clave=" + clave+ ", precio=" + precio   +"]";
  }
}
