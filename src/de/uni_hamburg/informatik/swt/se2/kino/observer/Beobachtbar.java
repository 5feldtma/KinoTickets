package de.uni_hamburg.informatik.swt.se2.kino.observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Beobachtbar {
	
	private List<Beobachter> _alleBeobachter;
	
	protected Beobachtbar()
	{
		_alleBeobachter = new ArrayList<Beobachter>();
	}
	
	public void registriereBeobachter(Beobachter neuerBeobachter)
	{
		_alleBeobachter.add(neuerBeobachter);
	}
	
	protected void informiereUeberAenderungen()
	{
		for (Beobachter beobachter : _alleBeobachter) {
			beobachter.reagiereAufAenderungen();
		}
	}
}
