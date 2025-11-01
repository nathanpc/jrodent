package com.innoveworkshop.rodent.exceptions;

/**
 * Exception that's thrown whenever a Gopher line is malformed and could not
 * have been parsed.
 *
 * @author Nathan Campos {@literal nathan@innoveworkshop.com}
 */
public class MalformedGopherLineException extends Exception {
	private final String line;

	/**
	 * Constructs the malformed line exception.
	 *
	 * @param line Line returned from server.
	 */
	public MalformedGopherLineException(String line) {
		super("Malformed line returned from server \"" + line + "\"");
		this.line = line;
	}

	public String getLine() {
		return line;
	}
}
