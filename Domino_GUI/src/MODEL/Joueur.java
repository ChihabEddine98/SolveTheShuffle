package MODEL;

import java.util.LinkedList;

public class Joueur {


    private String nom;                                // Le nom du joueur !
    private int age;                                   // L'age du joueur  !
    private int score;                                 // Le score du joueur !
    private LinkedList<Piece_Domino> pieces;           // Les pieces du joueurs
    private boolean visible;



    public Joueur ( String nom, int age ) {

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
        return "\nJoueur \n" +
                "{" +
                "\n nom='" + nom + '\'' +
                "\n age=" + age +
                "\n score=" + score +
                '}'+"\n";
    }

    public boolean ajouterPiece ( Piece_Domino p ) {
        if (pieces==null) return false;
        return pieces.add(p) ;

    }

    public boolean retirerPiece( Piece_Domino p ) {

        return pieces.remove(p);
    }

    public Piece_Domino getPiece(int i, int j) {

        Piece_Domino sortie=null;

        for (Piece_Domino p:pieces) {

            if((p.getNbPts1()==i && p.getNbPts2()==j) ||(p.getNbPts1()==j && p.getNbPts2()==i)) {
                sortie=p;
            }
        }

        return sortie;
    }


    //---------------------------------------------------------
    // Getters Et Setters !


    public String getNom() {

        return nom;
    }

    public int getAge() {

        return age;
    }


    public LinkedList<Piece_Domino> getPieces() {

        return pieces;
    }

    public void setPieces ( LinkedList<Piece_Domino> pieces ) {

        this.pieces = pieces;
    }

    public boolean isVisible() {

        return visible;
    }

    public void setVisible( boolean visible ) {

        this.visible = visible;
    }

    public int getScore() {

        return score;
    }

    public void setScore(int score) {

        this.score = score;
    }

}
