package com.innoveworkshop.rodent.ui.windows;

import com.innoveworkshop.rodent.utils.ResourceManager;
import sun.nio.ch.sctp.PeerAddrChange;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

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
		// Set the application icon.
		setApplicationIcon();

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
}
