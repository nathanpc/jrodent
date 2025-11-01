package com.innoveworkshop.rodent.ui.components;

import com.innoveworkshop.rodent.models.Item;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * A single browser instance.
 *
 * @author Nathan Campos {@literal nathan@innoveworkshop.com}
 */
public class Browser extends JList implements ActionListener {
	private String title;
	private final ArrayList<Item> items;

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

		// Give ourselves some sample data.
		populateSample();
		setListData(items.toArray());
	}

	/**
	 * Handles actions that are performed inside the browser context.
	 *
	 * @param e Action event to be handled.
	 */
	public void actionPerformed(ActionEvent e) {
	}

	private void populateSample() {
		items.add(new Item('i', "Hello world!"));
		items.add(new Item('0', "Another example"));
		items.add(new Item('1', "It works!"));
	}
}
