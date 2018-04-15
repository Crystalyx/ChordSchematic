package com.faceless.core;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.faceless.chords.Chords;
import com.faceless.chords.Note;
import com.faceless.listeners.ChordListener;
import com.faceless.listeners.CloseListener;
import com.faceless.listeners.FileListener;
import com.faceless.listeners.TuningListener;
import com.faceless.panel.ChordCanvas;
import com.faceless.panel.ChordPanel;

import Utilities.Library;

public class Main
{
	public static JFrame frame;
	public static JPanel buttonPanel;
	public static JPanel chordPanel;
	public static Canvas canvas;
	public static TuningListener tuner = new TuningListener();
	public static ChordListener chorder = new ChordListener();
	public static FileListener filer = new FileListener();
	public static CloseListener closer = new CloseListener();

	public static JTextField textload;
	public static JTextField textsave;
	public static JSpinner varload;
	public static JSpinner varsave;

	public static Note[] tune = new Note[] { Note.E, Note.B, Note.G, Note.D, Note.A, Note.E };
	public static JLabel[] labels = new JLabel[6];
	public static String[] chord = Chords.EmptyChord;

	public static Library chordlib = new Library("chords");

	public static void main(String[] args)
	{
		load();
		createFrame();

		frame.setVisible(true);
	}

	private static void load()
	{
		chordlib.read();
		Chords.chords = chordlib.knowlege;
	}

	public static void save()
	{
		chordlib.knowlege = Chords.chords;
		chordlib.output();
	}

	public static void createFrame()
	{
		frame = new JFrame();
		frame.addWindowListener(closer);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

		JPanel savingpan = new JPanel();
		JPanel loadingpan = new JPanel();
		JButton chsave = new JButton("Save");
		chsave.setName("save");
		chsave.addActionListener(filer);
		JButton chload = new JButton("Load");
		chload.setName("load");
		chload.addActionListener(filer);

		textsave = new JTextField();
		textsave.setPreferredSize(new Dimension(80, 30));
		textload = new JTextField();
		textload.setPreferredSize(new Dimension(80, 30));

		varload = new JSpinner();
		varload.setPreferredSize(new Dimension(30, 30));
		varsave = new JSpinner();
		varsave.setPreferredSize(new Dimension(30, 30));

		savingpan.add(textsave);
		savingpan.add(varsave);
		savingpan.add(chsave);
		loadingpan.add(textload);
		loadingpan.add(varload);
		loadingpan.add(chload);
		chordPanel.add(savingpan);
		chordPanel.add(loadingpan);
		JPanel pan = new JPanel();
		pan.setBounds(0, 0, 800, 600);
		frame.add(pan);
	}
}
