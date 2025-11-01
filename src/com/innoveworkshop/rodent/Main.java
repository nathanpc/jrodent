package com.innoveworkshop.rodent;

import com.innoveworkshop.rodent.ui.windows.MainWindow;
import com.innoveworkshop.rodent.utils.ResourceManager;

import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The application's main runnable class.
 *
 * @author Nathan Campos {@literal <nathan@innoveworkshop.com>}
 */
public class Main {
	private static final Logger Log = Logger.getLogger(Main.class.getName());

	/**
	 * Application's main entry point.
	 *
	 * @param args Command line arguments.
	 */
	public static void main(String[] args) {
		System.out.println("jRodent");

		// Implicitly load all our resources.
		ResourceManager.getInstance();

		// Show the application's main window.
		setNativeLookAndFeel();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MainWindow window = new MainWindow();
				window.setVisible(true);
			}
		});
	}

	/**
	 * Sets the platform's native Swing Look and Feel.
	 */
	private static void setNativeLookAndFeel() {
		// Print out the available themes.
		Log.log(Level.INFO, "Available Swing look and feels:");
		UIManager.LookAndFeelInfo[] looks = UIManager.getInstalledLookAndFeels();
		for (UIManager.LookAndFeelInfo look : looks) {
			Log.log(Level.INFO, "    " + look.getName() + ": " + look.getClassName());
		}
		Log.log(Level.INFO, "System look and feel: " + UIManager.getSystemLookAndFeelClassName());

		try {
			System.setProperty("apple.laf.useScreenMenuBar", "true");
			System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Rodent");
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException e) {
			Log.log(Level.WARNING, "Failed to set system look and feel: " + e);
		} catch (ClassNotFoundException e) {
			Log.log(Level.WARNING, "Failed to set system look and feel: " + e);
		} catch (InstantiationException e) {
			Log.log(Level.WARNING, "Failed to set system look and feel: " + e);
		} catch (IllegalAccessException e) {
			Log.log(Level.WARNING, "Failed to set system look and feel: " + e);
		}
	}
}
