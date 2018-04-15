package com.faceless.core;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.faceless.chords.Note;
import com.faceless.listeners.ChordListener;
import com.faceless.listeners.TuningListener;
import com.faceless.panel.ChordCanvas;
import com.faceless.panel.ChordPanel;

public class Main
{
	public static JFrame frame;
	public static JPanel buttonPanel;
	public static JPanel chordPanel;
	public static Canvas canvas;
	public static TuningListener tuner = new TuningListener();
	public static ChordListener chorder = new ChordListener();

	public static Note[] tune = new Note[] { Note.E, Note.B, Note.G, Note.D, Note.A, Note.E };
	public static JLabel[] labels = new JLabel[6];
	public static String[] chord = new String[] { "0", "0", "0", "0", "0", "0" };

	public static void main(String[] args)
	{
		createFrame();

		frame.setVisible(true);
	}

	public static void createFrame()
	{
		frame = new JFrame();
		frame.setSize(800, 600);
		JMenuBar menu = new JMenuBar();
		menu.setSize(800, 30);
		JMenu mifile = new JMenu("File");
		JMenuItem filemenu = new JMenuItem("New");
		mifile.add(filemenu);
		menu.add(mifile);
		frame.add(menu);
		chordPanel = new ChordPanel();
		chordPanel.setLocation(300, 30);
		chordPanel.setBounds(150, 30, 633, 531);
		chordPanel.setBackground(Color.WHITE);
		chordPanel.setBorder(BorderFactory.createTitledBorder("Chord"));
		frame.add(chordPanel);
		buttonPanel = new JPanel();
		buttonPanel.setBounds(0, 30, 150, 531);
		buttonPanel.setBackground(Color.WHITE);
		buttonPanel.setBorder(BorderFactory.createTitledBorder("Tuning"));
		frame.add(buttonPanel);
		JPanel butpanel = new JPanel();
		butpanel.setLocation(100, 200);
		butpanel.setLayout(new GridLayout(7, 1, 0, 20));
		for (int i = 0; i < tune.length; i++)
		{
			JButton flat = new JButton("♭");
			flat.setName("f" + i);
			flat.addActionListener(tuner);
			JLabel note = new JLabel(tune[i].toString());
			note.setHorizontalAlignment(SwingConstants.CENTER);
			labels[i] = note;
			JButton sharp = new JButton("#");
			sharp.setName("s" + i);
			sharp.addActionListener(tuner);
			butpanel.add(flat, BorderLayout.WEST);
			butpanel.add(note, BorderLayout.WEST);
			butpanel.add(sharp, BorderLayout.WEST);
		}
		
		JButton flat = new JButton("♭");
		flat.setName("f" + 6);
		flat.addActionListener(tuner);
		butpanel.add(flat, BorderLayout.WEST);
		JLabel note = new JLabel("HH");
		note.setVisible(false);
		note.setHorizontalAlignment(SwingConstants.CENTER);
		butpanel.add(note, BorderLayout.WEST);
		JButton sharp = new JButton("#");
		sharp.setName("s" + 6);
		sharp.addActionListener(tuner);
		butpanel.add(sharp, BorderLayout.WEST);

		buttonPanel.add(butpanel);

		canvas = new ChordCanvas();
		canvas.addMouseListener(chorder);
		canvas.setSize(600, 300);
		canvas.setBackground(Color.white);
		chordPanel.add(canvas);

		JPanel pan = new JPanel();
		pan.setBounds(0, 0, 800, 600);
		frame.add(pan);
	}
}
