import com.almasb.fxgl.entity.Entity;

public class RegionMouseDragTargetBehaviour extends MouseDragTargetBehaviour {
  public RegionMouseDragTargetBehaviour() {}

  @Override
  public void onDragReleased(Entity draggedEntity) {
    //TODO: cards stop stacking once dragged onto a target region
    System.out.println("dragged into the honeydew");
    if (!draggedEntity.hasComponent(RegionMouseDragTargetBehaviour.class)) {
      draggedEntity.addComponent(new RegionMouseDragTargetBehaviour());
      System.out.println("new behaviour added");
    }
  }
}
