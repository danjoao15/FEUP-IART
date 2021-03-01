package iart;

import iart.application.GUIMain;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import se.sics.jasper.SICStus;
import se.sics.jasper.SPException;

public class BookWorm
{
    public static final String strProgramName = "B O O K W O R M";

    public static void main(String argv[])
    {
	try
	{
	    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	}
	catch (final ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex)
	{
	    JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), strProgramName, JOptionPane.ERROR_MESSAGE);
	}

	EventQueue.invokeLater(() ->
	{
	    try
	    {
		SICStus sicstusInstance = new SICStus(null);
		sicstusInstance.load("././proj1_server/gramatical.pl");
	    }
	    catch (SPException ex)
	    {
		showCritical(null, ex);
	    }

	    new GUIMain().setVisible(true);
	});
    }
    
    private static void showCritical(final JFrame paramFrame, final Exception paramThrowable)
    {
	JOptionPane.showMessageDialog(paramFrame, paramThrowable, "BookWorm : ERROR", JOptionPane.ERROR_MESSAGE);
	System.exit(0);
    }
    
    public static void showError(final JFrame paramFrame, final Exception paramThrowable)
    {
	JOptionPane.showMessageDialog(paramFrame, paramThrowable, "BookWorm : ERROR", JOptionPane.ERROR_MESSAGE);
    }

    public static void showWarning(final Component paramParent, final String paramMessage)
    {
	JOptionPane.showMessageDialog(paramParent, paramMessage, strProgramName, JOptionPane.WARNING_MESSAGE);
    }
}