public class Abillities {
    public boolean iceShotEnabled = false;
    public boolean fireShotEnabled = false;
    int iceShotTimesBought = 0;
    int fireShotTimesBought = 0;
    int pierceTimesBought = 0;
    double priceIncrease1 = 1.00;
    double priceIncrease2 = 1.00;
    double priceIncrease3 = 1.00;
    double priceIncrease4 = 1.00;
    double priceIncrease5 = 1.00;
    double priceIncrease6 = 1.00;
    double priceIncrease7 = 1.00;
    double priceIncrease8 = 1.00;
    double priceIncrease9 = 1.00;
    int bulletDelay = 25;

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
        Game.playerHealth += 5;
    }

    // Method that increases the speed a bullet travels at
    public void bulletSpeedIncrease() {
        Game.speedBoost++;
    }

    // Enables the ice shot ability which give a basic enemy with a freezing affect
    // when hit by a bullet
    public void iceShot() {
        iceShotEnabled = true;
        iceShotTimesBought++;
    }

    // Enables the fire shot ability which give a basic enemy with a burning affect
    // when hit by a bullet
    public void fireShot() {
        fireShotEnabled = true;
        fireShotTimesBought++;
    }

    // Reduces the delay on bullets by -5 each time called
    public void reduceBulletDelay() {
        bulletDelay -= 5;
    }

    // Adds to the total amount of times piece ability is bought
    public void bulletPierce() {
        pierceTimesBought++;
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
        } else if (random == 8) {
            return "Reduce Bullet Delay";
        } else if (random == 9) {
            return "Bullet Pierce";
        }

        return null;
    }

    // Selects an items price at random by taking an integer random as the arguement
    // return an integer price
    public int itemPrice(int random) {
        if (random == 1) {
            return (int) (2000 * priceIncrease1);
        } else if (random == 2) {
            return (int) (10000 * priceIncrease2);
        } else if (random == 3) {
            return (int) (7000 * priceIncrease3);
        } else if (random == 4) {
            return (int) (15000 * priceIncrease4);
        } else if (random == 5) {
            return (int) (9000 * priceIncrease5);
        } else if (random == 6) {
            return (int) (30000 * priceIncrease6);
        } else if (random == 7) {
            return (int) (30000 * priceIncrease7);
        } else if (random == 8) {
            return (int) (10000 * priceIncrease8);
        } else if (random == 9) {
            return (int) (15000 * priceIncrease8);
        }
        return 1;
    }
}
