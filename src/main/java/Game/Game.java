package Game;

import Audio.Audio;
import JSON.Data;
import JSON.Response;
import com.google.gson.Gson;
import de.umass.lastfm.Track;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;


public abstract class Game {
    private String apiKEY = "934e465fcc193d8ad5b8d052b6864b14";
    private Guess guess;
    private String search;
    private int rounds;


    Game(Guess guess, int rounds) {
        this.guess = guess;
        this.rounds = rounds;
    }

    Game(String search, int rounds) {
        this.guess = Guess.TITLE;
        this.search = search;
        this.rounds = rounds;
    }

    Game(Guess guess, String search, int rounds) {
        this.guess = guess;
        this.search = search;
        this.rounds = rounds;
    }

    protected abstract Collection<Track> generate();

    protected String getNiceString(String title) {
        String validChars = "abcdefghijklmnopqrstuvwxyz ',.!";
        StringBuilder niceTitle = new StringBuilder();
        for (int i = 0; i < title.length(); i++) {
            char c = title.charAt(i);
            if (!validChars.contains(Character.toString(c))) {
                break;
            }
            niceTitle.append(c);
        }
        int length = niceTitle.length();
        if (niceTitle.substring(length, length).equals(" ")) {
            return niceTitle.substring(0, length);
        }
        return niceTitle.toString();
    }


    protected List<String> getTitleFromTracks(Collection<Track> tracks) {
        List<String> title = new ArrayList<>();
        for (Track track : tracks) {
            title.add(track.getName());
        }
        return title;
    }

    protected List<String> getArtistFromTracks(Collection<Track> tracks) {
        List<String> title = new ArrayList<>();
        for (Track track : tracks) {
            title.add(track.getArtist());
        }
        return title;
    }

    public boolean beginGame() {
        Collection<Track> tracks = generate();
        List<String> title = getTitleFromTracks(tracks);
        List<String> artists = getArtistFromTracks(tracks);
        int possibleSongs = title.size();
        int rounds = getRounds();
        int min = Math.min(possibleSongs, rounds);
        if (min == possibleSongs) {
            System.out.println("There are only " + min + " songs available");
        }
        int score = 0;
        for (int i = 0; i < min; i++) {
            System.out.println("Current score = " + score);
            System.out.println("Playing song " + i + " out of " + min);
            System.out.println("Type [skip] if you would like to skip");
            String currentTitle = title.get(i);
            String currentArtist = artists.get(i);
            String previewURL = getPreviewURL(currentTitle, currentArtist);
            if (previewURL != null) {
                Audio audio = new Audio(previewURL);
                Thread thread = new Thread(audio);
                thread.start();
                String answer;
                if (guess.equals(Guess.ARTIST)) {
                    answer = currentArtist.toLowerCase();
                } else {
                    answer = getNiceString(currentTitle.toLowerCase()).toLowerCase();
                }
                boolean won = false;
                while (!audio.getReset()) {
                    Scanner scanner = new Scanner(System.in);
                    String input = scanner.nextLine().toLowerCase();
                    if (input.toLowerCase().equals("skip")) {
                        System.out.println("You stopped");
                        audio.setReset();
                        break;
                    } else if (input.contains(answer)) {
                        System.out.println("You guessed correctly!");
                        won = true;
                        score++;
                        audio.setReset();
                        break;
                    } else {
                        System.out.println("Incorrect guess");
                    }
                }
                if (!won) {
                    System.out.println("Correct answer: " + answer);
                }
            }

        }
        System.out.println("You scored " + score + "/" + min);
        System.out.println("Would you like to play again? [y] or [n]");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.toLowerCase().equals("y") && !input.toLowerCase().equals("n")) {
            System.out.println("Not a valid input");
            System.out.println("Would you like to play again? [y] or [n]");
            input = scanner.nextLine();
        }
        return input.toLowerCase().equals("y");

    }


    protected String getPreviewURL(String title, String artist) {

        HttpResponse<String> response = Unirest.get("https://deezerdevs-deezer.p.rapidapi.com/search?q=" + title + "+" + artist)
                .header("x-rapidapi-host", "deezerdevs-deezer.p.rapidapi.com")
                .header("x-rapidapi-key", "ca78b3646fmsh0d4c6d4ced25555p16612ejsne9e69d87bcdd")
                .asString();

        String JSON = response.getBody();
        Gson gson = new Gson();
        Response responseJSON = gson.fromJson(JSON, Response.class);
        try {
            Data data = responseJSON.getData()[0];
            return data.getPreview();
        } catch (Exception e) {
            System.out.println("Can't open song");
            return null;
        }

    }


    protected String getApiKEY() {
        return apiKEY;
    }

    protected Guess getGuess() {
        return guess;
    }

    protected String getSearch() {
        return search;
    }

    protected int getRounds() {
        return rounds;
    }


}
