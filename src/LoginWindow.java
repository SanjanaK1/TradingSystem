import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginWindow extends JFrame implements ActionListener {
    public LoginWindow(){
        initComponents();
    }
    private void initComponents(){
        JTextField userTextField = new JTextField(25);
        JTextField passwordTextField = new JPasswordField(25);
        JButton loginButton = new JButton("Log In");//creating instance of JButton
        JLabel userLabel = new JLabel("Username:");
        JLabel pwLabel = new JLabel("Password:");
        ImageIcon loginIcon = IconCreator.createImageIcon("Heptakaidecahedron_example.gif", "Heptakaidecahedron example");
        JLabel iconLabel = new JLabel("This is the login screen", loginIcon, JLabel.CENTER);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setAutoCreateGaps(true);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(userLabel)
                                .addComponent(pwLabel))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(userTextField)
                                .addComponent(passwordTextField))
                        .addComponent(loginButton)
                        .addComponent(iconLabel)
                        .addContainerGap()
        );

        layout.linkSize(SwingConstants.VERTICAL, userLabel, userTextField);
        layout.linkSize(SwingConstants.VERTICAL, pwLabel, passwordTextField);

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(iconLabel)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(userLabel)
                                        .addComponent(pwLabel))
                                .addGroup(layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(userTextField)
                                        .addComponent(passwordTextField))
                                .addGroup(layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(loginButton)))
        );
        pack();

        setSize(700,500);
    }

    public void createWindow(){
        JFrame f = new JFrame();

        JButton loginInUser = new JButton("Log In as User");//creating instance of JButton
        JButton loginInPM = new JButton("Log In as Portfolio Manager");
        loginInUser.setBounds(250,300,200, 40);//x axis, y axis, width, height
        f.add(loginInUser);

        loginInPM.setBounds(230,350,250, 40);//x axis, y axis, width, height
        f.add(loginInPM);

        f.setSize(800,700);
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
