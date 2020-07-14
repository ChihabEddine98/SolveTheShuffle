package MODEL;

import java.util.LinkedList;

public class Joueur {


    private String nom;                                // Le nom du joueur !
    private int age;                                   // L'age du joueur  !
    private int score;                                 // Le score du joueur !
    private LinkedList<Piece_Puzzle> pieces;           // Les pieces du joueurs
    private boolean visible;



    public Joueur ( String nom , int age ) {

        this.nom = nom;
        this.age = age;
        pieces = new LinkedList<>();
        this.visible = false;
        this.score = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Joueur joueur = (Joueur) o;
        return getAge() == joueur.getAge() &&
                isVisible() == joueur.isVisible() &&
                getNom().equals(joueur.getNom()) &&
                getPieces().equals(joueur.getPieces());
    }


    //--------------- Méthodes ------------------------------


    @Override
    public String toString() {
        return "\nMODEL.Joueur \n" +
                "{" +
                "\n nom='" + nom + '\'' +
                "\n age=" + age +
                "\n score=" + score +
                '}'+"\n";
    }

    public boolean ajouterPiece( Piece_Puzzle p ) {
        if ( pieces == null ) return false;
        return pieces.add(p) ;

    }

    public boolean retirerPiece( Piece_Puzzle p )
    {
        return pieces.remove( p );
    }


    //---------------------------------------------------------
    // Getters Et Setters !


    public String getNom() {
        return nom;
    }

    public int getAge() {
        return age;
    }

    public LinkedList<Piece_Puzzle> getPieces() {
        return pieces;
    }

    public void setPieces(LinkedList<Piece_Puzzle> pieces) {
        this.pieces = pieces;
    }

    public boolean isVisible() {
        return visible;
    }


}
