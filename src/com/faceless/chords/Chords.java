package com.faceless.chords;

import java.util.ArrayList;
import java.util.HashMap;

public class Chords
{
	public static HashMap<String, String> chords = new HashMap<String, String>();
	public static final String[] EmptyChord = new String[] { "0", "0", "0", "0", "0", "0" };

	public static String[] getChord(String name, int variant)
	{
		String key = compact(name, variant + "");
		if (chords.containsKey(compact(name, variant + "")))
		{
			String rawchord = chords.get(key);
			ArrayList<String> alstr = unpact(rawchord);
			return alstr.toArray(new String[0]);
		}
		return EmptyChord;
	}

	public static void saveChord(String name, int variant, String[] raw)
	{
		String key = compact(name, variant + "");
		if (!chords.containsKey(key))
		{
			chords.put(key, compact(raw));
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

	public static String compact(String... ss)
	{
		String s = "";
		for (int i = 0; i < ss.length; i++)
		{
			s += ss[i];
			if (i != ss.length - 1)
				s += d;
		}

		return s;
	}
}
