import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

public class Main extends GameApplication {

    private Entity card;

    @Override
    protected void initSettings(GameSettings settings) { }

    @Override
    protected void initGame() {
        getGameWorld().addEntityFactory(new GameEntityFactory());

        spawn(SpawnKeys.BACKGROUND);
        var cardData = new SpawnData(getAppWidth() / 2d, getAppHeight() / 2d);
        cardData.put(CardModel.MODEL, new CardModel(CardModel.Rank.FOUR, CardModel.Suit.DIAMONDS));

        card = spawn(SpawnKeys.CARD, cardData);
    }

    @Override
    protected void initPhysics() {
        // the order of entities is determined by
        // the order of their types passed into this method

    }

    public static void main(String[] args) {
        launch(args);
    }
}