import javafx.scene.input.MouseEvent;

public class CardMouseDragBehaviour extends MouseDragBehaviour {
  @Override
  protected void onMouseReleased(MouseEvent e) {
    super.onMouseReleased(e);
    System.out.println("card got released");

    var sourceContainer = CardContainerUtil.getSourceContainer(entity);
    sourceContainer.ifPresent(CardContainerComponent::layoutCards);
  }
}
