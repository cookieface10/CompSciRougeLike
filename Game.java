import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

//Made by Ryan McGurrin

public class Game implements MouseMotionListener {
    public static int WorldPosX = 0;
    public static int WorldPosY = 0;
    public static float speed = 5f;
    public static int damageBoost = 0;
    public static int speedBoost = 0;
    public static boolean left = false;
    public static boolean right = false;
    public static boolean up = false;
    public static boolean down = false;
    public static boolean horizontal = false;
    public static boolean vertical = false;
    public static boolean shooting =false;
    public static int bulletCooldown =0;
    public static boolean coyoteShot =false;
    public static boolean openShop = false;
    public static boolean openStartScreen = true;
    public static int characterPosX = 0;
    public static int characterPosY = 0;
    public static double playerHealth = 20;
    public static double playerMaxHealth = 20;
    public static ArrayList<BasicBullet> bullets = new ArrayList<>();
    public static ArrayList<BasicSpawnPoint> spawns = new ArrayList<>();
    public static ArrayList<BasicEnemy> enemys = new ArrayList<>();
    public static ArrayList<PointOrbs> pointOrbs = new ArrayList<>();
    public static int x;
    public static int y;
    public static double enemyHealthMultiplyer = 1;
    public static JFrame frame;
    public static JPanel panel;
    public static long gameTime = 0;
    public static long spawnTime = 500;
    public static long points = 0;
    public static Random rand = new Random();
    public static Shop shop = new Shop();
    public static StartScreen startScreen = new StartScreen();
    public static EndScreen endScreen = new EndScreen();
    public static Game newInstanceGame;
    public static Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();

    public static void main(String[] args) throws Exception {
        // setup the frame
        frame = new JFrame("JFrame");
        JPanel panel = new JPanel();
        frame.add(panel);
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new ShapeDrawing());
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setVisible(true);
        frame.addMouseListener(new CoolMouseEvents());
        frame.addMouseMotionListener(new Game());
        WorldPosX = center.x;
        characterPosX = center.x - 25;
        characterPosY = center.y - 25;

        // testing enemy
        Game.spawns.add(new BasicSpawnPoint(100, 100));
        Game.spawns.add(new BasicSpawnPoint(1000, 100));
        Game.spawns.add(new BasicSpawnPoint(1000, 1000));
        Game.spawns.add(new BasicSpawnPoint(100, 1000));
        frame.setFocusTraversalKeysEnabled(false);
        // adds keyListener
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                // a or <- //
                if (key == 65 || key == 37) {
                    left = true;
                    horizontal = true;
                    System.out.println("a");
                }
                // d or -> //
                else if (key == 68 || key == 39) {
                    right = true;
                    horizontal = true;
                }
                // W or uparrow //
                if (key == 87 || key == 38) {
                    up = true;
                    vertical = true;
                }
                // S or downarrow //
                else if (key == 83 || key == 40) {
                    down = true;
                    vertical = true;
                }
                // Press tab button to open the shop menu and pause game
                if (key == 9) {
                    openShop = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int key = e.getKeyCode();
                // a or <- //
                if (key == 65 || key == 37) {
                    left = false;
                    horizontal = false;
                }
                // d or -> //
                else if (key == 68 || key == 39) {
                    right = false;
                    horizontal = false;
                }
                // W or uparrow //
                if (key == 87 || key == 38) {
                    up = false;
                    vertical = false;
                }
                // S or downarrow //
                else if (key == 83 || key == 40) {
                    down = false;
                    vertical = false;
                }
            }
        });
        // Game loop
        while (true) {
            if (openStartScreen == true) {
                startScreen.startFrame.setVisible(true);
                frame.setVisible(false);
            }
            // Checks to see if shop has been opened by player
            if (openShop == true) {
                // Makes the shop UI visible in a new frame that overlaps the main frame.
                shop.shopFrame.setVisible(true);
                shop.shopFrame.setFocusable(true);
                // Stops all player movement
                up = false;
                down = false;
                right = false;
                left = false;
                vertical = false;
                horizontal = false;
            }
            // Runs the main game while the shop menu is not opened
            else{
                // redraw the frame
                frame.repaint();

                double normalizedSpeed = speed;
                if (horizontal && vertical) {
                    normalizedSpeed = speed * Math.sin(Math.toRadians(45));
                }
                // move the character
                if (right) {
                    WorldPosX += normalizedSpeed;
                }
                if (left) {
                    WorldPosX -= normalizedSpeed;
                }
                if (up) {
                    WorldPosY -= normalizedSpeed;
                }
                if (down) {
                    WorldPosY += normalizedSpeed;
                }
                if(bulletCooldown>0){bulletCooldown--;}
                if(shooting && bulletCooldown == 0 || coyoteShot && bulletCooldown==0){
                    // creates a new bullet, and puts it in the arrayList
                    Game.bullets.add(new BasicBullet(1 + Game.damageBoost, 5 + Game.speedBoost, Game.WorldPosX, Game.WorldPosY,Game.x-20, Game.y-20, Game.characterPosX, Game.characterPosY));
                    coyoteShot = false;
                    bulletCooldown = 25;
                }
                if (playerHealth <= 0) {
                    endScreen.endFrame.setVisible(true);
                    frame.setVisible(false);
                }
            }
            // wait 10 milliseconds
            Thread.sleep(10);
            // repeat
        }
    }

    // get x and y coordinates of the mouse
    @Override
    public void mouseMoved(MouseEvent me) {
        x = me.getX();
        y = me.getY();
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        x = me.getX();
        y = me.getY();
    }

}

