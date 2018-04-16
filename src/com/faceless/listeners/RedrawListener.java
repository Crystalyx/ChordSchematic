package com.faceless.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.faceless.chords.Chords;
import com.faceless.core.Main;

public class RedrawListener implements ActionListener, MouseListener
{

	@Override
	public void mouseClicked(MouseEvent arg0)
	{

	}

	@Override
	public void mouseEntered(MouseEvent arg0)
	{

	}

	@Override
	public void mouseExited(MouseEvent arg0)
	{

	}

	@Override
	public void mousePressed(MouseEvent arg0)
	{
		redo();
	}

	@Override
	public void mouseReleased(MouseEvent arg0)
	{

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		redo();
	}

	public static void redo()
	{
		String namevar = Chords.getName(Main.chord);
		if (namevar != "@")
		{
			String name = Chords.unpact(namevar).get(0);
			String var = Chords.unpact(namevar).get(1);
			Main.chordlabel.setText(name);
			Main.varlabel.setText(var);
		}
		else
		{
			Main.chordlabel.setText("");
			Main.varlabel.setText("");
		}
		Main.canvas.repaint();
		Main.chordlabel.repaint();
		Main.chordPanel.repaint();
	}

}
