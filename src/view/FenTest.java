package view;

import javax.swing.JFrame;

public class FenTest extends JFrame {
    public FenTest() {
        super("Ma Fenêtre");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new  FenTest().setVisible(true);
    }
}
