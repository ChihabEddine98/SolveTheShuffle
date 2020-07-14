package MODEL;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Partie {



    private LinkedList<Joueur> joueurs;
    private LinkedList<Piece_Domino> piecesTemporaires;
    private Pioche pioche;
    private LinkedList<Piece_Domino> pcsPosee;


    public Partie( LinkedList<Joueur> joueurs ) {

        this.joueurs = joueurs;
        this.piecesTemporaires = new LinkedList<>();
        this.pcsPosee = new LinkedList<>();


        initializeGame();


    }

    public void initializeGame(){

        initializePieces();

        LinkedList<Piece_Domino> pcs = new LinkedList<>();

        int i = 0;

        LinkedList<Piece_Domino> piecesPioche = new LinkedList<Piece_Domino>();

        int[] t1 = genereNums(28, 0, 27);

        while ( i < 7 ) {

            joueurs.get(0).ajouterPiece( piecesTemporaires.get( t1[i] ) );
            piecesPioche.add( piecesTemporaires.get( t1[i] ) );
            i += 1;

        }

        i = 7;

        while ( i < 14 ) {

            joueurs.get(1).ajouterPiece( piecesTemporaires.get( t1[i] ) );
            piecesPioche.add(piecesTemporaires.get( t1[i] ) );

            i += 1;
        }

        // On doit remplir la pioche !

        Iterator<Piece_Domino> itP = piecesTemporaires.iterator();

        while ( itP.hasNext() ) {

            Piece_Domino p = itP.next();

            if( !piecesPioche.contains( p ) ) {

                pcs.add( p );
            }
        }

        // Maintenant il faut mélanger la MODEL.Pioche !

        Collections.shuffle( pcs );

        pioche = new Pioche(pcs);
    }

    public void initializePieces(){

        int i=0;
        int j;

        while ( i<7 ) {

            j = i;

            while ( j<7 ) {

                piecesTemporaires.add( new Piece_Domino(i,j) );
                j++;

            }

            i++;

        }
    }

    public int[] genereNums ( int nombre, int min, int max) {
        /** On va mélanger les valeurs de tab et les mettrent dans un tableau matricules
         nombre    : nombre des matrciules a generer
         min-max   : la plage des matricules qui convient
         tab       : un tableau trié  (contient des valeurs sans doublans )
         matricules: un tableau non trié (contient des valeurs sans doublans )**/


        int[] tab, matricules;
        int i, ind, ecart;
        ecart = max - min;

        if (min >= max || nombre > ecart + 1 || nombre < 1)
            return null;

        tab = new int[ecart + 1];
        matricules = new int[ecart + 1];

        for (i = 0; i < ecart + 1; i++)
            tab[i] = i + min;                       /** on remplie la table tab par les nombres de
     l'intervalle [min;max] */
        for (i = 0; i < nombre; i++) {
            ind = ThreadLocalRandom.current().nextInt(0, ecart + 1); /** On génere un indice aléatoire
             qui ne dépasse pas notre ecart+1 */
            matricules[i] = tab[ind];            /** On affecte notre valeur d'indice donné dans
             la nouvelle table Matricules */
            tab[ind] = tab[ecart];
            ecart--;
        }

        return matricules;
    }

    public Joueur whosYounger() {

        Joueur player1 = joueurs.get(0);
        Joueur player2 = joueurs.get(1);

        if ( player1.getAge() < player2.getAge() ){
            return player1;
        }

        return player2;
    }

    public boolean canPlayTwoWays ( Piece_Domino p ) {

        boolean ok=false;

        if( pcsPosee.isEmpty() ) { // La premiere piece jouée !

            ok = false;

        } else {

            Piece_Domino first=pcsPosee.getFirst();
            Piece_Domino last=pcsPosee.getLast();

            if ( (first.getNbPts1()==p.getNbPts1() || first.getNbPts1()==p.getNbPts2()) && (last.getNbPts2()==p.getNbPts1() || last.getNbPts2()==p.getNbPts2()) ) {
                ok = true;
            }
        }




        return ok;
    }

    public boolean canPlayPiece ( Piece_Domino p )  {

        boolean ok = false;

        if( pcsPosee.isEmpty() ) {

            ok = true;

        } else {

            Piece_Domino first = pcsPosee.getFirst();
            Piece_Domino last = pcsPosee.getLast();

            if ( first.getNbPts1() == p.getNbPts1() || first.getNbPts1() == p.getNbPts2() ) {

                ok = true;

            } else if ( last.getNbPts2() == p.getNbPts1() || last.getNbPts2() == p.getNbPts2() ) {

                ok = true;

            }

        }

        return ok;

    }

    public boolean canPlay ( Joueur player ) {

        LinkedList<Piece_Domino> pieces = player.getPieces();

        for( Piece_Domino p : pieces ) {

            if( canPlayPiece( p ) ) {

                return true;
            }

        }


        return false;
    }

    public void piocher( Joueur player , Piece_Domino p  ){

        this.getPioche().getListePieces().remove( p );
        player.getPieces().add( p );

    }

    public Joueur matchFini() {

        Joueur player = null;

        if ( joueurs.size() == 1 ) return joueurs.get(0);

        else {

            for (Joueur j : joueurs) {
                if ( j.getPieces().size()==0 ) {

                    player = j;
                }
            }
        }

        return player;

    }

    //-----------------------------------------------------------------------------





    //---------- Getters & Setters ----------------------

    public LinkedList<Joueur> getJoueurs () {
        return joueurs;
    }

    public Pioche getPioche () {
        return pioche;
    }

    public LinkedList<Piece_Domino> getPcsPosee() {
        return pcsPosee;
    }




}
