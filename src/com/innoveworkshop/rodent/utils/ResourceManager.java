package com.innoveworkshop.rodent.utils;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages the resources included with the application and their loading in an efficient fashion.
 *
 * @author Nathan Campos {@literal <nathan@innoveworkshop.com>}
 */
public final class ResourceManager {
	private static ResourceManager INSTANCE;

	/**
	 * Constructor for the resource manager singleton object.
	 *
	 * @throws IOException When we are unable to read a resource from archive.
	 */
	private ResourceManager() throws IOException {
	}

	/**
	 * Gets the global resource manager instance.
	 *
	 * @return Resource manager instance.
	 */
	public static ResourceManager getInstance() {
		// Create instance if we haven't before.
		try {
			if (INSTANCE == null)
				INSTANCE = new ResourceManager();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return INSTANCE;
	}

	/**
	 * Gets a resource {@link ImageIcon}.
	 *
	 * @param resourcePath Path to the image resource.
	 *
	 * @return Resource as a stream.
	 *
	 * @throws FileNotFoundException if the resourcePath doesn't point to a valid resource file.
	 */
	public static ImageIcon getResourceIcon(String resourcePath) throws FileNotFoundException {
		return new ImageIcon(getResourceURL(resourcePath));
	}

	/**
	 * Gets a list of resources from a path. Equivalent to "ls" for class resources.
	 *
	 * @param resourcePath Path to a resource directory.
	 *
	 * @return List of resources found inside a path.
	 *
	 * @throws FileNotFoundException if the resourcePath doesn't point to a valid resource path.
	 */
	public static List<String> getResourceList(String resourcePath) throws FileNotFoundException {
		InputStream in = getResourceAsStream(resourcePath);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		ArrayList<String> resources = new ArrayList<String>();

		try {
			String resource;
			while ((resource = br.readLine()) != null) {
				resources.add(resource);
			}
		} catch (IOException e) {
			System.out.println("Failed to get resource list.");
		}

		return resources;
	}

	/**
	 * Gets a resource as a stream.
	 *
	 * @param resourcePath Path to the resource.
	 *
	 * @return Resource as a stream.
	 *
	 * @throws FileNotFoundException if the resourcePath doesn't point to a valid resource file.
	 */
	public static InputStream getResourceAsStream(String resourcePath) throws FileNotFoundException {
		InputStream in = ResourceManager.class.getClassLoader().getResourceAsStream(resourcePath);
		if (in == null) {
			throw new FileNotFoundException("Could not open resource file \"" + resourcePath +
					"\" as a stream");
		}

		return in;
	}

	/**
	 * Gets a resource URL.
	 *
	 * @param resourcePath Path to the resource.
	 *
	 * @return Resource URL to open and read later.
	 *
	 * @throws FileNotFoundException if the resourcePath doesn't point to a valid resource file.
	 */
	public static URL getResourceURL(String resourcePath) throws FileNotFoundException {
		URL url = ResourceManager.class.getClassLoader().getResource(resourcePath);
		if (url == null) {
			throw new FileNotFoundException("Could not open resource file \"" + resourcePath +
					"\" as a stream");
		}

		return url;
	}
}
