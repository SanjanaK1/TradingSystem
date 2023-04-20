package src;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginWindow extends JFrame implements ActionListener {
    public LoginWindow(){
        initComponents();
    }
    private void initComponents(){
        JButton loginInUser = new JButton("Log In as User");//creating instance of JButton
        JButton loginInPM = new JButton("Log In as Portfolio Manager");
        loginInUser.setBounds(250,300,200, 40);//x axis, y axis, width, height
        add(loginInUser);

        loginInPM.setBounds(230,350,250, 40);//x axis, y axis, width, height
        add(loginInPM);

        setSize(700,700);
        setLayout(null);//using no layout managers
        setVisible(true);//making the frame visible
    }

    public void createWindow(){
        JFrame f = new JFrame();

        JButton loginInUser = new JButton("Log In as User");//creating instance of JButton
        JButton loginInPM = new JButton("Log In as Portfolio Manager");
        loginInUser.setBounds(250,300,200, 40);//x axis, y axis, width, height
        f.add(loginInUser);

        loginInPM.setBounds(230,350,250, 40);//x axis, y axis, width, height
        f.add(loginInPM);

        f.setSize(700,700);
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
