package unisainvaders;

public class PepperController {
    Pepper pepper;
    PepperView pepperView;

    PepperController(Pepper pepper, PepperView pepperView){
        this.pepper = pepper;
        this.pepperView = pepperView;
    }

    public void moveX(int pixels){

    }

    public void moveY(int pixels){

    }

    public void hitDamage(int value){
        pepper.setLife(pepper.getLife()-value);
        pepperView.healthBar.reduce(value);
    }
}
