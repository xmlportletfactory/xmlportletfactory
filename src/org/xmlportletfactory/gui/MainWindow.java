/**
 *     Copyright (C) 2009-2011  Jack A. Rider All rights reserved.
 * 
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 * 
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 */

package org.xmlportletfactory.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingWorker;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.Properties;

import javax.swing.JProgressBar;

import org.xmlportletfactory.XMLPortletFactory;
import org.xmlportletfactory.utils.MetodosComunes;

import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;

/**
 * @author Moises Belda
 *
 */
public class MainWindow extends JFrame {

	private JPanel contentPane;
	private JTextField txtPortletDir;
	private JTextField txtXmlDefinition;
	private XMLPortletFactory xmlPortletFactory;
	private Properties properties;
	private File fichero;
	private String sdk_version;
	private File directorio;
	private JLabel lblStatus;
	JProgressBar progressBar;
	private Task task;
	private JButton btnProcesar;
	private JButton btnCancelar;
	AbstractButton btn60 = new JCheckBox("6.0.x");
	AbstractButton btn61 = new JCheckBox("6.1.x");
	AbstractButton btn62 = new JCheckBox("6.2.x");
	
	
	/**
	 * Create the frame.
	 */
	public MainWindow(XMLPortletFactory xmlPortletFactory) {
		
		this.xmlPortletFactory = xmlPortletFactory;
		properties = xmlPortletFactory.getProperties();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 263);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

// 		portlet base dir
		JLabel lblPortletsBaseDir = new JLabel("Portlet's base dir");
		lblPortletsBaseDir.setBounds(10, 11, 90, 14);
		contentPane.add(lblPortletsBaseDir);
		
		txtPortletDir = new JTextField();
		txtPortletDir.setBounds(10, 36, 347, 20);
		contentPane.add(txtPortletDir);
		txtPortletDir.setColumns(10);
		
		JButton btnSelectPortletDir = new JButton("...");
		btnSelectPortletDir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CustomFileChooser fileChooser = new CustomFileChooser("",true,directorio);				
				if(fileChooser.showOpenDialog(MainWindow.this) == JFileChooser.APPROVE_OPTION){
					directorio = fileChooser.getSelectedFile();
					MainWindow.this.txtPortletDir.setText(directorio.getAbsolutePath());
					MainWindow.this.postFields();
				}
			}
		});
		btnSelectPortletDir.setBounds(359, 35, 65, 23);
		contentPane.add(btnSelectPortletDir);

//       xml selecction		
		JLabel lblXmlDefinition = new JLabel("Xml definition");
		lblXmlDefinition.setBounds(10, 77, 90, 14);
		contentPane.add(lblXmlDefinition);
		
		JButton btnSelectXml = new JButton("...");
		btnSelectXml.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CustomFileChooser fileChooser = new CustomFileChooser("xml",false,fichero);				
				if(fileChooser.showOpenDialog(MainWindow.this) == JFileChooser.APPROVE_OPTION){
					fichero = fileChooser.getSelectedFile();
					MainWindow.this.txtXmlDefinition.setText(fichero.getAbsolutePath());
					MainWindow.this.postFields();
				}
			}
		});
		btnSelectXml.setBounds(359, 99, 65, 23);
		contentPane.add(btnSelectXml);
		
		txtXmlDefinition = new JTextField();
		txtXmlDefinition.setColumns(10);
		txtXmlDefinition.setBounds(10, 100, 347, 20);
		contentPane.add(txtXmlDefinition);
		
