import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

//Made by Ryan McGurrin

public class Game implements MouseMotionListener{
    public static int WorldPosX = 0;
    public static int WorldPosY = 0;
    public static float speed = 5f;
    public static boolean left = false;
    public static boolean right = false;
    public static boolean up = false;
    public static boolean down = false;
    public static boolean horizontal = false;
    public static boolean vertical = false;
    public static int characterPosX = 0;
    public static int characterPosY = 0;
    public static ArrayList<BasicBullet> bullets = new ArrayList<>();
    public static ArrayList<BasicSpawnPoint> spawns = new ArrayList<>();
    public static ArrayList<BasicEnemy> enemys = new ArrayList<>();
    public static int x;
    public static int y;
    public static JFrame frame;
    public static JPanel panel;
    public static long gameTime = 0;
    public static long spawnTime = 500;
    public static Random rand = new Random();

    public static void main(String[] args) throws Exception {
        //setup the frame
        frame = new JFrame("JFrame");  
        JPanel panel = new JPanel();  
        frame.add(panel);  
        frame.setSize(800, 800);  
        frame.setLocationRelativeTo(null);  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        frame.getContentPane().add(new ShapeDrawing ());
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        frame.setUndecorated(true); 
        frame.setVisible(true);
        frame.addMouseListener(new CoolMouseEvents());
        frame.addMouseMotionListener(new Game());
        Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        WorldPosX = center.x;
        characterPosX = center.x-25;
        characterPosY = center.y-25;

        //testing enemy
        enemys.add(new BasicEnemy(5,WorldPosX,WorldPosY,2,center.x,center.y));
        spawns.add(new BasicSpawnPoint (100,100));
        spawns.add(new BasicSpawnPoint (1000,100));
        spawns.add(new BasicSpawnPoint (1000,1000));
        spawns.add(new BasicSpawnPoint (100,1000));
        //adds keyListener
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed (KeyEvent e) {
                int key = e.getKeyCode();
                // a or <- //
                if (key == 65 || key == 37) {
                    left = true;
                    horizontal = true;
                }
                // d or -> //
                else if (key == 68 || key == 39){
                    right = true;
                    horizontal = true;
                }
                // W or uparrow //
                if(key == 87 || key == 38){
                    up=true;
                    vertical = true;
                }
                // S or downarrow //
                else if(key ==83 || key == 40){
                    down=true;
                    vertical = true;
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
                else if (key == 68 || key == 39){
                    right = false;
                    horizontal = false;
                }
                // W or uparrow //
                if(key == 87 || key == 38){
                    up=false;
                    vertical = false;
                }
                // S or downarrow //
                else if(key ==83 || key == 40){
                    down=false;
                    vertical = false;
                }
            }
        });
        //Game loop
        while (true) {
            //re draw the frame
            frame.repaint();
            double normalizedSpeed = speed;
            if(horizontal && vertical){
                normalizedSpeed = speed * Math.sin(Math.toRadians(45));
            }
            //move the character
            if(right){
                WorldPosX+=normalizedSpeed;
            }
            if(left){
                WorldPosX-=normalizedSpeed;
            }
            if(up){
                WorldPosY-=normalizedSpeed;
            }
            if(down){
                WorldPosY+=normalizedSpeed;
            }
            //wait 10 milliseconds
            Thread.sleep(10);
            //repeat
        }
    }

    //get x and y coordinates of the mouse
    @Override
    public void mouseMoved(MouseEvent me){
        x = me.getX();
        y = me.getY();
    }
    @Override
    public void mouseDragged (MouseEvent me){
        x = me.getX();
        y = me.getY();
    }
    
}
class CoolMouseEvents implements MouseListener{
    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    //clicked
    @Override
    public void mousePressed(MouseEvent e) {
        Game.bullets.add(new BasicBullet(1, 5, Game.WorldPosX, Game.WorldPosY, Game.x, Game.y, Game.characterPosX, Game.characterPosY));
    }
    @Override
    public void mouseReleased(MouseEvent e) {}
}
class ShapeDrawing extends JComponent{
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        int orientatedXWorldPosition = -Game.WorldPosX+Game.characterPosX;
        int orientatedYWorldPosition = -Game.WorldPosY+Game.characterPosY;
        for(int i = 0; i<=50;i++){
            for(int k = 0; k<=50;k++){
                g.setColor(new Color (20,200,30));
                g.fillRect(i*50+orientatedXWorldPosition-(10*50), k*50+orientatedYWorldPosition-(25*50),50,50);
                g.setColor(new Color(0,80,0));
                g.drawRect(i*50+orientatedXWorldPosition-(10*50), k*50+orientatedYWorldPosition-(25*50),50,50);
                g.fillRect(i*50+orientatedXWorldPosition+12-(10*50), k*50+orientatedYWorldPosition+10-(25*50),5,8);
                g.fillRect(i*50+orientatedXWorldPosition+32-(10*50), k*50+orientatedYWorldPosition+25-(25*50),5,8);
                g.fillRect(i*50+orientatedXWorldPosition+27-(10*50), k*50+orientatedYWorldPosition+40-(25*50),5,8);
                g.fillRect(i*50+orientatedXWorldPosition+8-(10*50), k*50+orientatedYWorldPosition+35-(25*50),5,8);
            
            }
        }
        g.setColor(Color.black);
        g.fillRect(Game.characterPosX, Game.characterPosY, 50,50);
        for (BasicBullet b : Game.bullets) {
            b.Move();
            g.fillRect((int)Math.round(b.xPos)+(orientatedXWorldPosition), (int)Math.round(b.yPos)+(orientatedYWorldPosition), 20, 20);
            if(b.dead){Game.bullets.remove(b);}
        }
        for (BasicEnemy e : Game.enemys){
            e.move();
            e.orientatedX=orientatedXWorldPosition;
            e.orientatedY=orientatedYWorldPosition;
            g.fillRect(Math.round(e.xPos)+orientatedXWorldPosition, Math.round(e.yPos)+orientatedYWorldPosition, 50, 100);
            if(e.dead){Game.enemys.remove(e);}
        }
        Game.spawnTime--;
        Game.gameTime++;
        if(Game.spawnTime <= 0){
            BasicSpawnPoint spawner = Game.spawns.get(Game.rand.nextInt(Game.spawns.size()));
            spawner.spawn();
            Game.spawnTime = Game.rand.nextLong(500-(Game.gameTime/100))+250-(Game.gameTime/100);
            if(Game.spawnTime <= 0){
                Game.spawnTime = 1;
            }
        }
        
    }
}