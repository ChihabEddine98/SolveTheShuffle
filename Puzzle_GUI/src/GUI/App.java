package GUI;

import static javax.swing.SwingUtilities.invokeLater;

public class App {


    public static void main (String [] args){


        invokeLater(() -> new MainFrame());

    }
}
