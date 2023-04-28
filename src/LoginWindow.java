package src;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class LoginWindow extends JFrame {
    JTextField userTextField;
    JTextField passwordTextField;
    JButton loginButton, createAccountButton;
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
        createAccountButton = new JButton("Create Account");
        userLabel = new JLabel("Username:");
        pwLabel = new JLabel("Password:");
        ImageIcon loginIcon = IconCreator.createImageIcon("rsc/Heptakaidecahedron_example.gif", "Heptakaidecahedron example");
        iconLabel = new JLabel("This is the login screen", loginIcon, JLabel.CENTER);

        loginButton.addActionListener(new ActionListener() {
                                          public void actionPerformed(ActionEvent evt) {
                                              convertLoginButtonActionPerformed(evt);
                                          }
                                      });
        createAccountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                convertAccountButtonActionPerformed(evt);
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
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(loginButton)
                                .addComponent(createAccountButton))
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
                                        .addComponent(loginButton)
                                        .addComponent(createAccountButton)))
        );
        pack();

        setSize(700,500);
    }

    private void convertAccountButtonActionPerformed(ActionEvent evt) throws IOException {
        String username = userTextField.getText().trim();
        String password = passwordTextField.getText();
        if (Security.login(username, password)) {
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
    }

    private void convertLoginButtonActionPerformed(ActionEvent evt) throws IOException {
        String username = userTextField.getText().trim();
        String password = passwordTextField.getText();
        if (Security.createAccount(username, password)) {
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
    }
}
