package GUI;

import MODEL.Joueur;
import MODEL.Partie;
import MODEL.Piece_Domino;
import MODEL.Pioche;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Objects;

public class PlayTurns extends JPanel {


    private Partie partie;

    private JPanel compOne;
    private JPanel compTwo;
    private JPanel compThree;
    private JPanel compFour;
    private JPanel compFive;
    private JPanel compSix;

    private JLabel scorePlayer1;
    private JLabel scorePlayer2;

    PlayTurns( Partie p ){


        partie = p;


        //=============================================== JPanels Handling ===============================================//

        compOne   = new JPanel( new GridBagLayout() );
        compTwo   = new JPanel( new FlowLayout());
        compThree = new JPanel( new FlowLayout() );
        compFour  = new JPanel( new FlowLayout( FlowLayout.CENTER ,5 , 40 ) );
        compFive  = new JPanel( new GridBagLayout() );
        compSix   = new JPanel( new FlowLayout() );


        scorePlayer1 = new JLabel( String.valueOf( partie.getJoueurs().get(0).getScore() ) );
        scorePlayer2 = new JLabel( String.valueOf( partie.getJoueurs().get(1).getScore() ) );

        scorePlayer1.setFont( new Font( "Arial" , Font.BOLD , 50 )  );
        scorePlayer2.setFont( new Font( "Arial" , Font.BOLD , 50 )  );

        initializeAllComp();


        play();

    }


    // ===================== Components du Jeu =========================== //

    public void initializeAllComp(){

        //============================ Component One ============================//

        initializeCompOne();

        //=======================================================================//

        //============================ Component Two ============================//

        initializeCompTwo();

        //=======================================================================//

        //============================ Component Three ============================//

        initializeCompThree();


        //=======================================================================//

        //============================ Component Four ============================//

        initializeCompFour();

        //=======================================================================//

        //============================ Component Five ============================//

        initializeCompFive();


        //=======================================================================//

        //============================ Component Six ============================//

        initializeCompSix();

        //=======================================================================//


    }

    private void initializeCompSix() {

        compSix.removeAll();

        Border outerBorder = BorderFactory.createEmptyBorder(5 , 5 , 5 , 5 );

        compSix.setPreferredSize( new Dimension(900 , 120 ) );

        for ( Piece_Domino p : partie.getJoueurs().get(1).getPieces() ){

            PieceGUI pieceGUI = new PieceGUI(p);
            compSix.add( pieceGUI.getButton() );
            revalidate();
            repaint();

        }

        Border innerBorderPlayerTwo = BorderFactory.createTitledBorder( partie.getJoueurs().get(1).getNom() );
        ((TitledBorder) innerBorderPlayerTwo).setTitleFont( new Font( "Arial" , Font.ITALIC , 15 ));
        ((TitledBorder) innerBorderPlayerTwo).setTitlePosition(TitledBorder.BOTTOM);
        ((TitledBorder) innerBorderPlayerTwo).setTitleJustification(TitledBorder.CENTER);
        compSix.setBorder( BorderFactory.createCompoundBorder( outerBorder , innerBorderPlayerTwo ));

    }

    private void initializeCompFive() {

        compFive.removeAll();

        Border outerBorder = BorderFactory.createEmptyBorder(5 , 5 , 5 , 5 );

        compFive.setPreferredSize( new Dimension(200 , 120 ) );
        compFive.add( scorePlayer2 );

        Border innerBorderScoreTwo = BorderFactory.createTitledBorder( "Score" );
        ((TitledBorder) innerBorderScoreTwo).setTitleFont( new Font( "Arial" , Font.ITALIC , 15 ));
        ((TitledBorder) innerBorderScoreTwo).setTitlePosition(TitledBorder.BOTTOM);
        compFive.setBorder( BorderFactory.createCompoundBorder( outerBorder , innerBorderScoreTwo ));
    }

    private void initializeCompFour() {

        compFour.setBackground( Color.black );

        compFour.removeAll();

        Border outerBorder = BorderFactory.createEmptyBorder(5 , 5 , 5 , 5 );

        compFour.setPreferredSize( new Dimension(900 , 400 ) );

        for ( Piece_Domino p : partie.getPcsPosee() ){

            PieceGUI pieceGUI = new PieceGUI(p);
            compFour.add( pieceGUI.getButton() );
            revalidate();
            repaint();

        }

        Border innerBorderTable = BorderFactory.createLineBorder( Color.LIGHT_GRAY );
        compFour.setBorder( BorderFactory.createCompoundBorder( outerBorder , innerBorderTable ));
    }

