package Game;

import de.umass.lastfm.Tag;
import de.umass.lastfm.Track;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Tags extends Game {

    public Tags(Guess guess, String search, int rounds){
        super(guess, search, rounds);
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
    protected Collection<Track> generate() {
        String key = getApiKEY();
        String tag = getSearch();
        Collection<Track> songs = Tag.getTopTracks(tag,key);
        ArrayList songsList = new ArrayList(songs);
        Collections.shuffle(songsList);
        return songsList;
    }
}
