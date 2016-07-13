/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package localnote;

import javax.swing.JInternalFrame;

import java.awt.event.*;
import java.awt.*;

public class noteFrame extends JInternalFrame{
    static int openFrameCount = 0;
    static final int xOffset = 30, yOffset = 30;
    private TextArea textArea = new TextArea("", 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);

    
    public noteFrame() {
        super("Document #" + (++openFrameCount), 
              true, //resizable
              true, //closable
              true, //maximizable
              true);//iconifiable

        //...Create the GUI and put it in the window...

        //...Then set the window size or call pack...
        setSize(300,300);

        //Set the window's location.
        this.textArea.setFont(new Font("Century Gothic", Font.BOLD, 12));
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(textArea);
        setLocation(xOffset, yOffset*openFrameCount);
    }
}
