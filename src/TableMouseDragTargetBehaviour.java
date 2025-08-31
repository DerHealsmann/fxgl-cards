import com.almasb.fxgl.entity.Entity;

public class TableMouseDragTargetBehaviour extends MouseDragTargetBehaviour {
  public TableMouseDragTargetBehaviour() {}

  @Override
  public void onDragReleased(Entity draggedEntity) {
    System.out.println("dragged onto table");
    draggedEntity.addComponent(IsOnTheTable.getInstance());


  }
}