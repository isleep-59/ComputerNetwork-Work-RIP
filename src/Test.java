import java.util.HashMap;
import java.util.Map;

public class Test {

    public static void main(String[] args) {
        Map<String, String> mp = new HashMap<>();
        mp.put("1", "111");
        mp.put("2", "222");
        System.out.println(mp.values());
    }
}