class CoolMouseEvents implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    // clicked
    @Override
    public void mousePressed(MouseEvent e) {
        Game.shooting = true;
        if(Game.bulletCooldown <= 15){
            Game.coyoteShot = true;
        }
    }
    // release click
    @Override
    public void mouseReleased(MouseEvent e) {
        Game.shooting = false;
    }
}

class ShapeDrawing extends JComponent {
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // the world position orienteted to the players position
        int orientatedXWorldPosition = -Game.WorldPosX + Game.characterPosX;
        int orientatedYWorldPosition = -Game.WorldPosY + Game.characterPosY;
        // this is a nestled for loop that draws the grid of grass tiles
        for (int i = 0; i <= 50; i++) {
            for (int k = 0; k <= 50; k++) {
                // these are the main grass tiles
                g.setColor(new Color(20, 200, 30));
                g.fillRect(i * 50 + orientatedXWorldPosition - 500, k * 50 + orientatedYWorldPosition - 1250, 50, 50);
                g.setColor(new Color(0, 80, 0));
                g.drawRect(i * 50 + orientatedXWorldPosition - 500, k * 50 + orientatedYWorldPosition - 1250, 50, 50);
                // these are the little blades of grass
                g.fillRect(i * 50 + orientatedXWorldPosition + 12 - 500, k * 50 + orientatedYWorldPosition + 10 - 1250,
                        5, 8);
                g.fillRect(i * 50 + orientatedXWorldPosition + 32 - 500, k * 50 + orientatedYWorldPosition + 25 - 1250,
                        5, 8);
                g.fillRect(i * 50 + orientatedXWorldPosition + 27 - 500, k * 50 + orientatedYWorldPosition + 40 - 1250,
                        5, 8);
                g.fillRect(i * 50 + orientatedXWorldPosition + 8 - 500, k * 50 + orientatedYWorldPosition + 35 - 1250,
                        5, 8);
            }
        }
        // this draws the player
        g.setColor(Color.black);
        g.fillRect(Game.characterPosX, Game.characterPosY, 50, 50);
        // this checks every bullet
        for (BasicBullet b : Game.bullets) {
            // this moves the bullet
            b.Move();
            // this draws the bullet at the new location
            g.fillRect((int) Math.round(b.xPos) + (orientatedXWorldPosition), (int) Math.round(b.yPos) + (orientatedYWorldPosition), 20, 20);
            // if the bullet dies (happens when it hits a enemy, or goes off screen) it
            // will remove it from the list, compleatly removing its exsitince from the game
            if (b.dead) {
                Game.bullets.remove(b);
            }
        }
        // this checks every enemy
        for (BasicEnemy e : Game.enemys) {
            // moves all the enemys
            e.move();
            // gives the enemys the new orientated world position (because it changes when
            // the player moves)
            e.orientatedX = orientatedXWorldPosition;
            e.orientatedY = orientatedYWorldPosition;
            // draws the enemys
            if (e instanceof SlimeEnemy) { // size(50,35)
                SlimeEnemy s = (SlimeEnemy) e;
                g.setColor(s.c);
                g.fillRect(Math.round(e.xPos) + orientatedXWorldPosition + 5, Math.round(e.yPos) + orientatedYWorldPosition + 5, 40, 25);// body color
                g.setColor(Color.white);
                g.fillRect(Math.round(e.xPos) + orientatedXWorldPosition + 10, Math.round(e.yPos) + orientatedYWorldPosition + 5, 5, 5);// higer shine
                g.fillRect(Math.round(e.xPos) + orientatedXWorldPosition + 5, Math.round(e.yPos) + orientatedYWorldPosition + 10, 5, 5);// lower shine
                g.setColor(Color.black);
                g.fillRect(Math.round(e.xPos) + orientatedXWorldPosition + 15, Math.round(e.yPos) + orientatedYWorldPosition + 15, 5, 5);// left eye
                g.fillRect(Math.round(e.xPos) + orientatedXWorldPosition + 30, Math.round(e.yPos) + orientatedYWorldPosition + 15, 5, 5);// right eye
                g.setColor(e.affect);
                g.fillRect(Math.round(e.xPos) + orientatedXWorldPosition + 5, Math.round(e.yPos) + orientatedYWorldPosition + 5, 5, 5);// left corner
                g.fillRect(Math.round(e.xPos) + orientatedXWorldPosition + 40, Math.round(e.yPos) + orientatedYWorldPosition + 5, 5, 5);// right corner
                g.fillRect(Math.round(e.xPos) + orientatedXWorldPosition, Math.round(e.yPos) + orientatedYWorldPosition + 10, 5, 20);// left side
                g.fillRect(Math.round(e.xPos) + orientatedXWorldPosition + 45, Math.round(e.yPos) + orientatedYWorldPosition + 10, 5, 20);// right side
                g.fillRect(Math.round(e.xPos) + orientatedXWorldPosition + 5, Math.round(e.yPos) + orientatedYWorldPosition + 30, 40, 5);// bottom
                g.fillRect(Math.round(e.xPos) + orientatedXWorldPosition + 10, Math.round(e.yPos) + orientatedYWorldPosition, 30, 5);// top
            }
            else if(e instanceof BatEnemy){
                g.setColor(new Color(70,70,70));
                g.fillRect(Math.round(e.xPos) + orientatedXWorldPosition+15, Math.round(e.yPos) + orientatedYWorldPosition+12, 9, 3); //part of body
                g.fillRect(Math.round(e.xPos) + orientatedXWorldPosition+3, Math.round(e.yPos) + orientatedYWorldPosition+6, 33, 6); //wings
                g.setColor(new Color(237, 28, 36));
                g.fillRect(Math.round(e.xPos) + orientatedXWorldPosition+15, Math.round(e.yPos) + orientatedYWorldPosition+6, 3, 3); //eyes
                g.fillRect(Math.round(e.xPos) + orientatedXWorldPosition+21, Math.round(e.yPos) + orientatedYWorldPosition+6, 3, 3); //eyes
                g.setColor(e.affect);
                g.fillRect(Math.round(e.xPos) + orientatedXWorldPosition+15, Math.round(e.yPos) + orientatedYWorldPosition+3, 9, 3); //top of head
                g.fillRect(Math.round(e.xPos) + orientatedXWorldPosition+15, Math.round(e.yPos) + orientatedYWorldPosition+15, 9, 3); //bottom
                g.fillRect(Math.round(e.xPos) + orientatedXWorldPosition+12, Math.round(e.yPos) + orientatedYWorldPosition, 3, 18); //left side body
                g.fillRect(Math.round(e.xPos) + orientatedXWorldPosition+24, Math.round(e.yPos) + orientatedYWorldPosition, 3, 18); //right side body
                g.fillRect(Math.round(e.xPos) + orientatedXWorldPosition+15, Math.round(e.yPos) + orientatedYWorldPosition+18, 3, 3); //left foot
                g.fillRect(Math.round(e.xPos) + orientatedXWorldPosition+21, Math.round(e.yPos) + orientatedYWorldPosition+18, 3, 3); //right foot
                g.fillRect(Math.round(e.xPos) + orientatedXWorldPosition, Math.round(e.yPos) + orientatedYWorldPosition+6, 3, 9); //left side wing
                g.fillRect(Math.round(e.xPos) + orientatedXWorldPosition+3, Math.round(e.yPos) + orientatedYWorldPosition+3, 6, 3); //left wing top
                g.fillRect(Math.round(e.xPos) + orientatedXWorldPosition+3, Math.round(e.yPos) + orientatedYWorldPosition+9, 6, 3); //left wing bottom
                g.fillRect(Math.round(e.xPos) + orientatedXWorldPosition+9, Math.round(e.yPos) + orientatedYWorldPosition+6, 3, 3); //left wing top connector
                g.fillRect(Math.round(e.xPos) + orientatedXWorldPosition+9, Math.round(e.yPos) + orientatedYWorldPosition+12, 3, 3); //left wing bottom connector
                g.fillRect(Math.round(e.xPos) + orientatedXWorldPosition+36, Math.round(e.yPos) + orientatedYWorldPosition+6, 3, 9); //right side wing
                g.fillRect(Math.round(e.xPos) + orientatedXWorldPosition+30, Math.round(e.yPos) + orientatedYWorldPosition+3, 6, 3); //right wing top
                g.fillRect(Math.round(e.xPos) + orientatedXWorldPosition+30, Math.round(e.yPos) + orientatedYWorldPosition+9, 6, 3); //right wing bottom
                g.fillRect(Math.round(e.xPos) + orientatedXWorldPosition+27, Math.round(e.yPos) + orientatedYWorldPosition+6, 3, 3); //right wing top connector
                g.fillRect(Math.round(e.xPos) + orientatedXWorldPosition+27, Math.round(e.yPos) + orientatedYWorldPosition+12, 3, 3); //right wing bottom connector
            }
            // and there health bars
            g.setColor(new Color(20, 20, 20));
            g.fillRect(Math.round(e.xPos) + orientatedXWorldPosition - 5+((e.xborder-50)/2),
                    Math.round(e.yPos) + orientatedYWorldPosition - 50, 60, 20);
            g.setColor(new Color(150, 20, 20));
            g.fillRect(Math.round(e.xPos) + orientatedXWorldPosition - 3+((e.xborder-50)/2),
                    Math.round(e.yPos) + orientatedYWorldPosition - 48,
                    (int) Math.round(56 * ((double) e.health / e.totalHealth)), 16);
            // if the attack is on cooldown, decrees the timer
            if (e.attackTimer > 0) {
                e.attackTimer--;
            }
            // if the enemy is overlaping with the player and the attack is not on
            // cooldown,then attack and, put the attack on cooldown
            if (Math.round(e.xPos) + orientatedXWorldPosition < Game.characterPosX + 50
                    && Math.round(e.xPos) + orientatedXWorldPosition + e.xborder > Game.characterPosX
                    && Math.round(e.yPos) + orientatedYWorldPosition < Game.characterPosY + 50
                    && Math.round(e.yPos) + orientatedYWorldPosition + e.yborder > Game.characterPosY
                    && e.attackTimer <= 0) {
                e.attack();
                e.attackTimer = 500;
            }
            // if it dies, it removes it from the list.
            if (e.dead) {
                Game.enemys.remove(e);
            }
        }
        g.setColor(Color.YELLOW);
        // this checks every orb
        for (PointOrbs p : Game.pointOrbs) {
            // moves all the orbs
            p.OrbMove(orientatedXWorldPosition, orientatedYWorldPosition);
            // draws the orbs
            g.fillOval(p.x + orientatedXWorldPosition, p.y + orientatedYWorldPosition, 10, 10);
        }
        // decreses the spawn time, as 10 milliseconds have now passed
        Game.spawnTime--;
        // incresing the game time, as 10 milliseconds have now passed
        Game.gameTime++;
        // if the spawn time has gotten to 0
        if (Game.spawnTime <= 0) {
            // picks a random spawner, from the array list of spawners
            BasicSpawnPoint spawner = Game.spawns.get(Game.rand.nextInt(Game.spawns.size()));
            // spawns an enemy at that spawner
            spawner.spawn();
            // resets the spawn time, to a random number that will decreasingly get lower
            // over time (the longer you play, the more enemys spawn)
            Game.spawnTime = (long) Game.rand.nextDouble() * (500 - (Game.gameTime / 100)) + 250 - (Game.gameTime / 100);
            // if the spawn time randomiser ends up to low (going into the negitives) just
            // set it back to a max of 1 (10 millisecods) enemys will never spawn faster
            // then that because of this
            //if(Game.spawnTime > 5){ ///// FOR TESTING
            if (Game.spawnTime <= 5) {
                Game.spawnTime = 5;
            }
        }
        // Enemy Scaling
        Game.enemyHealthMultiplyer = ((double) Game.gameTime / 50000.0) + 1;
        ////////////////////////////// UI////////////////////////////////
        // draws the players health bar in the bottom left corner
        g.setColor(Color.BLACK);
        g.fillRect(5, (Game.characterPosY + 20) * 2, 200, 40);
        g.setColor(new Color(220, 30, 30));
        g.fillRect(7, ((Game.characterPosY + 21) * 2),
                (int) Math.round(196 * ((double) Game.playerHealth / Game.playerMaxHealth)), 36);
        // draws the players score in the top right corner
        g.setColor(Color.BLACK);
        g.setFont(new Font("SansSerif", Font.BOLD, 15));
        // the math for the x moves the text to the left more for every digit in the
        // score, making sure it never goes off screen
        g.drawString("Score: " + Game.points,
                (((Game.characterPosX + 25) * 2) - (((int) Math.floor(Math.log10(Game.points) + 1) * 7) + 60)), 11);
    }
}