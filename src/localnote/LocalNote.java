/*
 * 
 * 
 * 
 */
package localnote;

import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JFrame;
import javax.swing.KeyStroke;
import javax.swing.JFileChooser;

import java.awt.event.*;
import java.awt.*;
import java.util.Scanner;
import java.io.*;

/**
 *
 * @author Matt
 */
public class LocalNote extends JFrame
        implements ActionListener {

    JDesktopPane desktop;

    public LocalNote() {
        Rectangle window = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        this.setSize(window.getSize());
        this.setTitle("Local Note");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        desktop = new JDesktopPane();
        this.setContentPane(desktop);
        this.setJMenuBar(createMenues());

        desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);

    }

    private JMenuBar createMenues() {
        JMenuBar menuBar = new JMenuBar();

        //Menu Creation
        JMenu menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_D);
        menuBar.add(menu);

        //File->New
        JMenuItem menuItem = new JMenuItem("New");
        menuItem.setMnemonic(KeyEvent.VK_N);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_N, ActionEvent.ALT_MASK));
        menuItem.setActionCommand("new");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        //File->Open
        menuItem = new JMenuItem("Open");
        menuItem.setMnemonic(KeyEvent.VK_O);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_O, ActionEvent.ALT_MASK));
        menuItem.setActionCommand("open");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        //File->Save
        menuItem = new JMenuItem("Save");
        menuItem.setMnemonic(KeyEvent.VK_S);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_S, ActionEvent.ALT_MASK));
        menuItem.setActionCommand("save");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        //File->Quit
        menuItem = new JMenuItem("Quit");
        menuItem.setMnemonic(KeyEvent.VK_Q);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_Q, ActionEvent.ALT_MASK));
        menuItem.setActionCommand("quit");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        return menuBar;
    }

    //React to menu selections.
    public void actionPerformed(ActionEvent e) {
        if ("new".equals(e.getActionCommand())) { //new
            createFrame();
        } else if ("save".equals(e.getActionCommand())) { //save
            saveNote();
        } else if ("open".equals(e.getActionCommand())) { //open
            open();
        } else if ("quit".equals(e.getActionCommand())) { //quit
            quit();
        }
    }

    //Create a new internal frame.
    protected void createFrame() {
        noteFrame frame = new noteFrame();
        frame.setVisible(true); //necessary as of 1.3
        desktop.add(frame);
        try {
            frame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
        }
    }

    protected void open() {
        final JFileChooser open = new JFileChooser();
        int option = open.showOpenDialog(this);
        noteFrame frame = new noteFrame();
        frame.setVisible(true); //necessary as of 1.3
        desktop.add(frame);
        if (option == JFileChooser.APPROVE_OPTION) {
                frame.textArea.setText("");
                try {
                    frame.setTitle(open.getSelectedFile().getName());
                    Scanner scan = new Scanner(new FileReader(open.getSelectedFile().getPath()));
                    while (scan.hasNext()) {
                        frame.textArea.append(scan.nextLine() + "\n");
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
    }

    protected void saveNote() {

    }

    //Quit the application.
    protected void quit() {
        System.exit(0);
    }

    private static void createGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);

        //Create and set up the window.
        LocalNote frame = new LocalNote();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Display the window.
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createGUI();
            }
        });
    }
}
