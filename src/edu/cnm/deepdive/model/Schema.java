package edu.cnm.deepdive.model;

import com.google.gson.annotations.Expose;
import java.util.stream.Stream;
import javax.xml.crypto.Data;

public class Schema {

  private static final String PLACEHOLDER = "${TABLE_NAME}";
  @Expose
  private Database database;

  public Database getDatabase() {
    return database;
  }

  public void setDatabase(Database database) {
    this.database = database;
  }
  public Stream<String> getDdl() {
    return database.getEntities().stream()
      .flatMap((entity) ->
          Stream.concat(
              Stream.of(entity.getDdl()),
              entity.getIndices().stream()
                  .map(Index::getDdl)
            )
          .map((s)  -> s.replace(PLACEHOLDER, entity.getName()))
      );
  }
}
