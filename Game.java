import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

//Made by Ryan McGurrin

public class Game implements MouseMotionListener{
    public static int WorldPosX = 0;
    public static int WorldPosY = 0;
    public static boolean left = false;
    public static boolean right = false;
    public static boolean up = false;
    public static boolean down = false;
    public static int characterPosX = 0;
    public static int characterPosY = 0;
    public static int x;
    public static int y;
    public static void main(String[] args) throws Exception {
        //setup the frame
        JFrame frame = new JFrame("JFrame");  
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

        //adds keyListener
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed (KeyEvent e) {
                int key = e.getKeyCode();
                // a or <- //
                if (key == 65 || key == 37) {
                    left = true;
                }
                // d or -> //
                else if (key == 68 || key == 39){
                    right = true;
                }
                // W or uparrow //
                if(key == 87 || key == 38){
                    up=true;
                }
                // S or downarrow //
                else if(key ==83 || key == 40){
                    down=true;
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
                int key = e.getKeyCode();
                // a or <- //
                if (key == 65 || key == 37) {
                    left = false;
                }
                // d or -> //
                else if (key == 68 || key == 39){
                    right = false;
                }
                // W or uparrow //
                if(key == 87 || key == 38){
                    up=false;
                }
                // S or downarrow //
                else if(key ==83 || key == 40){
                    down=false;
                }
            }
        });
        //Game loop
        while (true) {
            //re draw the frame
            frame.repaint();
            //move the character
            if(right){
                WorldPosX+=5;
            }
            if(left){
                WorldPosX-=5;
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
        for(int i = 0; i<=20;i++){
            for(int k = 0; k<=20;k++){
                g.setColor(new Color (200,150,0));
                g.fillRect(i*50+orientatedXWorldPosition, k*50+orientatedYWorldPosition,50,50);
                g.setColor(new Color(0,0,0));
                g.drawRect(i*50+orientatedXWorldPosition, k*50+orientatedYWorldPosition,50,50);
            }
        }
        g.fillRect(Game.characterPosX, Game.characterPosY, 50,50);
    }
}