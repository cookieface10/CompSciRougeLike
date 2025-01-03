import java.awt.Color;

public class BasicEnemy {
    public boolean dead;
    public int totalHealth;
    public int health;
    public float xPos;
    public float yPos;
    public float speed;
    public int fireDamage = 0;
    public int playerX;
    public int playerY;
    public int orientatedX;
    public int orientatedY;
    public double damage;
    public int attackTimer;
    public int xborder;
    public int yborder;
    public Color affect = Color.black;
    public boolean onFire;
    public boolean frozen;
    public int fireTicker;

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
        fireTicker = 50;
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
        checkBullet();
        if (onFire) {
            fireTick();
        }
        checkHealth();
    }

    public void checkBullet() {
        // checks every bullet
        for (BasicBullet b : Game.bullets) {
            // if any part of the bullet is overlaping with this enemy
            if (b.xPos + 20 > xPos && b.xPos < xPos + xborder && b.yPos + 20 > yPos && b.yPos < yPos + yborder) {
                // take damage
                health -= b.damage;
                // If the player has bought the fireshot ability, add an effect to their bullets
                // and deal additional damage
                if (Game.shop.ab.fireShotEnabled == true) {
                    // changes enemy outline to the color of fire
                    affect = new Color(176, 34, 2);
                    // Sets the passibe damage ticker to true
                    onFire = true;
                }
                // If the player has bought the iceshot ability, add an effect to their bullets
                // that slows the hit enemies
                if (Game.shop.ab.iceShotEnabled == true) {
                    // changes the enemy outline to the color of ice
                    affect = new Color(2, 139, 189);
                    // Checks how many times ice shot has been bought and decreases the enemy speed
                    // accordingly
                    if (!frozen) {
                        speed /= (Game.shop.ab.iceShotTimesBought + 1);
                        frozen = true;
                    }
                }
                // delete the bullet
                b.dead = true;
            }
        }
    }

    public void checkHealth() {
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

    public void fireTick() {
        fireTicker--;
        if (fireTicker <= 0) {
            health -= Game.shop.ab.fireShotTimesBought;
            fireTicker = 50;
        }
    }

    public void attack() {
        Game.playerHealth -= damage;
    }
}