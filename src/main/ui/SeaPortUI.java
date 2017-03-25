package main.ui;

import main.SeaPortProgram;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static main.SeaPortProgram.parseObjectDefinitions;

/**
 * Created by vanwinklej on 3/23/17.
 */
public class SeaPortUI {

    private SeaPortProgram spp;

    private JFrame frame;
    private JPanel panel;
    private JButton btnFileSelect;

    public SeaPortUI(SeaPortProgram spp) {
        this.spp = spp;

        javax.swing.SwingUtilities.invokeLater(() -> init());
    }

    /**
     * This method calls the appropriate GUI initializers in their appropriate orders and makes the frame visible
     * after all initialization is done.
     */
    private void init() {
        initFrame();
        initComponents();
        frame.setResizable(false);
        // auto-size to squeeze everything as tight as it will go
        frame.pack();
        // make it all visible to the user
        frame.setVisible(true);
    }

    /**
     * JFrame initialization method
     */
    private JFrame initFrame() {
        // instantiate jframe with a title
        frame = new JFrame("Sea Port");
        // instantiate borderlayout and assign it as the layout manager of frame
        frame.setLayout(new BorderLayout());
        // add pnlMain in frame at center
        frame.add(initPanel(), BorderLayout.CENTER);
        // set default close action
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return frame;
    }

    /**
     * Initialization method for JPanel
     */
    private JPanel initPanel() {
        // instantiate jpanel
        panel = new JPanel();
        // set padding for components
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        // set layout
        panel.setLayout((new GridBagLayout()));
        return panel;
    }

    /**
     * Initialization method for the GUI components
     */
    private void initComponents() {
        // instantiate the components
        btnFileSelect = new JButton("Select File");

        addComponent(0,0,1,1,1,1,GridBagConstraints.NONE, GridBagConstraints.CENTER, panel, btnFileSelect);

        // add listeners
        btnFileSelect.addActionListener(new ButtonAction());
    }

    /**
     * This method sets GridBagConstraints and adds specified component to specified container.
     * @param x int
     * @param y int
     * @param gw int
     * @param gh int
     * @param wx double
     * @param wy double
     * @param fill int
     * @param anchor int
     * @param aContainer Container
     * @param aComponent Component
     */
    private void addComponent(int x, int y, int gw, int gh, double wx, double wy, int fill, int anchor
            ,Container aContainer
            ,Component aComponent) {
        // instantiate GBL and GBC
        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        // set constraints
        c.fill = fill;
        c.anchor = anchor;
        c.gridx = x;
        c.gridy = y;
        c.gridwidth = gw;
        c.gridheight = gh;
        c.weightx = wx;
        c.weighty = wy;
//        c.insets = new Insets(5, 5, 5, 5);
        // place component
        gridBagLayout.setConstraints(aComponent, c);
        aContainer.add(aComponent, c);
    }

    /**
     * This class is the action listener for building a directed graph
     */
    public class ButtonAction implements ActionListener {
        /**
         * This method handles the button click and delegates actions
         *
         * @param e ActionEvent
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fc = new JFileChooser(new java.io.File("."));
            fc.showDialog(frame, "Open");

            spp.createWorld(fc.getSelectedFile().getPath());

        }
    }
}
