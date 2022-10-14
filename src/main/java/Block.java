
public class Block {
    //Attributes
    boolean bomb = false;
    boolean flag = false;
    boolean untouched = true;
    int nearbyBombs;
    //Constructor
    public Block(){}
    //Methods
    public void giveBomb(){
        this.bomb = true;
    }
    public void giveFlag(){
        this.flag = !this.flag;
    }
    public boolean getFlag(){
        return this.flag;
    }
    public boolean getBomb(){
    return this.bomb;
    }
    public boolean getUntouched(){
       return this.untouched;
    }
    public void touchCell(){
        this.untouched = false;
    }
    public void giveNearby(){
        this.nearbyBombs++;
    }
    public int getNearbyBombs(){
        return this.nearbyBombs;
    }
}
