import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameLogic {


    Board board = new Board();

    // could add the following to a class constructor
    Player player1 = new HumanPlayer(Token.HUMAN_PLAYER.label, "Human Player");
    Player player2 = new ComputerPlayer(Token.COMPUTER_PLAYER.label, "Computer Player");
    List<Player> players = new ArrayList<>();

    public void playGame(){
        System.out.println("Welcome to Connect 4");
        System.out.println("There are 2 players red and yellow");
        System.out.println("Player 1 is Red, Player 2 is Yellow");
        System.out.println("To play the game type in the number of the column you want to drop you counter in");
        System.out.println("A player wins by connecting 4 counters in a row - vertically, horizontally or diagonally\n");

        players.add(player1);
        players.add(player2);


        Player currentPlayer = null;

        while(true){

            if (currentPlayer == null) {
                currentPlayer = player1;
            }

            System.out.println(currentPlayer.getName() + " it is your turn.");
            System.out.println(board.printBoard());
            int col = 0;
            boolean inputIsValid = false;

            while(!inputIsValid) {
                try {
                    col = currentPlayer.takeTurn();
                    inputIsValid = true;
                } catch(ArrayIndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println("Please make sure you enter a number between 1 and 7");
                    System.out.println("Try again " + currentPlayer.getName());
                    System.out.println(board.printBoard());
                }
            }

            boolean placed = board.placeToken(currentPlayer,col);

            if (placed) {

                if(board.hasPlayerWon(currentPlayer)) {
                    System.out.println("Congratulations " + currentPlayer.getName() + ". You Have Won!!!");
                    System.out.println(board.printBoard());
                    break;
                }
                currentPlayer = board.switchPlayer(currentPlayer, players);

            } else {
                System.out.println("Column full: please try a different column");
            }
        }
    }
}