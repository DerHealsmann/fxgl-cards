import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


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
    CardModel cardModel = data.get(SpawnDataKeys.MODEL);
    boolean isFaceUp = data.get(SpawnDataKeys.IS_FACE_UP);
    var card = new CardView(cardModel, isFaceUp);
    return entityBuilder(data)
        .type(EntityType.CARD)
        .with(new CardModelComponent(cardModel))
        .with(new MouseDragBehaviour())
        .with(new StackingTargetBehaviour())
        .view(card)
        .build();
  }
}