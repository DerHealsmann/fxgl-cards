import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.input.UserAction;
import javafx.scene.input.KeyCode;

public class DeveloperWASDControl extends Component {
    private static boolean exists = false;

    @Override
    public void onAdded() {
        if (exists) return;

        exists = true;

        final var input = FXGL.getInput();
        input.addAction(new UserAction("Dev_UP") {
            @Override
            public void onAction() {
                up();
            }
        }, KeyCode.W);
        input.addAction(new UserAction("Dev_DOWN") {
            @Override
            public void onAction() {
                down();
            }
        }, KeyCode.S);
        input.addAction(new UserAction("Dev_LEFT") {
            @Override
            public void onAction() {
                left();
            }
        }, KeyCode.A);
        input.addAction(new UserAction("Dev_RIGHT") {
            @Override
            public void onAction() {
                right();
            }
        }, KeyCode.D);
    }

    private double speed = 0.0;

    @Override
    public void onUpdate(double tpf) {
        speed = tpf * 60;
    }

    void up() {
        entity.translateY(-5 * speed);
    }

    void down() {
        entity.translateY(5 * speed);
    }

    void left() {
        entity.translateX(-5 * speed);
    }

    void right() {
        entity.translateX(5 * speed);
    }

    @Override
    public boolean isComponentInjectionRequired() {
        return false;
    }
}