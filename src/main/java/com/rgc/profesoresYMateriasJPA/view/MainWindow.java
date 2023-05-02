package com.rgc.profesoresYMateriasJPA.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.rgc.profesoresYMateriasJPA.controller.AsignaturaController;
import com.rgc.profesoresYMateriasJPA.controller.AsignaturasPorDocenteController;
import com.rgc.profesoresYMateriasJPA.controller.DocenteController;
import com.rgc.profesoresYMateriasJPA.model.Asignatura;
import com.rgc.profesoresYMateriasJPA.model.Asignaturaspordocente;
import com.rgc.profesoresYMateriasJPA.model.Docente;


import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.GridBagConstraints;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	private JTextField jtfProfesor;
	private JComboBox<Docente> jcbProfesores;
	private JList jlistAsignaturasIzq;
	private JList jlistAsignaturasDer;
	private DefaultListModel<Asignatura> listModelIzq = null;
	private DefaultListModel<Asignatura> listModelDer = null;
	private List<Asignatura> asignaturas = AsignaturaController.findAll();
	private List<Asignaturaspordocente> asignaturasPorDocente = null;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 723, 521);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0};
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0};
//		gbl_contentPane.columnWidths = new int[]{0, 0};
//		gbl_contentPane.rowHeights = new int[]{0, 0};
//		gbl_contentPane.columnWeights = new double[]{0.0, Double.MIN_VALUE};
//		gbl_contentPane.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblNewLabel = new JLabel("Docentes y asignaturas");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 28));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.insets = new Insets(50, 0, 10, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		jtfProfesor = new JTextField();
		jtfProfesor.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_jtfProfesor = new GridBagConstraints();
		gbc_jtfProfesor.insets = new Insets(10, 0, 10, 5);
		gbc_jtfProfesor.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfProfesor.gridx = 0;
		gbc_jtfProfesor.gridy = 1;
		contentPane.add(jtfProfesor, gbc_jtfProfesor);
		jtfProfesor.setColumns(10);
		
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarDocentesEnJCombo();
			}
		});
		btnFiltrar.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_btnFiltrar = new GridBagConstraints();
		gbc_btnFiltrar.insets = new Insets(0, 0, 5, 0);
		gbc_btnFiltrar.gridx = 1;
		gbc_btnFiltrar.gridy = 1;
		contentPane.add(btnFiltrar, gbc_btnFiltrar);
		
		jcbProfesores = new JComboBox();
		jcbProfesores.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_jcbProfesores = new GridBagConstraints();
		gbc_jcbProfesores.insets = new Insets(0, 0, 10, 5);
		gbc_jcbProfesores.fill = GridBagConstraints.HORIZONTAL;
		gbc_jcbProfesores.gridx = 0;
		gbc_jcbProfesores.gridy = 2;
		contentPane.add(jcbProfesores, gbc_jcbProfesores);
		
		JButton btnCargarMaterias = new JButton("Cargar materias");
		btnCargarMaterias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarAsignaturasDerecha();
			}
		});
		btnCargarMaterias.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_btnCargarMaterias = new GridBagConstraints();
		gbc_btnCargarMaterias.insets = new Insets(0, 0, 5, 0);
		gbc_btnCargarMaterias.gridx = 1;
		gbc_btnCargarMaterias.gridy = 2;
		contentPane.add(btnCargarMaterias, gbc_btnCargarMaterias);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridwidth = 2;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 3;
		contentPane.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		panel.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("Asignaturas no seleccionadas");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.weightx = 1.0;
		gbc_lblNewLabel_1.insets = new Insets(5, 0, 10, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		panel_1.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Asignaturas seleccionadas");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_lblNewLabel_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_1_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1_1.weightx = 1.0;
		gbc_lblNewLabel_1_1.gridx = 2;
		gbc_lblNewLabel_1_1.gridy = 0;
		panel_1.add(lblNewLabel_1_1, gbc_lblNewLabel_1_1);	

		jlistAsignaturasIzq = new JList(this.getDefaultListModelIzq());
		this.jlistAsignaturasIzq.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		JScrollPane scrollPane = new JScrollPane(jlistAsignaturasIzq);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		panel_1.add(scrollPane, gbc_scrollPane);
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 0, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 1;
		panel_1.add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
//		gbl_panel_2.columnWidths = new int[]{0};
//		gbl_panel_2.rowHeights = new int[]{0};
//		gbl_panel_2.columnWeights = new double[]{Double.MIN_VALUE};
//		gbl_panel_2.rowWeights = new double[]{Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JButton btnTodosIzq = new JButton("<<");
		btnTodosIzq.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnTodosIzq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pasarTodoAIzquierda();
			}
		});
		GridBagConstraints gbc_btnTodosIzq = new GridBagConstraints();
		gbc_btnTodosIzq.insets = new Insets(0, 0, 5, 0);
		gbc_btnTodosIzq.gridx = 0;
		gbc_btnTodosIzq.gridy = 0;
		panel_2.add(btnTodosIzq, gbc_btnTodosIzq);

		JButton btnIzq = new JButton("<");
		btnIzq.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnIzq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pasarUnoAIzquierda();
			}
		});
		GridBagConstraints gbc_btnIzq = new GridBagConstraints();
		gbc_btnIzq.insets = new Insets(0, 0, 5, 0);
		gbc_btnIzq.gridx = 0;
		gbc_btnIzq.gridy = 1;
		panel_2.add(btnIzq, gbc_btnIzq);

		JButton btnDer = new JButton(">");
		btnDer.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnDer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pasarUnoADerecha();
			}
		});
		GridBagConstraints gbc_btnDer = new GridBagConstraints();
		gbc_btnDer.insets = new Insets(0, 0, 5, 0);
		gbc_btnDer.gridx = 0;
		gbc_btnDer.gridy = 2;
		panel_2.add(btnDer, gbc_btnDer);

		JButton btnTodosDer = new JButton(">>");
		btnTodosDer.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnTodosDer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pasarTodoADerecha();
			}
		});
		GridBagConstraints gbc_btnTodosDer = new GridBagConstraints();
		gbc_btnTodosDer.gridx = 0;
		gbc_btnTodosDer.gridy = 3;
		panel_2.add(btnTodosDer, gbc_btnTodosDer);

		jlistAsignaturasDer = new JList(this.getDefaultListModelDer());
		this.jlistAsignaturasDer.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		JScrollPane scrollPane_1 = new JScrollPane(jlistAsignaturasDer);
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 2;
		gbc_scrollPane_1.gridy = 1;
		panel_1.add(scrollPane_1, gbc_scrollPane_1);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardar();
			}
		});
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_btnGuardar = new GridBagConstraints();
		gbc_btnGuardar.insets = new Insets(5, 0, 5, 0);
		gbc_btnGuardar.gridwidth = 2;
		gbc_btnGuardar.gridx = 0;
		gbc_btnGuardar.gridy = 4;
		contentPane.add(btnGuardar, gbc_btnGuardar);
	}
	
	/**
	 * 
	 */
	private void cargarDocentesEnJCombo() {			
		this.jcbProfesores.removeAllItems();
		for (Docente d : DocenteController.findByLikeDescripcion(this.jtfProfesor.getText())) {
			this.jcbProfesores.addItem(d);
		}
	}
	
	
	/**
	 *
	 * 
	 */
	private DefaultListModel getDefaultListModelIzq() {
		this.listModelIzq = new DefaultListModel<Asignatura>();
		return this.listModelIzq;
	}

	
	/**
	 * 
	 * @return
	 */
	private DefaultListModel getDefaultListModelDer() {
		this.listModelDer = new DefaultListModel<Asignatura>();
		return this.listModelDer;
	}
	
	/**
	 * 
	 */
	private void cargarAsignaturasDerecha() {
		
		Docente d = (Docente) jcbProfesores.getSelectedItem();
		
		listModelDer.removeAllElements();		
		for (Asignaturaspordocente apd : AsignaturasPorDocenteController.findByAsignaturasPorDocente(d.getId()) {
			listModelDer.addAll(asignaturas);
		}
	}
	
	
	
	/**
	 * 
	 */
	private void pasarTodoAIzquierda() {
		listModelDer.removeAllElements();
		listModelIzq.removeAllElements();
		listModelIzq.addAll(asignaturas);
	}
	
	/**
	 * 
	 */
	private void pasarTodoADerecha() {
		listModelDer.removeAllElements();
		listModelIzq.removeAllElements();
		listModelDer.addAll(asignaturas);
	}
	
	/**
	 * 
	 */
	private void pasarUnoADerecha() {
		listModelDer.addAll(jlistAsignaturasIzq.getSelectedValuesList());
		for (int i = this.jlistAsignaturasIzq.getSelectedIndices().length - 1; i >= 0; i--) {
			this.listModelIzq.removeElementAt(this.jlistAsignaturasIzq.getSelectedIndices()[i]);
		}

	}
	
	
	/**
	 * 
	 */
	private void pasarUnoAIzquierda() {
		listModelIzq.addAll(jlistAsignaturasDer.getSelectedValuesList());
		for (int i = this.jlistAsignaturasDer.getSelectedIndices().length - 1; i >= 0; i--) {
			this.listModelDer.removeElementAt(this.jlistAsignaturasDer.getSelectedIndices()[i]);
		}
	}
	
	
	/**
	 * 
	 */
	private void guardar() {
		List<Docente> l = new ArrayList<Docente>();
		for (int i = 0; i < listModelDer.size(); i++) {
			l.add(listModelDer.getElementAt(i));
		}

		for (Docente docente : l) {
			Asignaturaspordocente apd = AsignaturasPorDocenteController.findByAsignaturasPorDocente(docente.getId());
			
			if (apd != null) {
//				System.out.println("UPDATE");
				apd.setAsignatura(null);;
				AsignaturasPorDocenteController.update(apd);
			} else {
//				System.out.println("INSERT");
				AsignaturasPorDocenteController.insert(apd);
			}
		}
	}
	
}
