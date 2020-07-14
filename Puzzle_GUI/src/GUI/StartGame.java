package GUI;

import MODEL.Joueur;
import MODEL.Partie;
import javax.swing.*;
import java.awt.*;

public class StartGame extends JPanel {

    private String playerName;
    private int playerAge;
    private int difficulty;
    private String path;


    public StartGame(String nameTextField, int value, int value1 , String path ) {


        this.playerName = nameTextField;
        this.playerAge  = value;
        this.difficulty = value1;
        this.path       = path;


        Joueur player = new Joueur( this.playerName , this.playerAge );
        Partie partie = new Partie( player , this.difficulty , this.path );
        PlayTurns playTurns = new PlayTurns( partie );


        setLayout( new GridBagLayout() );
        GridBagConstraints gc = new GridBagConstraints();

        gc.gridx = 0;
        gc.gridy = 0;

        add( playTurns.getCompOne() , gc );

        gc.gridx = 1;
        gc.gridy = 0;

        add( playTurns.getCompThree() , gc );


        System.out.println(toString());

    }




    @Override
    public String toString() {
        return ( playerName + " " + playerAge + " " + difficulty + " " + path );
    }





}
