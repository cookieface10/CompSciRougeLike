public class BasicSpawnPoint {
    public int x;
    public int y;
    BasicSpawnPoint(int x, int y){
        this.x = x;
        this.y= y;
    }
    public void spawn(){
        Game.enemys.add(new BasicEnemy(5,x,y,2,Game.characterPosX+25,Game.characterPosY+25));
    }
}
