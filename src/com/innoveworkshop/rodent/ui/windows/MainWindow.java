package com.innoveworkshop.rodent.ui.windows;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
	/**
	 * Creates the main window of our application.
	 */
	public MainWindow() {
		super();
		setupComponents();
	}

	/**
	 * Sets up the components and lays them out in our frame.
	 */
	private void setupComponents() {
		// Top-level layout manager for the frame.
		BorderLayout topLayout = new BorderLayout();
		setLayout(topLayout);

		// Sets the properties for the frame itself.
		setSize(600, 800);
		setJMenuBar(setupMenuBar());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
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
}
