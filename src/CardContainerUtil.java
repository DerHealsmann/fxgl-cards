import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;

import java.util.Optional;

final class CardContainerUtil {

  private CardContainerUtil() {}

  public static Optional<CardContainerComponent> getSourceContainer(Entity entityToFind) {
    // get all the card containers than contain this card - should only be one
    return FXGL.getGameWorld().getEntitiesByComponentMapped(CardContainerComponent.class).values().stream()
        .filter(cardContainerComponent -> cardContainerComponent.getCards().contains(entityToFind))
        .findFirst();
  }
}
