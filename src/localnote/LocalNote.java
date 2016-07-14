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
        frame.setVisible(true);
        desktop.add(frame);
        try {
            frame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
        }
    }

    protected void open() {
        final JFileChooser open = new JFileChooser();
        int option = open.showOpenDialog(this);

        //System.out.println("Open: " + frame);
        if (option == JFileChooser.APPROVE_OPTION) {
            noteFrame frame = new noteFrame();
            frame.setVisible(true);
            desktop.add(frame);
            frame.textArea.setText("");
            try {
                File current = open.getSelectedFile();
                String filename = current.getName();
                String filepath = current.getPath();
                frame.setTitle(filename);
                frame.title = filename;
                frame.path = filepath;
                Scanner scan = new Scanner(new FileReader(filepath));
                while (scan.hasNext()) {
                    frame.textArea.append(scan.nextLine() + "\n");
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

    /**
     *
     *
     * protected void dump(Component x, int depth, int id) {
     * System.out.println("Depth: " + depth + " ID: " + id + " Component: " +
     * x); if (depth < 3) {
     *
     * if (x instanceof Container) { Container XX = (Container) x; int n =
     * XX.getComponentCount(); for (int i = 0; i < n; i++) {
     * dump(XX.getComponent(i), depth + 1, i); } } } }
     *
     */
    protected void saveNote() {
        Component i = desktop.getSelectedFrame();
        //dump(i, 0, 0);
        if (i instanceof noteFrame) {
            noteFrame n = (noteFrame) i;
            if (n.path.isEmpty()) {
                JFileChooser saver = new JFileChooser();
                int option = saver.showSaveDialog(n);
                if (option == JFileChooser.APPROVE_OPTION) {
                    try {
                        BufferedWriter out = new BufferedWriter(new FileWriter(n.getPath()));
                        out.write(n.textArea.getText());
                        out.close();
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            } else {
                try {
                    BufferedWriter out = new BufferedWriter(new FileWriter(n.getPath()));
                    out.write(n.textArea.getText());
                    out.close();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }

        } else {
            System.out.println("Save: Unexpected Frame: " + i);
        }
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
