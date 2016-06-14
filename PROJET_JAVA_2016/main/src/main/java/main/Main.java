package main;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;

import controller.Controller;
import model.Model;
import view.View;

/**
 * The Class Main.
 *
 * @author Jean-Aymeric Diet
 */
public abstract class Main {

	/**
	 * The main method.
	 *
	 * @param args
	 *          the arguments
	 */
	public static void main(final String[] args) {

		final Model model = new Model();
		final View view = new View(model);
		final Controller controller = new Controller(view, model);
		view.setController(controller);
		controller.control();
		
		File son = new File("C:/ProjetJava/Music/Chrono_Trigger_Frog_Theme.wav");
		AudioClip clip = null;
		try
		{
		clip = Applet.newAudioClip(son.toURL());
		}
		catch (MalformedURLException e)
		{
		System.out.println(e.getMessage());
		}
		clip.play();	
	}
}