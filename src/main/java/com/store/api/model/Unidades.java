package com.store.api.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "unidades")
@Data
public class Unidades {
  @Id
  private String id;
  private String unidad;
}
