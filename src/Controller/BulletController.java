package Controller;
import TemplatePackage1.*;
import entities.*;
import java.util.ArrayList;

public class BulletController implements Controller {

    ArrayList<Bullet> bulletsArray;
    UpdateBulletControllerY update;
    public BulletController() {
        this.bulletsArray = new ArrayList<>(); 
        this.update=new UpdateBulletControllerY();
    }

    public void deleteBullets(Bullet b) {
        bulletsArray.remove(b);
    }

    @Override
    public void update() {
        update.move((ArrayList)bulletsArray);
    }

    public ArrayList<Bullet> getBulletsArray() {
        return bulletsArray;
    }
}
