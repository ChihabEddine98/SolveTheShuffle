package GUI;

import MODEL.Partie;
import MODEL.Piece_Puzzle;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.LinkedList;

public class PlayTurns extends JPanel {

    private Partie partie;

    private JPanel compOne;
    private JPanel compTwo;
    private JPanel compThree;
    private JPanel compFour;
    private JPanel compFive;
    private JPanel compSix;

    private JScrollPane scrollPane;
    private JPanel insideScrollComp;

    private JButton startBtn;
    private JButton pauseBtn;
    private JButton endBtn;

    private LinkedList<PieceGUI> puzzle;
    private LinkedList<Piece_Puzzle> pieces;

    private JButton tmpBtn;


    private JLabel timeLabel;

    private byte centiseconds = 0;
    private byte seconds = 0;
    private short minutes = 0;
    private short hours = 0;

    private DecimalFormat timeFormatter;

    private Timer timer;

    public PlayTurns( Partie partie ) {


        this.partie = partie;

        puzzle = new LinkedList<>();
        pieces = new LinkedList<>();
        tmpBtn = new JButton();
        tmpBtn.setIcon( null );


        compOne = new JPanel( new BorderLayout() );
        compTwo = new JPanel( new FlowLayout( FlowLayout.CENTER , 100 , 5 ) );
        compThree = new JPanel( new GridBagLayout() );
        compFour = new JPanel( new GridLayout() );
        compFive = new JPanel( new FlowLayout( FlowLayout.CENTER , 5 , 20 ) );
        compSix  = new JPanel( new FlowLayout() );

        insideScrollComp = new JPanel( new FlowLayout() );
        insideScrollComp.setPreferredSize( new Dimension( 400 , 1300 ) );
        scrollPane = new JScrollPane( insideScrollComp  );
        scrollPane.setHorizontalScrollBarPolicy( ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED );
        scrollPane.setVerticalScrollBarPolicy( ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED );
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);


        initializeTimer();


        startBtn = new JButton("Start");
        pauseBtn = new JButton( "Pause");
        endBtn   = new JButton("Exit");


