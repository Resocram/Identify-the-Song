package Game;

public enum Guess {
    ARTIST,
    SONGS;


    public boolean isValid(String string){
        return string.toLowerCase().equals("artist") || string.toUpperCase().equals("songs");
    }
}
