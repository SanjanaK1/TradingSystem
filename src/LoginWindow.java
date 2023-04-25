import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginWindow extends JFrame {
    JTextField userTextField;
    JTextField passwordTextField;
    JButton loginButton;
    JLabel userLabel;
    JLabel pwLabel;
    JLabel iconLabel;
    public LoginWindow(){
        initComponents();
    }
    private void initComponents(){
        userTextField = new JTextField(25);
        userTextField.setMinimumSize(new java.awt.Dimension(100, 20));
        passwordTextField = new JPasswordField(25);
        passwordTextField.setMinimumSize(new java.awt.Dimension(100, 20));
        loginButton = new JButton("Log In");//creating instance of JButton
        userLabel = new JLabel("Username:");
        pwLabel = new JLabel("Password:");
        ImageIcon loginIcon = IconCreator.createImageIcon("Heptakaidecahedron_example.gif", "Heptakaidecahedron example");
        iconLabel = new JLabel("This is the login screen", loginIcon, JLabel.CENTER);

        loginButton.addActionListener(new ActionListener() {
                                          public void actionPerformed(ActionEvent evt) {
                                              convertButtonActionPerformed(evt);
                                          }
                                      });

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

    private void convertButtonActionPerformed(ActionEvent evt) {
        String username = userTextField.getText().trim();
        String password = passwordTextField.getText();
        Security.login(username, password);
    }
}