    private void initializeCompThree() {

        compThree.removeAll();

        Border outerBorder = BorderFactory.createEmptyBorder(5 , 5 , 5 , 5 );

        compThree.setPreferredSize( new Dimension(200 , 400 ) );


        for ( Piece_Domino p : partie.getPioche().getListePieces() ){

            PieceGUI pieceGUI = new PieceGUI(p);
            compThree.add( pieceGUI.getButton() );
            revalidate();
            repaint();

        }

        Border innerBorderPioche = BorderFactory.createTitledBorder( "Pioche" );
        ((TitledBorder) innerBorderPioche).setTitleFont( new Font( "Arial" , Font.ITALIC , 15 ));
        compThree.setBorder( BorderFactory.createCompoundBorder( outerBorder , innerBorderPioche ));
    }

    private void initializeCompTwo() {

        compTwo.removeAll();

        Border outerBorder = BorderFactory.createEmptyBorder(5 , 5 , 5 , 5 );

//        compTwo.setBackground( new Color(21, 141, 192 ) );

        compTwo.setPreferredSize( new Dimension(900 , 120 ) );

        for ( Piece_Domino p : partie.getJoueurs().get(0).getPieces() ){

            PieceGUI pieceGUI = new PieceGUI(p);
            compTwo.add( pieceGUI.getButton() );
            revalidate();
            repaint();

        }


        Border innerBorderPlayerOne = BorderFactory.createTitledBorder( partie.getJoueurs().get(0).getNom() );
        ((TitledBorder) innerBorderPlayerOne).setTitleFont( new Font( "Arial" , Font.TRUETYPE_FONT , 15 ));
//        ((TitledBorder) innerBorderPlayerOne).;

        ((TitledBorder) innerBorderPlayerOne).setTitleJustification(TitledBorder.CENTER);
        compTwo.setBorder( BorderFactory.createCompoundBorder( outerBorder , innerBorderPlayerOne));

    }

    private void initializeCompOne() {

        compOne.removeAll();

        Border outerBorder = BorderFactory.createEmptyBorder(5 , 5 , 5 , 5 );

        compOne.setPreferredSize( new Dimension(200 , 120 ) );
        compOne.add( scorePlayer1 );

        Border innerBorderScoreOne = BorderFactory.createTitledBorder("Score");
        ((TitledBorder) innerBorderScoreOne).setTitleFont( new Font( "Arial" , Font.ITALIC , 15 ));
        compOne.setBorder( BorderFactory.createCompoundBorder( outerBorder , innerBorderScoreOne ));
    }

    private void refresh(){

        this.compOne.revalidate();
        this.compOne.repaint();

        this.compTwo.revalidate();
        this.compTwo.repaint();

        this.compThree.revalidate();
        this.compThree.repaint();

        this.compFour.revalidate();
        this.compFour.repaint();

        this.compFive.revalidate();
        this.compFive.repaint();

        this.compSix.revalidate();
        this.compSix.repaint();
    }

    // ===================================================================== //



    // =========== Méthodes pour ajouter des Actions aux Boutons =========== //



    private void giveActionPlayerOne(Piece_Domino p, PieceGUI pieceGUI, Joueur player1, Joueur player2) {

        if ( partie.canPlayPiece( p ) ){


            pieceGUI.getButton().addActionListener( addActionToPiecePlayerOne( p , pieceGUI.getButton() , player1 , player2 ) );


        } else {

            pieceGUI.setUnclickable();

        }


    }

    private void giveActionPlayerTwo(Piece_Domino p, PieceGUI pieceGUI, Joueur player1, Joueur player2) {

        if ( partie.canPlayPiece( p ) ){


            pieceGUI.getButton().addActionListener( addActionToPiecePlayerTwo( p , pieceGUI.getButton() , player1 , player2 ) );


        } else {

            pieceGUI.setUnclickable();
        }



    }

