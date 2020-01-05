import Game.Artists;
import Game.Charts;
import Game.Guess;
import Game.Tags;

import java.util.Scanner;


public class Start {

    public static void main(String[] args) {
        startNewGame();
    }

    public static void startNewGame() {
        String[] validGameModes = {"artist", "charts", "tags"};
        System.out.println("Please select game mode: [" + validGameModes[0] + "] [" + validGameModes[1] + "] [" + validGameModes[2] + "]");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().toLowerCase();
        while (!validInput(validGameModes, input)) {
            System.out.println("Not a valid input.");
            System.out.println("Please select game mode: [" + validGameModes[0] + "] [" + validGameModes[1] + "] [" + validGameModes[2] + "]");
            scanner = new Scanner(System.in);
            input = scanner.nextLine().toLowerCase();
        }
        String gameMode = input;
        query(validGameModes, gameMode);
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
        String artist = scanner.nextLine();
        int rounds = askRounds();
        Artists game = new Artists(artist, rounds);
        if (game.beginGame()) {
            startNewGame();
        } else {
            System.out.println("Thanks for playing");
        }
    }

    private static void askCharts(){
        Guess guess = askGuess();
        int rounds = askRounds();
        Charts game = new Charts(guess,rounds);
        if (game.beginGame()) {
            startNewGame();
        } else {
            System.out.println("Thanks for playing");
        }
    }

    private static void askTags(){
        Guess guess = askGuess();
        System.out.println("Please enter tag: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        int rounds = askRounds();
        Tags game = new Tags(guess,input,rounds);
        if (game.beginGame()) {
            startNewGame();
        } else {
            System.out.println("Thanks for playing");
        }

    }

    private static Guess askGuess(){
        while (true) {
            System.out.println("Would you like to guess the [artist] or the [title]?");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if(Guess.isValid(input)){
                return Guess.getGuess(input);
            }else{
                System.out.println("Not valid input");
            }
        }
    }

    private static int askRounds(){
        int rounds;
        while(true){
            try{
                System.out.println("Please enter the number of rounds: ");
                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine();
                rounds = Integer.parseInt(input);
                break;
            }catch(Exception e){
                System.out.println("Not valid number");
            }
        }
        return rounds;
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






