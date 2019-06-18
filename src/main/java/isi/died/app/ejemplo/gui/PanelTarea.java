package isi.died.app.ejemplo.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import isi.died.app.ejemplo.gui.util.GenericTableColumn;
import isi.died.app.ejemplo.gui.util.GenericTableModel;
import isi.died.app.ejemplo.modelo.Proyecto;
import isi.died.app.ejemplo.modelo.Tarea;

public class PanelTarea extends JPanel {

	private JLabel lblNombreTarea;
	private JLabel lblDuracionTarea;
	private JLabel lblProyecto;
	private JLabel lblTituloPanel;
	private JTextField nombreTarea;
	private JTextField duracionTarea;
	private JComboBox<Proyecto> comboProyecto;
	private JButton btnGuardar;
	private JButton btnNuevo;
	private JButton btnCancelar;
	private JButton btnEditar;
	private JButton btnBuscar;
	private JButton btnBorrar;
	private JTable tablaTareas;
	GenericTableModel<Tarea> gtm ;
	
	private Integer idTareaSeleccionado;
	
	
	private PanelTareaController controller;

	public PanelTarea() {
		super();
		controller = new PanelTareaController(this);
		this.armar();
		this.configurarEventos();
		
	}

	public Dimension getPreferredSize() {
        return new Dimension(450,250);
    }

    public void armar() {
    	this.setLayout(new GridBagLayout());
    	
    	int fila = 0;
    	int col = 0;
    	
    	GridBagConstraints c = new GridBagConstraints();    	

    	this.lblTituloPanel = new JLabel("Gestion de Tareas");
    	this.lblTituloPanel.setFont(ServiceLocator.FUENTE_TITULO);
    	this.lblTituloPanel.setForeground(ServiceLocator.COLOR_TITULO);
    	c.gridx=col++;
    	c.gridy=fila;
    	c.gridwidth=7;
    	c.anchor=GridBagConstraints.CENTER;
    	c.insets = new Insets(10, 5, 5, 10);
    	this.add(lblTituloPanel,c);
    	// reinicio variables de celda
    	col =0;
    	fila++;

    	this.lblNombreTarea = new JLabel("Nombre: ");
    	c.gridx=col++;
    	c.gridy=fila;
    	c.gridwidth=1;
    	c.anchor=GridBagConstraints.FIRST_LINE_START;
    	c.insets = new Insets(10, 5, 5, 10);
    	this.add(lblNombreTarea,c);
    	
    	this.nombreTarea = new JTextField(30);
    	this.nombreTarea.setEnabled(false);
    	c.gridx=col++;
    	c.fill=GridBagConstraints.HORIZONTAL;
    	c.weightx=0.5;
    	this.add(nombreTarea,c);
    	
    	this.lblDuracionTarea= new JLabel("Duracion: ");
    	c.gridx=col++;
    	c.gridy=fila;
    	c.gridwidth=1;
    	c.anchor=GridBagConstraints.FIRST_LINE_START;
    	c.insets = new Insets(10, 5, 5, 10);
    	this.add(lblDuracionTarea,c);
    	
    	this.duracionTarea = new JTextField(3);
    	this.duracionTarea.setEnabled(false);
    	c.gridx=col++;
    	c.fill=GridBagConstraints.HORIZONTAL;
    	c.weightx=0.5;
    	this.add(duracionTarea,c);
    	
    	this.lblProyecto= new JLabel("Proyecto: ");
    	c.gridx=col++;
    	c.gridy=fila;
    	c.gridwidth=1;
    	c.anchor=GridBagConstraints.FIRST_LINE_START;
    	c.insets = new Insets(10, 5, 5, 10);
    	this.add(lblProyecto,c);
    	
    	this.comboProyecto = new JComboBox<>();
    	this.controller.cargarComboProyectos(this.comboProyecto);
    	this.comboProyecto.setEnabled(false);
    	c.gridx=col++;
    	c.fill=GridBagConstraints.HORIZONTAL;
    	c.weightx=0.5;
    	this.add(comboProyecto,c);

    	this.btnGuardar = new JButton("Guardar");
    	this.btnGuardar.setEnabled(false);
    	c.gridx=col++;
    	c.weightx=0.0;
    	c.fill=GridBagConstraints.NONE;
    	this.add(btnGuardar,c);
    	
    	this.btnCancelar = new JButton("Cancelar");
    	this.btnCancelar.setEnabled(false);
    	c.gridx=col++;
    	c.weightx=0.2;
    	c.anchor = GridBagConstraints.LINE_START;
    	c.fill=GridBagConstraints.NONE;
    	this.add(btnCancelar,c);

    	
    	// reinicio variables de celda
    	c.gridwidth=col;
    	col =0;
    	fila++;
    	
    	c.gridx=col++;
    	c.gridy=fila;
    	c.fill=GridBagConstraints.BOTH;
    	c.weighty=1.0;
    	c.weightx=1.0;
    	
    	this.gtm = crearModeloTabla();
    	this.tablaTareas = new JTable(this.gtm);
    	this.tablaTareas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    	JScrollPane scrollPane= new  JScrollPane(this.tablaTareas);
    	this.add(scrollPane,c);
    	
    	col =0;
    	fila++;
    	JPanel panelBotones = new JPanel();

    	this.btnNuevo = new JButton("Nuevo");
    	this.btnEditar = new JButton("Editar");
    	this.btnBuscar = new JButton("Buscar");
    	this.btnBorrar = new JButton("Eliminar");
    	this.btnEditar.setEnabled(false);
    	this.btnBorrar.setEnabled(false);
    	panelBotones.add(btnBuscar);
    	panelBotones.add(btnNuevo);
    	panelBotones.add(btnEditar);
    	panelBotones.add(btnBorrar);
    	c.gridx=col++;
    	c.fill=GridBagConstraints.NONE;
    	c.gridx=col++;
    	c.gridy=fila;
    	c.gridwidth=7;
    	c.fill=GridBagConstraints.BOTH;
    	c.weighty=0.0;
    	c.weightx=1.0;
    	this.add(panelBotones,c);
    	
    }
    
