/**
 * Filename: SortPicker.java
 * Date: 9 April 2017
 * Author: Justin VanWinkle
 * Purpose: This class serves as the UI for choosing the sort method
 */

package main.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Created by vanwinklej on 4/9/17.
 */
public class SortPicker {
    private JFrame frame;
    private JPanel panel;
    private JButton btnSort;
    private JComboBox cbThing;
    private JComboBox cbField;
    private JComboBox cbOrder;
    private SeaPortUI mainUI;

    // string arrays for comboboxes
    private final String[] things = {"All", "Ports", "Ships", "Que", "Persons", "Docks"};
    private final String[] fields = {"Name", "Weight", "Length", "Draft", "Width"};
    private final String[] orders = {"Ascending", "Descending"};

    /**
     * The constructor for this UI
     * @param mainUI the context for this ui
     */
    public SortPicker(SeaPortUI mainUI) {
        this.mainUI = mainUI;

        // initialize this ui
        new Thread(() -> init() ).start();


    }

    /**
     * This method calls the appropriate GUI initializers in their appropriate orders and makes the frame visible
     * after all initialization is done.
     */
    private void init() {
        initPanel();
        initComponents();
        initFrame();
    }

    /**
     * JFrame initialization method
     */
    private JFrame initFrame() {
        // instantiate jframe with a title
        frame = new JFrame("Sort");
        // instantiate borderlayout and assign it as the layout manager of frame
        frame.setLayout(new BorderLayout());
        // add the panel
        frame.add(panel, BorderLayout.CENTER);
        // set default close action
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // set frame size
        frame.setPreferredSize(new Dimension(200,200));
        // not resizeable
        frame.setResizable(false);
        // auto-size to squeeze everything as tight as it will go
        frame.pack();
        // make it all visible to the user
        frame.setVisible(true);

        return frame;
    }

    /**
     * Initialization method for JPanel
     */
    private void initPanel() {
        panel = new JPanel();
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setLayout((new GridBagLayout()));
    }

    /**
     * Initialization method for the GUI components
     */
    private void initComponents() {
        // instantiate the components
        btnSort = new JButton("Sort");
        cbThing = new JComboBox(things);
        cbField = new JComboBox(fields);
        cbOrder = new JComboBox(orders);


        // add components to panel
        addComponent(0,0,1,1,1,1,GridBagConstraints.BOTH, GridBagConstraints.WEST, panel, new JLabel("Sort:"));
        addComponent(1,0,1,1,1,1,GridBagConstraints.BOTH, GridBagConstraints.WEST, panel, cbThing);
        addComponent(0,1,1,1,1,1,GridBagConstraints.BOTH, GridBagConstraints.WEST, panel, new JLabel("By:"));
        addComponent(1,1,1,1,1,1,GridBagConstraints.BOTH, GridBagConstraints.WEST, panel, cbField);
        addComponent(0,2,1,1,1,1,GridBagConstraints.BOTH, GridBagConstraints.WEST, panel, new JLabel("Order:"));
        addComponent(1,2,1,1,1,1,GridBagConstraints.BOTH, GridBagConstraints.WEST, panel, cbOrder);
        addComponent(1,3,1,1,1,1,GridBagConstraints.BOTH, GridBagConstraints.WEST, panel, btnSort);

        // listener for sort
        btnSort.addActionListener(e -> mainUI.sortAction(
                String.valueOf(cbThing.getSelectedItem()),
                String.valueOf(cbField.getSelectedItem()),
                String.valueOf(cbOrder.getSelectedItem())));

        // listener for combobox updates based on thing selection
        cbThing.addActionListener(e -> {
            // get the CB select
            String selected = String.valueOf(cbThing.getSelectedItem());

            // if ship or que, add all options
            if (selected == "Ships" || selected == "Que") {
                cbField.removeAllItems();
                for (String field : fields) {
                    cbField.addItem(field);
                }

            }
            // otherwise, only name
            else {
                cbField.removeAllItems();
                cbField.addItem("Name");
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

}
