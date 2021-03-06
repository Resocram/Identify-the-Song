package Game;

import de.umass.lastfm.Tag;
import de.umass.lastfm.Track;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Tags extends Game {

    public Tags(Guess guess, String search, int rounds){
        super(guess, search, rounds);
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
