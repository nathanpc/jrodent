package com.innoveworkshop.rodent.ui.windows;

import com.innoveworkshop.rodent.models.Item;
import com.innoveworkshop.rodent.ui.components.GopherItemCellRenderer;
import com.innoveworkshop.rodent.utils.ResourceManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Application's main browser window.
 *
 * @author Nathan Campos {@literal nathan@innoveworkshop.com}
 */
public class MainWindow extends JFrame implements ActionListener {
	private JToolBar toolbar;
	private JTextField addressBar;
	private JList list;
	private final ArrayList<Item> items;

	/**
	 * Creates the main window of our application.
	 */
	public MainWindow() {
		super();
		items = new ArrayList<Item>();
		setupComponents();
	}

	/**
	 * Sets up the components and lays them out in our frame.
	 */
	private void setupComponents() {
		// Set the application icon.
		setApplicationIcon();

		// Top-level layout manager for the frame.
		BorderLayout topLayout = new BorderLayout();
		setLayout(topLayout);

		// Sets the properties for the frame itself.
		setPreferredSize(new Dimension(650, 500));
		setJMenuBar(setupMenuBar());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();

		// Create sample data for now.
		populateSample();

		// Set up the application's toolbar.
		toolbar = new JToolBar("Navigation");
		toolbar.setFloatable(false);
		toolbar.setRollover(true);
		setupToolbar();
		add(toolbar, BorderLayout.PAGE_START);

		// Set up main list of the browser.
		GopherItemCellRenderer renderer = new GopherItemCellRenderer();
		list = new JList(items.toArray());
		list.setCellRenderer(renderer);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Set up JScrollPane for our browser's list.
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getViewport().add(list);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		pack();
	}

	/**
	 * Sets up the menu bar of the frame.
	 */
	private JMenuBar setupMenuBar() {
		JMenuBar mb = new JMenuBar();
		JMenu menu = null;
		JMenuItem item = null;

		// File menu.
		menu = new JMenu("File");
		item = new JMenuItem("Exit");
		menu.add(item);
		mb.add(menu);

		// Help menu.
		menu = new JMenu("Help");
		item = new JMenuItem("About...");
		menu.add(item);
		mb.add(menu);

		return mb;
	}

	/**
	 * Sets up the application's main {@link JToolBar} with navigation buttons
	 * and an address bar.
	 */
	private void setupToolbar() {
		// Add navigation buttons.
		toolbar.add(createToolbarButton("Back", "arrow_left_blue"));
		toolbar.add(createToolbarButton("Forward", "arrow_right_blue"));
		toolbar.add(createToolbarButton("Parent", "arrow_up_blue"));
		toolbar.add(createToolbarButton("Refresh", "refresh"));
		toolbar.addSeparator();

		// Add address bar and Go button.
		addressBar = new JTextField("gopher://gopher.floodgap.com:70/1/overbite");
		toolbar.add(addressBar);
		toolbar.add(createToolbarButton("Go", "media_play_green"));
	}

	/**
	 * Creates a {@link JButton} that's ready to be used in the application's
	 * {@link JToolBar}.
	 *
	 * @param tooltip       Label to show when hovering over the button.
	 * @param actionCommand Action command string to be triggered when clicked.
	 * @param iconName      Name of the icon used for the button.
	 *
	 * @return Button ready to be used in the application's toolbar.
	 */
	private JButton createToolbarButton(String tooltip, String actionCommand, String iconName) {
		JButton button = new JButton();

		// Populate common properties.
		button.setActionCommand(actionCommand);
		button.setToolTipText(tooltip);
		button.addActionListener(this);

		// Set the icon.
		try {
			button.setIcon(ResourceManager.getResourceIcon("ui_icons/" + iconName + ".png"));
		} catch (FileNotFoundException e) {
			System.err.println("ERROR: Failed to get " + tooltip + " button icon (" +
					iconName + ").");
		}

		return button;
	}

	/**
	 * Creates a {@link JButton} that's ready to be used in the application's
	 * {@link JToolBar}. This version automatically associates an Action
	 * Command based on the provided tooltip.
	 *
	 * @param tooltip  Label to show when hovering over the button.
	 * @param iconName Name of the icon used for the button.
	 *
	 * @return Button ready to be used in the application's toolbar.
	 */
	private JButton createToolbarButton(String tooltip, String iconName) {
		String actionCommand = tooltip.toLowerCase().replace(' ', '-');
		return createToolbarButton(tooltip, actionCommand, iconName);
	}

	/**
	 * Handles actions that are performed inside the browser context.
	 *
	 * @param e Action event to be handled.
	 */
	public void actionPerformed(ActionEvent e) {
	}

	/**
	 * Sets the window's icon.
	 */
	private void setApplicationIcon() {
		try {
			// Set icon on most OSes.
			Image icon = ResourceManager.getResourceIcon("icon/Rodent_64.png").getImage();
			setIconImage(icon);

			// Set dock application icon on Mac OS X.
			if (Package.getPackage("com.apple.eawt") != null)
				com.apple.eawt.Application.getApplication().setDockIconImage(icon);
		} catch (FileNotFoundException e) {
			System.out.println("WARNING: Failed to load application icon");
		}
	}

	private void populateSample() {
		items.add(new Item('i', "Hello world!"));
		items.add(new Item('0', "Another example"));
		items.add(new Item('1', "It works!"));
	}
}
