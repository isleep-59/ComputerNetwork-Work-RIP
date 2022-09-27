import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public void program_init(LAN lan) {
        lan.initRouter();
        lan.initNetwork();
    }

    public void program_launch(LAN lan) {
        for(String routerName : lan.getRouterMap().keySet()) {
            lan.getRouterMap().get(routerName).start();
        }
    }

    public void program_pause(LAN lan) {

    }

    public static void setFrameMiddle(JFrame jf) {
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        jf.setLocation((d.width - jf.getSize().width) / 2,(d.height - jf.getSize().height) / 2);
    }

    public static void GUI_functions(JFrame jf) {
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(800, 600);

        JButton jb = new JButton("BACK!");
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                return;
            }
        });
        jf.add(jb);
        setFrameMiddle(jf);
        jf.setVisible(true);

    }

    public static void GUI_init() {
        JFrame jf = new JFrame("RIP Protocol");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(800, 600);

        JPanel jp = new JPanel();
        JButton jb = new JButton("START!");
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUI_functions(jf);
            }
        });
        jp.add(jb);
        jf.add(jp);
        setFrameMiddle(jf);
        jf.setVisible(true);
    }
    public static void main(String[] args) {
        LAN lan = new LAN();

        GUI_init();
    }
}
