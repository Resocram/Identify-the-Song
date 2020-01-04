package Game;

public enum Guess {
    ARTIST {
        public boolean equals(String string) {
            return string.toLowerCase().equals("artist");
        }
    },
    TITLE {
        public boolean equals(String string) {
            return string.toLowerCase().equals("title");
        }
    };


    public static boolean isValid(String string) {
        return string.toLowerCase().equals("artist") || string.toLowerCase().equals("title");
    }

    public static Guess getGuess(String string) {
        if (ARTIST.equals(string)) {
            return Guess.ARTIST;
        } else {
            return Guess.TITLE;
        }
    }
}
