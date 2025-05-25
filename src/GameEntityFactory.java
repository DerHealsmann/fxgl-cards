import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

public class GameEntityFactory implements EntityFactory {

  @Spawns(SpawnKeys.BACKGROUND)
  public Entity newBackground(SpawnData data) {
    var background = new Rectangle(getAppWidth(), getAppHeight());
    background.setFill(Color.BLANCHEDALMOND);

    return entityBuilder(data)
        .view(background)
        .build();
  }

  @Spawns(SpawnKeys.CARD)
  public Entity newCard(SpawnData data) {
    var card = new StackPane();
    var cardBackground = new Rectangle(88, 112);
    CardModel cardModel = data.get("model");
    var topLeftLabel = new Text(cardModel.toString());
    var bottomRightLabel = new Text(cardModel.toString());
    bottomRightLabel.setRotate(180d);

    card.setStyle(
        "-fx-border-color: red;" + // Border color
        "-fx-border-width: 2;" + // Border width in pixels
        "-fx-border-style: solid;" + // Border style (solid, dashed, dotted)
        "-fx-border-radius: 5;"
    );

    final var paint = cardModel.isRed() ? Color.RED : Color.BLACK;
    topLeftLabel.setFill(paint);
    bottomRightLabel.setFill(paint);

    StackPane.setAlignment(topLeftLabel, Pos.TOP_LEFT);
    StackPane.setAlignment(bottomRightLabel, Pos.BOTTOM_RIGHT);
    StackPane.setMargin(topLeftLabel, new Insets(10));
    StackPane.setMargin(bottomRightLabel, new Insets(10));

    cardBackground.setFill(Color.GHOSTWHITE);
    card.getChildren().addAll(cardBackground, topLeftLabel, bottomRightLabel);

    return entityBuilder(data)
        .type(EntityType.CARD)
        .with(new CardModelComponent(cardModel))
        .view(card)
        .build();
  }
}