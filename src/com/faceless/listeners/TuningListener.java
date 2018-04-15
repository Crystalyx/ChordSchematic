package com.faceless.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.faceless.core.Main;

public class TuningListener implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent e)
	{
		JButton b = (JButton) e.getSource();
		String name = b.getName();
		char sf = name.charAt(0);
		char n = name.charAt(1);
		int num = Integer.parseInt(n + "");
		if (num < 6)
		{
			switch (sf)
			{
			case 's':
				Main.tune[num] = Main.tune[num].add(1);
				break;
			case 'f':
				Main.tune[num] = Main.tune[num].add(-1);
				break;
			default:
				break;
			}
			Main.labels[num].setText(Main.tune[num].toString());
			if (Main.chord[num] != "X")
			{
				int ch = Integer.parseInt(Main.chord[num]);
				if (sf == 's')
					ch += 1;
				if (sf == 'f')
					ch -= 1;
				ch = Math.floorMod(ch, 12);
				Main.chord[num] = "" + ch;
			}
		}
		if (num == 6)
		{
			for (int i = 0; i < 6; i++)
			{
				switch (sf)
				{
				case 's':
					Main.tune[i] = Main.tune[i].add(1);
					break;
				case 'f':
					Main.tune[i] = Main.tune[i].add(-1);
					break;
				default:
					break;
				}
				Main.labels[i].setText(Main.tune[i].toString());
				if (Main.chord[i] != "X")
				{
					int ch = Integer.parseInt(Main.chord[i]);
					if (sf == 's')
						ch += 1;
					if (sf == 'f')
						ch -= 1;
					ch = Math.floorMod(ch, 12);
					Main.chord[i] = "" + ch;
				}
			}
		}
		Main.canvas.repaint();
		Main.buttonPanel.repaint();
	}

}