    private ActionListener addActionToPiecePlayerOne(Piece_Domino piece , JButton pieceBtn, Joueur player1, Joueur player2 ) {

        return (actionEvent -> {

            boolean b = true;

            if ( partie.canPlayTwoWays( piece ) ) {

                b = askWichWay( player1 , piece );

            } else {

                b = playOneTurn( player1 , piece , 0 );

            }

            if ( b ){

                player1.setVisible( false );
                player2.setVisible( true );


                compTwo.remove( pieceBtn );

                resetAllPiecePlayer( player1 );
                convertPiecePlayers();

                refresh();
            }

        });
    }

    private ActionListener addActionToPiecePlayerTwo(Piece_Domino piece , JButton pieceBtn, Joueur player1, Joueur player2 ) {

        return (actionEvent -> {

            boolean b = true;

            if ( partie.canPlayTwoWays( piece ) ) {

                b = askWichWay( player1 , piece );

            } else {

                b = playOneTurn( player1 , piece , 0 );

            }

            if ( b ){
                player1.setVisible( false );
                player2.setVisible( true );


                compSix.remove( pieceBtn );

                resetAllPiecePlayer( player1 );
                convertPiecePlayers();

                refresh();
            }

        });
    }


    // ===================================================================== //



    // =========== Méthodes pour enlever les ActionListners ================ //

    public void resetPiece( JButton pieceBtn ){

        for ( ActionListener al : pieceBtn.getActionListeners() ){
            pieceBtn.removeActionListener( al );
        }
    }

    public void resetAllPiecePlayer( Joueur player ){

        if ( player.equals( partie.getJoueurs().get(0) ) ){

            for ( Component component : compTwo.getComponents() ){

                resetPiece( (JButton)component );
            }

        } else {

            for ( Component component : compSix.getComponents() ){

                resetPiece( (JButton)component );
            }

        }


    }

    public void resetAllPiecePioche( ){

        for ( Component component : compThree.getComponents() ){

            resetPiece( (JButton)component );
        }
    }


    // ===================================================================== //



    // == Méthodes pour liéer entre une Piece, un Bouton, et ActionListener == //

    public void convertPiecePlayers ( ) {

        Joueur player1 = partie.getJoueurs().get(0);
        Joueur player2 = partie.getJoueurs().get(1);


        if ( !player1.isVisible() ){

            compTwo.removeAll();


            for ( Piece_Domino p : player1.getPieces() ){

                PieceGUI pieceGUI = new PieceGUI( p );
                pieceGUI.setInvisibleIcon();
                compTwo.add( pieceGUI.getButton() );
                refresh();

            }


            if( partie.canPlay( player2 ) ){

                compSix.removeAll();


                System.out.println("player2 can play");
                System.out.println( player2.getPieces().size() );

                for ( Piece_Domino p : player2.getPieces() ){

                    PieceGUI pieceGUI = new PieceGUI( p );
                    pieceGUI.setVisibileIcon( 0 );
                    giveActionPlayerTwo( p , pieceGUI , player2 , player1 );
                    compSix.add( pieceGUI.getButton() );

                    refresh();
                }

            } else {

                compSix.removeAll();


                for ( Piece_Domino p : player2.getPieces() ){

                    PieceGUI pieceGUI = new PieceGUI( p );
                    pieceGUI.setVisibileIcon( 0 );
                    pieceGUI.setUnclickable();
                    compSix.add( pieceGUI.getButton() );

                    refresh();
                }


                if ( this.partie.getPioche().getListePieces().size() > 0 ){

                    System.out.println( "stop player2");
                    piocher( player2 );

                } else {

                    if ( partie.canPlay( player1 ) == false && partie.canPlay( player2 ) == false ){

                        noWinner();

                    } else if ( partie.canPlay( player2 ) == false ) {

                        player1.setVisible( true );
                        player2.setVisible( false );
                        convertPiecePlayers();

                    }
                }


            }

        }
        else {

            if ( !player2.isVisible() ){

                compSix.removeAll();


                for ( Piece_Domino p : player2.getPieces() ){

                    PieceGUI pieceGUI = new PieceGUI( p );
                    pieceGUI.setInvisibleIcon();
                    compSix.add( pieceGUI.getButton() );
                    refresh();

                }

            }



            if ( partie.canPlay( player1 ) ) {

                compTwo.removeAll();


                System.out.println("player1 can play");
                System.out.println( player1.getPieces().size() );

                for ( Piece_Domino p : player1.getPieces() ){

                    PieceGUI pieceGUI = new PieceGUI( p );
                    pieceGUI.setVisibileIcon( 0 );
                    giveActionPlayerOne( p , pieceGUI , player1 , player2 );
                    compTwo.add( pieceGUI.getButton() );
                    refresh();
                }


            } else {

                compTwo.removeAll();


                for ( Piece_Domino p : player1.getPieces() ){

                    PieceGUI pieceGUI = new PieceGUI( p );
                    pieceGUI.setVisibileIcon( 0 );
                    pieceGUI.setUnclickable();
                    compTwo.add( pieceGUI.getButton() );

                    refresh();
                }

                if ( this.partie.getPioche().getListePieces().size() > 0 ){

                    System.out.println( "stop player1");
                    piocher( player1 );

                } else {

                    if ( partie.canPlay( player1 ) == false && partie.canPlay( player2 ) == false ){

                        noWinner();

                    } else if ( partie.canPlay( player1 ) == false ) {

                        player1.setVisible( false );
                        player2.setVisible( true );
                        convertPiecePlayers();

                    }
                }

            }

        }




    }

