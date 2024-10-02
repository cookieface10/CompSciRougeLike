public class BasicBullet {
    public boolean dead;
    public int damage;
    public float speed;
    public int xPos;
    public int yPos;
    public int xDestination;
    public int yDestination;
    public int screenSizeX;
    public int screenSizeY;
    BasicBullet(int dam, float bulletSpeed, int x, int y, int xDest, int yDest){
        damage=dam;
        speed = bulletSpeed;
        xPos=x;
        yPos=y;
        xDestination=xDest-x;
        yDestination=yDest-y;
        screenSizeX=x*2;
        screenSizeY=y*2;
    }
    public void Move(){
        double angle = Math.atan2(yDestination, xDestination);
        xPos += Math.cos(angle)*speed;
        yPos += Math.sin(angle)*speed;

        if(xPos<-50 || xPos > screenSizeX || yPos<-50 || yPos > screenSizeY){dead=true;}
    }
}
