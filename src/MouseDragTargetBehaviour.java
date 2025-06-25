import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import javafx.scene.Parent;

public abstract class MouseDragTargetBehaviour extends Component {
  protected Parent viewRoot;

  protected MouseDragTargetBehaviour() {
  }

  protected MouseDragTargetBehaviour(Parent viewRoot) {
    this.viewRoot = viewRoot;
  }

  protected abstract void onDragReleased(Entity draggedEntity);

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
      onDragReleased(draggedEntity);
    });
  }
}
