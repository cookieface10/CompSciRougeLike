public class BatEnemy extends BasicEnemy{
    public double xVol = 0;
    public double yVol = 0;
    public BatEnemy(int x, int y){
        super((int)Math.round((double)2*Game.enemyHealthMultiplyer), 3,x,y,0.2f,39,21);
    }
    @Override
    public void move(){
        // calculates the x and y distance between the enemy and the player
        float yDist = playerY - 50 - yPos + yborder - orientatedY;
        float xDist = playerX - 25 - xPos - orientatedX;
        // calculates the angle of the hypotanuse between the enemy and the player
        double angle = Math.atan2(yDist, xDist);
        // accelerates by x and y component of the hypotinouse if the triangle was shrunk to
        // make the hypotanuse 1
        xVol += Math.cos(angle) * speed;
        yVol += Math.sin(angle) * speed;
        xPos += Math.cos(angle) * speed*2; //wihtout this the acceleration would cause an effect like centripetal force
        yPos += Math.sin(angle) * speed*2;
        if(xVol > 10){ xVol = 10;} if(xVol < -10){ xVol = -10;} // sets maximums for velocity
        if(yVol > 10){ yVol = 10;} if(yVol < -10){ yVol = -10;}
        xPos+=xVol;
        yPos+=yVol;
        checkBullet();
    }
}
