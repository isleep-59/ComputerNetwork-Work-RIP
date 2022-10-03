import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//首页
class P1 extends JPanel {
    public P1(LAN lan, CardLayout cardLayout, JPanel panel) {
        setLayout(null);

        JLabel jl = new JLabel("请设定更新时间：");
        jl.setBounds(100, 50, 120, 20);

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
                //lan.initRouter();
                //lan.initNetwork();
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

//功能选择
class P2 extends JPanel {
    public P2(LAN lan, CardLayout cardLayout, JPanel panel) {
        setLayout(null);

        JLabel jl_function = new JLabel("请选择下列要使用的功能：");
        jl_function.setBounds(80, 40, 200, 20);

        JComboBox<String> jc_function = new JComboBox<>();
        jc_function.setBounds(80, 70, 100, 20);
        jc_function.addItem("请选择");
        jc_function.addItem("网络加入");
        jc_function.addItem("网络退出");
        jc_function.addItem("路由器故障");
        jc_function.addItem("路由器修复");

        JButton jb_select = new JButton("选择");
        jb_select.setBounds(240, 70, 80, 20);
        jb_select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String op = (String)jc_function.getSelectedItem();
                if(op.equals("网络加入")) {
                    cardLayout.show(panel, "p3");
                }
                else if(op.equals("网络退出")) {
                    if(lan.getNetworkMap().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "该局域网中已经没有网络！", "提示", JOptionPane.INFORMATION_MESSAGE);
                    }
                    cardLayout.show(panel, "p4");
                }
                else if(op.equals("路由器故障")) {
                    if(lan.getWorkingRouterCount() == 0) {
                        JOptionPane.showMessageDialog(null, "该局域网中路由器已全部故障！", "提示", JOptionPane.INFORMATION_MESSAGE);
                    }
                    cardLayout.show(panel, "p5");
                }
                else if(op.equals("路由器修复")) {
                    if(lan.getWorkingRouterCount() == 6) {
                        JOptionPane.showMessageDialog(null, "该局域网中路由器全部正常运行！", "提示", JOptionPane.INFORMATION_MESSAGE);
                    }
                    cardLayout.show(panel, "p6");
                }
            }
        });

        JLabel jl_router = new JLabel("请选择要查看路由表的路由器：");
        jl_router.setBounds(80, 160, 200, 20);

        JComboBox<String> jc_router = new JComboBox<>();
        jc_router.setBounds(80, 190, 100, 20);
        jc_router.addItem("请选择");
        for(String routerName : lan.getRouterMap().keySet()) {
            jc_router.addItem(routerName);
        }

        JButton jb_check = new JButton("查看");
        jb_check.setEnabled(false);
        jb_check.setBounds(240, 190, 80, 20);
        jb_check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String out = "目的网络           距离            下一跳\n";
                String routerName = (String)jc_router.getSelectedItem();
                for(Information information : lan.getRouterMap().get(routerName).getInformationTable().values()) {
                    out += information.getTargetNetwork() + "           " + information.getDistance() + "                 " + information.getNextRouterName() + "\n";
                }
                JOptionPane.showMessageDialog(null, out, routerName + "的路由表", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JButton jb_resume = new JButton("恢复运行");
        JButton jb_pause = new JButton("暂停运行");

        jb_resume.setEnabled(false);
        jb_resume.setBounds(70, 120, 100, 20);
        jb_resume.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(String routerName : lan.getRouterMap().keySet()) {
                    lan.getRouterMap().get(routerName).resume();
                }

                jb_select.setEnabled(true);
                jb_pause.setEnabled(true);
                jb_resume.setEnabled(false);
                jb_check.setEnabled(false);
            }
        });

        jb_pause.setBounds(230, 120, 100, 20);
        jb_pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(String routerName : lan.getRouterMap().keySet()) {
                    lan.getRouterMap().get(routerName).suspend();
                }

                jb_select.setEnabled(false);
                jb_pause.setEnabled(false);
                jb_resume.setEnabled(true);
                jb_check.setEnabled(true);
            }
        });


        this.add(jl_function);
        this.add(jc_function);
        this.add(jb_select);
        this.add(jl_router);
        this.add(jc_router);
        this.add(jb_pause);
        this.add(jb_resume);
        this.add(jb_check);
    }
}

