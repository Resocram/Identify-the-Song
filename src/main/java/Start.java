import Game.Game;
import java.util.Scanner;
import Game.Artist;
import Game.Charts;
import Game.Tags;
import Game.Guess;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;


public class Start {

    public static void main(String[] args) {
        String[] validGameModes = {"artist", "charts", "tags"};
        String[] validWhatToGuess = {"title", "artist"};
        System.out.println("Please select game mode: [" + validGameModes[0] + "] [" + validGameModes[1] + "] [" + validGameModes[2] + "]");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().toLowerCase();
        while (!validInput(validGameModes,input)) {
            System.out.println("Not a valid input.");
            System.out.println("Please select game mode: [" + validGameModes[0] + "] [" + validGameModes[1] + "] [" + validGameModes[2] + "]");
            scanner = new Scanner(System.in);
            input = scanner.nextLine().toLowerCase();
        }
        String gameMode = input;
        query(gameMode);
    }

    private static void query(String[] validGameModes, String gameMode){
        if(validGameModes[0].equals(gameMode)){
            askArtist();
        }
        else if(validGameModes[1].equals(gameMode)){
            askCharts();
        } else if (validGameModes[2].equals(gameMode)) {
            askTags();
        }
    }

    private static void askArtist(){
        System.out.println("Please enter artist name: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        int rounds = askRounds();
        Artist game = new Artist(input, rounds);
        game.beginGame();
    }

    private static void askCharts(){
        System.out.println("Would you like ");
    }

    private static Guess askGuess(){
        System.out.println("Would you like to guess the [artist] or the [title]?");
        Scanner scanner = new Scanner(System.in);
        //TODO
        return null;


    }

    private static int askRounds(){
        int rounds;
        while(true){
            try{
                System.out.println("Please enter a number");
                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine();
                rounds = Integer.parseInt(input);
                break;
            }catch(Exception e){
                System.out.println("Not valid number");;
            }
        }
        return rounds;
    }


    private static void beginGame(String search, int rounds) {
        Artist game = new Artist(search,rounds);
        game.beginGame();
    }


    private static void beginGame(String[] validGameModes, String gameMode, String whatToGuess, String search, int rounds) {
       if(gameMode.equals(validGameModes[1])) {
           Charts game = new Charts(whatToGuess, search, rounds);
       } else if(gameMode.equals(validGameModes[2])) {
           Tags game = new Tags(whatToGuess, search, rounds);
       }
       else {
           throw new RuntimeException("Invalid game mode");
       }

    }

    private static boolean validInput(String[] validGameModes, String input) {
            for (String gameMode : validGameModes) {
                if (gameMode.equals(input)) {
                    return true;
                }
            }
            return false;
        }
}






