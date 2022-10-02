import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class P1 extends JPanel {
    public P1(LAN lan, CardLayout cardLayout, JPanel panel) {
        setLayout(null);

        JLabel jl = new JLabel("���趨����ʱ�䣺");
        jl.setFont(new Font("΢���ź�", Font.BOLD, 10));
        jl.setBounds(100, 50, 80, 20);

        JTextField jt = new JTextField();
        jt.setBounds(100, 70, 80, 20);

        JButton jb_start = new JButton("��ʼ");
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

        JButton jb_init = new JButton("��ʼ��");
        jb_init.setEnabled(false);
        jb_init.setBounds(160, 130, 80, 20);
        jb_init.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //lan.initRouter();
                //lan.initNetwork();
                JOptionPane.showMessageDialog(null, "��ʼ���ɹ���", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("�����ʼ���ɹ���");
                jb_start.setEnabled(true);
            }
        });

        JButton jb_setTime = new JButton("�趨");
        jb_setTime.setBounds(220, 70, 80, 20);
        jb_setTime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int t = Integer.parseInt(jt.getText());
                String out = "·��������ʱ���趨Ϊ��" + t + "�룡";
                lan.setRouterUpdateTime(t);
                JOptionPane.showMessageDialog(null, out, "��ʾ", JOptionPane.INFORMATION_MESSAGE);
                System.out.printf("·��������ʱ���趨Ϊ��%d�룡\n", t);
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
    public P2(CardLayout cardLayout, JPanel panel) {
        setLayout(null);

        JComboBox<String> jc = new JComboBox<>();
        jc.setBounds(80, 70, 100, 20);
        jc.addItem("��ѡ��");
        jc.addItem("�������");
        jc.addItem("�����˳�");
        jc.addItem("·��������");
        jc.addItem("·�����޸�");

        JButton jb_select = new JButton("ѡ��");
        jb_select.setBounds(240, 70, 80, 20);
        jb_select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String op = (String)jc.getSelectedItem();
                if(op.equals("�������")) {
                    cardLayout.show(panel, "p3");
                }
                else if(op.equals("�����˳�")) {

                }
                else if(op.equals("·��������")) {

                }
                else if(op.equals("·�����޸�")) {

                }
            }
        });

        this.add(jc);
        this.add(jb_select);
    }
}

class P3 extends JPanel {
    public P3(LAN lan) {
        setLayout(null);

        JLabel jl_inputNetworkName = new JLabel("������Ҫ������������ƣ�");
        jl_inputNetworkName.setFont(new Font("΢���ź�", Font.BOLD, 10));
        jl_inputNetworkName.setBounds(130, 50, 140, 20);

        JTextField jt_inputNetworkName = new JTextField();
        jt_inputNetworkName.setBounds(140, 70, 120, 20);

        JLabel jl_selectRouter = new JLabel("�빴ѡ���ڵ�·������");
        jl_selectRouter.setFont(new Font("΢���ź�", Font.BOLD, 10));
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

        JButton jb_add = new JButton("���");
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
                    JOptionPane.showMessageDialog(null, "��ѡ������·������", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    lan.addNetwork(networkName, adjRouterList);
                }
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


public class MyFrame extends JFrame {
    CardLayout cardLayout = new CardLayout();

    public MyFrame(LAN lan) {
        super("RIPЭ��ģ�����");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(cardLayout);
        panel.setBounds(0, 0, 400, 300);
        setContentPane(panel);

        P1 p1 = new P1(lan, cardLayout, panel);
        panel.add(p1, "p1");
        P2 p2 = new P2(cardLayout, panel);
        panel.add(p2, "p2");
        P3 p3 = new P3(lan);
        panel.add(p3, "p3");
    }
}
