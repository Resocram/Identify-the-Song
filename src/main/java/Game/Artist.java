package Game;

import de.umass.lastfm.Track;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Artist extends Game {
    private String whatToGuess;

    public Artist(String search, int rounds){
        super(search, rounds);
    }

    @Override
    public void beginGame() {
        Collection<Track> tracks = generate();
        List<String> title = getTitleFromTracks(tracks);
        int possibleSongs = title.size();
        int rounds = getRounds();
        int min = Math.min(possibleSongs,rounds);
        for(int i =0; i<min;i++){
            System.out.println(title.get(i));
        }

    }

    @Override
    public Collection<Track> generate() {
        String key = getApiKEY();
        String search = getSearch();
        Collection<Track> songs = de.umass.lastfm.Artist.getTopTracks(search, key);
        ArrayList songsList = new ArrayList(songs);
        Collections.shuffle(songsList);
        return songsList;
    }
}
