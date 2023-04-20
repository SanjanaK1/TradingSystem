package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginWindow extends JFrame implements ActionListener {
    public LoginWindow(){
        initComponents();
    }
    private void initComponents(){
        JButton loginInUser = new JButton("Log In as User");//creating instance of JButton
        JLabel loginInUserLabel = new JLabel("User");
        JButton loginInPM = new JButton("Log In as Portfolio Manager");
        JLabel loginInPMLabel = new JLabel("Password");



        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(loginInUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(loginInUserLabel))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(loginInPM)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(loginInPMLabel)))
                                .addContainerGap(27, Short.MAX_VALUE))
        );

        layout.linkSize(SwingConstants.HORIZONTAL, loginInUser, loginInPM);

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(loginInUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(loginInUserLabel))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(loginInPM)
                                        .addComponent(loginInPMLabel))
                                .addContainerGap(21, Short.MAX_VALUE))
        );
        pack();
//
//        loginInUser.setBounds(250,300,200, 40);//x axis, y axis, width, height
//        add(loginInUser);
//
//        loginInPM.setBounds(230,350,250, 40);//x axis, y axis, width, height
//        add(loginInPM);

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
