/**
 * Filename: SeaPortUI.java
 * Date: 26 March 2017
 * Author: Justin VanWinkle
 * Purpose: This class serves as the UI for the Sea Port Program
 */

package main.ui;

import main.SeaPortProgram;
import main.thing.Dock;
import main.thing.Job;
import main.thing.Thing;
import main.thing.ship.Ship;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.ExpandVetoException;
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
    private JButton btnSuspendJob;
    private JButton btnCancelJob;

    /**
     * The constructor for this UI
     * @param spp the context for this ui
     */
    public SeaPortUI(SeaPortProgram spp) {
        this.spp = spp;

        // initialize this ui
        init();
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

        btnSort = new JButton("Sort Menu");
        btnSort.setEnabled(false);

        btnCancelJob = new JButton("Cancel Job");
        btnCancelJob.setEnabled(false);

        btnSuspendJob = new JButton("Suspend Job");
        btnSuspendJob.setEnabled(false);

        txtSearch = new JTextField();

        // add tree
        root = new DefaultMutableTreeNode("The World");
        tree = new JTree(root);
        tree.setRootVisible(false);
        tree.setCellRenderer(new ProgressBarRenderer());
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
        addComponent(1,2,1,1,5,1,GridBagConstraints.BOTH, GridBagConstraints.EAST, btnPanel, txtSearch);
        addComponent(2,2,1,1,1,1,GridBagConstraints.NONE, GridBagConstraints.WEST, btnPanel, btnSearch);
        addComponent(2,0,1,1,1,1,GridBagConstraints.NONE, GridBagConstraints.EAST, btnPanel, btnSort);
        addComponent(0,1,1,1,1,1,GridBagConstraints.NONE, GridBagConstraints.WEST, btnPanel, btnCancelJob);
        addComponent(0,2,1,1,1,1,GridBagConstraints.NONE, GridBagConstraints.WEST, btnPanel, btnSuspendJob);

        // add listeners
        btnFileSelect.addActionListener(e -> chooseFileAction());
        btnSearch.addActionListener(e -> searchAction());
        btnSort.addActionListener(e -> new SortPicker(this));
        btnSuspendJob.addActionListener(e -> suspendAction());
        btnCancelJob.addActionListener(e -> cancelAction());

        // prevent the tree from collapsing
        tree.addTreeWillExpandListener(new TreeWillExpandListener() {
            public void treeWillExpand(TreeExpansionEvent e) { }
            public void treeWillCollapse(TreeExpansionEvent e) throws ExpandVetoException {
                throw new ExpandVetoException(e);
            }
        });
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
        btnCancelJob.setEnabled(true);
        btnSuspendJob.setEnabled(true);
    }

    /**
     * Gets a Job ID from the user and suspends the specified job
     */
    private void suspendAction() {

        // show dialog
        String s = (String)JOptionPane.showInputDialog(
                frame, "Job ID to suspend:",
                "Suspend Job",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "");

        // parse and suspend
        Thing thing = spp.getWorld().getThing(Integer.parseInt(s));
        if (thing instanceof Job) {
            ((Job) thing).suspend();
        }
    }

    /**
     * Gets a Job ID from the user and cancels the specified job
     */
    private void cancelAction() {

        // show dialog
        String s = (String)JOptionPane.showInputDialog(
                frame, "Job ID to cancel:",
                "Cancel Job",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "");

        // parse and cancel
        Thing thing = spp.getWorld().getThing(Integer.parseInt(s));
        if (thing instanceof Job) {
            ((Job) thing).cancel();
        }
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


    /**
     *  Updates the tree until all ports have gone inactive then disposes of the thread
     */
    private void updateTree() {

        // kick off the tree updater
        new Thread(() -> {
            while (spp.getWorld().activePorts()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }


                // for each port, add a dock
                for (int i = 0; i < root.getChildCount(); i++) {
                    DefaultMutableTreeNode portNode = (DefaultMutableTreeNode) root.getChildAt(i);

                    // step into the docks node
                    for (int j = 0; j < portNode.getChildCount(); j++) {
                        Object obj = ((DefaultMutableTreeNode) portNode.getChildAt(j)).getUserObject();
                        // make sure this was a dock
                        if (obj != "Docks") {
                            continue;
                        }
                        DefaultMutableTreeNode docksNode = (DefaultMutableTreeNode) portNode.getChildAt(j);

                        // for each dock, add a ship or an empty node as appropriate
                        for (int k = 0; k < docksNode.getChildCount(); k++) {
                            DefaultMutableTreeNode dockNode = (DefaultMutableTreeNode) docksNode.getChildAt(k);
                            Dock dock = (Dock) ((DefaultMutableTreeNode) docksNode.getChildAt(k)).getUserObject();

                            obj = ((DefaultMutableTreeNode) dockNode.getFirstChild()).getUserObject();
                            if (obj instanceof String) {
                                continue;
                            }

                            // get the ship of this dock
                            Ship ship = (Ship) obj;


                            // if the ship is null add an empty node
                            if (dock.getShip() == null) {
                                dockNode.removeAllChildren();
                                DefaultMutableTreeNode shipNode = new DefaultMutableTreeNode("Empty");
                                dockNode.add(shipNode);
                                ((DefaultTreeModel) tree.getModel()).nodeStructureChanged(shipNode);
                            }

                            // if the ship in dock is not ship in tree, replace
                            if (dock.getShip() != ship) {

                                dockNode.removeAllChildren();
                                DefaultMutableTreeNode shipNode = new DefaultMutableTreeNode(dock.getShip());
                                dockNode.add(shipNode);

                                shipNode.removeAllChildren();

                                // ships and jobs change, synchronize here
                                if (dock.getShip() != null) {
                                    synchronized (dock.getShip()) {
                                        // set jobs for ship
                                        for (Job job : dock.getShip().getJobs()) {
                                            synchronized (job) {
                                                DefaultMutableTreeNode jobNode = new DefaultMutableTreeNode(job);
                                                shipNode.add(jobNode);
                                            }
                                        }
                                    }
                                }
                                // note the change
                                ((DefaultTreeModel) tree.getModel()).nodeStructureChanged(shipNode);
                            }
                        }
                    }
                }

                // expand the tree
                for (int i = 0; i < tree.getRowCount(); i++) {
                    tree.expandRow(i);
                }

                tree.revalidate();
                tree.repaint();

            }
        }).start();
    }


    /**
     * Draws a Jtree according to the underlying data structure
     */
    private synchronized void drawTree() {

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

                // add ship to dock
                DefaultMutableTreeNode shipNode = new DefaultMutableTreeNode(dock.getShip());
                dockNode.add(shipNode);

                // add jobs to the ship
                Ship ship = dock.getShip();
                if (ship != null) {
                    for (Job job : dock.getShip().getJobs()) {
                        DefaultMutableTreeNode jobNode = new DefaultMutableTreeNode(job);
                        shipNode.add(jobNode);
                    }
                }

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

        // note that the root has been changes and expand the new tree
        ((DefaultTreeModel)tree.getModel()).nodeStructureChanged(root);
        for (int i = 0; i < tree.getRowCount(); i++) {
            tree.expandRow(i);
        }

        updateTree();
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

    }

    /**
     * This class allows the rendering of a JProgressBar inside of the JTree
     */
    class ProgressBarRenderer extends DefaultTreeCellRenderer {

        private final JProgressBar bar = new JProgressBar(0, 100);

        /**
         * Constructor for the renderer
         */
        public ProgressBarRenderer() {
            super();
            setOpaque(true);
            bar.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 5));
        }

        /**
         * Builds the component to be rendered in the JTree
         * All following parameters are as defined in the Java 8 API documentation
         * @param tree
         * @param value
         * @param selected
         * @param expanded
         * @param leaf
         * @param row
         * @param hasFocus
         * @return the Object to be displayed in a JTree node
         */
        @Override
        public Component getTreeCellRendererComponent(JTree tree, final Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
            Object obj = ((DefaultMutableTreeNode) value).getUserObject();

            if (obj instanceof Job) {

                // build a progress bar
                int progress = ((Job) obj).getProgress();
                bar.setValue(progress);
                bar.setStringPainted(true);

                JPanel panel = new JPanel();
                panel.setLayout(new BorderLayout());

                JLabel label = new JLabel(obj.toString());

                // place the items on the panel
                panel.add(label, BorderLayout.EAST);
                panel.add(bar, BorderLayout.WEST);

                return panel;
            }

            else if (obj instanceof Thing) {
                super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
                return this;
            }

            else if (obj instanceof String || obj == null) {
                super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
                return this;
            }

            return null;
        }
    }
}
