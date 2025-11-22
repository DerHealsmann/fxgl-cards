import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.entity.SpawnData;

import java.util.Map;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;
public class Main extends GameApplication {

    @Override
    protected void initSettings(GameSettings settings) {
      settings.setWidth(1920);
      settings.setHeight(1080);
      settings.setFullScreenAllowed(false);
      settings.setFullScreenFromStart(false);
    }

    @Override
    protected void initGame() {
        getGameWorld().addEntityFactory(new GameEntityFactory());
        spawn(SpawnKeys.BACKGROUND);

        var deckData = new SpawnData(30d, 40d);
        spawn(SpawnKeys.DECK, deckData);

        // todo: don't hardcode this in such a dumb way
        var playerHandData = new SpawnData(
            (getAppWidth() / 2d) - 175,
            (getAppHeight() * 0.8d - 88)
        );
        playerHandData.put(SpawnDataKeys.IS_FACE_UP, true);
        spawn(SpawnKeys.HAND, playerHandData);

        var compHandData = new SpawnData(
            (getAppWidth() / 2d) - 175,
            (getAppHeight() * 0.2d - 88)
        );
        compHandData.put(SpawnDataKeys.IS_FACE_UP, false);
        spawn(SpawnKeys.HAND, compHandData);


        spawn(SpawnKeys.TEST_REGION, new SpawnData(175d, 175d));
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        super.initGameVars(vars);
    }

    public static void main(String[] args) {
        launch(args);
    }
}