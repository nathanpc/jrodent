package com.innoveworkshop.rodent.ui.components;

import com.innoveworkshop.rodent.models.Item;
import com.innoveworkshop.rodent.utils.GopherConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

/**
 * A single browser instance.
 *
 * @author Nathan Campos {@literal nathan@innoveworkshop.com}
 */
public class Browser extends JList implements ActionListener {
	private String title;
	private ArrayList<Item> items;

	/**
	 * Initializes a generic browser instance with a title.
	 *
	 * @param title Instance's title.
	 */
	public Browser(String title) {
		super();
		this.items = new ArrayList<Item>();
		this.title = title;

		// Set up our JList component.
		GopherItemCellRenderer renderer = new GopherItemCellRenderer();
		setCellRenderer(renderer);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	/**
	 * Navigates this browser to the specified URL.
	 *
	 * @param uri URL of the Gopher resource.
	 *
	 * @throws IOException if any error occurs with the server connection.
	 */
	public void navigateTo(URI uri) throws IOException {
		// Connect to the server and fetch the listing.
		GopherConnection conn = new GopherConnection(uri);
		items = conn.fetchItemListing();

		// Populate our list with the returned results.
		setListData(items.toArray());
	}

	/**
	 * Handles actions that are performed inside the browser context.
	 *
	 * @param e Action event to be handled.
	 */
	public void actionPerformed(ActionEvent e) {
	}
}
