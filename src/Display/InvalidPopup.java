import javax.swing.*;
import java.awt.event.*;

public class InvalidPopup extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton button3;
    private JLabel label1;

    public InvalidPopup() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        label1.setIcon(IconCreator.createImageIcon("rsc/uhoh2.gif", "Something went wrong"));

        buttonOK.addActionListener(e -> {onCancel();});

        buttonCancel.addActionListener(e -> {onCancel();});

        button3.addActionListener(e -> {onCancel();});

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onCancel() {
        // add your code here
        dispose();
    }

    public static void main(String[] args) {
        InvalidPopup dialog = new InvalidPopup();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
