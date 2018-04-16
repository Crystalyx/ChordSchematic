package com.faceless.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;

import com.faceless.chords.Chords;
import com.faceless.core.Main;

public class FileListener implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String name = ((JComponent) e.getSource()).getName();

		switch (name)
		{
		case "load":
			String chordl = Main.textload.getText();
			int varl = (int) Main.varload.getValue();
			String[] rawchord = Chords.getChord(chordl, varl);
			if (!rawchord.equals(Chords.EmptyChord))
			{
				Main.chord = rawchord;
			}
			break;
		case "save":
			String chords = Main.textsave.getText();
			int vars = (int) Main.varsave.getValue();
			Chords.saveChord(chords, vars, Main.chord);
			break;
		case "output":
			Main.save();
			break;
		}
		RedrawListener.redo();
	}

}
