package de.uni_hamburg.informatik.swt.se2.kino.startup;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.SwingUtilities;

import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Datum;
import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.FSK;
import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Uhrzeit;
import de.uni_hamburg.informatik.swt.se2.kino.materialien.Film;
import de.uni_hamburg.informatik.swt.se2.kino.materialien.Kino;
import de.uni_hamburg.informatik.swt.se2.kino.materialien.Kinosaal;
import de.uni_hamburg.informatik.swt.se2.kino.materialien.Vorstellung;
import de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.kasse.KassenWerkzeug;

/**
 * Startet die Anwendung.
 * 
 * @author SE2-Team
 * @version SoSe 2016
 */
public class StartupKinoticketverkauf_Blatt07
{
	
    /**
     * Die Main-Methode.
     * 
     * @param args die Aufrufparameter.
     */
    public static void main(String[] args)
    {
        final Kino kino = erzeugeKinoMitBeispieldaten();
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new KassenWerkzeug(kino);
            }
        });
    }

    /**
     * Erzeugt ein Kino mit einigen Vorstellungen.
     */
    private static Kino erzeugeKinoMitBeispieldaten()
    {
    	Film[] filme = new Film[256];
    	
        final Kinosaal[] saele = { new Kinosaal("Saal 1", 20, 25),
                new Kinosaal("Saal 2", 16, 20), new Kinosaal("Saal 3", 10, 16) };

        // Filme: Top-5 Deutschland laut kino.de in der Kalenderwoche 20, 2011.

                filme[0] = new Film("Pirates of the Caribbean - Fremde Gezeiten", 136,
                        FSK.FSK12, true);
                filme[1] = new Film("Fast & Furious Five", 130, FSK.FSK12, true);
                filme[2] = new Film("Rio", 96, FSK.FSK0, false);
                filme[3] = new Film("Wasser für die Elefanten", 120, FSK.FSK12, false);
                filme[4] = new Film("Thor", 115, FSK.FSK12, false);       

        
        int index = 5;
        
     // TODO filme
    	Scanner sc;
    	File movies = new File("movies/movies.txt");

    	
		try {
			sc = new Scanner(movies, "ISO-8859-1");
			String filmtitel;
	    	System.out.println(sc.hasNext());
	        while (sc.hasNextLine()) {
				filmtitel = sc.nextLine();
				int runtime = (int) Math.floor(Math.random() * (90)) + 60;
				filme[index] = new Film(filmtitel, runtime, FSK.FSK18, runtime > 120);
				++index;
			}
	        sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    	
        
        System.out.println(filme[8]);

        Uhrzeit nachmittag = new Uhrzeit(17, 30);
        Uhrzeit abend = new Uhrzeit(20, 0);
        Uhrzeit spaet = new Uhrzeit(22, 30);
        Uhrzeit nacht = new Uhrzeit(1, 0);

        Datum d1 = Datum.heute();
        Datum d2 = d1.naechsterTag();
        Datum d3 = d2.naechsterTag();

        final Vorstellung[] vorstellungen = {
                // Heute
                new Vorstellung(saele[0], filme[2], nachmittag, abend, d1, 500),
                new Vorstellung(saele[0], filme[0], abend, spaet, d1, 700),
                new Vorstellung(saele[0], filme[0], spaet, nacht, d1, 700),

                new Vorstellung(saele[1], filme[3], nachmittag, abend, d1, 900),
                new Vorstellung(saele[1], filme[1], spaet, nacht, d1, 800),

                new Vorstellung(saele[2], filme[3], abend, spaet, d1, 1000),
                new Vorstellung(saele[2], filme[4], spaet, nacht, d1, 900),

                // Morgen
                new Vorstellung(saele[0], filme[0], abend, spaet, d2, 500),
                new Vorstellung(saele[0], filme[0], spaet, nacht, d2, 700),

                new Vorstellung(saele[1], filme[2], nachmittag, abend, d2, 900),
                new Vorstellung(saele[1], filme[4], abend, nacht, d2, 800),

                new Vorstellung(saele[2], filme[3], nachmittag, abend, d2, 1000),
                new Vorstellung(saele[2], filme[1], spaet, nacht, d2, 900),

                // Übermorgen
                new Vorstellung(saele[0], filme[1], abend, spaet, d3, 500),
                new Vorstellung(saele[0], filme[1], spaet, nacht, d3, 700),

                new Vorstellung(saele[1], filme[2], nachmittag, abend, d3, 900),
                new Vorstellung(saele[1], filme[0], abend, nacht, d3, 800),

                new Vorstellung(saele[2], filme[3], abend, spaet, d3, 1000),
                new Vorstellung(saele[2], filme[4], spaet, nacht, d3, 900) };
        
        
       

        return new Kino(saele, vorstellungen);
    }
}
