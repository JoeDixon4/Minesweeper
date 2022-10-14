import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

//___test
public class MineTest {


    //Name of method is : test_______

    @Test
    public void testBoard(){
        Block[][] testBoard = new Block[10][10];
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                Block gameBlock = new Block();
                testBoard[i][j] = gameBlock;
            }
        }
        //AssertEquals(Expected, Actual, Error Message);

        Assertions.assertEquals(true,testBoard[1][1].getUntouched(), "cells of the board are not being initialised as untouched.");

        testBoard[1][1].touchCell();
        Assertions.assertEquals(false, testBoard[1][1].getUntouched(),"touching cells is working incorrectly.");

        Assertions.assertEquals(false, testBoard[2][2].getFlag(),"getFlag is working incorrectly.");

        testBoard[2][2].giveFlag();
        Assertions.assertEquals(true, testBoard[2][2].getFlag(),"Flags are not being assigned properly.");

        Assertions.assertEquals(false, testBoard[0][0].getBomb(),"Cell are not being initialised without a bomb.");

        testBoard[0][0].giveBomb();
        Assertions.assertEquals(true, testBoard[0][0].getBomb(),"Bombs are not being assigned properly.");

        for(int i = 0; i < 3;i++){
            testBoard[0][2].giveNearby();
        }
        Assertions.assertEquals(3, testBoard[0][2].getNearbyBombs(),"Nearby bombs are not being counted properly.");

    }

}
