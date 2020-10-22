package edu.cnm.deepdive;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.cnm.deepdive.model.Schema;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.stream.Collectors;

public class Parser {

  private static final String STATEMENT_TERMINATOR = ";";
  private static final String STATEMENT_SEPARATOR = String.format("%s%n%n", STATEMENT_TERMINATOR);
  private Schema schema;

  public static void main(String... args) throws IOException {
    Parser parser = new Parser();
    Schema schema = parser.parse(System.in);
    System.out.println(parser.getDdl(schema));
  }

  private Schema parse(InputStream input) throws IOException {
    try (Reader reader = new InputStreamReader(input)) {
      Gson gson = new GsonBuilder()
          .excludeFieldsWithoutExposeAnnotation()
          .create();
      return gson.fromJson(reader, Schema.class);
    }
  }

  private String getDdl(Schema schema) {
    this.schema = schema;
    return schema.getDdl()
        .collect(Collectors.joining(STATEMENT_SEPARATOR, "", STATEMENT_TERMINATOR));
  }

}
