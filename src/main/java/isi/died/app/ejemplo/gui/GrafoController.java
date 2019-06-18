package isi.died.app.ejemplo.gui;

import java.awt.Color;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.swing.SwingUtilities;

import isi.died.app.ejemplo.logica.ProyectoLogica;
import isi.died.app.ejemplo.logica.ProyectoLogicaDefault;
import isi.died.app.ejemplo.logica.TareaLogica;
import isi.died.app.ejemplo.logica.TareaLogicaDefault;
import isi.died.app.ejemplo.modelo.Tarea;

public class GrafoController {

	private GrafoPanel panel;
	private TareaLogica logicaTareas;
	private ProyectoLogica logicaProyecto;
		
	public GrafoController(GrafoPanel listener) {
		this.panel = listener;
		this.logicaTareas = new TareaLogicaDefault();
		this.logicaProyecto = new ProyectoLogicaDefault(); 
	}
	
	public void inicalizarVertices() {
		Runnable r = () -> {
			List<Tarea> lista = this.logicaTareas.buscar();
			int x = 0;
			for(Tarea p : lista){
				x +=25; 
				VerticeView v = new VerticeView(x,100,Color.BLUE);
				v.setId(p.getId());
				v.setNombre(p.getDescripcion());
				panel.agregar(v);
			}
			try {
				SwingUtilities.invokeAndWait(() -> {
				});
			} catch (InvocationTargetException | InterruptedException e) {
				e.printStackTrace();
			}
		};
		
		Thread hilo = new Thread(r);
		
		hilo.start();	
	}
}
