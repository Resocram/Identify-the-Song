# What's that tune?
**Version 1.0.0**

Identify the Song is a music recognition game that is played in the terminal. The program plays a 30s snippet of a song and you have to try
to guess either the artist or the title of the track.

## Installation
```bash
# Clone this repository into a folder

# Add the appropriate classpath

# Navigate to ./src/main/java

# Run Start.java
javac Start.java
```

## Starting the game
Choose how you would like to filter the songs.
You can choose the songs based on an artist, the current billboard top hits, or from a tag.


![Instruction1](https://i.imgur.com/APml49Z.png "Choose Artist, Charts ,or Tags")

### Artist
If you pick artist, you will need to specify the artist name and the number of rounds you would like to play.

![Artist](https://i.imgur.com/cfzef2Z.png "Artist")

### Charts
If you pick charts, you will need to specify what you would like to guess, and the number of rounds you would like to play.

![Charts](https://i.imgur.com/iQDh1IN.png "Charts")


### Tags

If you pick tags, you will need to specify what you would like to guess, the tag you want to query, and the number of rounds you would like to play.

![Tags](https://i.imgur.com/TNzfLrt.png "Tags")

## Acknowledgements

[Deezer](https://developers.deezer.com/api)
* Provided the 30s mp3 playback URL for the songs

[Last.fm](https://www.last.fm/api/)
* Provided the info for each song e.g. artist, title, current hits

[Last.fm Wrapper](https://github.com/jkovacs/lastfm-java)
* Licensed wrapper for the Last.fm api

[Unirest](http://unirest.io/)
* Helped handle HTTP Requests
