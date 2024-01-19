package view;

import java.awt.event.WindowAdapter;

import com.sun.glass.events.*;

public class WindowDestroyer extends WindowAdapter {
	@SuppressWarnings("restriction")
	public void windowClosing(WindowEvent e) {
		System.exit(0); }
}
