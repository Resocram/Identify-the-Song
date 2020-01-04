package Game;

import de.umass.lastfm.Track;
import java.util.Collection;

public class Tags extends Game {

    public Tags(String whatToGuess, String search, int rounds){
        super(whatToGuess, search, rounds);
    }

    @Override
    protected void beginGame() {

    }

    @Override
    protected Collection<Track> generate() {
        return null;
    }
}
