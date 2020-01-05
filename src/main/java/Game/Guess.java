package Game;

public enum Guess {
    ARTIST {

    },
    TITLE {

    };


    public static boolean isValid(String string) {
        return string.toLowerCase().equals("artist") || string.toLowerCase().equals("title");
    }

    public static Guess getGuess(String string) {
        if (string.toLowerCase().equals("artist")) {
            return Guess.ARTIST;
        } else {
            return Guess.TITLE;
        }
    }
}
