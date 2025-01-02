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
    int random1 = (int) (Math.random() * 7) + 1;
    int random2 = (int) (Math.random() * 7) + 1;
    int random3 = (int) (Math.random() * 7) + 1;
    String itemTitle1 = ab.randomSelectItemTitles(random1);
    String itemTitle2 = ab.randomSelectItemTitles(random2);
    String itemTitle3 = ab.randomSelectItemTitles(random3);

    int itemsBought = 0;

    // Dimension getScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
    // int height = (int) getScreenSize.getHeight();

    public Shop() {
        // Setting up shop UI when opened by player
        Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        shopPanel.setBackground(Color.darkGray);
        shopPanel.setLayout(null);

        title.setBounds(center.x - 75, 50, 200, 200);
        title.setFont(new Font("Serif", Font.PLAIN, 78));
        title.setForeground(Color.WHITE);

        item1.setBounds(center.x - 400, center.y - 100, 200, 200);
        item2.setBounds(center.x - 100, center.y - 100, 200, 200);
        item3.setBounds(center.x + 200, center.y - 100, 200, 200);
        closeButton.setBounds(center.x - 125, center.y + 200, 250, 100);

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

    // Action Listener for when player hits buttons in shop
    public void actionPerformed(ActionEvent e) {
        // Checks to see if the first purchase button is clicked
        if (e.getSource() == item1) {
            // Calls method that takes the randomly selected item information as arguements
            buttonAction(ab.itemPrice(random1), itemTitle1, item1, random1);
        }
        // Same code as actionlistener for item button #1
        if (e.getSource() == item2) {
            // Calls method that takes the randomly selected item information as arguements
            buttonAction(ab.itemPrice(random2), itemTitle2, item2, random2);
        }
        // Same code as actionlistener for item button #1
        if (e.getSource() == item3) {
            // Calls method that takes the randomly selected item information as arguements
            buttonAction(ab.itemPrice(random3), itemTitle3, item3, random3);
        }
        // Checks to see if the player hits the exit button
        if (e.getSource() == closeButton) {
            // If all of the items have been bought in the shop call for a reset on the
            // choices
            if (itemsBought >= 3) {
                reset();
            }
            // Set the shop to not open in the Game class unfreezing player and enemies
            Game.openShop = false;
            // Gets rid of the frame
            shopFrame.dispose();
        }
    }

    // Resets all of the buttons with new UI after reselecting a new set of items
    // for sale
    // Reactivates buttons so they can be used and sets number of items bought to 0
    public void reset() {
        item1.setEnabled(true);
        item2.setEnabled(true);
        item3.setEnabled(true);
        random1 = (int) (Math.random() * 7) + 1;
        random2 = (int) (Math.random() * 7) + 1;
        random3 = (int) (Math.random() * 7) + 1;
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

    public void buttonAction(long price, String itemTitle, JButton item, int random) {
        // Check if the player has enough points to afford item and stops them if they
        // don't
        if (Game.points >= price) {
            // Finds which of the 7 abilities was randomly choosen and then activates it
            // when purchased
            if (random == 1) {
                ab.heal();
            } else if (random == 2) {
                ab.attackIncrease();
            } else if (random == 3) {
                ab.movementIncrease();
            } else if (random == 4) {
                ab.increaseHealth();
            } else if (random == 5) {
                ab.bulletSpeedIncrease();
            } else if (random == 6) {
                ab.iceShot();
            } else if (random == 7) {
                ab.fireShot();
            }

            /*
             * - UI displays that the player has succesfully made a purchase
             * - Disables the button so they cannot buy it again
             * - Removes the points from their total
             * - Adds +1 to the items registered as bought
             */
            item.setBackground(Color.green);
            item.setText("Purchased");
            item.setEnabled(false);
            Game.points -= price;
            itemsBought++;
        } else {
            // Changes the UI to signify a player cannot buy anything
            item.setBackground(Color.red);
            item.setText("Cannot Buy!");
            // Returns to UI to the original state after 2 seconds using an Anynomous method
            // as an arguement in an instance of the Timer class
            Timer timer = new Timer(2000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    item.setBackground(Color.white);
                    item.setText(itemTitle);
                }
            });
            // Starts the timer delay
            timer.start();
        }
    }
}
