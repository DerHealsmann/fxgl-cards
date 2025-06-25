import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import javafx.scene.Parent;

/**
 * Makes this entity draggable with the mouse
 */
public class MouseDragBehaviour extends Component {

  private Parent viewRoot;

  private double dragStartMouseXRelative;
  private double dragStartMouseYRelative;

  public MouseDragBehaviour() {
  }

  @Override
  public void onAdded() {
    super.onAdded();
    setupViewRoot();
    setupMouseDragListeners();
  }

  private void setupViewRoot() {
    if (viewRoot == null) {
      this.viewRoot = getEntity().getViewComponent().getParent();
    }
  }

  private void setupMouseDragListeners() {
    viewRoot.setOnMousePressed(e -> {
      // store mouse position relative to object
      dragStartMouseXRelative = e.getSceneX() - entity.getX();
      dragStartMouseYRelative = e.getSceneY() - entity.getY();
      // required so that card is not a target during its own mouse events
      viewRoot.setMouseTransparent(true);
      // bring to fore
      entity.setZIndex(100);
    });

    viewRoot.setOnMouseDragged(e -> {
      entity.setX(e.getSceneX() - dragStartMouseXRelative);
      entity.setY(e.getSceneY() - dragStartMouseYRelative);
    });

    viewRoot.setOnMouseReleased(e -> {
      // required so that card is not a target during its own mouse events
      viewRoot.setMouseTransparent(false);
      entity.setZIndex(0);
    });

    // begin full drag event
    // https://openjfx.io/javadoc/21/javafx.graphics/javafx/scene/input/MouseDragEvent.html
    viewRoot.setOnDragDetected(e -> {
      viewRoot.startFullDrag();
      FXGL.getWorldProperties().setValue(VarKeys.DRAGGED_ENTITY, entity);
    });

  }
}
