public class BasicBullet {
    public boolean dead;
    public int damage;
    public double speed;
    public double xPos;
    public double yPos;
    public double xDestination;
    public double yDestination;
    //public double startX;
    //public double startY;
    public int screenSizeX;
    public int screenSizeY;
    BasicBullet(int dam, double bulletSpeed, int x, int y, int xDest, int yDest, int charPosX, int charPosY){
        damage=dam;
        speed = bulletSpeed;
        xPos=(double)x;
        yPos=(double)y;
        xDestination=(double)xDest-charPosX;
        yDestination=(double)yDest-charPosY;
        screenSizeX=x*2;
        screenSizeY=y*2;

    }
    public void Move(){
        double angle = Math.atan2(yDestination, xDestination);
        xPos += Math.cos(angle)*speed;
        yPos += Math.sin(angle)*speed;

        if(xPos > screenSizeX || yPos > screenSizeY){dead=true;}
    }
}
