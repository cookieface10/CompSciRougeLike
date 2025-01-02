public class Abillities {
    public boolean iceShotEnabled = false;
    public boolean fireShotEnabled = false;
    long price1 = 0;
    long price2 = 0;
    long price3 = 0;

    // Method that sets the players health back to full
    public void heal() {
        Game.playerHealth = Game.playerMaxHealth;
    }

    // Method that increases the speed a player moves at
    public void movementIncrease() {
        Game.speed += 1;
    }

    // Method that increaes the players attack damage
    public void attackIncrease() {
        Game.damageBoost += 1;
    }

    // Method that increases the max health a player can have
    public void increaseHealth() {
        Game.playerMaxHealth += 5;
    }

    // Method that increases the speed a bullet travels at
    public void bulletSpeedIncrease() {
        Game.speedBoost++;
    }

    // Enables the ice shot ability which give a basic enemy with a freezing affect
    // when hit by a bullet
    public void iceShot() {
        iceShotEnabled = true;
    }

    // Enables the fire shot ability which give a basic enemy with a burning affect
    // when hit by a bullet
    public void fireShot() {
        fireShotEnabled = true;
    }

    // Selects an item title at random by taking an integer random as the arguement
    // return a string title
    public String randomSelectItemTitles(int random) {
        if (random == 1) {
            return "Heal";
        } else if (random == 2) {
            return "Damage Increase";
        } else if (random == 3) {
            return "Speed Increase";
        } else if (random == 4) {
            return "Increase Max Health";
        } else if (random == 5) {
            return "Bullet Speed Increase";
        } else if (random == 6) {
            return "Ice Shot";
        } else if (random == 7) {
            return "Fire Shot";
        }
        return null;
    }

    // Selects an items price at random by taking an integer random as the arguement
    // return an integer price
    public int itemPrice(int random) {
        if (random == 1) {
            return 0;
        } else if (random == 2) {
            return 0;
        } else if (random == 3) {
            return 0;
        } else if (random == 4) {
            return 0;
        } else if (random == 5) {
            return 0;
        } else if (random == 6) {
            return 0;
        } else if (random == 7) {
            return 0;
        }
        return 1;
    }
}
