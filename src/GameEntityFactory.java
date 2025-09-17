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
    var tableTargetBehaviour = new TableMouseDragTargetBehaviour();
    return entityBuilder(data)
        .view(background)
        .with(new MouseDragTargetManager())
        .with(tableTargetBehaviour)
        .build();
  }

  @Spawns(SpawnKeys.TEST_REGION)
  public Entity newTestRegion(SpawnData data) {
    var test = new Rectangle(125, 125);
    test.setFill(Color.HONEYDEW);
    var cardContainer = new CardContainerComponent(125, 125, AlignmentMode.TOP_LEFT);
    return entityBuilder(data)
        .view(test)
        .with(cardContainer)
        .with(new MouseDragTargetManager())
        .with(new CardContainerMouseDragTargetBehaviour())
        .build();
  }

  @Spawns(SpawnKeys.CARD)
  public Entity newCard(SpawnData data) {
    CardModel cardModel = data.get(SpawnDataKeys.MODEL);
    boolean isFaceUp = data.get(SpawnDataKeys.IS_FACE_UP);
    var component = new CardComponent(cardModel, isFaceUp);
    var view = new CardView(component);
    return entityBuilder(data)
        .type(EntityType.CARD)
        .with(component)
        .with(new MouseDragTargetManager())
        .with(new CardMouseDragBehaviour())
        .view(view)
        .build();
  }

  @Spawns(SpawnKeys.DECK)
  public Entity newDeck(SpawnData data) {
    var deckModel = new DeckModel();
    var deckComponent = new DeckComponent(deckModel);
    var deck = new DeckView(deckComponent);
    return entityBuilder(data)
        .type(EntityType.DECK)
        .with(deckComponent)
        .view(deck)
        .build();
  }

  @Spawns(SpawnKeys.HAND)
  public Entity newHand(SpawnData data) {
    int regionWidth = 350;
    int regionHeight = 175;
    var view = new HandView(regionWidth, regionHeight);
    var cardContainer = new CardContainerComponent(regionWidth, regionHeight, AlignmentMode.CENTER);
    return entityBuilder(data)
        .type(EntityType.HAND)
        .with(cardContainer)
        .viewWithBBox(view)
        .build();
  }
}