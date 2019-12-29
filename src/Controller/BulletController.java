package Controller;
import ObserverPackage.Controller;
import TemplatePackage1.UpdateBulletControllerY;
import entities.*;
import java.util.ArrayList;

public class BulletController implements Controller {

    ArrayList<Bullet> bulletsArray;
    UpdateBulletControllerY update;
    public BulletController() {
        bulletsArray = new ArrayList<>(); 
        update=new UpdateBulletControllerY();
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
