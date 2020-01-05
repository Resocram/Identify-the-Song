package Audio;

import javazoom.jl.player.Player;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class Audio implements Runnable {
    private String url;
    private boolean reset;

    public Audio(String url) {
        this.url = url;
        this.reset = false;
    }

    @Override
    public void run() {
        try {
            try {
                URLConnection conn = new URL(url).openConnection();
                InputStream is = conn.getInputStream();
                OutputStream outstream = new FileOutputStream(new File("./src/main/local/Song.mp3"));
                byte[] buffer = new byte[4096];
                int len;
                while ((len = is.read(buffer)) > 0) {
                    outstream.write(buffer, 0, len);
                }
                outstream.close();
            } catch (Exception e) {
                System.out.println("Could not download music");
            }
            try {
                FileInputStream fileInputStream = new FileInputStream("./src/main/local/Song.mp3");
                Player player = new Player(fileInputStream);
                while (player.play(1)) {
                    if (reset) {
                        break;

                    }
                }
                setReset();

            } catch (Exception e) {
                System.out.println("Could not play music");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public boolean getReset() {
        return reset;
    }

    public void setReset() {
        this.reset = true;
    }
}
