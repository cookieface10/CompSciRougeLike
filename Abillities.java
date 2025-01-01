public class Abillities {
    public boolean iceShotEnabled = false;
    public boolean fireShotEnabled = false;

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
}
