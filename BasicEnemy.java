import java.awt.Color;

public class BasicEnemy {
    public boolean dead;
    public int totalHealth;
    public int health;
    public float xPos;
    public float yPos;
    public float speed;
    public int playerX;
    public int playerY;
    public int orientatedX;
    public int orientatedY;
    public double damage;
    public int attackTimer;
    public int xborder;
    public int yborder;
    public Color affect = Color.black;

    BasicEnemy(int totalHealth, double damage, int x, int y, float speed, int xborder, int yborder) {
        this.totalHealth = totalHealth;
        health = totalHealth;
        this.damage = damage;
        xPos = x;
        yPos = y;
        this.speed = speed;
        playerX = Game.characterPosX + 25;
        playerY = Game.characterPosY + 25;
        this.xborder = xborder;
        this.yborder = yborder;
    }

    // move method
    public void move() {
        // calculates the x and y distance between the enemy and the player
        float yDist = playerY - 50 - yPos + yborder - orientatedY;
        float xDist = playerX - 25 - xPos - orientatedX;
        // calculates the angle of the hypotanuse between the enemy and the player
        double angle = Math.atan2(yDist, xDist);
        // moves by x and y component of the hypotinouse if the triangle was shrunk to
        // make the hypotanuse 1
        xPos += Math.cos(angle) * speed;
        yPos += Math.sin(angle) * speed;
        // checks every bullet
        for (BasicBullet b : Game.bullets) {
            // if any part of the bullet is overlaping with this enemy
            if (b.xPos + 20 > xPos && b.xPos < xPos + xborder && b.yPos + 20 > yPos && b.yPos < yPos + yborder) {
                // take damage
                health -= b.damage;
                if (Game.shop.ab.fireShotEnabled == true) {
                    affect = new Color(176, 34, 2);
                }
                if (Game.shop.ab.iceShotEnabled == true) {
                    affect = new Color(2, 139, 189);
                }
                // delete the bullet
                b.dead = true;
            }
        }
        // if health is less then or = to 0
        if (health <= 0) {
            // loop 10 times
            for (int i = 1; i <= 10; i++) {
                // creating point orbs
                Game.pointOrbs.add(new PointOrbs((int) xPos, (int) yPos));
            }
            // then die (removing it from the game / ram)
            dead = true;
        }
    }

    public void attack() {
        Game.playerHealth -= damage;
    }
}
