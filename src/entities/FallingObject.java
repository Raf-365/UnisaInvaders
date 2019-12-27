package entities;

public abstract class FallingObject extends Entity {
    private boolean checkCollision;
    
    
    public FallingObject(){
        
    }
    
    public FallingObject(int x, int y, String path) {
        super(x, y, true, path);
        checkCollision = false;
    }

    public boolean getCheckCollision() {
        return this.checkCollision;
    }

    public void setChekCollision(boolean check) {
        this.checkCollision = check;
    }
}
