import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class HandView extends StackPane {
  public HandView(HandComponent handComponent) {
    createHandRegion(handComponent.getRegionWidth(), handComponent.getRegionHeight());
  }
  private void createHandRegion(int width, int height) {
    var region = new Rectangle(width, height);
    region.setFill(Color.CORAL);
    region.setOpacity(0.5);
    getChildren().add(region);

    var hand = new Rectangle(width - 90, height - 25);
    hand.setFill(Color.DODGERBLUE);
    hand.setOpacity(0.5);
    getChildren().add(hand);
  }
}
