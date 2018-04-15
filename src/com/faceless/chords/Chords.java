package com.faceless.chords;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Chords
{
	public HashMap<String, String> chords = new HashMap<String, String>();

	public String[] getChord(String name, int variant)
	{
		if (chords.containsKey(name))
		{
			Entry[] ents = chords.entrySet().stream().filter(e -> e.getKey() == compact(name, variant + "")).toArray(Entry[]::new);
			String rawchord = chords.get(name);
			if (ents.length > 0)
				rawchord = (String) ents[0].getValue();
			ArrayList<String> alstr = unpact(rawchord);
			alstr.remove(0);
			return alstr.toArray(new String[0]);
		}
		return new String[] { "0", "0", "0", "0", "0", "0" };
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
