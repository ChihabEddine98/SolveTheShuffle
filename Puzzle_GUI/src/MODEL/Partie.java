package MODEL;

import java.util.Collections;
import java.util.LinkedList;

public class Partie {

    private Joueur player;
    private int difficulty;
    private String path;

    public Partie( Joueur player, int difficulty, String path ) {

        this.player     = player;
        this.difficulty = difficulty;
        this.path       = path;


    }

    public void initializePlayerPieces( LinkedList<Piece_Puzzle> piece_puzzles ){

        LinkedList<Piece_Puzzle> playerPiece = piece_puzzles;
        Collections.shuffle( playerPiece );
        player.setPieces( playerPiece );
    }


    public Joueur getPlayer() {
        return player;
    }

    public void setPlayer(Joueur player) {
        this.player = player;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