    public void convertPioche( Joueur player ){

        compThree.removeAll();

        for ( Piece_Domino p : partie.getPioche().getListePieces() ){

            PieceGUI pieceGUI = new PieceGUI( p );
            pieceGUI.setClickable();
            pieceGUI.getButton().addActionListener( actionEvent ->  {

                partie.piocher( player , p );
                compThree.remove( pieceGUI.getButton() );

                resetPiece( pieceGUI.getButton() );

                if ( player.equals( partie.getJoueurs().get(0) ) ){

                    compTwo.add( pieceGUI.getButton() );

                } else {

                    compSix.add( pieceGUI.getButton() );
                }

                hidePioche();
                convertPiecePlayers();

                System.out.println(" clicked " + p.getNbPts1() + p.getNbPts2());
                refresh();

            });

            compThree.add( pieceGUI.getButton() );
        }
    }

    // ===================================================================== //



    // ======================== Méthodes pour jouer ======================== //

    public void play() {

        resetAllPiecePlayer( partie.getJoueurs().get(0) );
        resetAllPiecePlayer( partie.getJoueurs().get(1) );

        if ( this.partie.getPcsPosee().isEmpty() ) {

            this.partie.whosYounger().setVisible( true );
            convertPiecePlayers();

        }

    }

    public void playOneTurn(Joueur player , Piece_Domino p ) {

        player.getPieces().remove( p );
        this.partie.getPcsPosee().add( p );
        PieceGUI pieceGUI = new PieceGUI( p );
        resetPiece( pieceGUI.getButton() );
        pieceGUI.setVisibileIcon( 270 );
        compFour.add( pieceGUI.getButton() );



    }