//      generate for liferay version 6.0 6.1		
		JLabel lblLfVersion = new JLabel("Choose SDK ");
		lblLfVersion.setBounds(10, 130, 120, 14);
		contentPane.add(lblLfVersion);

		ButtonGroup buttonGroup = new ButtonGroup();
		btn60.setBounds(35, 150, 80, 20);
		contentPane.add(btn60);
		buttonGroup.add(btn60);

		btn61.setBounds(35, 170, 80, 20);
		contentPane.add(btn61);
		buttonGroup.add(btn61);
		
		btn62.setBounds(35, 170, 80, 20);
		contentPane.add(btn62);
		buttonGroup.add(btn62);
		
		btnCancelar = new JButton("Exit");
		btnCancelar.setBounds(220, 191, 90, 23);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainWindow.this.closeWindow();		
			}
		});
		contentPane.add(btnCancelar);
		
		btnProcesar = new JButton("Start");
		btnProcesar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					btnCancelar.setText("Cancel");
					MainWindow.this.procesar();					
				} catch (Exception e) {					
					
				}
			}
		});
		btnProcesar.setBounds(127, 191, 90, 23);
		contentPane.add(btnProcesar);
		
		lblStatus = new JLabel("");		
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setForeground(new Color(34, 139, 34));
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblStatus.setBounds(10, 144, 414, 20);
		contentPane.add(lblStatus);
		
		progressBar = new JProgressBar(0,100);
		progressBar.setIndeterminate(true);
		progressBar.setBounds(127, 144, 183, 20);
		progressBar.setVisible(false);
		contentPane.add(progressBar);
		initFields();
	}
	
	/**
	 *	 Function that fills all components with the properties values.
	 */
	private void initFields() {
		//Define file to default path at xml chooser window
		fichero = new File(properties.getProperty(XMLPortletFactory.LASTXML_KEY));
		//Define folder to default path at portlet dir chooser window
		directorio = new File(properties.getProperty(XMLPortletFactory.PORLETSDIR_KEY));
		//Define SDK version
		sdk_version = properties.getProperty(XMLPortletFactory.SDK_VERSION);
		if (sdk_version==null) {
			sdk_version="6.0.x";
		}
		//Fill textfields with properties values
		txtXmlDefinition.setText(properties.getProperty(XMLPortletFactory.LASTXML_KEY));
		txtPortletDir.setText(properties.getProperty(XMLPortletFactory.PORLETSDIR_KEY));
		if (sdk_version.startsWith("6.0")){
			btn60.setSelected(true);
		} else if (sdk_version.startsWith("6.1")){
			btn61.setSelected(true);
		} else {
			btn62.setSelected(true);
		}
	}
	
	/**
	 * Function that fills properties object with last values at text fields
	 */
	private void postFields(){
		properties.setProperty(XMLPortletFactory.PORLETSDIR_KEY, txtPortletDir.getText());
		properties.setProperty(XMLPortletFactory.LASTXML_KEY, txtXmlDefinition.getText());
		if (btn60.isSelected()) {
			properties.setProperty(XMLPortletFactory.SDK_VERSION, "6.0.x");		
		} else if (btn61.isSelected()) {
			properties.setProperty(XMLPortletFactory.SDK_VERSION, "6.1.x");		
		} else {
			properties.setProperty(XMLPortletFactory.SDK_VERSION, "6.2.x");	
		}
	}
	
	/**
	 * Check that the fields are ok
	 * @return
	 * @throws Exception
	 */
	private boolean checkFields () throws Exception {
		boolean check = true;
		//Verify Portlet's dir field
		File d = new File(txtPortletDir.getText());
		if(!d.exists()){
			check = false;
			setStatusLabel(true,"Invalid portlet dir");
			txtPortletDir.requestFocusInWindow();
		}	
		//Verify Xml file field
		File f = new File(txtXmlDefinition.getText());
		if(!f.exists()) {
			check = false;
			setStatusLabel(true,"Invalid xml file");
			txtXmlDefinition.requestFocusInWindow();
		}
		return check;
	}
	
	/**
	 * Function to control lblStatus color and message 
	 * @param error
	 * @param message
	 */
	private void setStatusLabel(boolean error,String message){
		//Error label foreground in red
		if(error) lblStatus.setForeground(new Color(255, 0, 0));
		//Succesfully label forefround in green 
		else lblStatus.setForeground(new Color(34, 139, 34));
		//Message
		if(message==null) message="";
		lblStatus.setText(message);		
	}
	
	private void procesar() throws Exception{
		if(checkFields()){
			//Update dirs properties
			postFields();
			//Disable Process button
			btnProcesar.setEnabled(false);
			//Start progressBar
			lblStatus.setVisible(false);
			progressBar.setVisible(true);
			// Call to the task for startConvertion process in background
			task = new Task();
			task.execute();
		}
	}
	
	/**
	 * Close window firing windowsClosing event.
	 */
	private void closeWindow() {
		getToolkit().getSystemEventQueue().postEvent(new WindowEvent(this,WindowEvent.WINDOW_CLOSING));
	}
	
	/**
	 * Task for execute startConversion in background
	 * @author mbelda
	 *
	 */
	class Task extends SwingWorker {
		
		Exception pendingException = null;
		
		@Override
		protected Object doInBackground() throws Exception {
			try{
				xmlPortletFactory.startConversion(new File(txtXmlDefinition.getText()));
			} catch(Exception ex) {
				pendingException = ex;
			}			
			return null;
		}
		
		 /*
         * Executed in event dispatching thread
         */
        @Override
        public void done() {        	
        	progressBar.setVisible(false);			
			lblStatus.setVisible(true);			
        	if(pendingException!=null){
        		MetodosComunes.getInstancia().ventanaError(pendingException);
        		setStatusLabel(true,pendingException.getMessage());
        	} else {
        		//Showing ending message
        		btnCancelar.setText("Exit");
        		setStatusLabel(false,"Process finished");	
        	}        	
			//Fill properties file
			postFields();
			//Enable process button
			btnProcesar.setEnabled(true);
        }
	}
}
