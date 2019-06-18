package isi.died.app.ejemplo.gui;

import java.awt.Color;
import java.awt.Font;

public class ServiceLocator {

	private static ServiceLocator _INSTANCIA = null;
	private PanelProyecto panelProyecto;
	public static final Font FUENTE_TITULO = new Font("Calibri",Font.BOLD,18);
	public static final Color COLOR_TITULO = new Color(5,85,244);
	
	private ServiceLocator() {
		this.panelProyecto = new PanelProyecto();
	}
	
	public static ServiceLocator getInstance() {
		if(_INSTANCIA == null ) _INSTANCIA = new ServiceLocator();
		return _INSTANCIA;
	}

	public PanelProyecto getPanelProyecto() {
		return panelProyecto;
	}
	
	
}
