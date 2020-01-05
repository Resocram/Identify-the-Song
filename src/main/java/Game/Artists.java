package Game;

import de.umass.lastfm.Track;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Artists extends Game {

    public Artists(String search, int rounds) {
        super(search, rounds);
    }

    @Override
    protected Collection<Track> generate() {
        String key = getApiKEY();
        String search = getSearch();
        Collection<Track> songs = de.umass.lastfm.Artist.getTopTracks(search, key);
        ArrayList songsList = new ArrayList(songs);
        Collections.shuffle(songsList);
        return songsList;
    }
}
