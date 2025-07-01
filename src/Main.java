import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.entity.SpawnData;

import java.util.Map;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;
public class Main extends GameApplication {

    @Override
    protected void initSettings(GameSettings settings) { }

    @Override
    protected void initGame() {
        getGameWorld().addEntityFactory(new GameEntityFactory());
        spawn(SpawnKeys.BACKGROUND);

        var cardData1 = new SpawnData(getAppWidth() / 2d, getAppHeight() / 2d);
        cardData1.put(SpawnDataKeys.MODEL, new CardModel(CardModel.Rank.FOUR, CardModel.Suit.DIAMONDS));
        cardData1.put(SpawnDataKeys.IS_FACE_UP, true);
        spawn(SpawnKeys.CARD, cardData1);

        var cardData2 = new SpawnData(25d, 35d);
        cardData2.put(SpawnDataKeys.MODEL, new CardModel(CardModel.Rank.JACK, CardModel.Suit.CLUBS));
        cardData2.put(SpawnDataKeys.IS_FACE_UP, true);
        spawn(SpawnKeys.CARD, cardData2);
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        super.initGameVars(vars);
    }

    public static void main(String[] args) {
        launch(args);
    }
}