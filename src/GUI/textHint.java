package GUI;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.plaf.basic.BasicTextFieldUI;
import javax.swing.text.JTextComponent;


public class textHint extends BasicTextFieldUI implements FocusListener {
	
	private String hint;
	private Color hintColor;
	
	public textHint(String h, Color c) {
		this.hint = h;
		this.hintColor = c;
	}
	
	private void repaint() {
		if (getComponent() !=null) {
			getComponent().repaint();
		}
	}
	
	@Override
	
	protected void paintSafely(Graphics grph) {
		super.paintSafely(grph);
		JTextComponent comp = getComponent();
		if (comp.getText().length() == 0 && !comp.hasFocus()) {
			grph.setColor(hintColor);
			int pad = (comp.getHeight() - comp.getFont().getSize()) / 2;
			int inst = 3;
			grph.drawString(hint,  inst,  comp.getHeight() - pad - inst);
		}
	}
	
	@Override
	public void focusGained(FocusEvent fe) {
		repaint();
	}
	
	@Override
	public void focusLost (FocusEvent fe) {
		repaint();
	}
	
	@Override
	public void installListeners() {
		super.installListeners();
		getComponent().addFocusListener(this);
	}
	
	@Override 
	public void uninstallListeners() {
		super.uninstallListeners();
		getComponent().removeFocusListener(this);
	}
}

