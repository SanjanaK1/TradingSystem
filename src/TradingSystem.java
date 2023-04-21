import src.LoginWindow;

import src.LoginWindow;

import java.awt.*;

public class TradingSystem {

    public static void main(String args[]){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginWindow().setVisible(true);
            }
        });
    }


}
