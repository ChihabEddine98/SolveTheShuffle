package MODEL;

import java.util.LinkedList;

public class Pioche {


    private LinkedList<Piece_Domino> listePieces; // La liste des pieces a piochés



    public Pioche ( LinkedList<Piece_Domino> listePieces ) {

        this.listePieces = listePieces;
    }



    public boolean isEmpty()
    {
        return listePieces.isEmpty();
    }

    public int taillePioche()
    {
        return listePieces.size();
    }

    public boolean retirerPiece(Piece_Domino p)
    {
        return listePieces.remove(p);
    }


    public boolean contient(int i,int j)
    {
        boolean sortie=false;

        for (Piece_Domino p:listePieces)
        {
            if(p.getNbPts1()==i && p.getNbPts2()==j)
            {
                sortie=true;
            }
        }

        return sortie;
    }



    public LinkedList<Piece_Domino> getListePieces() {
        return listePieces;
    }

    public void setListePieces(LinkedList<Piece_Domino> listePieces) {
        this.listePieces = listePieces;
    }


}
