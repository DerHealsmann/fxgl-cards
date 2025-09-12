import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import javafx.scene.Parent;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the DragReleaseHandlers that fire when an entity is dragged onto this entity
 */
public class MouseDragTargetManager extends Component {
  protected Parent viewRoot;

  private final List<DragReleaseHandler> handlers = new ArrayList<>();

  protected MouseDragTargetManager() {
  }

  private void setupViewRoot() {
    if (viewRoot == null) {
      this.viewRoot = getEntity().getViewComponent().getParent();
    }
  }

  @Override
  public void onAdded() {
    super.onAdded();
    setupViewRoot();
    viewRoot.setOnMouseDragReleased(event -> {
      final var draggedEntity = FXGL.getWorldProperties().<Entity>getObject(VarKeys.DRAGGED_ENTITY);
      System.out.println(handlers);
      for (DragReleaseHandler handler : handlers) {
        handler.onDragReleased(draggedEntity);
      }
    });
  }

  public void addHandler(DragReleaseHandler handler) {
    handlers.add(handler);
  }

  public void removeHandler(DragReleaseHandler handler) {
    handlers.remove(handler);
  }
}
