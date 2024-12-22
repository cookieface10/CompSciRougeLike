import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Shop implements ActionListener {
    JFrame shopFrame = new JFrame();
    JPanel shopPanel = new JPanel(new BorderLayout());
    JButton exitButton = new JButton("Exit");

    public Shop() {
        shopPanel.setBackground(Color.black);
        shopPanel.add(exitButton, BorderLayout.SOUTH);
        exitButton.addActionListener(this);
        shopFrame.add(shopPanel);
        shopFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        shopFrame.setSize(800, 800);
        shopFrame.setVisible(false);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitButton) {
            Game.openShop = false;
            shopFrame.dispose();
        }
    }
}