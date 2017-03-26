package main.ui;

import main.SeaPortProgram;
import main.thing.Thing;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by vanwinklej on 3/23/17.
 */
public class SeaPortUI {

    private SeaPortProgram spp;

    private JFrame frame;
    private JPanel btnPanel;
    private JButton btnFileSelect;
    private JButton btnSearch;
    private JTextField txtSearch;
    private JTextArea txtOutputArea;

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
        // add scroll pane
        txtOutputArea = new JTextArea();
        frame.add(new JScrollPane( txtOutputArea ));
        // set default close action
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

        addComponent(1,0,1,1,1,1,GridBagConstraints.NONE, GridBagConstraints.EAST, btnPanel, btnFileSelect);
        addComponent(0,1,1,1,1,1,GridBagConstraints.BOTH, GridBagConstraints.CENTER, btnPanel, txtSearch);
        addComponent(1,1,1,1,1,1,GridBagConstraints.NONE, GridBagConstraints.WEST, btnPanel, btnSearch);


        // add listeners
        btnFileSelect.addActionListener(e ->chooseFileAction());
        btnSearch.addActionListener(e -> searchAction());
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

    private void chooseFileAction() {
        JFileChooser fc = new JFileChooser(new java.io.File("."));
        fc.showDialog(frame, "Open");

        spp.createWorld(fc.getSelectedFile().getPath());

        txtOutputArea.setText(spp.getWorld().toString());

    }

    private void searchAction() {
        String pattern = txtSearch.getText();
        ArrayList<Thing> matches = spp.getWorld().search(pattern);

        StringBuilder sb = new StringBuilder("");
        sb.append("Matches:\n");

        for(Thing match : matches) {
            sb.append("--------------------------------\n\nMatch:\n");
            sb.append(match + "\n\n");
        }

        sb.append("--------------------------------\nEnd Matches\n");

        txtOutputArea.setText(sb.toString());
    }

}
