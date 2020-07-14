package GUI;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;


public class MainFrame extends JFrame {

    private Intro intro;
    private StartGame game;
    private JButton playBtn;

    public MainFrame() throws HeadlessException {

        super("Dominos Classique");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize( screenSize.width , screenSize.height );
        setResizable( false );
        setVisible( true );


        playBtn = new JButton("Play");
        playBtn.setEnabled( false );
        intro = new Intro();

        setLayout( new GridBagLayout() );
        GridBagConstraints gc = new GridBagConstraints();

        gc.gridx = 0;
        gc.gridy = 0;
        gc.weightx = 0;
        gc.weighty = 0;
        gc.fill = GridBagConstraints.NONE;

        add( intro , gc );

        gc.gridx = 0;
        gc.gridy = 1;

        add ( playBtn , gc );


        // ==================== PlayButton And TextFields Listener ========================== //

        intro.getNameFieldOne().getDocument().addDocumentListener(new DocumentListener() {
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
                if (intro.getNameFieldOne().getText().equals("")){
                    playBtn.setEnabled(false);
                }
                else {
                    intro.getNameFieldTwo().getDocument().addDocumentListener(new DocumentListener() {
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
                            if (intro.getNameFieldTwo().getText().equals("")){
                                playBtn.setEnabled(false);
                            }
                            else {
                                playBtn.setEnabled(true);
                            }

                        }
                    });
                }

            }
        });



        playBtn.addActionListener( actionEvent -> {

            String p1 = intro.getNameFieldOne().getText();
            System.out.println( p1 );
            int a1 = (int)intro.getAgeOne().getValue();
            System.out.println( a1 );

            String p2 = intro.getNameFieldTwo().getText();
            System.out.println( p2 );
            int a2 = (int)intro.getAgeTwo().getValue();
            System.out.println( a2 );

            remove( intro );
            remove( playBtn );

            game = new StartGame( p1 , a1 , p2 , a2 );

            smallScreenBackUpGame( game );

            revalidate();
            repaint();
        });

    }


    public void smallScreenBackUpGame( StartGame game ){

        Dimension windowSize = this.getBounds().getSize();

        if ( windowSize.getWidth() < 1200 || windowSize.getHeight() < 650 ) {

            game.setPreferredSize( new Dimension( 1200 , 800 ));

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
