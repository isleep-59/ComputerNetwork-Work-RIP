import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        LAN lan = new LAN();
        MyFrame mf = new MyFrame(lan);
        mf.setVisible(true);
    }
}
