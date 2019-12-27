package entities;



public abstract class FallingObject extends Entity {
    private boolean checkCollision;
    int y0;
    public static final int FALLING_OBJECT_SPEED = 3,FALLING_OBJECT_SPEED_UPDATE=1 ;
    static int speed;
    
    public FallingObject(){
        
    }
    
    public FallingObject(int x, int y, String path) {
        super(x, y, true, path);
        speed=FALLING_OBJECT_SPEED;
        y0 = y;
        checkCollision = false;
    }

    public boolean getCheckCollision() {
        return this.checkCollision;
    }

    public void setChekCollision(boolean check) {
        this.checkCollision = check;
    }
    
    public static void setSpeed(int speed) {
        FallingObject.speed = speed;
    }
    
    public static int getSpeed(){return speed;}
    
    public static void updateSpeed(){speed+=FALLING_OBJECT_SPEED_UPDATE;}
    
    public int getY0() {return this.y0;}
    
}
