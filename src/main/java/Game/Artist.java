package Game;

import de.umass.lastfm.Track;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Artist extends Game {

    public Artist(String search, int rounds){
        super(search, rounds);
    }

    @Override
    public void beginGame() {
        Collection<Track> tracks = generate();
        List<String> title = getTitleFromTracks(tracks);
        List<String> artists = getArtistFromTracks(tracks);
        int possibleSongs = title.size();
        int rounds = getRounds();
        int min = Math.min(possibleSongs,rounds);
        for(int i =0; i<min;i++){
            String currentTitle = title.get(i);
            String artist = artists.get(i);
            try {
                String url = getYoutubeLink(currentTitle, artist);
                downloadYoutubeVideo(url);
            }catch (Exception ignored){

            }

        }

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
