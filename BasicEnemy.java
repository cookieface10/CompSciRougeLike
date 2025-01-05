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
    public BasicBullet hitBullet;

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
    }

    public void checkBullet() {
        // checks every bullet
        for (BasicBullet b : Game.bullets) {
            // if any part of the bullet is overlaping with this enemy
            if (b.xPos + 10 > xPos && b.xPos - 10 < xPos + xborder && b.yPos + 10 > yPos
                    && b.yPos - 10 < yPos + yborder && hitBullet != b) {
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
                    // Checks how many times ice shot has been bought and divides the enemy speed
                    // accordingly
                    if (!frozen) {
                        speed /= (Game.shop.ab.iceShotTimesBought + 1);
                        frozen = true;
                    }
                }
                // Sets the bullets health to 0 by default
                b.bulletHealth--;
                // If the bullethealth is 0
                if (b.bulletHealth <= 0) {
                    // delete the bullet
                    b.dead = true;
                }
                // If an enemy has been hit by the bullet, let the game know that it has been
                // hit once and to inflict further damage
                hitBullet = b;
            }
        }
        // Check if the fire affect has been applied and call the fire tick method
        if (onFire) {
            fireTick();
        }
        // Calls method to check the health of enemy
        checkHealth();
    }

    public void checkHealth() {
        // if health is less then or = to 0
        if (health <= 0) {
            //if there are allready a lot of point orbs on screen, then just give the points without making anymore, to save on proformance
            if(Game.pointOrbs.size() >= 50){
                Game.points+=1000;
            }
            else{
                // loop 10 times
                for (int i = 1; i <= 10; i++) {
                    // creating point orbs
                    Game.pointOrbs.add(new PointOrbs((int) xPos, (int) yPos));
                }
            }
            // then die (removing it from the game / ram)
            dead = true;
        }
    }

    public void fireTick() {
        // Reduces the fireTicker value by 1
        fireTicker--;
        // If the fireTicker is less than or equal to 0
        if (fireTicker <= 0) {
            // Reduce enemy health by the amount of times the player has bougth the fire
            // shot ability
            health -= Game.shop.ab.fireShotTimesBought;
            fireTicker = 50;
        }
    }

    public void attack() {
        // Reduces player health by the damage value
        Game.playerHealth -= damage;
    }
}