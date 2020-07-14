package MODEL;

public class Piece_Domino {


    private int nbPts1;  // Le nombre des Points 1
    private int nbPts2;  // Le nombre des Points 2


    public Piece_Domino(int nbPts1, int nbPts2) {

        this.nbPts1 = nbPts1;
        this.nbPts2 = nbPts2;



    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece_Domino piece = (Piece_Domino) o;
        return nbPts1 == piece.nbPts1 &&
                nbPts2 == piece.nbPts2;
    }


    public int getNbPts1() {
        return nbPts1;
    }


    public int getNbPts2() {
        return nbPts2;
    }


}
