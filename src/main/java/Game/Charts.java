package Game;

import de.umass.lastfm.Chart;
import de.umass.lastfm.PaginatedResult;
import de.umass.lastfm.Track;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Charts extends Game {
    public Charts(Guess guess, int rounds){
        super(guess, rounds);
    }


    @Override
    protected Collection<Track> generate() {
        String key = getApiKEY();
        PaginatedResult<Track> songs = Chart.getTopTracks(key);
        ArrayList songsList = new ArrayList(songs.getPageResults());
        Collections.shuffle(songsList);
        return songsList;
    }
}