    public boolean playOneTurn(Joueur player , Piece_Domino p , int direction ){

        boolean ok = false;

        if ( direction == 0 ) {

            if( partie.getPcsPosee().isEmpty() )  { // La premiere piece jouée !

                playOneTurn( player , p );
                ok = true;

            } else {

                Piece_Domino first = partie.getPcsPosee().getFirst();
                Piece_Domino last = partie.getPcsPosee().getLast();

                if ( first.getNbPts1() == p.getNbPts1() || first.getNbPts1() == p.getNbPts2() ) {

                    if (first.getNbPts1()==p.getNbPts2()) {

                        partie.getPcsPosee().addFirst( p );
                        player.retirerPiece( p );
                        PieceGUI pieceGUI = new PieceGUI( p );
                        resetPiece( pieceGUI.getButton() );
                        pieceGUI.setVisibileIcon( 270 );
                        compFour.add( pieceGUI.getButton() , 0 );

                    } else {

                        player.retirerPiece( p );

                        p = new Piece_Domino( p.getNbPts2() , p.getNbPts1() );
                        PieceGUI pieceGUI = new PieceGUI( p );
                        pieceGUI.setVisibileIcon( 90 );
                        pieceGUI.setClickable();
                        partie.getPcsPosee().addFirst( p );

                        resetPiece( pieceGUI.getButton() );
                        compFour.add( pieceGUI.getButton() , 0 );

                    }

                    ok = true;

                } else if ( last.getNbPts2() == p.getNbPts1() || last.getNbPts2() == p.getNbPts2() ) {

                    if ( last.getNbPts2() == p.getNbPts1() ) {

                        partie.getPcsPosee().addLast( p );
                        player.retirerPiece( p );
                        PieceGUI pieceGUI = new PieceGUI( p );
                        resetPiece( pieceGUI.getButton()) ;
                        pieceGUI.setVisibileIcon( 270 );
                        compFour.add( pieceGUI.getButton() );

                    } else {

                        player.retirerPiece( p );

                        p = new Piece_Domino( p.getNbPts2() , p.getNbPts1() );
                        PieceGUI pieceGUI = new PieceGUI( p );
                        pieceGUI.setVisibileIcon( 90 );
                        pieceGUI.setClickable();
                        partie.getPcsPosee().addLast( p );

                        resetPiece( pieceGUI.getButton() );
                        compFour.add( pieceGUI.getButton() );

                    }

                    ok = true;
                }

            }

        } else if ( direction == 1 ) { // haut de la liste

            Piece_Domino first = partie.getPcsPosee().getFirst();

            if ( first.getNbPts1() == p.getNbPts2() ) {

                partie.getPcsPosee().addFirst(p);
                player.retirerPiece( p );
                PieceGUI pieceGUI = new PieceGUI( p );
                resetPiece( pieceGUI.getButton() );
                pieceGUI.setVisibileIcon( 270 );
                compFour.add( pieceGUI.getButton() , 0 );

            } else {

                player.retirerPiece( p );

                p = new Piece_Domino( p.getNbPts2() , p.getNbPts1() );
                PieceGUI pieceGUI = new PieceGUI( p );
                pieceGUI.setVisibileIcon( 90 );
                pieceGUI.setClickable();
                partie.getPcsPosee().addFirst( p );

                resetPiece( pieceGUI.getButton() );
                compFour.add( pieceGUI.getButton() , 0 );

            }

            ok = true;

        } else {  // Queue De liste

            Piece_Domino last = partie.getPcsPosee().getLast();

            if ( last.getNbPts2() == p.getNbPts1() ) {

                partie.getPcsPosee().addLast(p);
                player.retirerPiece( p );
                PieceGUI pieceGUI = new PieceGUI( p );
                resetPiece( pieceGUI.getButton() );
                pieceGUI.setVisibileIcon( 270 );
                compFour.add( pieceGUI.getButton() );

            } else {

                player.retirerPiece( p );

                p = new Piece_Domino( p.getNbPts2() , p.getNbPts1() );
                PieceGUI pieceGUI = new PieceGUI( p );
                pieceGUI.setVisibileIcon( 90 );
                pieceGUI.setClickable();
                partie.getPcsPosee().addLast( p );

                resetPiece( pieceGUI.getButton() );
                compFour.add( pieceGUI.getButton() );

            }

            ok = true;
        }


        if ( partie.matchFini() != null ){

            gameOver();
            ok = false ;
        }

        return ok;

    }

    public void piocher( Joueur player ){

        resetAllPiecePioche( );
        convertPioche( player );


    }

