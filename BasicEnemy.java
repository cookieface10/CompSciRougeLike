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
    BasicEnemy(int totalHealth, int x,int y,float speeds,int charX,int charY){
        this.totalHealth = totalHealth;
        health = totalHealth;
        xPos=x;
        yPos=y;
        speed=speeds;
        playerX=charX;
        playerY=charY;
    }
    //move method
    public void move(){
        //calculates the x and y distance between the enemy and the player
        float yDist = playerY-50-yPos-orientatedY;
        float xDist = playerX-25-xPos-orientatedX;
        //calculates the angle of the hypotanuse between the enemy and the player
        double angle = Math.atan2(yDist, xDist);
        //moves by x and y component of the hypotinouse if the triangle was shrunk to make the hypotanuse 1
        xPos += Math.cos(angle)*speed;
        yPos += Math.sin(angle)*speed;
        //checks every bullet
        for(BasicBullet b : Game.bullets){
            //if any part of the bullet is overlaping with this enemy
            if(b.xPos+20>xPos && b.xPos<xPos+50 && b.yPos+20>yPos && b.yPos<yPos+100){
                //take damage
                health-=b.damage;
                //delete the bullet
                b.dead=true;
            }
        }
        //if health is less then or = to 0
        if(health <= 0){
            //loop 10 times
            for(int i = 1; i <= 10; i++){
                //creating point orbs
                Game.pointOrbs.add(new PointOrbs((int)xPos, (int)yPos));
            }
            //then die (removing it from the game / ram)
            dead = true;
        }
    }
}
