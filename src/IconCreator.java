package src;

import javax.swing.*;

public class IconCreator {
    static ImageIcon createImageIcon(String path,
                                     String description) {
        if (path != null) {
            return new ImageIcon(path, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}
