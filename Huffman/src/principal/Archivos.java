package principal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class Archivos {

	public String leerArchivo(String dir){
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		String cadena = "";
		try {
			archivo = new File (dir);
			br  = new BufferedReader (new InputStreamReader (new FileInputStream (archivo), "utf-8"));   
			fr = new FileReader (archivo);
			String linea;
			while((linea=br.readLine())!=null)
				cadena = cadena+linea;
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			try{                    
				if( null != fr ){   
					fr.close();     
					br.close();
				}                  
			}catch (Exception e2){ 
				e2.printStackTrace();
			}
		}
		//System.out.println("Abierto antes replace: "+cadena);
		cadena = cadena.replaceAll("EOL", "\n");
		//System.out.println("abierto despues repalceal: "+cadena);
		return cadena;
	}
	
	public void crearArchivos(String mensaje,String direccion){
		mensaje = mensaje.replaceAll("\n","EOL");
		//System.out.println("Contenido Crear"+mensaje);
		  Writer write = null;
	        try {
				File archivo = new File (direccion);
	            write = new BufferedWriter(new OutputStreamWriter(
	                    new FileOutputStream(archivo),"UTF8"));
	            write.write(mensaje);
	             
	        }
	        catch(Exception e){
	         e.printStackTrace();
	        }
	        finally{
				try{                    
					if( null != write ){   
						write.close();     
					}                  
				}catch (Exception e2){ 
					e2.printStackTrace();
				}
	        }
	}

}
