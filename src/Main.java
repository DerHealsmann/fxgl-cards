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

        var deckData = new SpawnData(30d, 40d);
        spawn(SpawnKeys.DECK, deckData);

        var handData = new SpawnData(
            (getAppWidth() / 2d) - (HandComponent.REGION_WIDTH / 2d),
            (getAppHeight() * 0.8d) - (HandComponent.REGION_HEIGHT / 2d)
        );
        spawn(SpawnKeys.HAND, handData);

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