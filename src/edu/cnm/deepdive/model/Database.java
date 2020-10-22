package edu.cnm.deepdive.model;

import com.google.gson.annotations.Expose;
import java.util.List;

public class Database {


  @Expose
  private List<Entity> entities;

  public List<Entity> getEntities() {
    return entities;
  }

  public void setEntities(List<Entity> entities) {
    this.entities = entities;
  }

}
