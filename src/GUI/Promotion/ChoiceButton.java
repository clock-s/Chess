package GUI.Promotion;

import javax.swing.*;
import java.awt.*;

public class ChoiceButton extends JButton {
    JLabel title;

    ChoiceButton(String title, ImageIcon icon) {
        setIcon(icon);

        setLayout(new BorderLayout());
        this.title = new JLabel(title, JLabel.CENTER);

        add(this.title, BorderLayout.SOUTH);
    }

}
