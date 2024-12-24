import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.management.timer.Timer;
import javax.swing.*;
import javax.swing.border.Border;

public class Shop implements ActionListener {
    JFrame shopFrame = new JFrame();
    JPanel shopPanel = new JPanel();
    JLabel title = new JLabel("Shop");
    JButton exitButton = new JButton("Exit");
    JButton purchase1 = new JButton();
    JButton purchase2 = new JButton();
    JButton purchase3 = new JButton();
    long price1 = 999999999;
    long price2 = 999999999;
    long price3 = 999999999;
    int healthItemsIndex = 0;
    int attackItemsIndex = 0;
    int movementItemsIndex = 0;

    public Shop() {
        //Setting up shop GUI when opened by player
        Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        shopPanel.setBackground(Color.darkGray);
        shopPanel.setLayout(null);

        title.setBounds(center.x-75, 50, 200, 200);
        title.setFont(new Font("Serif", Font.PLAIN, 78));
        title.setForeground(Color.WHITE);

        exitButton.setBounds(center.x-125, 600, 250, 100);
        purchase1.setBounds(center.x-400, center.y-100, 200, 200);
        purchase2.setBounds(center.x-100, center.y-100, 200, 200);
        purchase3.setBounds(center.x+200, center.y-100, 200, 200);

        exitButton.setBackground(Color.WHITE);
        purchase1.setBackground(Color.ORANGE);
        purchase2.setBackground(Color.ORANGE);
        purchase3.setBackground(Color.ORANGE);

        Border border = BorderFactory.createLineBorder(Color.white);
        exitButton.setBorder(border);
        purchase1.setBorder(border);
        purchase2.setBorder(border);
        purchase3.setBorder(border);

        exitButton.addActionListener(this);
        purchase1.addActionListener(this);
        purchase2.addActionListener(this);
        purchase3.addActionListener(this);

        shopPanel.add(title);
        shopPanel.add(exitButton);
        shopPanel.add(purchase1);
        shopPanel.add(purchase2);
        shopPanel.add(purchase3);

        shopFrame.add(shopPanel);
        shopFrame.setUndecorated(true);
        shopFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        shopFrame.setSize(800, 800);
        shopFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        shopFrame.setVisible(false);
    }

    //Action Listener for when player hits buttons in shop
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitButton) {
            Game.openShop = false;
            shopFrame.dispose();
            purchase1.setBackground(Color.orange);
            purchase2.setBackground(Color.orange);
            purchase3.setBackground(Color.orange);
            purchase1.setText(null);
            purchase2.setText(null);
            purchase3.setText(null);
        }
        //Checks to see if the first purchase button is clicked
        if(e.getSource() == purchase1)
        {
            //Check if the player has enough points to afford item and stop them if they don't
            if(Game.points >= price1)
            {

            }
            else{
                purchase1.setBackground(Color.red);
                purchase1.setText("Cannot Buy!");                
            }
        }
        if(e.getSource() == purchase2)
        {
            if(Game.points >= price2)
            {

            }
            else{
                purchase2.setBackground(Color.red);
                purchase2.setText("Cannot Buy!");
            }
        }
        if(e.getSource() == purchase3)
        {
            if(Game.points >= price3)
            {

            }
            else{
                purchase3.setBackground(Color.red);
                purchase3.setText("Cannot Buy!");
            }
        }
    }
    public void randomItems()
    {
        healthItemsIndex = (int) (Math.random()*3)+1;
        attackItemsIndex = (int) (Math.random()*3)+1;
        movementItemsIndex = (int) (Math.random()*3)+1;
    }
}
