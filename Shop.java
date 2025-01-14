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
    JLabel price1 = new JLabel();
    JLabel price2 = new JLabel();
    JLabel price3 = new JLabel();
    JButton closeButton = new JButton("Exit");
    JButton item1 = new JButton();
    JButton item2 = new JButton();
    JButton item3 = new JButton();
    JLabel numberOfPoints = new JLabel();
    int random1 = (int) (Math.random() * 9) + 1;
    int random2 = (int) (Math.random() * 9) + 1;
    int random3 = (int) (Math.random() * 9) + 1;
    int randomTieBreaker = (int) (Math.random() * 6 + 1);
    int itemPrice1 = ab.itemPrice(random1);
    int itemPrice2 = ab.itemPrice(random2);
    int itemPrice3 = ab.itemPrice(random3);
    String itemTitle1 = ab.randomSelectItemTitles(random1);
    String itemTitle2 = ab.randomSelectItemTitles(random2);
    String itemTitle3 = ab.randomSelectItemTitles(random3);
    public boolean resetOnceItem1;
    public boolean resetOnceItem2;
    public boolean resetOnceItem3;
    Timer resetDelay1;
    Timer resetDelay2;
    Timer resetDelay3;

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
        shopPanel.setBackground(new Color(161, 102, 47));
        shopPanel.setLayout(null);

        title.setBounds(center.x - 75, 50, 200, 200);
        title.setFont(new Font("Serif", Font.PLAIN, 78));
        title.setForeground(Color.WHITE);

        numberOfPoints.setBounds(100, 50, 1000, 200);
        numberOfPoints.setFont(new Font("Serif", Font.PLAIN, 50));
        numberOfPoints.setForeground(Color.white);

        price1.setBounds(center.x - 330, center.y - 50, 200, 200);
        price1.setFont(new Font("Serif", Font.PLAIN, 20));
        price1.setForeground(Color.WHITE);

        price2.setBounds(center.x - 30, center.y - 50, 200, 200);
        price2.setFont(new Font("Serif", Font.PLAIN, 20));
        price2.setForeground(Color.WHITE);

        price3.setBounds(center.x + 275, center.y - 50, 200, 200);
        price3.setFont(new Font("Serif", Font.PLAIN, 20));
        price3.setForeground(Color.WHITE);

        item1.setBounds(center.x - 400, center.y - 200, 200, 200);
        item2.setBounds(center.x - 100, center.y - 200, 200, 200);
        item3.setBounds(center.x + 200, center.y - 200, 200, 200);
        closeButton.setBounds(center.x - 125, center.y + 200, 250, 100);

        item1.setText(itemTitle1);
        item2.setText(itemTitle2);
        item3.setText(itemTitle3);

        price1.setText(String.valueOf(itemPrice1));
        price2.setText(String.valueOf(itemPrice2));
        price3.setText(String.valueOf(itemPrice3));

        item1.setBackground(Color.WHITE);
        item2.setBackground(Color.WHITE);
        item3.setBackground(Color.WHITE);
        closeButton.setBackground(Color.WHITE);

        closeButton.addActionListener(this);
        item1.addActionListener(this);
        item2.addActionListener(this);
        item3.addActionListener(this);

        shopPanel.add(title);
        shopPanel.add(numberOfPoints);
        shopPanel.add(closeButton);
        shopPanel.add(item1);
        shopPanel.add(item2);
        shopPanel.add(item3);
        shopPanel.add(price1);
        shopPanel.add(price2);
        shopPanel.add(price3);

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
            buttonAction(itemPrice1, itemTitle1, item1, random1, 1);
            shopFrame.requestFocusInWindow();
        }
        // Same code as actionlistener for item button #1
        if (e.getSource() == item2) {
            // Calls method that takes the randomly selected item information as arguements
            buttonAction(itemPrice2, itemTitle2, item2, random2, 2);
            shopFrame.requestFocusInWindow();
        }
        // Same code as actionlistener for item button #1
        if (e.getSource() == item3) {
            // Calls method that takes the randomly selected item information as arguements
            buttonAction(itemPrice3, itemTitle3, item3, random3, 3);
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

    public void buttonAction(int price, String itemTitle, JButton item, int random, int buttonNumber) {
        // Check if the player has enough points to afford item and stops them if they
        // don't
        if (Game.points >= price) {
            // Finds which of the 7 abilities was randomly choosen and then activates it
            // when purchased, increasing the cost for the next time it is put into the shop
            switch (random) {
                case 1:
                    ab.heal();
                    ab.priceIncrease1 += 0.1;
                    break;
                case 2:
                    ab.attackIncrease();
                    ab.priceIncrease2 += 0.1;
                    break;
                case 3:
                    ab.movementIncrease();
                    ab.priceIncrease3 += 0.1;
                    break;
                case 4:
                    ab.increaseHealth();
                    ab.priceIncrease4 += 0.1;
                    break;
                case 5:
                    ab.bulletSpeedIncrease();
                    ab.priceIncrease5 += 0.1;
                    break;
                case 6:
                    ab.iceShot();
                    ab.priceIncrease6 += 0.1;
                    break;
                case 7:
                    ab.fireShot();
                    ab.priceIncrease7 += 0.1;
                    break;
                case 8:
                    ab.reduceBulletDelay();
                    ab.priceIncrease8 += 0.1;
                    break;
                case 9:
                    ab.bulletPierce();
                    ab.priceIncrease9 += 0.1;
                    break;
                default:
                    break;
            }
            /*
             * - UI displays that the player has succesfully made a purchase
             * - Disables the button so they cannot buy it again
             * - Removes the points from their total and displays new balance in shop
             */
            item.setBackground(Color.green);
            item.setText("Purchased");
            item.setEnabled(false);
            Game.points -= price;
            numberOfPoints.setText(String.valueOf(Game.points));
            if (random2 == random) {
                price2.setText(String.valueOf(ab.itemPrice(random)));
            }
            if (random3 == random) {
                price3.setText(String.valueOf(ab.itemPrice(random)));
            }
            // If button 1 has been clicked, run it's reset and button delay
            if (buttonNumber == 1) {
                // The button has not been reset once yet
                resetOnceItem1 = false;
                // Button #1 delay, passes the time in milliseconds and instance of
                // actionlistener class to reset shop item #1
                resetDelay1 = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Calls reset method with the Jbutton, title, corresponding random value, which
                        // button is reset, price tag and price tage Jlabel
                        reset(random, buttonNumber);
                        // The button has now been reset once
                        resetOnceItem1 = true;
                        // If the button is reset, stop the timer from repeating the action performed
                        if (resetOnceItem1) {
                            resetDelay1.stop();
                        }
                    }
                });
                // Starts the timer delay
                resetDelay1.start();
            }
            // If button 2 has been clicked, run it's reset and button delay
            if (buttonNumber == 2) {
                // The button has not been reset once yet
                resetOnceItem2 = false;
                // Button #2 delay, passes the time in milliseconds and instance of
                // actionlistener class to reset shop item #2
                resetDelay2 = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Calls reset method with the Jbutton, title, corresponding random value, which
                        // button is reset, price tag and price tage Jlabel
                        reset(random, buttonNumber);
                        // The button has now been reset once
                        resetOnceItem2 = true;
                        // If the button is reset, stop the timer from repeating the action
                        if (resetOnceItem2) {
                            resetDelay2.stop();
                        }
                    }
                });
                // Starts the timer delay
                resetDelay2.start();
            }
            // If button 2 has been clicked, run it's reset and button delay
            if (buttonNumber == 3) {
                // The button has not been reset once yet
                resetOnceItem3 = false;
                // Button #3 delay, passes the time in milliseconds and instance of
                // actionlistener class to reset shop item #3
                resetDelay3 = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Calls reset method with the Jbutton, title, corresponding random value, which
                        // button is reset, price tag and price tage Jlabel
                        reset(random, buttonNumber);
                        // The button has now been reset once
                        resetOnceItem3 = true;
                        // If the button is reset, stop the timer from repeating the action
                        if (resetOnceItem3) {
                            resetDelay3.stop();
                        }
                    }
                });
                // Starts the timer delay
                resetDelay3.start();
            }

        } else {
            // Changes the UI to signify a player cannot buy anything
            item.setBackground(Color.red);
            item.setText("Cannot Buy!");
            // Returns to UI to the original state after 2 seconds using an instance of the
            // actionlistner class to perform an action after a delay
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

    public void reset(int random, int buttonNumber) {

        // Reselecting a new set of items for sale
        random = (int) (Math.random() * 9) + 1;
        // Checks to make sure that fire shot and ice shot cannot be rerolled into the
        // same shop
        if (random == 6 && (random1 == 7 || random2 == 7 || random3 == 7)) {
            while (random != 6) {
                random = (int) (Math.random() * 9) + 1;
            }
        }
        if (random == 7 && (random1 == 6 || random2 == 6 || random3 == 6)) {
            while (random != 7) {
                random = (int) (Math.random() * 9) + 1;
            }
        }
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
        // Checks if the bullet delay decrease has been bought too much and chooses a
        // new number if it has, rerolling until it has not selected 8

        if (random == 8 && ab.bulletDelay <= 10) {
            while (random != 8) {
                random = (int) (Math.random() * 9) + 1;
            }
        }

        // Sets the new randomly number to the random value of the corresponding button
        if (buttonNumber == 1) {
            random1 = random;
            itemPrice1 = ab.itemPrice(random1);
            price1.setText(String.valueOf(itemPrice1));
            // Resets all of the buttons and price tags with new UI
            itemTitle1 = ab.randomSelectItemTitles(random1);
            item1.setText(itemTitle1);
            item1.setBackground(Color.WHITE);
            // Reactivates buttons so they can be used
            item1.setEnabled(true);
        } else if (buttonNumber == 2) {
            random2 = random;
            itemPrice2 = ab.itemPrice(random2);
            price2.setText(String.valueOf(itemPrice2));
            // Resets all of the buttons and price tags with new UI
            itemTitle2 = ab.randomSelectItemTitles(random2);
            item2.setText(itemTitle2);
            item2.setBackground(Color.WHITE);
            // Reactivates buttons so they can be used
            item2.setEnabled(true);
        } else if (buttonNumber == 3) {
            random3 = random;
            itemPrice3 = ab.itemPrice(random3);
            price3.setText(String.valueOf(itemPrice3));
            // Resets all of the buttons and price tags with new UI
            itemTitle3 = ab.randomSelectItemTitles(random3);
            item3.setText(itemTitle3);
            item3.setBackground(Color.WHITE);
            // Reactivates buttons so they can be used
            item3.setEnabled(true);
        }

    }
}
