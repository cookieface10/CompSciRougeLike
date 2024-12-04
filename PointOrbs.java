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
        if(y<startingY+50 && !hitGround){
            x+=xMomentum;
            y+=yMomentum;
            yMomentum+=1;
        }
        else{
            hitGround = true;
            float yDist = Game.characterPosY+25-y-orientatedY;
            float xDist = Game.characterPosX+25-x-orientatedX;
            double angle = Math.atan2(yDist, xDist);
            x += Math.cos(angle)*20;
            y += Math.sin(angle)*20;
            if(Game.characterPosY+15<y+orientatedY+10 && Game.characterPosY+35>y+orientatedY && Game.characterPosX+15<x+orientatedX+10 && Game.characterPosX+35>x+orientatedX){
                swirlCount++;
                //add points here
                if(swirlCount == 5){
                    Game.pointOrbs.remove(this);
                }
            }
        }
    }

}
