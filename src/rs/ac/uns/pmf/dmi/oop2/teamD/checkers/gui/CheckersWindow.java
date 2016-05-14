package rs.ac.uns.pmf.dmi.oop2.teamD.checkers.gui;

import rs.ac.uns.pmf.dmi.oop2.teamD.checkers.RegistryManager;
import rs.ac.uns.pmf.dmi.oop2.teamD.checkers.server.UserDb;
import rs.ac.uns.pmf.dmi.oop2.teamD.checkers.user.User;

import javax.swing.*;
import java.awt.*;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Our checkers game window
 * Apart from creating and maintaining gui it responds when other player sends his move
 */

public class CheckersWindow extends JFrame {

    private static final Logger logger = Logger.getLogger(CheckersWindow.class.getName());

    private static final int NUM_BTN = 100;

    private JButton[] fields;
    private Icon bluePawn;
    private Icon orangePawn;
    private Icon blueQueen;
    private Icon orangeQueen;
    private JTextField txt;
    private JLabel label;
    private UserDb userDb;


    public CheckersWindow() {
        bluePawn = new ImageIcon("res\\blue.png");
        orangePawn = new ImageIcon("res\\orange.png");
        blueQueen = new ImageIcon("res\\blueQ.png");
        orangeQueen = new ImageIcon("res\\orangeQ.png");


        logInScreen();

    }

    private void logInScreen() {

        getContentPane().removeAll();
        setLayout(new BorderLayout());
        JPanel panel1 = new JPanel();

        label = new JLabel("Unos imena: ");
        txt = new JTextField(30);

        JButton logIn = new JButton("LogIn");

        logIn.addActionListener(e -> {
            try {
                String host = System.getProperty("java.rmi.server.hostname");
                if (host == null) {
                    host = "localhost";
                }
                String name = txt.getText();

                Registry reg = RegistryManager.get();
                User user = new User(CheckersWindow.this);

                reg.rebind(name, user);

                /**
                 * DB
                 */

                initTable();

            } catch (RemoteException ex) {
                reportError("Cannot create User object", true, ex);
            }
        });

        panel1.add(label);
        panel1.add(txt);
        panel1.add(logIn);

        add(panel1, BorderLayout.NORTH);
    }

    private void initTable() {
        getContentPane().removeAll();
        setLayout(new GridLayout(10, 10));

        fields = new JButton[NUM_BTN];

    }

    private void reportError(String msg, boolean exit, Throwable throwable) {
        logger.log(exit ? Level.SEVERE : Level.WARNING, msg, throwable);

        if (exit) {
            msg += "\n Program EXIT";
        }

        JOptionPane.showMessageDialog(this, msg, "ERROR", JOptionPane.ERROR_MESSAGE);

        if (exit) {
            System.exit(-1);
        }
    }

    public static void main(String[] a) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }

        CheckersWindow chw = new CheckersWindow();
        chw.setDefaultCloseOperation(EXIT_ON_CLOSE);
        chw.setSize(500, 500);
        chw.setTitle("Checkers");
        chw.setVisible(true);
    }


}
