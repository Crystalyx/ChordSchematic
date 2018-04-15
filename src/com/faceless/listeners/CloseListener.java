package com.faceless.listeners;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import com.faceless.core.Main;

public class CloseListener implements WindowListener
{

	@Override
	public void windowActivated(WindowEvent arg0)
	{
		
	}

	@Override
	public void windowClosed(WindowEvent arg0)
	{
		
	}

	@Override
	public void windowClosing(WindowEvent arg0)
	{
		Main.save();
	}

	@Override
	public void windowDeactivated(WindowEvent arg0)
	{
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0)
	{
		
	}

	@Override
	public void windowIconified(WindowEvent arg0)
	{
		
	}

	@Override
	public void windowOpened(WindowEvent arg0)
	{
		
	}

}
