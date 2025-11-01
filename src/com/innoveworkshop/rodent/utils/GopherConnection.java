package com.innoveworkshop.rodent.utils;

import com.innoveworkshop.rodent.exceptions.MalformedGopherLineException;
import com.innoveworkshop.rodent.models.Item;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URI;
import java.util.ArrayList;

/**
 * Establishes a connection to a Gopher server and fetches data from it.
 *
 * @author Nathan Campos {@literal nathan@innoveworkshop.com}
 */
public class GopherConnection {
	private URI uri;
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;

	/**
	 * Sets up a Gopher server connection using an {@link URI}.
	 *
	 * @param uri URI of the Gopher resource to access.
	 */
	public GopherConnection(URI uri) {
		this.uri = uri;
		this.socket = new Socket();
	}

	/**
	 * Fetches an item listing from the Gopher server.
	 *
	 * @return List of items retrieved from the server.
	 *
	 * @throws IOException if an error occurs with the connection.
	 */
	public ArrayList<Item> fetchItemListing() throws IOException {
		// Setup socket connection to server.
		socket.connect(resolveURL(uri));
		out = new PrintWriter(socket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		// Send out the selector for the server.
		out.print(getSelector() + "\r\n");
		out.flush();

		// Read in the response from the server.
		ArrayList<Item> items = new ArrayList<Item>();
		String line = in.readLine();
		while (line != null) {
			// Ignore if it's the last "." line.
			if (line.equals(".")) {
				line = in.readLine();
				continue;
			}

			// Parse the item returned from the server and read the next line.
			try {
				items.add(Item.fromLine(uri, line));
			} catch (MalformedGopherLineException e) {
				System.err.println(e.getMessage());
			}
			line = in.readLine();
		}

		// Close everything.
		in.close();
		out.close();
		socket.close();

		return items;
	}

	/**
	 * Gets the selector from the {@link URI}.
	 *
	 * @return Selector of the {@link URI}.
	 */
	public String getSelector() {
		return uri.getPath().substring(2);
	}

	/**
	 * Resolves an {@link URI} into an {@link InetSocketAddress} to quickly
	 * connect to a server.
	 *
	 * @param uri URI of the connection we want to attempt.
	 *
	 * @return Resolved socket address ready for connecting.
	 */
	public static InetSocketAddress resolveURL(URI uri) {
		return new InetSocketAddress(uri.getHost(), uri.getPort());
	}
}
