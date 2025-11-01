package com.innoveworkshop.rodent.models;

import javax.swing.*;

/**
 * Abstraction of a Gopher menu item.
 *
 * @author Nathan Campos {@literal nathan@innoveworkshop.com}
 */
public class Item {
	private char type;
	private String name;
	private String selector;
	private String hostname;
	private int port;

	/**
	 * Constructs a fully populated item.
	 *
	 * @param type     Item type character.
	 * @param name     Label associated with the item.
	 * @param selector Selector pointing to the address in the server.
	 * @param hostname Hostname of the associated server.
	 * @param port     Server's port.
	 */
	public Item(char type, String name, String selector, String hostname, int port) {
		this.type = type;
		this.name = name;
		this.selector = selector;
		this.hostname = hostname;
		this.port = port;
	}

	/**
	 * Constructs an item assuming the server's port is 70.
	 *
	 * @param type     Item type character.
	 * @param name     Label associated with the item.
	 * @param selector Selector pointing to the address in the server.
	 * @param hostname Hostname of the associated server.
	 */
	public Item(char type, String name, String selector, String hostname) {
		this(type, name, selector, hostname, 70);
	}

	/**
	 * Constructs an item assuming the server's port is 70 and the hostname is
	 * to be ignored.
	 *
	 * @param type     Item type character.
	 * @param name     Label associated with the item.
	 * @param selector Selector pointing to the address in the server.
	 */
	public Item(char type, String name, String selector) {
		this(type, name, selector, null);
	}

	/**
	 * Constructs an item assuming the server's port is 70 and the hostname and
	 * selector are to be ignored.
	 *
	 * @param type Item type character.
	 * @param name Label associated with the item.
	 */
	public Item(char type, String name) {
		this(type, name, null);
	}

	/**
	 * Gets the {@link ImageIcon} related to type of the item.
	 *
	 * @return Icon related to the type of item.
	 */
	public ImageIcon getImageIcon() {
		return ItemType.getImageIcon(type);
	}

	/**
	 * Checks if the item type accepts being clicked to navigate to it.
	 *
	 * @return {@code true} if the item type is something that the user can
	 *         navigate to, {@code false} otherwise.
	 */
	public boolean isClickable() {
		return (type != ItemType.INFO) && (type != ItemType.ERROR);
	}

	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSelector() {
		return selector;
	}

	public void setSelector(String selector) {
		this.selector = selector;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		return this.toString().equals(o.toString());
	}

	@Override
	public int hashCode() {
		return toString().hashCode();
	}

	/**
	 * Gets the string representation of how this item would be in its proper
	 * tab-delimited form.
	 *
	 * @return String representation of the item as a server would reply with.
	 */
	@Override
	public String toString() {
		return type + name + '\t' + selector + '\t' + hostname + '\t' + port;
	}
}
