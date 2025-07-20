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
    var component = new CardComponent(cardModel, isFaceUp);
    var view = new CardView(component);
    return entityBuilder(data)
        .type(EntityType.CARD)
        .with(component)
        .with(new MouseDragBehaviour())
        .with(new StackingTargetBehaviour())
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
    var handComponent = new HandComponent();
    var hand = new HandView(handComponent);
    return entityBuilder(data)
        .with(handComponent)
        .viewWithBBox(hand)
        .build();
  }
}