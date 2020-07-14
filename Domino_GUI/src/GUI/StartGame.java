package GUI;

import MODEL.Joueur;
import MODEL.Partie;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class StartGame extends JPanel {



    private Joueur joueur1;
    private Joueur joueur2;


    public StartGame(String p1 , int a1 , String p2 , int a2 ){

        joueur1 = new Joueur( p1 , a1 );
        joueur2 = new Joueur( p2 , a2 );

        LinkedList<Joueur> joueurs = new LinkedList<>();
        joueurs.add( joueur1 );
        joueurs.add( joueur2 );

        Partie partie = new Partie( joueurs );
        PlayTurns playTurns = new PlayTurns( partie );


        // =================== Add components to the Game ======================= //

        setLayout( new GridBagLayout() );
        GridBagConstraints gc = new GridBagConstraints();

        gc.gridx = 0;
        gc.gridy = 0;
        add( playTurns.getCompOne() , gc );

        gc.gridx = 1;
        gc.gridy = 0;
        add( playTurns.getCompTwo() , gc );

        gc.gridx = 0;
        gc.gridy = 1;
        add( playTurns.getCompThree() , gc );

        gc.gridx = 1;
        gc.gridy = 1;
        add( playTurns.getCompFour() , gc );

        gc.gridx = 0;
        gc.gridy = 2;
        add( playTurns.getCompFive() , gc );

        gc.gridx = 1;
        gc.gridy = 2;
        add( playTurns.getCompSix() , gc );

        // ================================================================= //


    }







}
