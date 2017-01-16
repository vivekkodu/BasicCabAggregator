import java.util.HashMap;
import java.util.Map;

/**
 * Created by VIVEK VERMA on 1/16/2017.
 */
public abstract class User {

    protected int userId;

    public abstract int getUserId();

    public User(int userId){
        this.userId = userId;
    }
}
