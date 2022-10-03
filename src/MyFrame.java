import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//��ҳ
class P1 extends JPanel {
    public P1(LAN lan, CardLayout cardLayout, JPanel panel) {
        setLayout(null);

        JLabel jl = new JLabel("���趨����ʱ�䣺");
        jl.setBounds(100, 50, 120, 20);

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

//����ѡ��
class P2 extends JPanel {
    public P2(LAN lan, CardLayout cardLayout, JPanel panel) {
        setLayout(null);

        JLabel jl_function = new JLabel("��ѡ������Ҫʹ�õĹ��ܣ�");
        jl_function.setBounds(80, 40, 200, 20);

        JComboBox<String> jc_function = new JComboBox<>();
        jc_function.setBounds(80, 70, 100, 20);
        jc_function.addItem("��ѡ��");
        jc_function.addItem("�������");
        jc_function.addItem("�����˳�");
        jc_function.addItem("·��������");
        jc_function.addItem("·�����޸�");

        JButton jb_select = new JButton("ѡ��");
        jb_select.setBounds(240, 70, 80, 20);
        jb_select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String op = (String)jc_function.getSelectedItem();
                if(op.equals("�������")) {
                    cardLayout.show(panel, "p3");
                }
                else if(op.equals("�����˳�")) {
                    if(lan.getNetworkMap().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "�þ��������Ѿ�û�����磡", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
                    }
                    cardLayout.show(panel, "p4");
                }
                else if(op.equals("·��������")) {
                    if(lan.getWorkingRouterCount() == 0) {
                        JOptionPane.showMessageDialog(null, "�þ�������·������ȫ�����ϣ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
                    }
                    cardLayout.show(panel, "p5");
                }
                else if(op.equals("·�����޸�")) {
                    if(lan.getWorkingRouterCount() == 6) {
                        JOptionPane.showMessageDialog(null, "�þ�������·����ȫ���������У�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
                    }
                    cardLayout.show(panel, "p6");
                }
            }
        });

        JLabel jl_router = new JLabel("��ѡ��Ҫ�鿴·�ɱ��·������");
        jl_router.setBounds(80, 160, 200, 20);

        JComboBox<String> jc_router = new JComboBox<>();
        jc_router.setBounds(80, 190, 100, 20);
        jc_router.addItem("��ѡ��");
        for(String routerName : lan.getRouterMap().keySet()) {
            jc_router.addItem(routerName);
        }

        JButton jb_check = new JButton("�鿴");
        jb_check.setEnabled(false);
        jb_check.setBounds(240, 190, 80, 20);
        jb_check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String out = "Ŀ������           ����            ��һ��\n";
                String routerName = (String)jc_router.getSelectedItem();
                for(Information information : lan.getRouterMap().get(routerName).getInformationTable().values()) {
                    out += information.getTargetNetwork() + "           " + information.getDistance() + "                 " + information.getNextRouterName() + "\n";
                }
                JOptionPane.showMessageDialog(null, out, routerName + "��·�ɱ�", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JButton jb_resume = new JButton("�ָ�����");
        JButton jb_pause = new JButton("��ͣ����");

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

//�������
class P3 extends JPanel {
    public P3(LAN lan, CardLayout cardLayout, JPanel panel) {
        setLayout(null);

        JLabel jl_inputNetworkName = new JLabel("������Ҫ������������ƣ�");
        jl_inputNetworkName.setBounds(130, 50, 140, 20);

        JTextField jt_inputNetworkName = new JTextField();
        jt_inputNetworkName.setBounds(140, 70, 120, 20);

        JLabel jl_selectRouter = new JLabel("�빴ѡ���ڵ�·������");
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
                    for(String routerName : routerMap.keySet()) {
                        routerMap.get(routerName).getInformationTable().put(networkName, new Information(networkName, 16, null));
                    }
                    lan.addNetwork(networkName, adjRouterList);
                }

                JOptionPane.showMessageDialog(null, "�ɹ�������磡", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
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

//�����˳�
class P4 extends JPanel {
    public P4(LAN lan, CardLayout cardLayout, JPanel panel) {
        setLayout(null);

        JLabel jl = new JLabel("��ѡ������Ҫ�˳������磺");
        jl.setBounds(80, 60, 140, 20);

        JComboBox<String> jc = new JComboBox<>();
        jc.setBounds(80, 90, 100, 20);
        jc.addItem("��ѡ��");
        for(String networkName : lan.getNetworkMap().keySet()) {
            jc.addItem(networkName);
        }

        JButton jb_select = new JButton("ѡ��");
        jb_select.setBounds(240, 90, 80, 20);
        jb_select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String networkName = (String)jc.getSelectedItem();
                lan.deleteNetwork(networkName);
                jc.removeItem(networkName);

                JOptionPane.showMessageDialog(null, "�ɹ��˳����磡", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
                cardLayout.show(panel, "p2");
            }
        });

        this.add(jl);
        this.add(jc);
        this.add(jb_select);
    }
}

//·��������
class P5 extends JPanel {
    public P5(LAN lan, CardLayout cardLayout, JPanel panel) {
        setLayout(null);

        JLabel jl = new JLabel("��ѡ�����г��ֹ��ϵ�·������");
        jl.setBounds(80, 60, 140, 20);

        JComboBox<String> jc = new JComboBox<>();
        jc.setBounds(80, 90, 100, 20);
        jc.addItem("��ѡ��");
        for(String routerName : lan.getRouterMap().keySet()) {
            if(lan.getRouterMap().get(routerName).getRouterStatus() == true) {
                jc.addItem(routerName);
            }
        }

        JButton jb_select = new JButton("ѡ��");
        jb_select.setBounds(240, 90, 80, 20);
        jb_select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String routerName = (String)jc.getSelectedItem();
                lan.routerFault(routerName);
                jc.removeItem(routerName);

                String out = routerName + "�������ϣ�";
                JOptionPane.showMessageDialog(null, out, "��ʾ", JOptionPane.INFORMATION_MESSAGE);
                cardLayout.show(panel, "p2");
            }
        });

        this.add(jl);
        this.add(jc);
        this.add(jb_select);
    }
}

//·�����ָ�
class P6 extends JPanel {
    public P6(LAN lan, CardLayout cardLayout, JPanel panel) {
        setLayout(null);

        JLabel jl = new JLabel("��ѡ������Ҫ�ָ���·������");
        jl.setBounds(80, 60, 140, 20);

        JComboBox<String> jc = new JComboBox<>();
        jc.setBounds(80, 90, 100, 20);
        jc.addItem("��ѡ��");
        for(String routerName : lan.getRouterMap().keySet()) {
            if(lan.getRouterMap().get(routerName).getRouterStatus() == false) {
                jc.addItem(routerName);
            }
        }

        JButton jb_select = new JButton("ѡ��");
        jb_select.setBounds(240, 90, 80, 20);
        jb_select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String routerName = (String)jc.getSelectedItem();
                lan.routerRecover(routerName);
                jc.removeItem(routerName);

                JOptionPane.showMessageDialog(null, "�ɹ��˳����磡", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
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
