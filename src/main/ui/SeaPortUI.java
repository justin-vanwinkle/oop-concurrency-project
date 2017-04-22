/**
 * Filename: SeaPortUI.java
 * Date: 26 March 2017
 * Author: Justin VanWinkle
 * Purpose: This class serves as the UI for the Sea Port Program
 */

package main.ui;

import main.SeaPortProgram;
import main.thing.Thing;
import main.thing.ship.Ship;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SeaPortUI {

    private SeaPortProgram spp;

    // the controls to be used
    private JFrame frame;
    private JPanel btnPanel;
    private JButton btnFileSelect;
    private JButton btnSearch;
    private JTextField txtSearch;
    private JTextArea txtOutputArea;
    private JTree tree;
    private DefaultMutableTreeNode root;
    private JPanel treePanel;
    private JPanel textPanel;
    private JSplitPane splitPane;
    private JButton btnSort;

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
        initPanels();
        initComponents();
        initFrame();

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
        frame.add(btnPanel, BorderLayout.NORTH);
        frame.add(splitPane, BorderLayout.CENTER);

        // set default close action
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // set frame size
        frame.setPreferredSize(new Dimension(1000,700));

        return frame;
    }

    /**
     * Initialization method for JPanel
     */
    private void initPanels() {
        btnPanel = new JPanel();
        btnPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        btnPanel.setLayout((new GridBagLayout()));

        treePanel = new JPanel();
        treePanel.setBorder(new BevelBorder(1));
        treePanel.setLayout(new GridBagLayout());

        textPanel = new JPanel();
        textPanel.setBorder(new BevelBorder(1));
        treePanel.setLayout(new GridBagLayout());
    }

    /**
     * Initialization method for the GUI components
     */
    private void initComponents() {
        // instantiate the components
        btnFileSelect = new JButton("Select File");

        btnSearch = new JButton("Search");
        btnSearch.setEnabled(false);

        btnSort = new JButton("Sort");
        btnSort.setEnabled(false);

        txtSearch = new JTextField();

        // add tree
        root = new DefaultMutableTreeNode("The World");
        tree = new JTree(root);
        tree.setRootVisible(false);
        JScrollPane treeScroll = new JScrollPane(tree);

        // add text area
        txtOutputArea = new JTextArea();
        txtOutputArea.setFont(new Font("Monospaced", 0, 12));
        JScrollPane textScroll = new JScrollPane(txtOutputArea);

        // set split pane
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treeScroll, textScroll);
        splitPane.setDividerLocation(400);

        // add components to panel
        addComponent(0,0,1,1,1,1,GridBagConstraints.NONE, GridBagConstraints.WEST, btnPanel, btnFileSelect);
        addComponent(1,0,1,1,5,1,GridBagConstraints.BOTH, GridBagConstraints.EAST, btnPanel, txtSearch);
        addComponent(2,0,1,1,1,1,GridBagConstraints.NONE, GridBagConstraints.WEST, btnPanel, btnSearch);
        addComponent(0,1,1,1,1,1,GridBagConstraints.NONE, GridBagConstraints.WEST, btnPanel, btnSort);

        // add listeners
        btnFileSelect.addActionListener(e -> chooseFileAction());
        btnSearch.addActionListener(e -> searchAction());
        btnSort.addActionListener(e -> new SortPicker(this));
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

        // draw the tree
        drawTree();

        // enable buttons
        btnSort.setEnabled(true);
        btnSearch.setEnabled(true);

        spp.startWorld();
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


    private void drawTree() {

        // clear all
        root.removeAllChildren();

        // add all ports to world
        spp.getWorld().getPorts().forEach( port -> {
            DefaultMutableTreeNode portNode = new DefaultMutableTreeNode(port);
            root.add(portNode);

            // add nodes to port
            DefaultMutableTreeNode docksNode = new DefaultMutableTreeNode("Docks");
            DefaultMutableTreeNode shipsNode = new DefaultMutableTreeNode("Ships");
            DefaultMutableTreeNode queNode = new DefaultMutableTreeNode("Que");
            DefaultMutableTreeNode personsNode = new DefaultMutableTreeNode("Personnel");
            portNode.add(docksNode);
            portNode.add(shipsNode);
            portNode.add(queNode);
            portNode.add(personsNode);

            // add all docks to docks node
            port.getDocks().forEach(dock -> {
                DefaultMutableTreeNode dockNode = new DefaultMutableTreeNode(dock);
                docksNode.add(dockNode);

                // add the docked ship here
                dockNode.add(new DefaultMutableTreeNode(dock.getShip()));
            });

            // add each ship to ships
            port.getShips().forEach(ship -> {
                DefaultMutableTreeNode shipNode = new DefaultMutableTreeNode(ship);
                shipsNode.add(shipNode);
            });

            // add each queued ship to que
            port.getQue().forEach(ship -> {
                DefaultMutableTreeNode shipNode = new DefaultMutableTreeNode(ship);
                queNode.add(shipNode);
            });

            // add each person to persons
            port.getPersons().forEach(person -> {
                DefaultMutableTreeNode personNode = new DefaultMutableTreeNode(person);
                personsNode.add(personNode);
            });

        });

        // reload
        ((DefaultTreeModel)tree.getModel()).reload(root);
    }

    /**
     * Sorts the appropriate fields based on the inbound params
     * @param thing the thing to be sorted
     * @param field the field to sort on
     * @param order the direction to sort
     */
    void sortAction(String thing, String field, String order) {

        // get the comparator based on the field
        Comparator comparator;
        switch (field) {
            case "Name":
                comparator = Comparator.comparing(Thing::getName);
                break;

            case "Weight":
                comparator = Comparator.comparing(Ship::getWeight);
                break;

            case "Draft":
                comparator = Comparator.comparing(Ship::getDraft);
                break;

            case "Length":
                comparator = Comparator.comparing(Ship::getLength);
                break;

            case "Width":
                comparator = Comparator.comparing(Ship::getWidth);
                break;

            default:
                comparator = Comparator.comparing(Thing::getName);
                break;
        }

        // route by thing
        switch (thing) {
            // sort all
            case "All":
                // ascending
                if (order == "Ascending") {
                    // sort ports and all inside
                    Collections.sort(spp.getWorld().getPorts(), comparator);
                    spp.getWorld().getPorts().forEach( port -> {
                        Collections.sort(port.getPersons(), comparator);
                        Collections.sort(port.getDocks(), comparator);
                        Collections.sort(port.getShips(), comparator);
                        Collections.sort(port.getQue(), comparator);
                    });
                }
                // descending
                else {
                    // sort ports and all inside
                    Collections.sort(spp.getWorld().getPorts(), comparator.reversed());
                    spp.getWorld().getPorts().forEach( port -> {
                        Collections.sort(port.getPersons(), comparator.reversed());
                        Collections.sort(port.getDocks(), comparator.reversed());
                        Collections.sort(port.getShips(), comparator.reversed());
                        Collections.sort(port.getQue(), comparator.reversed());
                    });
                }
                break;

                // sort ports
            case "Ports":
                // ascending sort
                if (order == "Ascending") {
                    Collections.sort(spp.getWorld().getPorts(), comparator);
                }
                // descending sort
                else {
                    Collections.sort(spp.getWorld().getPorts(), comparator.reversed());
                }
                break;

                // sort ships
            case "Ships":
                // ascending
                if (order == "Ascending") {
                    spp.getWorld().getPorts().forEach( port -> Collections.sort(port.getShips(), comparator));
                }
                else {
                    // descending
                    spp.getWorld().getPorts().forEach( port -> Collections.sort(port.getShips(), comparator.reversed()));
                }
                break;

                // sort que
            case "Que":
                // ascending
                if (order == "Ascending") {
                    spp.getWorld().getPorts().forEach( port -> Collections.sort(port.getQue(), comparator));
                }
                // descending
                else {
                    spp.getWorld().getPorts().forEach( port -> Collections.sort(port.getQue(), comparator.reversed()));
                }
                break;

                // sort persons
            case "Persons":
                // ascending
                if (order == "Ascending") {
                    spp.getWorld().getPorts().forEach( port -> Collections.sort(port.getPersons(), comparator));
                }
                // descending
                else {
                    spp.getWorld().getPorts().forEach( port -> Collections.sort(port.getPersons(), comparator.reversed()));
                }
                break;

                // sort docks
            case "Docks":
                // ascending
                if (order == "Ascending") {
                    spp.getWorld().getPorts().forEach( port -> Collections.sort(port.getDocks(), comparator));
                }
                // descending
                else {
                    spp.getWorld().getPorts().forEach( port -> Collections.sort(port.getDocks(), comparator.reversed()));
                }
                break;
        }

        // redraw the tree
        drawTree();
    }
}
