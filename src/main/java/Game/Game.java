package Game;

import com.github.kiulian.downloader.YoutubeDownloader;
import com.github.kiulian.downloader.YoutubeException;
import com.github.kiulian.downloader.model.VideoDetails;
import com.github.kiulian.downloader.model.YoutubeVideo;
import com.github.kiulian.downloader.model.formats.Format;
import com.github.kiulian.downloader.model.formats.VideoFormat;
import com.github.kiulian.downloader.model.quality.VideoQuality;
import de.umass.lastfm.Track;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public abstract class Game {
    private String apiKEY = "934e465fcc193d8ad5b8d052b6864b14";
    private Guess guess;
    private String search;
    private int rounds;


    Game(Guess guess, int rounds){
        this.guess = guess;
        this.rounds = rounds;
    }

    Game(String search, int rounds){
        this.search = search;
        this.rounds = rounds;
    }

    Game(Guess guess, String search, int rounds){
        this.guess = guess;
        this.search = search;
        this.rounds = rounds;
    }

    public abstract void beginGame();
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

    protected String getYoutubeLink(String title, String artist) throws UnsupportedEncodingException {
        String base_url = "https://www.youtube.com/results?search_query=";
        String url = base_url + URLEncoder.encode(title + " " + artist + " " + "audio","UTF-8");
        return url;
    }


    protected void downloadYoutubeVideo(String search) throws IOException, YoutubeException, GeneralSecurityException {
        String ID = ApiExample.getVideoID(search);

        YoutubeVideo video = YoutubeDownloader.getVideo(ID);

// filtering formats
        List<VideoFormat> videoFormats = video.findVideoWithQuality(VideoQuality.hd720);
        videoFormats.forEach(it -> {
            System.out.println(it.videoQuality() + " : " + it.url());
        });


// downloading
        File outputDir = new File("C:/Users/marco/Desktop/Identify the Song/src/Lol");


// async downloading

        video.downloadAsync(videoFormats.get(0), outputDir, new YoutubeDownloader.DownloadCallback() {
            @Override
            public void onDownloading(int progress) {
                System.out.printf("Downloaded %d%%\n", progress);
            }

            @Override
            public void onFinished(File file) {
                System.out.println("Finished file: " + file);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("Error: " + throwable.getLocalizedMessage());
            }
        });
    }


    protected String getApiKEY(){
        return apiKEY;
    }

    protected Guess getGuess(){
        return guess;
    }

    protected String getSearch(){
        return search;
    }

    protected int getRounds(){
        return rounds;
    }


}