    private void configurarEventos() {
    	this.btnGuardar.addActionListener( e -> {
    		
    		Proyecto prySeleccionado = (Proyecto) comboProyecto.getSelectedItem();
    		if(idTareaSeleccionado<=0) {
    			controller.crearTarea(nombreTarea.getText(),Integer.valueOf(duracionTarea.getText()),prySeleccionado);
    		}
    		else {
    			controller.actualizarTarea(idTareaSeleccionado,nombreTarea.getText(),Integer.valueOf(duracionTarea.getText()),prySeleccionado);
    		}
    		btnGuardar.setEnabled(false);
    		btnBuscar.setEnabled(true);
    		btnCancelar.setEnabled(false);
    		btnEditar.setEnabled(false);
    		nombreTarea.setEnabled(false);
    		comboProyecto.setEnabled(false);
    		duracionTarea.setEnabled(false);
    		btnNuevo.setEnabled(true);
    		nombreTarea.setText("");
    		duracionTarea.setText("0");
    	});
    	
    	this.btnNuevo.addActionListener( e -> {
    		btnBuscar.setEnabled(false);
    		btnGuardar.setEnabled(true);
    		btnCancelar.setEnabled(true);
    		btnEditar.setEnabled(false);
    		nombreTarea.setEnabled(true);
    		comboProyecto.setEnabled(true);
    		duracionTarea.setEnabled(true);
    		btnNuevo.setEnabled(false);
    		nombreTarea.setText("");
    		duracionTarea.setText("0");
    		idTareaSeleccionado=-1;
    	});
    	
    	this.btnCancelar.addActionListener( e -> {
    		btnGuardar.setEnabled(false);
    		btnBuscar.setEnabled(true);
    		btnCancelar.setEnabled(false);
    		btnEditar.setEnabled(false);
    		btnNuevo.setEnabled(true);
    		nombreTarea.setEnabled(false);
    		comboProyecto.setEnabled(false);
    		duracionTarea.setEnabled(false);
    		nombreTarea.setText("");
    		duracionTarea.setText("0");
    		idTareaSeleccionado=-1;
    	});
    	
    	this.btnBuscar.addActionListener( e -> {
    		System.out.println("BUSCAR....");
    	});
    	
    	btnEditar.addActionListener( e -> {
    		btnBuscar.setEnabled(false);
    		btnNuevo.setEnabled(false);
    		btnGuardar.setEnabled(true);
    		btnCancelar.setEnabled(true);
    		btnEditar.setEnabled(false);
    		nombreTarea.setEnabled(true);
    	});
    	
    	this.btnBorrar.addActionListener( e -> {
    		int resultado = JOptionPane.showConfirmDialog(null, "Borar Tarea", "Desea borrar el Tarea "+nombreTarea.getText()+ "?", JOptionPane.YES_NO_OPTION);
    		if(resultado ==0) {
    			controller.borrarTarea(idTareaSeleccionado);
	    		btnBorrar.setEnabled(false);
    		}
    	});
    
    	this.tablaTareas.getSelectionModel().addListSelectionListener(lse -> {
    		if(gtm.getDatos()!=null && !gtm.getDatos().isEmpty() && lse.getFirstIndex()< gtm.getDatos().size()) {
	    		Tarea aux = gtm.datos.get(lse.getFirstIndex());
	    		idTareaSeleccionado = aux.getId();
	    		nombreTarea.setText(aux.getDescripcion());
	    		duracionTarea.setText(aux.getDuracion().toString());
	    		comboProyecto.setSelectedItem(aux.getProyecto());
	    		btnEditar.setEnabled(true);
	    		btnBorrar.setEnabled(true);
    		}
        });
    }
    
    
    private GenericTableModel<Tarea> crearModeloTabla(){
    	this.gtm = new GenericTableModel<Tarea>();
    	List<GenericTableColumn> lista = new ArrayList<GenericTableColumn>();
    	lista.add(new GenericTableColumn("Id" , "getId"));
    	lista.add(new GenericTableColumn("Nombre" , "getDescripcion"));
    	lista.add(new GenericTableColumn("Duracion" , "getDuracion"));
    	lista.add(new GenericTableColumn("Proyecto" , "getProyecto"));
    	gtm.setColumnas(lista);
    	return gtm;
    }
    
    
    /**
     * Metodo invocado por el controller para que actualice los datos de la JTable
     * @param listaPry
     */
    public void actualizarDatosTabla(List<Tarea> listaPry) {
    	this.gtm.setDatos(listaPry);
    	this.gtm.fireTableDataChanged();
    }
    
}
