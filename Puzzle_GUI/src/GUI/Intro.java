package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.File;
import java.util.LinkedList;

public class Intro extends JPanel {


    private JPanel player;
    private JPanel images;

    private JLabel nameLabel;
    private JLabel ageLabel;
    private JLabel difficultyLabel;
    private JTextField nameTextField;
    private JSpinner ageSpinner;
    private JSlider difficultySlider;

    private LinkedList<JButton> imageList;

    public Intro(){


        imageList = new LinkedList<>();

        Border outerBorder = BorderFactory.createEmptyBorder(70 , 70 , 70 , 70 );


        // ===================== Player Panel Handling ===================== //

        nameLabel = new JLabel("Name");
        nameLabel.setFont( new Font( "Arial" , Font.ITALIC , 15));

        nameTextField = new JTextField(20);
        nameTextField.setFont( new Font( "Arial" , Font.HANGING_BASELINE , 15));

        ageLabel = new JLabel("Age");
        ageLabel.setFont( new Font( "Arial" , Font.ITALIC , 15));

        SpinnerModel spinnerOne = new SpinnerNumberModel(15, 6, 80, 1);
        ageSpinner = new JSpinner( spinnerOne );
        ageSpinner.setFont( new Font( "Arial" , Font.ITALIC , 15));

        difficultyLabel = new JLabel("Difficulty");
        difficultyLabel.setFont( new Font( "Arial" , Font.ITALIC , 15));

        difficultySlider = new JSlider( JSlider.HORIZONTAL , 1 , 5 , 1 );
        difficultySlider.setMajorTickSpacing( 1 );
        difficultySlider.setPaintTicks( true );

        player = new JPanel( new GridLayout( 6 , 1 , 10 , 5 ) );
        player.setPreferredSize( new Dimension( 400 , 400 )  );

        player.add( nameLabel );

        player.add( nameTextField );

        player.add( ageLabel );

        player.add( ageSpinner );

        player.add( difficultyLabel );

        player.add( difficultySlider );

        Border innerBorderOne = BorderFactory.createTitledBorder("Player");
        ((TitledBorder) innerBorderOne).setBorder( BorderFactory.createLineBorder( Color.BLACK , 2 , true));
        ((TitledBorder) innerBorderOne).setTitleJustification(TitledBorder.CENTER);
        ((TitledBorder) innerBorderOne).setTitleFont( new Font( "Arial" , Font.ITALIC , 15));
        player.setBorder( BorderFactory.createCompoundBorder( outerBorder , innerBorderOne ));

        // ================================================================= //

        // ===================== Options Panel Handling ===================== //

        images = new JPanel( new FlowLayout( FlowLayout.CENTER , 10 , 10 ) );
        images.setPreferredSize( new Dimension( 1100 , 300 ) );

        for (int i = 1 ; i<= 10 ; i++ ){

            File imgPath = new File("img/p" + i + "-compressor.jpg" );

            if ( imgPath.canRead() == false ){
                imgPath = new File("img/p" + i + ".png" );
            }

            ImageIcon icon = new ImageIcon( String.valueOf(imgPath) );
            Image img = icon.getImage();
            img = img.getScaledInstance( 200 , 113 , Image.SCALE_SMOOTH);
            icon.setImage( img );

            JButton option = new JButton( icon );
            option.setPreferredSize( new Dimension( 200 , 113 ) );

            option.setBackground( new Color(0x0000000, true));
            option.setBorder(BorderFactory.createLineBorder( Color.black , 2 , true) );
            option.setEnabled( false );

            imageList.add( option );
            images.add( option );

        }

        // ================================================================= //

        setLayout( new GridBagLayout() );
        GridBagConstraints gc = new GridBagConstraints();

        gc.gridy = 0;
        gc.gridx = 0;

        add( player , gc );

        gc.gridy = 1;
        gc.gridx = 0;

        add( images , gc );
    }


    public JTextField getNameTextField() {
        return nameTextField;
    }

    public JSpinner getAgeSpinner() {
        return ageSpinner;
    }

    public JSlider getDifficultySlider() {
        return difficultySlider;
    }

    public LinkedList<JButton> getImageList() {
        return imageList;
    }
}
