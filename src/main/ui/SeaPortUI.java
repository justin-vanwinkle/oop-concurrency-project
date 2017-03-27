/**
 * Filename: SeaPortUI.java
 * Date: 26 March 2017
 * Author: Justin VanWinkle
 * Purpose: This class serves as the UI for the Sea Port Program
 */

package main.ui;

import main.SeaPortProgram;
import main.thing.Thing;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class SeaPortUI {

    private SeaPortProgram spp;

    // the controls to be used
    private JFrame frame;
    private JPanel btnPanel;
    private JButton btnFileSelect;
    private JButton btnSearch;
    private JTextField txtSearch;
    private JTextArea txtOutputArea;

    /**
     * The constructor for this UI
     * @param spp the context for this ui
     */
    public SeaPortUI(SeaPortProgram spp) {
        this.spp = spp;

        // initialize this ui
        new Thread(() -> init() ).start();
    }

    /**
     * This method calls the appropriate GUI initializers in their appropriate orders and makes the frame visible
     * after all initialization is done.
     */
    private void init() {
        initFrame();
        initComponents();
        frame.setResizable(true);
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
        frame.add(initPanel(), BorderLayout.NORTH);
        // add scroll pane with text area
        txtOutputArea = new JTextArea();
        txtOutputArea.setFont(new Font("Monospaced", 0, 12));
        frame.add(new JScrollPane( txtOutputArea ));
        // set default close action
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // set frame size
        frame.setPreferredSize(new Dimension(500,900));
        return frame;
    }

    /**
     * Initialization method for JPanel
     */
    private JPanel initPanel() {
        // instantiate jpanel
        btnPanel = new JPanel();
        // set padding for components
        btnPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        // set layout
        btnPanel.setLayout((new GridBagLayout()));
        return btnPanel;
    }

    /**
     * Initialization method for the GUI components
     */
    private void initComponents() {
        // instantiate the components
        btnFileSelect = new JButton("Select File");
        btnSearch = new JButton("Search");
        txtSearch = new JTextField();

        // add components to panel
        addComponent(1,0,1,1,1,1,GridBagConstraints.NONE, GridBagConstraints.EAST, btnPanel, btnFileSelect);
        addComponent(0,1,1,1,1,1,GridBagConstraints.BOTH, GridBagConstraints.CENTER, btnPanel, txtSearch);
        addComponent(1,1,1,1,1,1,GridBagConstraints.NONE, GridBagConstraints.WEST, btnPanel, btnSearch);


        // add listeners
        btnFileSelect.addActionListener(e -> chooseFileAction());
        btnSearch.addActionListener(e -> searchAction());
    }

    /**
     * This method sets GridBagConstraints and adds specified component to specified container.
     * @param x x positioning on the grid
     * @param y y positioning on the grid
     * @param gw width on the grid
     * @param gh height on the grid
     * @param wx weight in the x direction
     * @param wy weight in the y direction
     * @param fill enumeration for how this should fill the container
     * @param anchor enumeration for how this should anchor in the container
     * @param aContainer the container this component should go in
     * @param aComponent the component to be placed
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

        // place component
        gridBagLayout.setConstraints(aComponent, c);
        aContainer.add(aComponent, c);
    }

    /**
     * Lets user select a file and builds a world based on that file
     */
    private void chooseFileAction() {
        // get a file chooser at the current path
        JFileChooser fc = new JFileChooser(new java.io.File("."));
        // open the dialog
        fc.showDialog(frame, "Open");

        // create the world
        spp.createWorld(fc.getSelectedFile().getPath());

        // ouput the string representation of the world
        txtOutputArea.setText(spp.getWorld().toString());
    }

    /**
     * Lets user search for an object in the world
     */
    private void searchAction() {
        // get the pattern
        String pattern = txtSearch.getText();

        // search
        ArrayList<Thing> matches = spp.getWorld().search(pattern);

        // get a string builder
        StringBuilder sb = new StringBuilder("");
        sb.append("Matches:\n");

        // add all the matches to the string
        for(Thing match : matches) {
            sb.append("--------------------------------\n\nMatch:\n");
            sb.append(match + "\n\n");
        }

        sb.append("--------------------------------\nEnd Matches\n");

        // output the matches to the text area
        txtOutputArea.setText(sb.toString());
    }

}
