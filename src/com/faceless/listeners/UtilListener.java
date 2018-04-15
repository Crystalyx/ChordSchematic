package com.faceless.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;

import com.faceless.core.Main;

public class UtilListener implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String name = ((JComponent) e.getSource()).getName();
		switch (name)
		{
		case "managleft":
		case "managright":
			for (int i = 0; i < Main.chord.length; i++)
			{
				if (Main.chord[i] != "X")
				{
					int ch = Integer.parseInt(Main.chord[i]);
					if (name == "managright")
						ch += 1;
					if (name == "managleft")
						ch -= 1;
					ch = Math.floorMod(ch, 12);
					Main.chord[i] = "" + ch;
				}
			}
			break;
		case "clear":
			for (int i = 0; i < Main.chord.length; i++)
			{
				Main.chord[i] = "0";
			}
			break;
		}
		Main.canvas.repaint();
		Main.chordPanel.repaint();
	}

}