//网络加入
class P3 extends JPanel {
    public P3(LAN lan, CardLayout cardLayout, JPanel panel) {
        setLayout(null);

        JLabel jl_inputNetworkName = new JLabel("请输入要加入的网络名称：");
        jl_inputNetworkName.setBounds(130, 50, 140, 20);

        JTextField jt_inputNetworkName = new JTextField();
        jt_inputNetworkName.setBounds(140, 70, 120, 20);

        JLabel jl_selectRouter = new JLabel("请勾选相邻的路由器：");
        jl_selectRouter.setBounds(140, 100, 120, 20);

        JCheckBox jc_1 = new JCheckBox("RouterA");
        jc_1.setBounds(100, 120, 80, 20);
        JCheckBox jc_2 = new JCheckBox("RouterB");
        jc_2.setBounds(220, 120, 80, 20);
        JCheckBox jc_3 = new JCheckBox("RouterC");
        jc_3.setBounds(100, 140, 80, 20);
        JCheckBox jc_4 = new JCheckBox("RouterD");
        jc_4.setBounds(220, 140, 80, 20);
        JCheckBox jc_5 = new JCheckBox("RouterE");
        jc_5.setBounds(100, 160, 80, 20);
        JCheckBox jc_6 = new JCheckBox("RouterF");
        jc_6.setBounds(220, 160, 80, 20);

        JButton jb_add = new JButton("添加");
        jb_add.setBounds(160, 200, 80, 20);
        jb_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String networkName;
                List<Router> adjRouterList = new ArrayList<>();
                Map<String, Router> routerMap = lan.getRouterMap();

                networkName = jt_inputNetworkName.getText();
                if(jc_1.isSelected()) {
                    adjRouterList.add(routerMap.get("RouterA"));
                }
                if(jc_2.isSelected()) {
                    adjRouterList.add(routerMap.get("RouterB"));
                }
                if(jc_3.isSelected()) {
                    adjRouterList.add(routerMap.get("RouterC"));
                }
                if(jc_4.isSelected()) {
                    adjRouterList.add(routerMap.get("RouterD"));
                }
                if(jc_5.isSelected()) {
                    adjRouterList.add(routerMap.get("RouterE"));
                }
                if(jc_6.isSelected()) {
                    adjRouterList.add(routerMap.get("RouterF"));
                }

                if(adjRouterList.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "请选择相邻路由器！", "提示", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    for(String routerName : routerMap.keySet()) {
                        routerMap.get(routerName).getInformationTable().put(networkName, new Information(networkName, 16, null));
                    }
                    lan.addNetwork(networkName, adjRouterList);
                }

                JOptionPane.showMessageDialog(null, "成功添加网络！", "提示", JOptionPane.INFORMATION_MESSAGE);
                cardLayout.show(panel, "p2");
            }
        });

        this.add(jl_inputNetworkName);
        this.add(jt_inputNetworkName);
        this.add(jl_selectRouter);
        this.add(jc_1);
        this.add(jc_2);
        this.add(jc_3);
        this.add(jc_4);
        this.add(jc_5);
        this.add(jc_6);
        this.add(jb_add);
    }
}

//网络退出
class P4 extends JPanel {
    public P4(LAN lan, CardLayout cardLayout, JPanel panel) {
        setLayout(null);

        JLabel jl = new JLabel("请选择下列要退出的网络：");
        jl.setBounds(80, 60, 140, 20);

        JComboBox<String> jc = new JComboBox<>();
        jc.setBounds(80, 90, 100, 20);
        jc.addItem("请选择");
        for(String networkName : lan.getNetworkMap().keySet()) {
            jc.addItem(networkName);
        }

        JButton jb_select = new JButton("选择");
        jb_select.setBounds(240, 90, 80, 20);
        jb_select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String networkName = (String)jc.getSelectedItem();
                lan.deleteNetwork(networkName);
                jc.removeItem(networkName);

                JOptionPane.showMessageDialog(null, "成功退出网络！", "提示", JOptionPane.INFORMATION_MESSAGE);
                cardLayout.show(panel, "p2");
            }
        });

        this.add(jl);
        this.add(jc);
        this.add(jb_select);
    }
}

//路由器故障
class P5 extends JPanel {
    public P5(LAN lan, CardLayout cardLayout, JPanel panel) {
        setLayout(null);

        JLabel jl = new JLabel("请选择下列出现故障的路由器：");
        jl.setBounds(80, 60, 140, 20);

        JComboBox<String> jc = new JComboBox<>();
        jc.setBounds(80, 90, 100, 20);
        jc.addItem("请选择");
        for(String routerName : lan.getRouterMap().keySet()) {
            if(lan.getRouterMap().get(routerName).getRouterStatus() == true) {
                jc.addItem(routerName);
            }
        }

        JButton jb_select = new JButton("选择");
        jb_select.setBounds(240, 90, 80, 20);
        jb_select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String routerName = (String)jc.getSelectedItem();
                lan.routerFault(routerName);
                jc.removeItem(routerName);

                String out = routerName + "发生故障！";
                JOptionPane.showMessageDialog(null, out, "提示", JOptionPane.INFORMATION_MESSAGE);
                cardLayout.show(panel, "p2");
            }
        });

        this.add(jl);
        this.add(jc);
        this.add(jb_select);
    }
}

//路由器恢复
class P6 extends JPanel {
    public P6(LAN lan, CardLayout cardLayout, JPanel panel) {
        setLayout(null);

        JLabel jl = new JLabel("请选择下列要恢复的路由器：");
        jl.setBounds(80, 60, 140, 20);

        JComboBox<String> jc = new JComboBox<>();
        jc.setBounds(80, 90, 100, 20);
        jc.addItem("请选择");
        for(String routerName : lan.getRouterMap().keySet()) {
            if(lan.getRouterMap().get(routerName).getRouterStatus() == false) {
                jc.addItem(routerName);
            }
        }

        JButton jb_select = new JButton("选择");
        jb_select.setBounds(240, 90, 80, 20);
        jb_select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String routerName = (String)jc.getSelectedItem();
                lan.routerRecover(routerName);
                jc.removeItem(routerName);

                JOptionPane.showMessageDialog(null, "成功退出网络！", "提示", JOptionPane.INFORMATION_MESSAGE);
                cardLayout.show(panel, "p2");
            }
        });

        this.add(jl);
        this.add(jc);
        this.add(jb_select);
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
        P2 p2 = new P2(lan, cardLayout, panel);
        panel.add(p2, "p2");
        P3 p3 = new P3(lan, cardLayout, panel);
        panel.add(p3, "p3");
        P4 p4 = new P4(lan, cardLayout, panel);
        panel.add(p4, "p4");
        P5 p5 = new P5(lan, cardLayout, panel);
        panel.add(p5, "p5");
        P6 p6 = new P6(lan, cardLayout, panel);
        panel.add(p6, "p6");
    }
}
