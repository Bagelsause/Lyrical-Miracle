package genius;


public class Lyrics {

    private final genius.GLA gla;
    private final String path;
    private final String id;

    public Lyrics(genius.GLA gla, String id, String path) {
        this.path = path;
        this.gla = gla;
        this.id = id;
    }

    public String getText() {
        return new LyricsParser(this.gla).get(this.id);
    }

    @Override
    public String toString() {
        return this.path;
    }

}