import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.input.MouseEvent;

public class CardMouseDragBehaviour extends MouseDragBehaviour {
  @Override
  protected void onMouseReleased(MouseEvent e) {
    super.onMouseReleased(e);
    System.out.println("card got released");
    System.out.println(entity.getComponents());

    //TODO: will need to update to handle multiple hands?
    var handComponent = FXGL.getGameWorld().getEntitiesByType(EntityType.HAND).getFirst().getComponent(HandComponent.class);

    if (entity.hasComponent(IsOnTheTable.class)) {
        System.out.println("pls ping card back to hand");
        entity.removeComponent(IsOnTheTable.class);
        handComponent.layoutCards();
    }
  }
}
