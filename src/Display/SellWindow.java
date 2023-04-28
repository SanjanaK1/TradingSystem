import javax.swing.*;
import java.awt.*;

public class SellWindow {
    private JComboBox comboBox1;
    private JSpinner spinner1;
    private JButton sellButton;
    private JButton cancelButton;
    JPanel panel;
    private JTable stockTable;
    private JButton refreshTableButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("SellWindow");
        frame.setContentPane(new SellWindow().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public SellWindow(){
        panel.setPreferredSize(new Dimension(500,400));
    }
}
