package com.faceless.panel;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.faceless.chords.Note;
import com.faceless.core.Main;

@SuppressWarnings("serial")
public class ChordCanvas extends Canvas
{

	public static int dx = 24;

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
			g.drawLine(10 + dx, 20 + i * d, 30 + dx + d * 11, 20 + i * d);
			if (Main.chord[i] == "X")
				g.setColor(Color.red);
			else
				if (Main.chord[i] == "0")
					g.setColor(Color.black);
				else
					g.setColor(Color.blue);
			g.drawString(Main.chord[i], +dx, 25 + i * d);
			g.setColor(Color.black);
			if (Main.chord[i] != "X" && Main.chord[i] != "0")
			{
				g.fillOval(6-s + dx + Integer.parseInt(Main.chord[i]) * d, 20 + d * i - s, s * 2, s * 2);
			}
			g.setColor(Color.lightGray);
			if (Main.notes.isSelected())
			{
				Note[] notes = Main.tune;
				for (int j = 0; j <= 11; j++)
				{
					g.drawString(notes[i].add(j).toString(), d * j, 25 + i * d);
				}
			}
		}
		for (int i = 0; i < 12; i++)
		{
			g.drawLine(20 + dx + d * i, 10, 20 + dx + d * i, 30 + 5 * d);
		}
	}

	@Override
	public void repaint()
	{
		super.repaint();
	}
}
