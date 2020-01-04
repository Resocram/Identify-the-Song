package Game;

import de.umass.lastfm.Track;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public abstract class Game {
    private String apiKEY = "934e465fcc193d8ad5b8d052b6864b14";
    private String user = "Resocram";
    private String whatToGuess;
    private String search;
    private int rounds;

    Game(int rounds){
        this.rounds = rounds;
    }

    Game(String search, int rounds){
        this.search = search;
        this.rounds = rounds;
    }

    Game(String whatToGuess, String search, int rounds){
        this.whatToGuess = whatToGuess;
        this.search = search;
        this.rounds = rounds;
    }

    protected abstract void beginGame();
    protected abstract Collection<Track> generate();

    protected List<String> getTitleFromTracks(Collection<Track> tracks){
        List<String> title = new ArrayList<>();
        for(Track track: tracks){
            title.add(track.getName());
        }
        return title;
    }

    protected List<String> getArtistFromTracks(Collection<Track> tracks){
        List<String> title = new ArrayList<>();
        for(Track track: tracks){
            title.add(track.getArtist());
        }
        return title;
    }

    protected String getApiKEY(){
        return apiKEY;
    }

    protected String getUser(){
        return user;
    }

    protected String getWhatToGuess(){
        return whatToGuess;
    }

    protected String getSearch(){
        return search;
    }

    protected int getRounds(){
        return rounds;
    }


}
