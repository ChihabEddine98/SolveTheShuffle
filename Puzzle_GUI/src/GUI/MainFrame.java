package GUI;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class MainFrame extends JFrame {

    private Intro intro;
    private StartGame game;

    public MainFrame() throws HeadlessException {

        super("Puzzle");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize( screenSize.width , screenSize.height );
        setResizable( false );
        setVisible( true );
        setLayout( new GridBagLayout() );


        intro = new Intro();

        smallScreenBackUpIntro( intro );

        addActionListners();

        checkValidity();


    }

    private void checkValidity(){

        intro.getNameTextField().getDocument().addDocumentListener( new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                changed();
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                changed();
            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {
                changed();
            }

            public void changed() {

                if ( intro.getNameTextField().getText().equals("") ) {

                    for ( JButton btn : intro.getImageList() ){

                        btn.setEnabled( false );
                    }

                } else {

                    for ( JButton btn : intro.getImageList() ){

                        btn.setEnabled( true );

                    }

                }

            }

        });

    }

    private void addActionListners(){

        for ( JButton btn : intro.getImageList() ){

            btn.addActionListener(actionEvent -> {

                remove(intro);

                this.game = new StartGame( intro.getNameTextField().getText() , (int)intro.getAgeSpinner().getValue() , intro.getDifficultySlider().getValue() , btn.getIcon().toString()  );

                smallScreenBackUpGame( game );

                revalidate();
                repaint();

            });

        }

    }


    public void smallScreenBackUpGame( StartGame game ){

        Dimension windowSize = this.getBounds().getSize();

        if ( windowSize.getWidth() < 1200 || windowSize.getHeight() < 650 ) {

            game.setPreferredSize( new Dimension( 1300 , 900 ));

            this.setLayout( new BorderLayout() );
            JScrollPane scrollPane = new JScrollPane( game );
            scrollPane.setHorizontalScrollBarPolicy( ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED );
            scrollPane.setVerticalScrollBarPolicy( ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED );
            scrollPane.getVerticalScrollBar().setUnitIncrement(10);
            add( scrollPane );

        } else {

            add( game );
        }

    }

    public void smallScreenBackUpIntro( Intro intro ){

        Dimension windowSize = this.getBounds().getSize();

        if ( windowSize.getWidth() < 1200 || windowSize.getHeight() < 650 ) {

            intro.setPreferredSize( new Dimension( 1300 , 900 ));

            this.setLayout( new BorderLayout() );
            JScrollPane scrollPane = new JScrollPane( intro );
            scrollPane.setHorizontalScrollBarPolicy( ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED );
            scrollPane.setVerticalScrollBarPolicy( ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED );
            scrollPane.getVerticalScrollBar().setUnitIncrement(10);
            add( scrollPane );

        } else {

            add( intro );
        }

    }
}
