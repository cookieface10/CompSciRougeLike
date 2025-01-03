import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
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
    int randomTieBreaker = (int) (Math.random() * 6 + 1);
    String itemTitle1 = ab.randomSelectItemTitles(random1);
    String itemTitle2 = ab.randomSelectItemTitles(random2);
    String itemTitle3 = ab.randomSelectItemTitles(random3);
    public boolean resetOnce;
    Timer resetDelay;

    public Shop() {
        // Checks to see if fire shot and ice shot were randomly selected at the same
        // time on first run
        // If both ice shot and fire shot are options the player rolls, do another
        // reroll tie breaker
        // and set it equal to either only ice shot or fire shot
        if ((random1 == 7 && random2 == 6) || (random1 == 6 && random2 == 7)) {
            random1 = randomTieBreaker;
            random2 = randomTieBreaker;
            itemTitle1 = ab.randomSelectItemTitles(random1);
            itemTitle2 = ab.randomSelectItemTitles(random1);

        }
        if ((random2 == 7 && random3 == 6) || (random2 == 6 && random3 == 7)) {
            random2 = randomTieBreaker;
            random3 = randomTieBreaker;
            itemTitle2 = ab.randomSelectItemTitles(random2);
            itemTitle3 = ab.randomSelectItemTitles(random3);
        }
        if ((random1 == 7 && random3 == 6) || (random1 == 6 && random3 == 7)) {
            random1 = randomTieBreaker;
            random3 = randomTieBreaker;
            itemTitle1 = ab.randomSelectItemTitles(random2);
            itemTitle3 = ab.randomSelectItemTitles(random3);
        }

        // Setting up shop UI when opened by player and activiating keylistener for exit
        // shop button
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
        shopFrame.setFocusTraversalKeysEnabled(false);
        shopFrame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                // Press tab button to close the shop menu and unpause game
                if (key == 9) {
                    // Set the shop to not open in the Game class unfreezing player and enemies
                    Game.openShop = false;
                    // Gets rid of the frame
                    shopFrame.dispose();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

    }

    // Action Listener for when player hits buttons in shop
    public void actionPerformed(ActionEvent e) {
        // Checks to see if the first purchase button is clicked
        if (e.getSource() == item1) {
            // Calls method that takes the randomly selected item information as arguements
            buttonAction(ab.itemPrice(random1), itemTitle1, item1, random1);
            shopFrame.requestFocusInWindow();
        }
        // Same code as actionlistener for item button #1
        if (e.getSource() == item2) {
            // Calls method that takes the randomly selected item information as arguements
            buttonAction(ab.itemPrice(random2), itemTitle2, item2, random2);
            shopFrame.requestFocusInWindow();
        }
        // Same code as actionlistener for item button #1
        if (e.getSource() == item3) {
            // Calls method that takes the randomly selected item information as arguements
            buttonAction(ab.itemPrice(random3), itemTitle3, item3, random3);
            shopFrame.requestFocusInWindow();
        }
        // Checks to see if the player hits the exit button
        if (e.getSource() == closeButton) {
            // Set the shop to not open in the Game class unfreezing player and enemies
            Game.openShop = false;
            // Gets rid of the frame
            shopFrame.dispose();
        }
    }

    public void buttonAction(long price, String itemTitle, JButton item, int random) {
        // Check if the player has enough points to afford item and stops them if they
        // don't
        if (Game.points >= price) {
            // Finds which of the 7 abilities was randomly choosen and then activates it
            // when purchased
            if (random == 1) {
                ab.heal();
                ab.priceIncrease1 += 0.5;
            } else if (random == 2) {
                ab.attackIncrease();
                ab.priceIncrease2 += 0.5;
            } else if (random == 3) {
                ab.movementIncrease();
                ab.priceIncrease3 += 0.5;
            } else if (random == 4) {
                ab.increaseHealth();
                ab.priceIncrease4 += 0.5;
            } else if (random == 5) {
                ab.bulletSpeedIncrease();
                ab.priceIncrease5 += 0.5;
            } else if (random == 6) {
                ab.iceShot();
                ab.priceIncrease6 += 0.5;
                ab.iceShotTimesBought++;
            } else if (random == 7) {
                ab.fireShot();
                ab.priceIncrease7 += 0.5;
                ab.fireShotTimesBought++;
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
            resetOnce = false;
            resetDelay = new Timer(2000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    reset(itemTitle, item, random);
                    resetOnce = true;
                    if (resetOnce) {
                        resetDelay.stop();
                    }
                }
            });
            // Starts the timer delay
            resetDelay.start();

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

    public void reset(String itemTitle, JButton item, int random) {
        // Reactivates buttons so they can be used
        item.setEnabled(true);
        // Reselecting a new set of items for sale
        random = (int) (Math.random() * 7) + 1;
        // Checks to see if fire shot or ice shot has been activitated
        // If fire shot is true, then skip any ice shot buy options and change it to
        // fire shot
        if (Game.shop.ab.fireShotEnabled == true) {
            if (random == 6) {
                random = 7;
            }
        }
        // Otherwise if ice shot is true, then skip any fire shot buy options and change
        // it to ice shot
        if (Game.shop.ab.iceShotEnabled == true) {
            if (random == 7) {
                random = 6;
            }
        }
        // Resets all of the buttons with new UI after
        itemTitle = ab.randomSelectItemTitles(random);
        item.setText(itemTitle);
        item.setBackground(Color.WHITE);

    }
}
