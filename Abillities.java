public class Abillities {
    public boolean iceShotEnabled = false;
    public boolean fireShotEnabled = false;
    long price1 = 0;
    long price2 = 0;
    long price3 = 0;

    public void heal() {
        Game.playerHealth = Game.playerMaxHealth;
    }

    public void movementIncrease() {
        Game.speed += 1;
    }

    public void attackIncrease() {
        Game.damageBoost += 1;
    }

    public void increaseHealth() {
        Game.playerMaxHealth += 5;
    }

    public void bulletSpeedIncrease() {
        Game.speedBoost++;
    }

    public void iceShot() {
        iceShotEnabled = true;
    }

    public void fireShot() {
        fireShotEnabled = true;
    }

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

    public int itemPrice(int random) {
        if (random == 1) {
            return 3000;
        } else if (random == 2) {
            return 10000;
        } else if (random == 3) {
            return 10000;
        } else if (random == 4) {
            return 10000;
        } else if (random == 5) {
            return 10000;
        } else if (random == 6) {
            return 25000;
        } else if (random == 7) {
            return 25000;
        }
        return 1;
    }
}
