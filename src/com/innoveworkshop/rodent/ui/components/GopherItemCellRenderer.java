package com.innoveworkshop.rodent.ui.components;

import com.innoveworkshop.rodent.models.Item;
import com.innoveworkshop.rodent.models.ItemType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.Map;

public class GopherItemCellRenderer extends JPanel implements ListCellRenderer<Item> {
	private JLabel lblType;
	private JLabel lblName;

	public GopherItemCellRenderer() {
		super();
		setupComponents();
	}

	private void setupComponents() {
		// Set some of the panel's properties and its layout manager.
		setOpaque(true);
		setLayout(new FlowLayout(FlowLayout.LEFT, 10, 1));
		setBorder(new EmptyBorder(1, 1, 1, 1));
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

		// Get font to be used in the browser.
		Font font = new Font("Monospaced", Font.PLAIN, 12);

		// Setup type label.
		lblType = new JLabel(ItemType.getImageIcon(' '));
		lblType.setPreferredSize(new Dimension(16, 16));
		add(lblType);

		// Setup name label.
		lblName = new JLabel("name");
		lblName.setFont(font);
		add(lblName);
	}

	public Component getListCellRendererComponent(JList list, Item item, int index,
	                                              boolean isSelected, boolean cellHasFocus) {
		// Show an underlined font for clickable items when hovering over them.
		Font font = lblName.getFont();
		Map attr = font.getAttributes();
		if (cellHasFocus && item.isClickable()) {
			attr.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		} else {
			attr.put(TextAttribute.UNDERLINE, null);
		}
		lblName.setFont(font.deriveFont(attr));

		// Set background and foreground colors.
		setBackground(isSelected ? list.getSelectionBackground() :
				list.getBackground());
		lblName.setForeground(isSelected ? list.getSelectionForeground() :
				list.getForeground());

		// Set label contents.
		lblType.setIcon(item.getImageIcon());
		lblName.setText(item.getName());

		return this;
	}
}
