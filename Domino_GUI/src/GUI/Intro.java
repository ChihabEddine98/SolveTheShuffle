package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Intro extends JPanel {

    private JLabel nameLabelOne;
    private JTextField nameFieldOne;
    private JLabel ageLabelOne;
    private JSpinner ageOne;

    private JLabel nameLabelTwo;
    private JTextField nameFieldTwo;
    private JLabel ageLabelTwo;
    private JSpinner ageTwo;


    public Intro(){


        Border outerBorder = BorderFactory.createEmptyBorder(0 , 70 , 70 , 70 );

        // ====================== Player One Informations ============================ //

        JPanel player1 = new JPanel( new GridLayout(0 , 1));

        SpinnerModel spinnerOne = new SpinnerNumberModel(15, 6, 80, 1);

        nameLabelOne = new JLabel("Name " , JLabel.LEFT);
        nameFieldOne = new JTextField(15);
        ageLabelOne = new JLabel("Age");
        ageOne  = new JSpinner( spinnerOne );

        player1.add( nameLabelOne );
        player1.add( nameFieldOne );
        player1.add( ageLabelOne );
        player1.add( ageOne );

        Border innerBorderOne = BorderFactory.createTitledBorder("Player 1");
        player1.setBorder( BorderFactory.createCompoundBorder( outerBorder , innerBorderOne ));

        // ============================================================================= //

        // ========================= Player Two Informations =========================== //

        JPanel player2 = new JPanel( new GridLayout(0 , 1) );

        SpinnerModel spinnerTwo = new SpinnerNumberModel(15, 6, 80, 1);

        nameLabelTwo = new JLabel("Name " , JLabel.LEFT);
        nameFieldTwo = new JTextField(15);
        ageLabelTwo = new JLabel("Age");
        ageTwo  = new JSpinner( spinnerTwo );

        player2.add( nameLabelTwo );
        player2.add( nameFieldTwo );
        player2.add( ageLabelTwo );
        player2.add( ageTwo );

        Border innerBorderTwo = BorderFactory.createTitledBorder("Player 2");
        player2.setBorder( BorderFactory.createCompoundBorder( outerBorder , innerBorderTwo));

        // ============================================================================= //


        // ============================= Grid Handling ================================= //



        setLayout( new GridBagLayout() );
        GridBagConstraints gc = new GridBagConstraints();

        gc.gridx = 0;
        gc.gridy = 0;
        gc.weightx = 0;
        gc.weighty = 0;
        gc.fill = GridBagConstraints.NONE;


        add( player1 , gc );

        gc.gridx = 2;
        gc.gridy = 0;

        add( player2 , gc );



        // ============================================================================== //


    }



        // ================================= Getters ==================================== //

    public JTextField getNameFieldOne() {
        return nameFieldOne;
    }

    public JTextField getNameFieldTwo() {
        return nameFieldTwo;
    }

    public JSpinner getAgeOne() {
        return ageOne;
    }

    public JSpinner getAgeTwo() {
        return ageTwo;
    }


}
