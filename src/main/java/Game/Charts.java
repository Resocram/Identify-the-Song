package Game;

import de.umass.lastfm.PaginatedResult;
import de.umass.lastfm.Track;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Charts extends Game {
    public Charts(String whatToGuess, int rounds){
        super(whatToGuess, rounds);
    }

    @Override
    protected void beginGame() {

    }

    @Override
    protected Collection<Track> generate() {
        String key = getApiKEY();
        String search = getSearch();
        PaginatedResult<Track> songs = de.umass.lastfm.Chart.getTopTracks(key);
        ArrayList songsList = new ArrayList(songs.getPageResults());
        Collections.shuffle(songsList);
        return songsList;
    }
}
