import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        LAN lan = new LAN();
        lan.initRouter();
        lan.initNetwork();
        for(String routerName : lan.getRouterMap().keySet()) {
            lan.getRouterMap().get(routerName).start();
        }

        JFrame jf = new JFrame("RIP协议模拟");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(800, 600);
        jf.setVisible(true);
    }
}
