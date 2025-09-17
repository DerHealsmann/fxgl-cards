import com.almasb.fxgl.entity.component.Component;

import java.util.Objects;

public abstract class MouseDragTargetBehaviour extends Component implements DragReleaseHandler {
  // auto-injected, does not need initialising
  private MouseDragTargetManager manager;

  @Override
  public void onAdded() {
    Objects.requireNonNull(manager, "manager must be added before behaviours");
    manager.addHandler(this);
  }

  @Override
  public void onRemoved() {
    manager.removeHandler(this);
  }
}
