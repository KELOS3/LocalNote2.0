/*
 *
 *
 *
 */
package localnote;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;

import javax.swing.*;

/**
 *
 * @author Matt
 */
public class LocalNote extends JFrame
        implements ActionListener {

    JDesktopPane desktop;
    Desktop desktopEmail;

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

        //File Menu Creation
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

        //Edit Menu Creation
        menu = new JMenu("Edit");
        menuBar.add(menu);

        //Edit->Select All
        menuItem = new JMenuItem("Select All");
        menuItem.setMnemonic(KeyEvent.VK_A);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        menuItem.setActionCommand("select");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        //Edit->Cut
        menuItem = new JMenuItem("Cut");
        menuItem.setMnemonic(KeyEvent.VK_X);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        menuItem.setActionCommand("cut");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        //Edit->Copy
        menuItem = new JMenuItem("Copy");
        menuItem.setMnemonic(KeyEvent.VK_C);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        menuItem.setActionCommand("copy");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        //Edit->Paste
        menuItem = new JMenuItem("Paste");
        menuItem.setMnemonic(KeyEvent.VK_V);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        menuItem.setActionCommand("paste");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        //Edit->Undo
        menuItem = new JMenuItem("Undo");
        menuItem.setMnemonic(KeyEvent.VK_Z);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        menuItem.setActionCommand("undo");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        menu.addSeparator();

        //Edit->Search
        menuItem = new JMenuItem("Search...");
        menu.add(menuItem);

        //Edit->Restore Notes
        menuItem = new JMenuItem("Restore Deleted Notes");
        menu.add(menuItem);

        //Support Menu Creation
        menu = new JMenu("Support");
        menuBar.add(menu);

        //Support->Report Bug
        menuItem = new JMenuItem("Report Bug");
        menuItem.setMnemonic(KeyEvent.VK_S);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_R, ActionEvent.ALT_MASK));
        menuItem.setActionCommand("bug");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        //Support->Contact Support
        menuItem = new JMenuItem("Contact Support");
        menuItem.setMnemonic(KeyEvent.VK_C);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_C, ActionEvent.ALT_MASK));
        menuItem.setActionCommand("email");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        //Help Menu Creation
        menu = new JMenu("Help");
        menuBar.add(menu);

        //Help->Help Topics
        JMenu subMenu = new JMenu("Help Topics");
        menu.add(subMenu);

        //Help->Help Topics->Opening Notebook
        menuItem = new JMenuItem("Opening Notebook");
        subMenu.add(menuItem);

        //Help->Help Topics->Closing Notebook
        menuItem = new JMenuItem("Closing Notebook");
        subMenu.add(menuItem);

        //Help->Help Topics->Reminders
        menuItem = new JMenuItem("Reminders");
        subMenu.add(menuItem);

        //Help->Help Topics->Text Messaging
        menuItem = new JMenuItem("Text Messaging");
        subMenu.add(menuItem);

        //Help->Help Topics->Hiding Notes
        menuItem = new JMenuItem("Hiding Notes");
        subMenu.add(menuItem);
        menu.addSeparator();

        //Help->About
        menuItem = new JMenuItem("About");
        menuItem.setActionCommand("about");
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
          else if ("about".equals(e.getActionCommand())) { //about
            about();
        }
          else if ("email".equals(e.getActionCommand())) { //email
            try {
                email("Local%20Note%20Help");
            } catch ( IOException x ) {
                x.printStackTrace();
            } catch ( URISyntaxException x ) {
                x.printStackTrace();
            }
        }
          else if ("bug".equals(e.getActionCommand())) { //bug report
              try {
                  email("Bug%20Report");
              } catch ( IOException x ) {
                  x.printStackTrace();
              } catch ( URISyntaxException x ) {
                  x.printStackTrace();
              }
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

    protected void saveNote() {
        Component i = desktop.getSelectedFrame();
        //dump(i, 0, 0);
        if (i instanceof noteFrame) {
            noteFrame n = (noteFrame) i;
            //System.out.println("Path: "+n.path);
            if (n.path == null) {
                JFileChooser saver = new JFileChooser();
                int option = saver.showSaveDialog(n);
                File current = saver.getSelectedFile();
                String path = current.getAbsolutePath();
                String filename = current.getName();
                if (!path.endsWith(".txt")) {
                    path = path + ".txt";
                    filename = filename + ".txt";
                }
                n.path = path;
                n.setTitle(filename);
                n.title = filename;
                if (option == JFileChooser.APPROVE_OPTION) {
                    try {
                        BufferedWriter out = new BufferedWriter(new FileWriter(path));
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

    //Creates 'About' Note
    protected void about() {
        noteFrame frame = new noteFrame();
        frame.setVisible(true);
        frame.textArea.setText("LocalNote\n\nVersion1.0\n\n\u00a9 Team JBMH. All rights reserved.\n\n" +
        "This software was created by Team JBMH.\n" +
        "LocalNote was created for academic purposes and should not be redistributed or reproduced.");
        frame.textArea.setEditable(false);
        frame.title = "About";
        desktop.add(frame);
        try {
            frame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
        }
    }

    //Send support email
    protected void email(String sub) throws IOException, URISyntaxException {
        if (Desktop.isDesktopSupported()
            && (desktopEmail = Desktop.getDesktop()).isSupported(Desktop.Action.MAIL)) {
          URI mailto = new URI("mailto:localnoteadmin@gmail.com?subject=" + sub);
          desktopEmail.mail(mailto);
        } else {
          // TODO fallback to some Runtime.exec(..) voodoo?
          throw new RuntimeException("desktop doesn't support mailto; mail is dead anyway ;)");
        }

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
