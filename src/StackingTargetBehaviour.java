import com.almasb.fxgl.entity.Entity;

public class StackingTargetBehaviour extends MouseDragTargetBehaviour {
  public StackingTargetBehaviour() {}

  @Override
  public void onDragReleased(Entity draggedEntity) {
    System.out.println("stacking onrelease is a go");
    draggedEntity.setX(entity.getX() + 15);
    draggedEntity.setY(entity.getY());
  }
}
