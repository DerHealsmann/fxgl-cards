import javafx.scene.input.MouseEvent;

public class CardMouseDragBehaviour extends MouseDragBehaviour {
  @Override
  protected void onMouseReleased(MouseEvent e) {
    super.onMouseReleased(e);
    System.out.println("card got released");
    System.out.println(entity.getComponents());

    var sourceContainer = CardContainerUtil.getSourceContainer(entity);

    if (entity.hasComponent(IsOnTheTable.class)) {
        System.out.println("pls ping card back to hand");
        entity.removeComponent(IsOnTheTable.class);
        sourceContainer.ifPresent(CardContainerComponent::layoutCards);
    }
  }
}
