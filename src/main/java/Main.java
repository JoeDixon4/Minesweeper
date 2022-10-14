import java.util.Scanner;
import java.util.Random;


public class Main {
    public static int getDifficulty(){

        Scanner myObj = new Scanner(System.in);
        int difficultyChoice = 0;
        while (difficultyChoice < 1 || difficultyChoice > 3 ){
            System.out.println("Please choose a difficulty:");
            System.out.println("1.Easy (10x10)");
            System.out.println("2.Medium (20x20)");
            System.out.println("3.Hard (30x30)");

            difficultyChoice = myObj.nextInt();
        }

        return difficultyChoice * 10;
    }
    public static boolean clickBlock(String clickFlag, Block[][] gameBoard, int positionX, int positionY, int difficulty){
        boolean continueGame = true;
        switch (clickFlag) {
            default:
                break;
            case "f":
                gameBoard[positionX][positionY].giveFlag();
                break;
            case "c":
                if(!gameBoard[positionX][positionY].getFlag()){
                    if (gameBoard[positionX][positionY].getBomb()) {
                        gameBoard[positionX][positionY].touchCell();
                        System.out.println("You Have Died!");
                        continueGame = false;
                        break;
                    }else if (gameBoard[positionX][positionY].getUntouched()) {
                        gameBoard[positionX][positionY].touchCell();
                        for (int i = -1; i <= 1; i++) {
                            for (int j = -1; j <= 1; j++) {
                                if (positionX + i >= 0 && positionX + i < difficulty) {
                                    if (positionY + j >= 0 && positionY + j < difficulty) {
                                        if (gameBoard[positionX + i][positionY + j].getBomb()) {
                                            gameBoard[positionX][positionY].giveNearby();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
        }
        if(gameBoard[positionX][positionY].getNearbyBombs() == 0 && clickFlag.equals("c"))
        {
            if(!gameBoard[positionX][positionY].getBomb()){
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        if (positionX + i >= 0 && positionX + i < difficulty) {
                            if (positionY + j >= 0 && positionY + j < difficulty) {
                                if (gameBoard[positionX + i][positionY + j].getUntouched()) {
                                    clickBlock(clickFlag,gameBoard,positionX+i,positionY+j,difficulty);
                                }
                            }
                        }
                    }
                }
            }
        }
        return continueGame;
    }
    public static void playGame(int difficulty){
        boolean continueGame = true;
        Block[][] gameBoard = new Block[difficulty][difficulty];
        for (int i = 0; i < difficulty; i++) {
            for (int j = 0; j < difficulty; j++) {
                Block gameBlock = new Block();
                gameBoard[i][j] = gameBlock;
            }
        }//Making the Cells
        for (int i = 0; i < difficulty; i++){
            System.out.print("    " + i);
        }
        System.out.print("\n");
        for (int i = 0; i < difficulty; i++) {
            System.out.print(" " + i);
            for (int j = 0; j < difficulty; j++) {
                boolean untouched = gameBoard[i][j].getUntouched();
                if (untouched) {
                    System.out.print(ConsoleColours.WHITE_BRIGHT + "  X  " +ConsoleColours.RESET);
                }

            }
            System.out.println("\n");
        }//Drawing the initial Board
        int totalBombs = 0;
        switch (difficulty) {
            case 10://15% of the grid has a bomb

                for (int i = 0; i < 15; i++) {
                    Random randX = new Random();
                    Random randY = new Random();
                    int positionX = randX.nextInt(10);
                    int positionY = randY.nextInt(10);
                    if (gameBoard[positionX][positionY].getBomb()) {
                        i--;//this is a check to see if the cell already has a bomb
                    } else {
                        gameBoard[positionX][positionY].giveBomb();
                        totalBombs++;
                    }
                }
                break;
            case 20://20% of the grid has a bomb
                for (int i = 0; i < 80; i++) {
                    Random randX = new Random();
                    Random randY = new Random();
                    int positionX = randX.nextInt(20);
                    int positionY = randY.nextInt(20);
                    if (gameBoard[positionX][positionY].getBomb()) {
                        i--;//this is a check to see if the cell already has a bomb
                    } else {
                        gameBoard[positionX][positionY].giveBomb();
                        totalBombs++;
                    }
                }
                break;
            case 30://33% of the grid has a bomb
                for (int i = 0; i < 300; i++) {
                    Random randX = new Random();
                    Random randY = new Random();
                    int positionX = randX.nextInt(30);
                    int positionY = randY.nextInt(30);
                    if (gameBoard[positionX][positionY].getBomb()) {
                        i--;//this is a check to see if the cell already has a bomb
                    } else {
                        gameBoard[positionX][positionY].giveBomb();
                        totalBombs++;
                    }
                }
                break;
        }//Planting Bombs
        while (continueGame) {
            System.out.println("Please enter the X position of the cell you would like to click or flag");
            Scanner choiceY = new Scanner(System.in);
            int positionY = choiceY.nextInt();
            System.out.println("Please enter the y position of the cell you would like to click or flag");
            Scanner choiceX = new Scanner(System.in);
            int positionX = choiceX.nextInt();
            System.out.println("Please select if you would like to flag or click the chosen cell [" + positionY + "," + positionX + "] (f/c)");
            Scanner clickChoice = new Scanner(System.in);
            String clickFlag = clickChoice.nextLine();
            continueGame = clickBlock(clickFlag,gameBoard,positionX,positionY,difficulty);
            for(int i = 0; i < difficulty;i++){
                System.out.print("    " + i);
            }
            System.out.print("\n");
            for(int i = 0; i < difficulty; i++) {
                System.out.print(" " + i);
                for (int j = 0; j < difficulty; j++) {
                    boolean flag = gameBoard[i][j].getFlag();
                    boolean untouched = gameBoard[i][j].getUntouched();
                    if (flag) {
                        System.out.print(ConsoleColours.RED_BRIGHT + "  F  " + ConsoleColours.RESET);
                    } else if (untouched) {
                        System.out.print(ConsoleColours.WHITE_BRIGHT + "  X  " + ConsoleColours.RESET);
                    } else{
                        int bombs = gameBoard[i][j].getNearbyBombs();
                        System.out.print(ConsoleColours.YELLOW_BRIGHT + "  " + bombs + "  " + ConsoleColours.RESET);
                    }
                }
                System.out.println("\n");
            }
            int untouchedBombs = 0;
            int flaggedBombs = 0;
            for(int i = 0; i < difficulty; i++){
                for(int j = 0; j < difficulty; j++){
                    if(gameBoard[i][j].getUntouched() == gameBoard[i][j].getBomb()) {
                        untouchedBombs++;
                    }
                    if(gameBoard[i][j].getFlag() == gameBoard[i][j].getBomb()){
                        if(gameBoard[i][j].getBomb())
                        {
                            flaggedBombs++;
                        }
                    }
                }
            }
            if(flaggedBombs == totalBombs || untouchedBombs == totalBombs){
                System.out.print(ConsoleColours.GREEN_BRIGHT + "You win the game!!!!" + ConsoleColours.RESET);
                break;
            }
        }//Gameplay loop. Asking for co-ords and click/flag
    }

    public static void main(String[] args) {
        String replay = "y";
        while(replay.equals("y")){
            int difficulty = getDifficulty();
            playGame(difficulty);
            replay = "x";
            while(!replay.equals("y") && !replay.equals("n")){
                System.out.print(ConsoleColours.WHITE_BRIGHT + "Would you like to play again?(y/n)" + ConsoleColours.RESET);
                Scanner choice = new Scanner(System.in);
                replay = choice.nextLine();
            }
        }
    }
}