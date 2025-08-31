import com.almasb.fxgl.entity.component.Component;

public class IsOnTheTable extends Component {
    private static final IsOnTheTable SINGLETON = new IsOnTheTable();

    public static IsOnTheTable getInstance() { return SINGLETON; }

    private IsOnTheTable() {}
}
