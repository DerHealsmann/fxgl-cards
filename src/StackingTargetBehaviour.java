import com.almasb.fxgl.entity.Entity;
import javafx.scene.Parent;

public class StackingTargetBehaviour extends MouseDragTargetBehaviour {

  public StackingTargetBehaviour() {
    super();
  }

  public StackingTargetBehaviour(Parent targetRegion) {
    super(targetRegion);
  }

  protected void onDragReleased(Entity draggedEntity) {
    draggedEntity.setX(entity.getX() + 15);
    draggedEntity.setY(entity.getY());
  }
}
