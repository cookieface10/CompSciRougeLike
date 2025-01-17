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
    JLabel finalTime = new JLabel();

    public EndScreen() {
        // Setting up GUI of death screen that player sees when they lose
        endPanel.setBackground(Color.black);
        endPanel.setLayout(null);

        endMessage.setBounds(340, 100, 200, 50);
        endMessage.setFont(new Font("Serif", Font.ROMAN_BASELINE, 30));
        endMessage.setForeground(Color.red);

        finalTime.setBounds(350, 150, 200, 50);
        finalTime.setFont(new Font("Serif", Font.ROMAN_BASELINE, 20));
        finalTime.setForeground(Color.white);

        restartButton.setBounds(350, 250, 100, 50);
        quitButton.setBounds(350, 310, 100, 50);

        restartButton.addActionListener(this);
        quitButton.addActionListener(this);

        endPanel.add(restartButton);
        endPanel.add(quitButton);
        endPanel.add(finalTime);
        endPanel.add(endMessage);

        endFrame.add(endPanel);
        endFrame.setUndecorated(true);
        endFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        endFrame.setSize(800, 400);
        endFrame.setLocationRelativeTo(null);
        endFrame.setVisible(false);
    }

    public void actionPerformed(ActionEvent e) {
        // If the player hits the restart button, set their health back to original
        // state and call reset method
        if (e.getSource() == restartButton) {
            Game.playerHealth = 20;
            reset();
        }
        // If player hits the quit button, close the thread and stop the game from
        // running
        if (e.getSource() == quitButton) {
            System.exit(0);
        }
    }

    // Method for setting all game variables and frames back to their original
    // states as if the game was open for the first time
    public void reset() {
        endFrame.setVisible(false);
        Game.startScreen.startFrame.setVisible(true);
        Game.points = 0;
        Game.playerMaxHealth = 20;
        Game.up = false;
        Game.down = false;
        Game.right = false;
        Game.left = false;
        Game.shop.ab.fireShotEnabled = false;
        Game.shop.ab.iceShotEnabled = false;
        Game.characterPosX = Game.center.x - 25;
        Game.characterPosY = Game.center.y - 25;
        Game.WorldPosX = Game.center.x;
        Game.WorldPosY = 0;
        Game.enemys = new ArrayList<>();
        Game.bullets = new ArrayList<>();
        Game.pointOrbs = new ArrayList<>();
        Game.gameTime = 0;
        Game.spawnTime = 500;
        Game.damageBoost = 0;
        Game.speedBoost = 0;
        Game.speed = 5;
        Game.shop.ab.bulletDelay = 25;
        Game.shop.ab.fireShotTimesBought = 0;
        Game.shop.ab.iceShotTimesBought = 0;
        Game.shop.ab.pierceTimesBought = 0;
        Game.shop.ab.priceIncrease1 = 1.00;
        Game.shop.ab.priceIncrease2 = 1.00;
        Game.shop.ab.priceIncrease3 = 1.00;
        Game.shop.ab.priceIncrease4 = 1.00;
        Game.shop.ab.priceIncrease5 = 1.00;
        Game.shop.ab.priceIncrease6 = 1.00;
        Game.shop.ab.priceIncrease7 = 1.00;
        Game.shop.ab.priceIncrease8 = 1.00;
        Game.shop.ab.priceIncrease9 = 1.00;
        Game.shop.random1 = (int) (Math.random() * 9) + 1;
        Game.shop.random2 = (int) (Math.random() * 9) + 1;
        Game.shop.random3 = (int) (Math.random() * 9) + 1;
        Game.shop.itemTitle1 = Game.shop.ab.randomSelectItemTitles(Game.shop.random1);
        Game.shop.itemTitle2 = Game.shop.ab.randomSelectItemTitles(Game.shop.random2);
        Game.shop.itemTitle3 = Game.shop.ab.randomSelectItemTitles(Game.shop.random3);
        Game.shop.itemPrice1 = Game.shop.ab.itemPrice(Game.shop.random1);
        Game.shop.itemPrice2 = Game.shop.ab.itemPrice(Game.shop.random2);
        Game.shop.itemPrice3 = Game.shop.ab.itemPrice(Game.shop.random3);
        Game.shop.item1.setText(Game.shop.itemTitle1);
        Game.shop.item2.setText(Game.shop.itemTitle2);
        Game.shop.item3.setText(Game.shop.itemTitle3);
        Game.shop.price1.setText(String.valueOf(Game.shop.itemPrice1));
        Game.shop.price2.setText(String.valueOf(Game.shop.itemPrice2));
        Game.shop.price3.setText(String.valueOf(Game.shop.itemPrice3));

    }
}
