import java.awt.Color;
public class SlimeEnemy extends BasicEnemy {
    public int level;
    public Color c;
    public SlimeEnemy(int x, int y, int level){
        super((int)Math.round((double)5*Game.enemyHealthMultiplyer), 5*level,x,y,2,50,35);
        this.level = level;
        //picks color based on the difficulty of the enemy
        if(level == 3){c= new Color(237, 28, 36);}
        else if(level == 2){c= new Color(168, 214, 29);}
        else {c= new Color(0, 183, 239);}

    }
}