    public void gameOver(){



        Joueur player = partie.matchFini();

        Object[] options = { "Rejouer" , "Sortir" };

        int answer = JOptionPane.showInternalOptionDialog( PlayTurns.this , player.getNom() + " a gagné !" , "Game Over" , JOptionPane.OK_CANCEL_OPTION , JOptionPane.QUESTION_MESSAGE , null , options , options[0]);

        while ( answer == -1 ){

//            option = (String) JOptionPane.showInputDialog(GUI.PlayTurns.this , "Où voulez-vous mettre la pièce ?" , "Choix" , JOptionPane.QUESTION_MESSAGE , null , options , options[0]);

            answer = JOptionPane.showInternalOptionDialog( PlayTurns.this , player.getNom() + " a gagné !" , "Game Over" , JOptionPane.OK_CANCEL_OPTION , JOptionPane.QUESTION_MESSAGE , null , options , options[0]);

        }

        System.out.println(answer);

        if ( answer == 0 ) {

            System.out.println("rejouer");

            LinkedList<Joueur> joueurs = new LinkedList<>();

            Joueur player1 = partie.getJoueurs().get(0);
            int score1     = player1.getScore();
            Joueur player2 = partie.getJoueurs().get(1);
            int score2     = player2.getScore();

            if ( player.equals( player1 ) ) {

                player1 = new Joueur( player1.getNom() , player1.getAge() );
                player2 = new Joueur( player2.getNom() , player2.getAge() );

                player1.setScore( score1 + 1  );
                player2.setScore( score2 );

            } else {

                player1 = new Joueur( player1.getNom() , player1.getAge() );
                player2 = new Joueur( player2.getNom() , player2.getAge() );

                player2.setScore( score2 + 1  );
                player1.setScore( score1 );
            }

            joueurs.add( player1 );
            joueurs.add( player2 );



            this.partie = new Partie( joueurs );
            removeAll();
            revalidate();
            repaint();
            initializeAllComp();

            this.scorePlayer1.setText( String.valueOf( partie.getJoueurs().get(0).getScore()) );
            this.scorePlayer2.setText( String.valueOf( partie.getJoueurs().get(1).getScore()) );

            refresh();

            System.out.println( "Player 1 score " + partie.getJoueurs().get(0).getScore() );
            System.out.println( "Player 2 score " + partie.getJoueurs().get(1).getScore() );
            play();


        } else if ( answer == 1) {

            System.out.println("exit");
            System.exit(0);

        }

    }

    public void noWinner(){

        Object[] options = { "Rejouer" , "Sortir" };

        int answer = JOptionPane.showInternalOptionDialog( PlayTurns.this , "Match nul !" , "Game Over" , JOptionPane.OK_CANCEL_OPTION , JOptionPane.QUESTION_MESSAGE , null , options , options[0]);

        while ( answer == -1 ){

//            option = (String) JOptionPane.showInputDialog(GUI.PlayTurns.this , "Où voulez-vous mettre la pièce ?" , "Choix" , JOptionPane.QUESTION_MESSAGE , null , options , options[0]);

            answer = JOptionPane.showInternalOptionDialog( PlayTurns.this , "Match nul !" , "Game Over" , JOptionPane.OK_CANCEL_OPTION , JOptionPane.QUESTION_MESSAGE , null , options , options[0]);

        }

        System.out.println(answer);

        if ( answer == 0 ) {

            System.out.println("rejouer");

            LinkedList<Joueur> joueurs = new LinkedList<>();

            Joueur player1 = partie.getJoueurs().get(0);
            int score1     = player1.getScore();
            Joueur player2 = partie.getJoueurs().get(1);
            int score2     = player2.getScore();

            player1 = new Joueur( player1.getNom() , player1.getAge() );
            player2 = new Joueur( player2.getNom() , player2.getAge() );

            player2.setScore( score2 );
            player1.setScore( score1 );

            joueurs.add( player1 );
            joueurs.add( player2 );



            this.partie = new Partie( joueurs );
            removeAll();
            revalidate();
            repaint();
            initializeAllComp();

            this.scorePlayer1.setText( String.valueOf( partie.getJoueurs().get(0).getScore()) );
            this.scorePlayer2.setText( String.valueOf( partie.getJoueurs().get(1).getScore()) );

            refresh();
            play();


        } else if ( answer == 1) {

            System.out.println("exit");
            System.exit(0);

        }

    }

    // ===================================================================== //



    public boolean askWichWay(Joueur player , Piece_Domino p ) {

        boolean b = true;

        Object[] options = { "Début" , "Fin" };

        int answer = JOptionPane.showInternalOptionDialog( PlayTurns.this , "Où voulez-vous mettre la pièce ?" , "Choix" , JOptionPane.OK_CANCEL_OPTION , JOptionPane.QUESTION_MESSAGE , null , options , options[0]);

        while ( answer == -1 ){

//            option = (String) JOptionPane.showInputDialog(GUI.PlayTurns.this , "Où voulez-vous mettre la pièce ?" , "Choix" , JOptionPane.QUESTION_MESSAGE , null , options , options[0]);

             answer = JOptionPane.showInternalOptionDialog( PlayTurns.this , "Où voulez-vous mettre la pièce ?" , "Choix" , JOptionPane.OK_CANCEL_OPTION , JOptionPane.QUESTION_MESSAGE , null , options , options[0]);

            System.out.println( answer );
        }

        if ( answer == 0 ) {

            b = playOneTurn( player , p , 1 );

        } else if ( answer == 1) {

            b = playOneTurn( player , p , -1 );

        }

        return b;

    }

