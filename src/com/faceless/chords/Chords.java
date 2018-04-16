package com.faceless.chords;

import java.util.ArrayList;
import java.util.HashMap;

import com.faceless.core.Main;

public class Chords
{
	public static HashMap<String, String> chords = new HashMap<String, String>();
	public static HashMap<String, String> names = new HashMap<String, String>();

	public static final String[] EmptyChord = new String[] { "0", "0", "0", "0", "0", "0" };

	public static String getName(String[] chord)
	{
		String key = compact((Object[]) processToNotes(chord));
		if (names.containsKey(key))
		{
			return names.get(key);
		}
		return "@";
	}

	public static String[] getChord(String name, int variant)
	{
		String key = compact(name, variant + "");
		if (chords.containsKey(key))
		{
			String rawchord = chords.get(key);
			ArrayList<String> alstr = unpact(rawchord);
			Main.chordPanel.repaint();
			Main.canvas.repaint();
			return processToFrets(readNotes(alstr.toArray(new String[6])));
		}
		return EmptyChord;
	}

	public static void saveChord(String name, int variant, String[] chord)
	{
		String key = compact(name, variant + "");
		if (!chords.containsKey(key))
		{
			chords.put(key, compact((Object[]) processToNotes(chord)));
			names.put(compact((Object[]) processToNotes(chord)), key);
		}
	}

	public static ArrayList<String> unpact(String s)
	{
		ArrayList<String> l = new ArrayList<String>();

		while (s.indexOf(d) > -1)
		{
			String ds = s.substring(0, s.indexOf(d));
			l.add(ds);
			s = s.substring(s.indexOf(d) + 1);
		}
		l.add(s);

		return l;
	}

	public static String d = "|";

	public static String compact(Object... ss)
	{
		String s = "";
		for (int i = 0; i < ss.length; i++)
		{
			s += ss[i].toString();
			if (i != ss.length - 1)
				s += d;
		}

		return s;
	}

	public static Note[] readNotes(String[] nots)
	{
		Note[] notes = new Note[nots.length];
		for (int i = 0; i < notes.length; i++)
		{
			notes[i] = Note.valueOf(nots[i].replace("#", "sharp"));
		}
		return notes;
	}

	public static Note[] processToNotes(String[] frets)
	{
		Note[] notes = new Note[frets.length];
		for (int i = 0; i < notes.length; i++)
		{
			if (frets[i] != "X")
				notes[i] = Main.tune[i].add(Integer.parseInt(frets[i]));
			else
				notes[i] = Note.Nil;
		}
		return notes;
	}

	public static String[] processToFrets(Note[] notes)
	{
		String[] frets = new String[notes.length];
		for (int i = 0; i < notes.length; i++)
		{
			if (notes[i] != Note.Nil)
			{
				int t = Math.floorMod(notes[i].ordinal() - Main.tune[i].ordinal(), 12);
				if (t == 0)
					frets[i] = "0";
				else
					frets[i] = "" + t;
			}
			else
				frets[i] = "X";
		}
		return frets;
	}

	public static void setupNames()
	{
		chords.entrySet().forEach(e -> names.put(e.getValue(), e.getKey()));
	}
}
