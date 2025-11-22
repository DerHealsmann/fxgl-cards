import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LayoutParser {
  /*
    parse a .layout file with the following format:

    LANGUAGE = [STATEMENT SEMICOLON]
    STATEMENT = (POSITION | POSITION_EXPR) EQUALS EXPR
    EXPR =  TYPE LEFT_PAREN ARGS? RIGHT_PAREN
    ARGS = '' | KEY = VAL   -- comma separated
    POSITION = INT COMMA INT

    TYPE = 'DECK' | 'CONTAINER'
    EQUALS = '='
    LEFT_PAREN = '('
    RIGHT_PAREN = ')'
    SEMICOLON = ';'
  */

  private String layoutDataRaw;

  public record Layout(Map<GridPosition, Expression> layoutData) {
  }

  record Statement(List<GridPosition> positions, Expression expr) {
  }

  record Expression(EntityType type, Map<String, String> args) {
  }

  record GridPosition(int x, int y) {
  }

  public LayoutParser(String layoutFile) {
    final var layoutFilePath = "/layouts/%s.layout".formatted(layoutFile);
    layoutDataRaw = readLayoutFile(layoutFilePath);
  }

  private String readLayoutFile(String layoutFilePath) {
    final var layoutFile = getClass().getResourceAsStream(layoutFilePath);
    try (var reader = new BufferedReader(new InputStreamReader(layoutFile))) {
      return reader.lines().peek(System.out::println).collect(Collectors.joining("\n"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public Layout parseLayout() {
    var statements = layoutDataRaw.split(";");
    var layout = new HashMap<GridPosition, Expression>();
    for (String s : statements) {
      var statement = parseStatement(s);
      for (GridPosition pos : statement.positions) {
        layout.put(pos, statement.expr);
      }
    }
    return new Layout(layout);
  }

  private Statement parseStatement(String statement) {
    var statementSplit = statement.indexOf("=");
    var pos = parsePosition(statement.substring(0, statementSplit));
    var expr = parseExpression(statement.substring(statementSplit + 1));
    return new Statement(pos, expr);
  }

  private List<GridPosition> parsePosition(String positionData) {
    //TODO: can handle individual coordinates, need to implement ranges
    //  e.g, 3..6, 2 would translate to (3, 2); (4, 2); (5, 2); (6, 2);
    var positionSplit = positionData.split(",");
    if (positionSplit.length != 2) {
      throw new IllegalArgumentException("Expected position to have length 2");
    }
    return List.of(new GridPosition(
        Integer.parseInt(positionSplit[0].trim()),
        Integer.parseInt(positionSplit[1].trim()))
    );
  }

  private Expression parseExpression(String expressionData) {
    var expressionSplit = expressionData.split("\\(");
    var type = EntityType.valueOf(expressionSplit[0].trim());
    var argsData = expressionSplit[1].split("\\)");
    if (argsData.length == 0) {
      return new Expression(type, Map.of());
    }
    return new Expression(type, parseArgs(argsData[0].trim()));
  }

  private Map<String, String> parseArgs(String argsData) {
    var argsMap = new HashMap<String, String>();
    if (!argsData.isBlank()) {
      var argsSplit = argsData.split(",");
      for (String arg : argsSplit) {
        var argSplit = arg.split("=");
        var key = argSplit[0].trim();
        var value = argSplit[1].trim();
        argsMap.put(key, value);
      }
    }
    return argsMap;
  }
}
