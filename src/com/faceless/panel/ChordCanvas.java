package com.faceless.panel;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.faceless.chords.Chords;
import com.faceless.chords.Note;
import com.faceless.core.Main;

@SuppressWarnings("serial")
public class ChordCanvas extends Canvas
{
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		int d = 45;
		int s = 5;
		g.setFont(new Font("Segoe", Font.PLAIN, 18));
		g.setColor(Color.lightGray);
		for (int i = 0; i < 6; i++)
		{
			g.drawLine(10, 20 + i * d, 30 + d * 12, 20 + i * d);
			if (Main.chord[i] == "X")
				g.setColor(Color.red);
			else
				if (Main.chord[i] == "0")
					g.setColor(Color.black);
				else
					g.setColor(Color.blue);
			g.drawString(Main.chord[i], 0, 25 + i * d);
			g.setColor(Color.black);
			if (Main.chord[i] != "X" && Main.chord[i] != "0")
			{
				g.fillOval(-2 - s + Integer.parseInt(Main.chord[i]) * d, 20 + d * i - s, s * 2, s * 2);
			}
			g.setColor(Color.lightGray);

			Note[] notes = Main.tune;
			for (int j = 0; j <= 12; j++)
			{
				g.drawString(notes[i].add(j).toString(), d * j, 25 + i * d);
			}
		}
		for (int i = 0; i < 13; i++)
		{
			g.drawLine(20 + d * i, 10, 20 + d * i, 30 + 5 * d);
		}
	}

	@Override
	public void repaint()
	{
		super.repaint();
	}
}
