/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author marcopreziosi
 */
public class HudController {

    private float score;
    private int health, bonus, malus;
    public static final int MALUS = -10, BONUS = 1;

    public HudController(int health) {
        score = 0;
        malus = 0;
        bonus = 0;
        this.health = health;

    }

    public float getScore() {
        return score;
    }

    public int getHealth() {
        return health;
    }

    public int getBonus() {
        return bonus;
    }

    public int getMalus() {
        return malus;
    }

    private void updateDefault() {
        score += 0.01;
    }

    public void update() {
        updateDefault();
    }

    public void updateScore(int score) {
        this.score += score;
    }

    public void updateHealth(int health) {
        this.health += health;
    }

    public void updateBonus(int bonus) {
        this.bonus += bonus;
    }

    public void updateMalus(int malus) {
        this.malus += malus;
    }
}