    private void hidePioche() {

        compThree.removeAll();

        for ( Piece_Domino p : partie.getPioche().getListePieces() ){

            PieceGUI pieceGUI = new PieceGUI( p );
            pieceGUI.setInvisibleIcon();
            pieceGUI.setUnclickable();
            compThree.add( pieceGUI.getButton() );

        }

    }


    public JPanel getCompOne() {
        return compOne;
    }

    public JPanel getCompTwo() {
        return compTwo;
    }

    public JPanel getCompThree() {
        return compThree;
    }

    public JPanel getCompFour() {
        return compFour;
    }

    public JPanel getCompFive() {
        return compFive;
    }

    public JPanel getCompSix() {
        return compSix;
    }





    private class PieceGUI {

        private Piece_Domino domino;
        private JButton button;

        public PieceGUI(Piece_Domino domino) {

            this.domino = domino;
            button = new JButton();

            setInvisibleIcon();

        }

        public void setVisibileIcon( int degree ){

            if ( domino.getNbPts1() <= domino.getNbPts2() ){

                ImageIcon icon = new ImageIcon( "img/" + domino.getNbPts1() + domino.getNbPts2() + "d" + degree + ".png" );
                Image img = icon.getImage();

                if ( this.domino.getNbPts1() == this.domino.getNbPts2() ){

                    icon = new ImageIcon( "img/" + domino.getNbPts1() + domino.getNbPts2() + "d" + 0 + ".png" );
                    img = icon.getImage();
                    img = img.getScaledInstance( 40 , 80 , Image.SCALE_SMOOTH);
                    button.setPreferredSize( new Dimension( 40 , 80 ) );

                } else {

                    if ( degree == 0 ){

                        img = img.getScaledInstance( 40 , 80 , Image.SCALE_SMOOTH);
                        button.setPreferredSize( new Dimension( 40 , 80 ) );


                    } else {

                        img = img.getScaledInstance( 80 , 40 , Image.SCALE_SMOOTH);
                        button.setPreferredSize( new Dimension( 80 , 40 ) );

                    }

                }

                icon.setImage( img );

                button.setIcon( icon );
                button.setBackground( new Color(0x0000000, true));
                button.setBorder(BorderFactory.createEmptyBorder());

                button.setEnabled( true );

            } else {

                ImageIcon icon = new ImageIcon( "img/" + domino.getNbPts2() + domino.getNbPts1() + "d" + degree + ".png" );

                Image img = icon.getImage();

                if ( degree == 0 ){

                    img = img.getScaledInstance( 40 , 80 , Image.SCALE_SMOOTH);
                    button.setPreferredSize( new Dimension( 40 , 80 ) );


                } else {

                    img = img.getScaledInstance( 80 , 40 , Image.SCALE_SMOOTH);
                    button.setPreferredSize( new Dimension( 80 , 40 ) );

                }

                icon.setImage( img );

                button.setIcon( icon );
                button.setBackground( new Color(0x0000000, true));
                button.setBorder(BorderFactory.createEmptyBorder());

            }

        }

        public void setInvisibleIcon(){

            ImageIcon icon = new ImageIcon("img/invisible.png");
            Image img = icon.getImage();
            img = img.getScaledInstance(40, 80, Image.SCALE_SMOOTH);
            icon.setImage(img);

            button.setIcon( icon );
            button.setPreferredSize( new Dimension( 40 , 80 ) );
            button.setBackground( new Color(0x0000000, true));
            button.setBorder(BorderFactory.createEmptyBorder());

            button.setEnabled( false );
        }

        public void setUnclickable(){

            this.button.setEnabled( false );
        }

        public void setClickable(){

            this.button.setEnabled( true );
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PieceGUI pieceGUI = (PieceGUI) o;
            return Objects.equals(getDomino(), pieceGUI.getDomino()) &&
                    Objects.equals(getButton(), pieceGUI.getButton());
        }


        public Piece_Domino getDomino() {
            return domino;
        }

        public void setDomino(Piece_Domino domino) {
            this.domino = domino;
        }

        public JButton getButton() {
            return button;
        }

        public void setButton(JButton button) {
            this.button = button;
        }
    }

}
