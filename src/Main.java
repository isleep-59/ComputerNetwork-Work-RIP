import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        LAN lan = new LAN();
        lan.initRouter();
        lan.initNetwork();
        List<Thread> list = new ArrayList<>(6);

        int index = 0;
        for(String routerName : lan.getRouterMap().keySet()) {
            Thread thread = list.get(index++);
            thread = lan.getRouterMap().get(routerName);
            thread.start();
        }
    }
}
