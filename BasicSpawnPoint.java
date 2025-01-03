
public class BasicSpawnPoint {
    // every spawn point has its own x and y cordnates to represent its position in
    // the world
    public int x;
    public int y;

    BasicSpawnPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void spawn(){
        //this will add a new enemy to the enemy arraylist in the game class
        Game.enemys.add(new SlimeEnemy(x,y,Game.rand.nextInt(3)+ 1));
    }
}
