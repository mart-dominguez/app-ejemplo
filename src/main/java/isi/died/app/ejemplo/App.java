package isi.died.app.ejemplo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import isi.died.app.ejemplo.gui.PanelProyecto;
import isi.died.app.ejemplo.gui.ServiceLocator;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	SwingUtilities.invokeLater(new Runnable() {
    	    public void run() {
    	        createAndShowGUI();
    	    }
    	});
    }
    
    private static void createAndShowGUI() {
    	  JFrame f = new JFrame("Aplicacion Ejemplo DIED");
          f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//          JPanel panel = new PanelProyecto();          
          f.setSize(800, 450);
          f.setMinimumSize(new Dimension(500, 500));
          JMenuBar mb;
          JMenu menu1;
          JMenuItem mi1,mi2,mi3;
          
          mb=new JMenuBar();
          f.setJMenuBar(mb);
          menu1=new JMenu("Opciones");
          mb.add(menu1);
          mi1=new JMenuItem("Tareas");
          mi1.addActionListener(e -> { 
        	  f.getContentPane().removeAll();
        	  f.getContentPane().add( ServiceLocator.getInstance().getPanelTarea());
        	  f.pack();
          });
          menu1.add(mi1);
          mi2=new JMenuItem("Proyectos");
          mi2.addActionListener(e -> { 
        	  f.getContentPane().removeAll();
        	  f.getContentPane().add( ServiceLocator.getInstance().getPanelProyecto());
        	  f.pack();
          });
          menu1.add(mi2);
          mi3=new JMenuItem("Grafo");
          mi3.addActionListener(e -> { 
        	  f.getContentPane().removeAll();
        	  f.getContentPane().add( ServiceLocator.getInstance().getPanelGrafo());
        	  f.pack();
          });
          menu1.add(mi3);     

          f.pack();
          f.setVisible(true);
    }
    
}
