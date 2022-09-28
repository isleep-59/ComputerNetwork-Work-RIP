import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class P1 extends JPanel {
    public P1(LAN lan, CardLayout cardLayout, JPanel panel) {
        setLayout(null);

        JLabel jl = new JLabel("请设定更新时间：");
        jl.setFont(new Font("微软雅黑", Font.BOLD, 10));
        jl.setBounds(100, 50, 80, 20);

        JTextField jt = new JTextField();
        jt.setBounds(100, 70, 80, 20);

        JButton jb_start = new JButton("开始");
        jb_start.setEnabled(false);
        jb_start.setBounds(160, 160, 80, 20);
        jb_start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(String routerName : lan.getRouterMap().keySet()) {
                    lan.getRouterMap().get(routerName).start();
                }
                cardLayout.show(panel, "p2");
            }
        });

        JButton jb_init = new JButton("初始化");
        jb_init.setEnabled(false);
        jb_init.setBounds(160, 130, 80, 20);
        jb_init.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lan.initRouter();
                lan.initNetwork();
                JOptionPane.showMessageDialog(null, "初始化成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("程序初始化成功！");
                jb_start.setEnabled(true);
            }
        });

        JButton jb_setTime = new JButton("设定");
        jb_setTime.setBounds(220, 70, 80, 20);
        jb_setTime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int t = Integer.parseInt(jt.getText());
                String out = "路由器更新时间设定为：" + t + "秒！";
                lan.setRouterUpdateTime(t);
                JOptionPane.showMessageDialog(null, out, "提示", JOptionPane.INFORMATION_MESSAGE);
                System.out.printf("路由器更新时间设定为：%d秒！\n", t);
                jb_init.setEnabled(true);
            }
        });

        this.add(jl);
        this.add(jt);
        this.add(jb_setTime);
        this.add(jb_init);
        this.add(jb_start);
    }
}

class P2 extends JPanel {
    public P2() {
        setLayout(null);

        JComboBox<String> jc = new JComboBox<>();
        jc.setBounds(100, 50, 80, 20);
        jc.addItem("请选择");
        jc.addItem("网络加入");
        jc.addItem("网络退出");
        jc.addItem("路由器故障");
        jc.addItem("路由器修复");

        JButton jb_select = new JButton("设定");
        jb_select.setBounds(220, 70, 80, 20);
        jb_select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String op = (String)jc.getSelectedItem();
                if(op.equals("网络加入")) {

                }
                else if(op.equals("网络退出")) {

                }
                else if(op.equals("路由器故障")) {

                }
                else if(op.equals("路由器修复")) {

                }
            }
        });

        this.add(jc);
    }
}

public class MyFrame extends JFrame {
    CardLayout cardLayout = new CardLayout();

    public MyFrame(LAN lan) {
        super("RIP协议模拟软件");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(cardLayout);
        panel.setBounds(0, 0, 400, 300);
        setContentPane(panel);

        P1 p1 = new P1(lan, cardLayout, panel);
        panel.add(p1, "p1");
        P2 p2 = new P2();
        panel.add(p2, "p2");
    }
}
