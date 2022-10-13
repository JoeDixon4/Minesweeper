import java.util.Scanner;
import java.io.IOException;

public class Block {
    //Attributes
    boolean bomb = false;
    boolean flag = false;
    boolean untouched = true;
    int nearbyBombs;

    //Constructor
    public Block(){

    }

    //Methods
    public void giveBomb(){
        this.bomb = true;
    }
    public void giveFlag(){
        this.flag = !this.flag;
    }
    public boolean getFlag(){
        if(this.flag == true){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean getBomb(){
        if(this.bomb == true){
            return true;
        }
        else{
            return false;
        }
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
