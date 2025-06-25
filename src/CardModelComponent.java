import com.almasb.fxgl.entity.component.Component;

public class CardModelComponent extends Component {
  private final CardModel cardModel;

  public CardModelComponent(CardModel cardModel) {
    this.cardModel = cardModel;
  }

  @Override
  public boolean isComponentInjectionRequired() {
    return false;
  }
}
