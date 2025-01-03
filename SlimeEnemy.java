import java.awt.Color;

public class SlimeEnemy extends BasicEnemy {
    public int moveTimer;
    public int level;
    double angle;
    public Color c;

    public SlimeEnemy(int x, int y, int level) {
        super((int) Math.round((double) 5 * Game.enemyHealthMultiplyer), 5 * level, x, y, 6, 50, 35);
        this.level = level;
        // picks color based on the difficulty of the enemy
        if (level == 3) {
            c = new Color(237, 28, 36);
        } else if (level == 2) {
            c = new Color(168, 214, 29);
        } else {
            c = new Color(0, 183, 239);
        }
        moveTimer = 80;
    }

    @Override
    public void move() {
        moveTimer--;
        if (moveTimer == 15) {
            // calculates the x and y distance between the enemy and the player
            float yDist = playerY - 50 - yPos + yborder - orientatedY;
            float xDist = playerX - 25 - xPos - orientatedX;
            // calculates the angle of the hypotanuse between the enemy and the player
            angle = Math.atan2(yDist, xDist);
            // moves by x and y component of the hypotinouse if the triangle was shrunk to
            // make the hypotanuse 1
            xPos += Math.cos(angle) * speed;
            yPos += Math.sin(angle) * speed;
        }
        if (moveTimer < 15) {
            yPos += 7 - moveTimer;
            // moves by x and y component of the hypotinouse if the triangle was shrunk to
            // make the hypotanuse 1
            xPos += Math.cos(angle) * speed;
            yPos += Math.sin(angle) * speed;
            if (moveTimer == 0) {
                moveTimer = 80;
            }
        }
        checkBullet();
    }
}
