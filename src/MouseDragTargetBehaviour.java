import com.almasb.fxgl.entity.component.Component;

public abstract class MouseDragTargetBehaviour extends Component implements DragReleaseHandler {
  @Override
  public void onAdded() {
    entity.getComponentOptional(MouseDragTargetManager.class)
        .ifPresent(manager -> manager.addHandler(this));
  }

  @Override
  public void onRemoved() {
    entity.getComponentOptional(MouseDragTargetManager.class)
        .ifPresent(manager -> manager.removeHandler(this));
  }
}
