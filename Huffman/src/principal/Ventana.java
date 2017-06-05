package principal;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Ventana{
	JFrame frame;
	TextArea texto = new TextArea("\n Tambien puede introducir el texto aqui");
	JButton button = new JButton("Selecccionar Un Archivo");
	JButton  transformar = new JButton("Transformar");
	JButton  abrir = new JButton("Abrir un Archivo Huffman.");
	JButton  guardar = new JButton("Guardar Codigo Huffman en un archivo.");
	TextArea transformado = new TextArea();
	Huffman huf;

	public void mostrar(){
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		this.frame = new JFrame("Compresion Huffman.");
		//Toolkit tk = Toolkit.getDefaultToolkit();
		//Dimension tamano = tk.getScreenSize();
		frame.setSize(650, 500);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		//frame.setBounds((int)(tamano.getWidth()/2)-200,(int) tamano.getHeight()/2-200, 650, 500);
		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String direccion = "";
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setAcceptAllFileFilterUsed(false);
				fileChooser.addChoosableFileFilter(new TxTFilter());
				fileChooser.addChoosableFileFilter(new DatFilter());
				int returnValue = fileChooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					direccion = selectedFile.getPath();
				}
				texto.setText(new Archivos().leerArchivo(direccion).replaceAll("EOL","\n"));
				SwingUtilities.updateComponentTreeUI(frame);
			}
		});
		
		transformar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				//System.out.println(texto.getText());
				if(texto.getText().length()<=0){
					JOptionPane.showMessageDialog(frame, "ÁÁNo hay textoo para Transformar!!");
				}else{
					huf = new Huffman(texto.getText());
					transformado.setText(huf.codigo);
					SwingUtilities.updateComponentTreeUI(frame);
				}
			}
		});
		
		guardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(transformado.getText().length()<=0){
					JOptionPane.showMessageDialog(frame, "ÁÁNo hay textoo para Guardar!!");
				}else{
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setAcceptAllFileFilterUsed(false);
					fileChooser.addChoosableFileFilter(new FileNameExtensionFilter(".huffman",".huffman"));
					int seleccion = fileChooser.showSaveDialog(null);
					try{
						if (seleccion == JFileChooser.APPROVE_OPTION){
							File JFC = fileChooser.getSelectedFile();
							String PATH = JFC.getAbsolutePath();
							//System.out.println(huf.caminos.recorrer()+"////"+huf.toChar(transformado.getText()));
							new Archivos().crearArchivos(huf.caminos.recorrer()+"////"+huf.toChar(transformado.getText()), PATH+".huffman");
							if(!(PATH.endsWith(".huff"))){
								File temp = new File(PATH+".huffman");
								JFC.renameTo(temp);
							}
							JOptionPane.showMessageDialog(null,"Guardado exitoso!", "Guardado exitoso!", JOptionPane.INFORMATION_MESSAGE);
						}
					}catch (Exception e1){
						JOptionPane.showMessageDialog(null,"Error al guardar el archivo!", "Error", JOptionPane.ERROR_MESSAGE);
					} 


				}
			}
		});
		abrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String direccion = "";
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setAcceptAllFileFilterUsed(false);
				//fileChooser.addChoosableFileFilter(new TxTFilter());
				//fileChooser.addChoosableFileFilter(new DatFilter());
				fileChooser.addChoosableFileFilter(new HuffFilter());
				int returnValue = fileChooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					direccion = selectedFile.getPath();
				}
				if(direccion.length()>1){
					final JFrame temp = new JFrame();
					temp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					temp.setLayout(new FlowLayout());
					Toolkit tk = Toolkit.getDefaultToolkit();
					Dimension tamano = tk.getScreenSize();
					temp.setSize(650, 500);
					temp.setLocationRelativeTo(null);
					temp.setBounds((int)(tamano.getWidth()/2)-325,(int) tamano.getHeight()/2-250, 650, 500);
					Button regresar = new Button("Regresar Menu Anterior");
					temp.add(regresar);
					TextArea codigo = new TextArea();
					//System.out.println("Leido: "+new Archivos().leerArchivo(direccion));
					codigo.setText(new Huffman().Decodificar(new Archivos().leerArchivo(direccion)));
					codigo.setEditable(false);
					temp.add(codigo);
					frame.setVisible(false);
					temp.pack();
					temp.setVisible(true);
					regresar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							temp.setVisible(false);
							transformado.setText("");
							texto.setText("");
							frame.setVisible(true);
						}
					});

				}else{
					JOptionPane.showMessageDialog(frame,"Error Al Abrir El Archivo");
				}
			}
		});
		button.setBounds(200,10 , 30, 30);
		transformado.setEditable(false);
		frame.add(button);
		frame.add(texto);
		frame.add(transformar);
		frame.add(transformado);
		frame.add(guardar);
		frame.add(abrir);
		//frame.pack();
		frame.setVisible(true);
	}
}


