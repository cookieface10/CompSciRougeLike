public class BasicEnemy {
    public boolean dead;
    public int health;
    public float xPos;
    public float yPos;
    public float speed;
    public int playerX;
    public int playerY;
    public int orientatedX;
    public int orientatedY;
    BasicEnemy(int totalHealth, int x,int y,float speeds,int charX,int charY){
        health = totalHealth;
        xPos=x;
        yPos=y;
        speed=speeds;
        playerX=charX;
        playerY=charY;
    }
    public void move(){
        float yDist = playerY-50-yPos-orientatedY;
        float xDist = playerX-25-xPos-orientatedX;
        double angle = Math.atan2(yDist, xDist);
        xPos += Math.cos(angle)*speed;
        yPos += Math.sin(angle)*speed;
        for(BasicBullet b : Game.bullets){
            if(b.xPos+20>xPos && b.xPos<xPos+50 && b.yPos+20>yPos && b.yPos<yPos+100){
                health-=b.damage;
                b.dead=true;
            }
        }
        if(health <= 0){
            dead = true;
        }
    }
}
