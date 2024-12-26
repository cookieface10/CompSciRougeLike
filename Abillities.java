public class Abillities {
    int random = 0;
    public void heal(){
        Game.playerHealth = 20;
    }
    public void movementIncrease()
    {
        Game.speed += 1;
    }
    public void attackIncrease(){
        Game.damageBoost += 1;
    }
    public String randomSelectItemTitles(int random)
    {
        if(random == 1)
        {
            return "Heal";
        }
        else if(random == 2)
        {
            return "Damage Increase"; 
        }
        else if(random == 3)
        {
            return "Speed Increase";
        }
        return null;
    }
}
