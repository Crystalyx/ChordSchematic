package com.faceless.chords;

public enum Note
{
	A, Asharp, B, C, Csharp, D, Dsharp, E, F, Fsharp, G, Gsharp;

	@Override
	public String toString()
	{
		return super.name().replaceAll("sharp", "#");
	}

	public Note add(int n)
	{
		return values()[Math.floorMod(ordinal() + n, 12)];
	}
}
