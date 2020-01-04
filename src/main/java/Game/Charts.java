package Game;

import de.umass.lastfm.Chart;
import de.umass.lastfm.PaginatedResult;
import de.umass.lastfm.Track;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Charts extends Game {
    public Charts(Guess guess, int rounds){
        super(guess, rounds);
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
        PaginatedResult<Track> songs = Chart.getTopTracks(key);
        ArrayList songsList = new ArrayList(songs.getPageResults());
        Collections.shuffle(songsList);
        return songsList;
    }
}
