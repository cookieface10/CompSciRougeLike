import java.util.Random;

public class PointOrbs {
    public int startingY;
    public int x;
    public int y;
    public int xMomentum;
    public int yMomentum;
    public boolean hitGround = false;
    int swirlCount;
    Random rand = new Random();
    public PointOrbs(int x, int y){
        this.x=x;
        this.y=y;
        startingY = y;
        xMomentum = rand.nextInt(14)-7;
        yMomentum = rand.nextInt(25)*-1;
    }

    public void OrbMove(int orientatedX, int orientatedY){
        //if the orb is still flying
        if(y<startingY+50 && !hitGround){
            //move by the x and the y
            x+=xMomentum;
            y+=yMomentum;
            //accelerate it downwards
            yMomentum+=1;
        }
        else{
            hitGround = true;
            //calculates the x and y distance between the enemy and the player
            float yDist = Game.characterPosY+25-y-orientatedY;
            float xDist = Game.characterPosX+25-x-orientatedX;
            //calculates the angle of the hypotanuse between the enemy and the player
            double angle = Math.atan2(yDist, xDist);
            //moves by x and y component of the hypotinouse if the triangle was shrunk to make the hypotanuse 1
            x += Math.cos(angle)*30;
            y += Math.sin(angle)*30;
            //if its touching the player
            if(Game.characterPosY<y+orientatedY+10 && Game.characterPosY+50>y+orientatedY && Game.characterPosX<x+orientatedX+10 && Game.characterPosX+50>x+orientatedX){
                //add to the amount of times its swirled around the player
                swirlCount++;
                //if that count hits 5
                if(swirlCount == 5){
                    //add the points to the players wallet
                    Game.points+=100;
                    //and destroy the orb
                    Game.pointOrbs.remove(this);
                }
            }
        }
    }

}
