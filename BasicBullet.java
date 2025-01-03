public class BasicBullet {
    public boolean dead;
    public int damage;
    public double speed;
    public double xPos;
    public double yPos;
    public double xDestination;
    public double yDestination;
    public int screenSizeX;
    public int screenSizeY;
    public double angle;
    BasicBullet(int dam, double bulletSpeed, int x, int y, int xDest, int yDest, int charPosX, int charPosY){
        damage=dam;
        speed = bulletSpeed;
        xPos=(double)x+15;
        yPos=(double)y+15;
        //subtracts the character positon, to triangulate, based on the corner rather then the center
        xDestination=(double)xDest-charPosX;
        yDestination=(double)yDest-charPosY;
        screenSizeX=x*2;
        screenSizeY=y*2;
        //atan returns the angle that is opposite to the y destination and adjacent to the x destination
        angle = Math.atan2(yDestination, xDestination);
    }
    public void Move(){
        //the x and y position are then added to by the cos and sin of that angle to get to the destination of the hypotinuse.
        xPos += Math.cos(angle)*speed;
        yPos += Math.sin(angle)*speed;
        //if the bullet exits the confines of the screen, kill the bullet (to save on ram)
        if(xPos+(-Game.WorldPosX+Game.characterPosX) > Game.frame.getWidth() || yPos+(-Game.WorldPosY+Game.characterPosY) > Game.frame.getHeight()){dead=true;System.out.println("s");}
    }
}
