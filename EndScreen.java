import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EndScreen implements ActionListener {
    JFrame endFrame = new JFrame();
    JPanel endPanel = new JPanel();
    JButton restartButton = new JButton("Restart");
    JButton quitButton = new JButton("Quit");
    JLabel endMessage = new JLabel("You Died");

    public EndScreen() {
        endPanel.setBackground(Color.black);
        endPanel.setLayout(null);

        endMessage.setBounds(335, 150, 200, 50);
        endMessage.setFont(new Font("Serif", Font.ROMAN_BASELINE, 30));
        endMessage.setForeground(Color.red);

        restartButton.setBounds(350, 300, 100, 50);
        quitButton.setBounds(350, 350, 100, 50);

        restartButton.addActionListener(this);
        quitButton.addActionListener(this);

        endPanel.add(restartButton);
        endPanel.add(quitButton);
        endPanel.add(endMessage);

        endFrame.add(endPanel);
        endFrame.setUndecorated(true);
        endFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        endFrame.setSize(800, 400);
        endFrame.setLocationRelativeTo(null);
        endFrame.setVisible(false);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == restartButton) {
            Game.playerHealth = 20;
            reset();
        }
        if (e.getSource() == quitButton) {
            System.exit(0);
        }
    }

    public void reset() {
        endFrame.setVisible(false);
        Game.startScreen.startFrame.setVisible(true);
        Game.points = 0;
        Game.up = false;
        Game.down = false;
        Game.right = false;
        Game.left = false;
        Game.vertical = false;
        Game.horizontal = false;
        Game.shop.reset();
        Game.enemys = new ArrayList<>();
        Game.gameTime = 0;
        Game.spawnTime = 500;

    }
}
