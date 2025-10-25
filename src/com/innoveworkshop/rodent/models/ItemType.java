package com.innoveworkshop.rodent.models;

import com.innoveworkshop.rodent.utils.ResourceManager;

import javax.swing.*;
import java.io.FileNotFoundException;

/**
 * Gopher item types utility.
 *
 * @author Nathan Campos {@literal nathan@innoveworkshop.com}
 */
public final class ItemType {
	public static final char FILE = '0';
	public static final char DIRECTORY = '1';
	public static final char CSO = '2';
	public static final char ERROR = '3';
	public static final char BINHEX = '4';
	public static final char ARCHIVE = '5';
	public static final char UUENCODED = '6';
	public static final char INDEXSEARCH = '7';
	public static final char TELNET = '8';
	public static final char BINARY = '9';
	public static final char INFO = 'i';
	public static final char CALENDAR = 'c';
	public static final char GIF = 'g';
	public static final char HTML = 'h';
	public static final char IMAGE = 'I';
	public static final char MAIL = 'M';
	public static final char DOCUMENT = 'd';
	public static final char SOUND = 's';
	public static final char VIDEO = ';';

	public static ImageIcon getImageIcon(char type) {
		// Convert type character to icon string name.
		String name;
		switch (type) {
			case INFO:
				return null;
			case FILE:
				name = "document_text";
				break;
			case DIRECTORY:
				name = "folder";
				break;
			case CSO:
			case INDEXSEARCH:
				name = "view";
				break;
			case ERROR:
				name = "error";
				break;
			case BINHEX:
			case UUENCODED:
				name = "box_white";
				break;
			case ARCHIVE:
				name = "package";
				break;
			case TELNET:
				name = "laptop";
				break;
			case BINARY:
				name = "window_gear";
				break;
			case CALENDAR:
				name = "calendar";
				break;
			case GIF:
			case IMAGE:
				name = "photo_scenery";
				break;
			case HTML:
				name = "earth";
				break;
			case MAIL:
				name = "index";
				break;
			case DOCUMENT:
				name = "document_certificate";
				break;
			case SOUND:
				name = "loudspeaker";
				break;
			case VIDEO:
				name = "movie";
				break;
			default:
				name = "unknown";
		}

		// Get resource icon from the item icons resource folder.
		try {
			return ResourceManager.getResourceIcon("item_icons/" + name + ".png");
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: Failed to get item type icon from resource.");
			return null;
		}
	}
}
