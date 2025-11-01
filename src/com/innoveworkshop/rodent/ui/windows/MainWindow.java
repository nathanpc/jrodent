package com.innoveworkshop.rodent.ui.windows;

import com.innoveworkshop.rodent.models.Item;
import com.innoveworkshop.rodent.ui.components.GopherItemCellRenderer;
import com.innoveworkshop.rodent.utils.ResourceManager;
import sun.nio.ch.sctp.PeerAddrChange;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Application's main browser window.
 *
 * @author Nathan Campos {@literal nathan@innoveworkshop.com}
 */
public class MainWindow extends JFrame {
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

		// Setup main list of the browser.
		GopherItemCellRenderer renderer = new GopherItemCellRenderer();
		list = new JList(items.toArray());
		list.setCellRenderer(renderer);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Setup JScrollPane for our browser's list.
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