        initializeAllComp();
        fillCompFour();
        refresh();

    }

    // ===================== Components du Jeu =========================== //

    public void initializeAllComp(){

        initializeCompOne();
        initializeCompTwo();
        initializeCompFour();
        initializeCompThree();
        initializeCompFive();
        initializeCompSix();

    }

    public void initializeCompOne(){

        Border outerBorder = BorderFactory.createEmptyBorder(5 , 5 , 5 , 5 );

        compOne.setPreferredSize( new Dimension( 450 , 700 ) );

        compOne.add( scrollPane );

        Border innerBorderTable = BorderFactory.createLineBorder( Color.LIGHT_GRAY );
        compOne.setBorder( BorderFactory.createCompoundBorder( outerBorder , innerBorderTable ));
    }

    public void initializeCompTwo(){

        Border outerBorder = BorderFactory.createEmptyBorder(5 , 5 , 5 , 5 );

        compTwo.setPreferredSize( new Dimension( 800 , 200 ) );

        ImageIcon icon = new ImageIcon( partie.getPath() );
        Image img = icon.getImage();
        img = img.getScaledInstance( 267 , 150 , Image.SCALE_SMOOTH);
        icon.setImage( img );

        compTwo.add( new JLabel( icon ) );

        compTwo.add( compFive );


        Border innerBorderTable = BorderFactory.createTitledBorder( BorderFactory.createLineBorder( Color.BLACK , 3 , true ) , partie.getPlayer().getNom() );
        ((TitledBorder) innerBorderTable).setTitleJustification( TitledBorder.CENTER );
        compTwo.setBorder( BorderFactory.createCompoundBorder( outerBorder , innerBorderTable ));
    }

    public void initializeCompThree(){


        compThree.setPreferredSize( new Dimension( 800 , 700 ) );

        GridBagConstraints gc = new GridBagConstraints();

        gc.gridy = 0;
        gc.gridx = 0;

        compThree.add( compTwo , gc );

        gc.gridy = 1;
        gc.gridx = 0;

        compThree.add( compFour , gc );


    }

    public void initializeCompFour(){

        Border outerBorder = BorderFactory.createEmptyBorder(0 , 5 , 10 , 5 );


        compFour.setPreferredSize( new Dimension( 800 , 450 ) );

        // difficulté 1 ----> 800/4 = 200 && 450/3 = 150
        // difficulté 2 ----> 800/5 = 160 && 450/3 = 150
        // difficulté 3 ----> 800/8 = 100 && 450/5 = 90
        // difficulté 4 ----> 800/10 = 80 && 450/6 = 75
        // difficulté 5 ----> 800/16 = 50 && 450/9 = 50


        Border innerBorderTable = BorderFactory.createLineBorder( Color.LIGHT_GRAY );
        compFour.setBorder( BorderFactory.createCompoundBorder( outerBorder , innerBorderTable ));

    }

    public void  initializeCompFive(){


        compFive.setPreferredSize( new Dimension( 250 , 150 ) );

        startBtn.addActionListener(actionEvent -> {

            timer.start();

            compFour.removeAll();
            startBtn.setEnabled( false );
            partie.initializePlayerPieces( pieces );


            for ( Piece_Puzzle piece : partie.getPlayer().getPieces() ){

                ImageIcon icon = null ;

                for ( PieceGUI tmp : this.puzzle ){

                    if ( tmp.getPuzzle().equals( piece ) ){

                        icon = tmp.getIcon();

                    }
                }

                final JButton btn = new JButton( icon );

                btn.setBackground( new Color(Transparency.TRANSLUCENT, true) );
                btn.setFocusable( false );
                btn.setPreferredSize( new Dimension( icon.getIconWidth() , icon.getIconHeight() ));

                btn.addActionListener(actionEvent1 -> {

                    if ( tmpBtn.getIcon() == null && btn.getIcon() != null ){

                        tmpBtn.setIcon( btn.getIcon() );
                        btn.setIcon( null );
                        btn.setSelected( true );


                    } else if ( btn.getIcon() == null && tmpBtn.getIcon() != null ){

                        btn.setIcon( tmpBtn.getIcon() );
                        tmpBtn.setIcon( null );

                        boolean puzzleCompleted = isPuzzleCompleted();

                        if ( isGridFull() && puzzleCompleted == true ){

                            compFour.setBorder( BorderFactory.createLineBorder( Color.GREEN , 3 , true ));
                            timer.stop();
                            pauseBtn.setEnabled( false );

                        } else if ( isGridFull() && puzzleCompleted == false ){

                            compFour.setBorder( BorderFactory.createLineBorder( Color.RED , 3 , true ));

                        }

                        refresh();

                    }

                });

                insideScrollComp.add( btn  );

                addPuzzleEmptyComp();
                refresh();
            }


        });
        compFive.add( startBtn );

        pauseBtn.addActionListener(actionEvent -> {

            if ( pauseBtn.getText().equals( "Pause") ){

                timer.stop();
                pauseBtn.setText( "Resume" );
                refresh();

            } else if ( pauseBtn.getText().equals("Resume") ){

                timer.start();
                pauseBtn.setText("Pause");
                refresh();
            }

        });
        compFive.add( pauseBtn );

        endBtn.addActionListener(actionEvent -> {
            System.exit(0 );
        });
        compFive.add( endBtn );



        compFive.add( compSix );

    }

    public void initializeCompSix(){


        compSix.add( timeLabel );

    }

    public void initializeTimer(){

        timeFormatter = new DecimalFormat("00");

        timer = new Timer(10, e -> {

            if ( centiseconds < 99 ) {

                centiseconds++;

            } else {

                if ( seconds < 59 ) {

                    seconds++;
                    centiseconds = 0;

                } else if ( seconds >= 59 && minutes < 59 ) {

                    minutes++;
                    seconds = 0;
                    centiseconds = 0;

                } else if ( minutes >= 59 && hours < 59 ) {

                    hours++;
                    minutes = 0;
                    seconds = 0;
                    centiseconds = 0;
                }
            }

            timeLabel.setText( timeFormatter.format(hours) + ":" + timeFormatter.format(minutes) + ":" + timeFormatter.format(seconds) + "." + timeFormatter.format(centiseconds));
        });


        timeLabel = new JLabel();
        timeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        timeLabel.setHorizontalAlignment(JLabel.CENTER);
        timeLabel.setText( timeFormatter.format(hours) + ":" + timeFormatter.format(minutes) + ":" + timeFormatter.format(seconds) + "." + timeFormatter.format(centiseconds));


    }

    // ===================================================================== //


    // =============== Découper Image et remplir le Grid =================== //

    public void fillCompFour(){

        LinkedList<ImageIcon> pieces = cropImage();
        int x = 0;


        for ( ImageIcon icon : pieces ){

            Piece_Puzzle p = new Piece_Puzzle( x );
            this.pieces.add( p );
            puzzle.add( new PieceGUI( p , icon )  );
            JLabel label = new JLabel( icon );

            compFour.add( label );

            x++;
        }


    }

    public LinkedList<ImageIcon> cropImage(){

        int diffculty = this.partie.getDifficulty();

        LinkedList<ImageIcon> pieces = new LinkedList<>();

        ImageIcon icon = new ImageIcon( this.partie.getPath() );
        Image imgOriganal     = icon.getImage();

        int originalWidth     = 800;
        int originalHeight    = 450;

        System.out.println( originalHeight + " " + originalWidth);
        BufferedImage img     = new BufferedImage( 800, 450 , BufferedImage.TYPE_INT_RGB);
        img.getGraphics().drawImage(imgOriganal, 0, 0, 800 , 450 , null);

        int x = 0;
        int y = 0;

        if ( diffculty == 1 ){

            compFour.setLayout( new GridLayout( 3 , 4 , 2 , 2)  );

            for ( int i = 0 ; i < 12 ; i++ ){


                if ( x >= originalWidth ) {

                    x = 0;
                    y += 150 ;

                }


                BufferedImage subImage = img.getSubimage( x , y , 200 , 150 );
                pieces.add( new ImageIcon( subImage ) );

                x += 200 ;
            }

        } else if ( diffculty == 2 ){

            compFour.setLayout( new GridLayout( 3 , 5 , 2 , 2)  );

            for ( int i = 0 ; i < 15 ; i++ ){


                if ( x >= originalWidth ) {

                    x = 0;
                    y += 150 ;

                }


                BufferedImage subImage = img.getSubimage( x , y , 160 , 150 );
                pieces.add( new ImageIcon( subImage ) );

                x += 160 ;
            }


        } else if ( diffculty == 3 ){

            compFour.setLayout( new GridLayout( 5 , 8 , 2 , 2)  );

            for ( int i = 0 ; i < 40 ; i++ ){


                if ( x >= originalWidth ) {

                    x = 0;
                    y += 90 ;

                }


                BufferedImage subImage = img.getSubimage( x , y , 100 , 90 );
                pieces.add( new ImageIcon( subImage ) );

                x += 100 ;
            }

        } else if ( diffculty == 4 ){

            compFour.setLayout( new GridLayout( 6 , 10 , 2 , 2)  );

            for ( int i = 0 ; i < 60 ; i++ ){


                if ( x >= originalWidth ) {

                    x = 0;
                    y += 75 ;

                }


                BufferedImage subImage = img.getSubimage( x , y , 80 , 75 );
                pieces.add( new ImageIcon( subImage ) );

                x += 80 ;
            }

        } else if ( diffculty == 5 ){

            compFour.setLayout( new GridLayout( 9 , 16 , 2 , 2)  );

            for ( int i = 0 ; i < 144 ; i++ ){


                if ( x >= originalWidth ) {

                    x = 0;
                    y += 50 ;

                }


                BufferedImage subImage = img.getSubimage( x , y , 50 , 50 );
                pieces.add( new ImageIcon( subImage ) );

                x += 50 ;
            }

        }


        return pieces;

    }

    public void addPuzzleEmptyComp(){

        Border innerBorderTable = BorderFactory.createLineBorder( Color.BLACK );


        final JButton btn = new JButton( );
        btn.setIcon( null );
        btn.setBackground( new Color(Transparency.TRANSLUCENT, true) );
        btn.setFocusable( false );
        btn.setSelected( true );

        btn.addActionListener(actionEvent -> {

            if ( tmpBtn.getIcon() != null && btn.getIcon() == null ){

                btn.setIcon( tmpBtn.getIcon() );
                tmpBtn.setIcon( null );

                boolean puzzleCompleted = isPuzzleCompleted();

                if ( isGridFull() && puzzleCompleted == true ){

                    compFour.setBorder( BorderFactory.createLineBorder( Color.GREEN , 3 , true ));
                    timer.stop();
                    pauseBtn.setEnabled( false );

                } else if ( isGridFull() && puzzleCompleted == false ){

                    compFour.setBorder( BorderFactory.createLineBorder( Color.RED , 3 , true ));

                }

                refresh();

            } else if ( tmpBtn.getIcon() == null && btn.getIcon() != null ){

                tmpBtn.setIcon( btn.getIcon() );
                btn.setIcon( null );
//                refresh();

            }

        });


        btn.setBorder( innerBorderTable );

        compFour.add( btn );


    }

    // ===================================================================== //



    // ======================== controler le jeu =========================== //

    public boolean isGridFull(){

        Component [] compTable = compFour.getComponents();

        for ( int i = 0 ; i < compTable.length ; i++ ){
            JButton btn = (JButton) compTable[i];

            if ( btn.getIcon() == null ) {

                return  false;
            }
        }

        return true;
    }

    public boolean isPuzzleCompleted(){

        if ( isGridFull() ){


            for ( PieceGUI piecePuzzle : this.puzzle ){

                JButton btn = (JButton)compFour.getComponent( piecePuzzle.getPuzzle().getId() );


                if ( !piecePuzzle.getIcon().equals( btn.getIcon() ) ){

                    return false;
                }

            }

            return true;
        }


        return false;
    }

    // ===================================================================== //




    private void refresh(){

        this.compOne.revalidate();
        this.compOne.repaint();

        this.compTwo.revalidate();
        this.compTwo.repaint();

        this.compThree.revalidate();
        this.compThree.repaint();

    }

    public JPanel getCompOne() {
        return compOne;
    }

    public JPanel getCompThree() {
        return compThree;
    }






    private class PieceGUI {

        private Piece_Puzzle puzzle;
        private ImageIcon icon;

        public PieceGUI(Piece_Puzzle piece , ImageIcon icon) {
            this.puzzle = piece;
            this.icon = icon;
        }

        public Piece_Puzzle getPuzzle() {
            return puzzle;
        }

        public void setPuzzle(Piece_Puzzle puzzle) {
            this.puzzle = puzzle;
        }

        public ImageIcon getIcon() {
            return icon;
        }

        public void setIcon(ImageIcon icon) {
            this.icon = icon;
        }
    }



}
