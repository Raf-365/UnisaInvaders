package Controller;

import TemplatePackage1.UpdateBulletBossControllerY;
import entities.*;
import java.util.ArrayList;

public class BulletBossController implements Controller{
        ArrayList<BulletBoss> bulletsArrayBoss;
        UpdateBulletBossControllerY update;
    public BulletBossController() {
        bulletsArrayBoss = new ArrayList<>();
        update=new UpdateBulletBossControllerY();
    }

    public void deleteBullets(BulletBoss b) {
        bulletsArrayBoss.remove(b);
    }

    @Override
    public void update() {
        update.move((ArrayList)bulletsArrayBoss);
    }

    public ArrayList<BulletBoss> getBulletsArrayBoss() {
        return bulletsArrayBoss;
    }
}
