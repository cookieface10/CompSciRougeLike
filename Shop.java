import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Shop implements ActionListener {
    JFrame shopFrame = new JFrame();
    JPanel shopPanel = new JPanel();
    Abillities ab = new Abillities();
    JLabel title = new JLabel("Shop");
    JButton closeButton = new JButton("Exit");
    JButton item1 = new JButton();
    JButton item2 = new JButton();
    JButton item3 = new JButton();

    int random1 = (int)(Math.random()*3)+1;
    int random2 = (int)(Math.random()*3)+1;
    int random3 = (int)(Math.random()*3)+1;
    String itemTitle1 = ab.randomSelectItemTitles(random1);
    String itemTitle2 = ab.randomSelectItemTitles(random2);
    String itemTitle3 = ab.randomSelectItemTitles(random3);
    
    int itemsBought = 0;

    long price1 = 1000;
    long price2 = 1000;
    long price3 = 1000;

    public Shop() {
        //Setting up shop UI when opened by player
        Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        shopPanel.setBackground(Color.darkGray);
        shopPanel.setLayout(null);

        title.setBounds(center.x-75, 50, 200, 200);
        title.setFont(new Font("Serif", Font.PLAIN, 78));
        title.setForeground(Color.WHITE);

        closeButton.setBounds(center.x-125, 600, 250, 100);
        item1.setBounds(center.x-400, center.y-100, 200, 200);
        item2.setBounds(center.x-100, center.y-100, 200, 200);
        item3.setBounds(center.x+200, center.y-100, 200, 200);

        item1.setText(itemTitle1);
        item2.setText(itemTitle2);
        item3.setText(itemTitle3);

        closeButton.setBackground(Color.WHITE);
        item1.setBackground(Color.WHITE);
        item2.setBackground(Color.WHITE);
        item3.setBackground(Color.WHITE);

        closeButton.addActionListener(this);
        item1.addActionListener(this);
        item2.addActionListener(this);
        item3.addActionListener(this);

        shopPanel.add(title);
        shopPanel.add(closeButton);
        shopPanel.add(item1);
        shopPanel.add(item2);
        shopPanel.add(item3);

        shopFrame.add(shopPanel);
        shopFrame.setUndecorated(true);
        shopFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        shopFrame.setSize(800, 800);
        shopFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        shopFrame.setVisible(false);
    }

    //Action Listener for when player hits buttons in shop
    public void actionPerformed(ActionEvent e) {
        //Checks to see if the player hits the exit button
        if (e.getSource() == closeButton) {
            //If all of the items have been bought in the shop call for a reset on the choices
            if(itemsBought >= 3)
            {
                reset();
            }
            //Set the shop to not open in the Game class unfreezing player and enemies
            Game.openShop = false;
            //Gets rid of the frame
            shopFrame.dispose();
        }
        //Checks to see if the first purchase button is clicked
        if(e.getSource() == item1)
        {
            //Check if the player has enough points to afford item and stops them if they don't
            if(Game.points >= price1)
            {
                //Finds what ability was randomly choosen and then activating once purchased
                if(random1 == 1)
                {
                    ab.heal();
                }
                else if(random1 == 2)
                {
                    ab.attackIncrease();
                }
                else if(random1 == 3)
                {
                    ab.movementIncrease();
                }
                /*
                 - UI displays that the player has succesfully made a purchase
                - Disables the button so they cannot buy it again
                 - Removes the points from their total 
                 - Adds +1 to the items registered as bought
                */
                item1.setBackground(Color.green);
                item1.setText("Purchased");
                item1.setEnabled(false);
                Game.points -= price1;
                itemsBought++;
            }
            else{
                //Changes the UI to signify a player cannot buy anything
                item1.setBackground(Color.red);
                item1.setText("Cannot Buy!"); 
                //Returns to UI to the original state after 2 seconds
                Timer timer = new Timer(2000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        item1.setBackground(Color.white);
                        item1.setText(itemTitle1); 
                    }
                });
                timer.start();
            }
        }
        //Same code as actionlistener for item button #1
        if(e.getSource() == item2)
        {
            if(Game.points >= price2)
            {
                if(random2 == 1)
                {
                    ab.heal();
                }
                else if(random2 == 2)
                {
                    ab.attackIncrease();
                }
                else if(random2 == 3)
                {
                    ab.movementIncrease();
                }
                item2.setBackground(Color.green);
                item2.setText("Purchased");
                item2.setEnabled(false);
                Game.points -= price2;
                itemsBought++;
            }
            else{
                item2.setBackground(Color.red);
                item2.setText("Cannot Buy!");
                Timer timer = new Timer(2000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        item2.setBackground(Color.white);
                        item2.setText(itemTitle2); 
                    }
                });
                timer.start();
            }
        }
        //Same code as actionlistener for item button #1
        if(e.getSource() == item3)
        {
            if(Game.points >= price3)
            {
                if(random1 == 1)
                {
                    ab.heal();
                }
                else if(random1 == 2)
                {
                    ab.attackIncrease();
                }
                else if(random1 == 3)
                {
                    ab.movementIncrease();
                }
                item3.setBackground(Color.green);
                item3.setText("Purchased");
                item3.setEnabled(false);
                Game.points -= price3;      
                itemsBought++;
            }
            else{
                item3.setBackground(Color.red);
                item3.setText("Cannot Buy!");
                Timer timer = new Timer(2000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        item3.setBackground(Color.white);
                        item3.setText(itemTitle3); 
                    }
                });
                timer.start();
            }
        }
    }
    //Resets all of the buttons with new UI after reselecting a new set of items for sale
    //Reactivates buttons so they can be used and sets number of items bought to 0
    public void reset()
    {
        item1.setEnabled(true);
        item2.setEnabled(true);
        item3.setEnabled(true);
        random1 = (int)(Math.random()*3)+1;
        random2 = (int)(Math.random()*3)+1;
        random3 = (int)(Math.random()*3)+1;
        itemTitle1 = ab.randomSelectItemTitles(random1);
        itemTitle2 = ab.randomSelectItemTitles(random2);
        itemTitle3 = ab.randomSelectItemTitles(random3);
        item1.setText(itemTitle1);
        item2.setText(itemTitle2);
        item3.setText(itemTitle3);
        item1.setBackground(Color.WHITE);
        item2.setBackground(Color.WHITE);
        item3.setBackground(Color.WHITE);
        itemsBought = 0;
    }
}
