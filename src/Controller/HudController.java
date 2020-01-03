package Controller;


public class HudController implements Controller{

    private float score;
    private int bonus, malus;
    public static final int MALUS = -10,KILLED_BOSS = 50, BONUS = 1;
    private static final double SCORE_UPDATE = 0.02;
   
    public HudController(int health) {
        score = 0;
        malus = 0;
        bonus = 0;

    }
   
    public float getScore() {
        return score;
    }

    public int getBonus() {
        return bonus;
    }

    public int getMalus() {
        return malus;
    }

    private void updateDefault() {
        score += SCORE_UPDATE;
    }

    public void update() {
        updateDefault();
    }

    public void updateScore(int score) {
        this.score += score;
    }

    public void updateBonus(int bonus) {
        this.bonus += bonus;
    }

    public void updateMalus(int malus) {
        this.malus += malus;
    }
}
