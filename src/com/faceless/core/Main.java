package com.faceless.core;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

import com.faceless.chords.Chords;
import com.faceless.chords.Note;
import com.faceless.listeners.ChordListener;
import com.faceless.listeners.CloseListener;
import com.faceless.listeners.FileListener;
import com.faceless.listeners.RedrawListener;
import com.faceless.listeners.TuningListener;
import com.faceless.listeners.UtilListener;
import com.faceless.panel.ChordCanvas;
import com.faceless.panel.ChordPanel;

import Utilities.Library;
import Utilities.Logger;

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
	public static UtilListener utiler = new UtilListener();
	public static RedrawListener redrawer = new RedrawListener();

	public static JTextField textload;
	public static JTextField textsave;
	public static JSpinner varload;
	public static JSpinner varsave;
	public static JRadioButton notes;
	public static JLabel chordlabel;
	public static JLabel varlabel;

	public static Note[] tune = new Note[] { Note.E, Note.B, Note.G, Note.D, Note.A, Note.E };
	public static JLabel[] labels = new JLabel[6];
	public static String[] chord = Chords.EmptyChord;

	public static Library chordlib = new Library("chords");

	public static void main(String[] args)
	{
		Logger.info(Note.A.toString() + "->" + Note.A.add(1).toString());
		load();
		createFrame();

		frame.setVisible(true);
	}

	private static void load()
	{
		chordlib.read();
		Chords.chords = chordlib.knowlege;
		Chords.setupNames();
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
		JPanel buttontext = new JPanel();
		buttontext.setBackground(Color.white);
		JLabel buttontextlabel = new JLabel("Tuning");
		buttontextlabel.setFont(new Font("Segoe", Font.PLAIN, 24));
		buttontextlabel.setPreferredSize(new Dimension(120, 30));
		buttontext.add(buttontextlabel);
		buttonPanel.add(buttontext);

		JPanel butpanel = new JPanel();
		butpanel.setBackground(Color.white);
		butpanel.setLocation(100, 200);
		butpanel.setLayout(new GridLayout(7, 1, 0, 20));
		for (int i = 0; i < tune.length; i++)
		{
			JButton flat = new JButton("b");
			flat.setName("f" + i);
			flat.addActionListener(tuner);
			JLabel note = new JLabel(tune[i].toString());
			note.setHorizontalAlignment(SwingConstants.CENTER);
			labels[i] = note;
			JButton sharp = new JButton("#");
			sharp.setName("s" + i);
			sharp.addActionListener(tuner);
			butpanel.add(sharp, BorderLayout.WEST);
			butpanel.add(note, BorderLayout.WEST);
			butpanel.add(flat, BorderLayout.WEST);
		}

		JButton sharp = new JButton("#");
		sharp.setName("s" + 6);
		sharp.addActionListener(tuner);
		butpanel.add(sharp, BorderLayout.WEST);
		JLabel note = new JLabel("HH");
		note.setVisible(false);
		note.setHorizontalAlignment(SwingConstants.CENTER);
		butpanel.add(note, BorderLayout.WEST);
		JButton flat = new JButton("b");
		flat.setName("f" + 6);
		flat.addActionListener(tuner);
		butpanel.add(flat, BorderLayout.WEST);

		buttonPanel.add(butpanel);

		JPanel chordguess = new JPanel();
		chordlabel = new JLabel("");
		chordlabel.setOpaque(true);
		chordlabel.setBackground(Color.WHITE);
		chordlabel.setPreferredSize(new Dimension(120, 30));
		chordlabel.setFont(new Font("Segoe", Font.PLAIN, 24));
		chordguess.add(chordlabel);
		varlabel = new JLabel("");
		varlabel.setOpaque(true);
		varlabel.setVisible(false);
		varlabel.setBackground(Color.WHITE);
		varlabel.setPreferredSize(new Dimension(30, 30));
		varlabel.setFont(new Font("Segoe", Font.PLAIN, 24));
		chordguess.add(varlabel);
		JToggleButton removeVar = new JToggleButton(">");
		removeVar.setSelected(true);
		removeVar.setFont(new Font("Segoe", Font.PLAIN, 18));
		removeVar.setPreferredSize(new Dimension(50, 30));
		removeVar.setName("removevar");
		removeVar.addActionListener(utiler);
		chordguess.add(removeVar);
		chordPanel.add(chordguess);

		canvas = new ChordCanvas();
		canvas.addMouseListener(chorder);
		canvas.addMouseListener(redrawer);
		canvas.setSize(600, 300);
		canvas.setBackground(Color.white);
		chordPanel.add(canvas);

		JPanel managepan = new JPanel();

		notes = new JRadioButton("Write Notes");
		notes.addActionListener(redrawer);
		managepan.add(notes);

		JButton clear = new JButton("Clear");
		clear.setName("clear");
		clear.addActionListener(utiler);
		managepan.add(clear);

		JButton managleft = new JButton("-1");
		managleft.setName("managleft");
		managleft.addActionListener(utiler);
		managleft.addActionListener(redrawer);
		managepan.add(managleft);

		JButton managright = new JButton("+1");
		managright.setName("managright");
		managright.addActionListener(utiler);
		managright.addActionListener(redrawer);
		managepan.add(managright);
		chordPanel.add(managepan);

		JPanel filepan = new JPanel();

		JPanel savingpan = new JPanel();
		JPanel loadingpan = new JPanel();
		JButton chsave = new JButton("Save");
		chsave.setName("save");
		chsave.addActionListener(filer);
		chsave.addActionListener(redrawer);
		JButton chload = new JButton("Load");
		chload.setName("load");
		chload.addActionListener(filer);
		chload.addActionListener(redrawer);

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
		filepan.add(savingpan);
		filepan.add(loadingpan);

		JPanel savepan = new JPanel();
		JButton output = new JButton("Output");
		output.setName("output");
		output.addActionListener(filer);
		output.addActionListener(redrawer);
		savepan.add(output);
		filepan.add(savepan);
		chordPanel.add(filepan);

		JPanel pan = new JPanel();
		pan.setBounds(0, 0, 800, 600);
		frame.add(pan);
	}
}
