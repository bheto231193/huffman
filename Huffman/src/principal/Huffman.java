package principal;


public class Huffman {
	String cadena;
	ListaArbol caracteres;
	Arbol arbol;
	Lista caminos;
	String codigo;
	public Huffman(){

	}
	public Huffman(String arg){
		this.Codificar(arg);
	}

	public String Codificar(String arg) {    
		cadena = arg;
		caracteres = new ListaArbol();
		caracteres.toListaArbol(cadena);
		caracteres.generarArbol();
		arbol = caracteres.arbolEnLaPosicion(1);
		caracteres.eliminaPosicion(1);
		caracteres = null;
		arbol.establecerCaminos("", arbol.raiz);
		caminos = arbol.getCaminos();
		//System.out.println(caminos.recorrer());
		codigo = arbol.toCodigoHuffman(cadena, caminos);
		//System.out.println("Codigo Huffman: "+codigo);
		return codigo;
	}

	public String Decodificar(String arg1){
		//System.out.println("Para Decodificar ="+arg1);
		String frase = "";
		String[] partes = arg1.split("////");
		String temp = partes[1];
		//String aux = "";
		//System.out.println("Parte 1: "+partes[0]);
		//System.out.println("Parte 2: "+partes[1]);
		//System.out.println((int)partes[1].charAt(0));
		//System.out.println((int)partes[1].charAt(1));
		//System.out.println(toBinarioFromDecimal((int)partes[1].charAt(0)));
		//System.out.println(toBinarioFromDecimal((int)partes[1].charAt(1)));
		//System.out.println("Parte 3: "+partes[2]);
		//for (int i=0; i<temp.length();i++) {
		//System.out.println("Partes "+i+" :"+partes[1].charAt(i));
		//}
//		for(int i = 0;i<temp.length();i++){
			//aux = aux+toBinarioFromDecimal(((int)partes[1].charAt(i)));
		//	}
		for(int i=0;i<temp.length();i++){
			frase+=toBinarioFromDecimal((int)temp.charAt(i));
		}
		//System.out.println(frase);
		//System.out.println("Frase antes:   "+frase);
		frase = frase.substring(0,frase.length()-Integer.parseInt(partes[2]));
		//System.out.println("frase despues: "+frase);
		//System.out.println("parte 1: "+partes[0]);
		//System.out.println(partes[1]);
		//System.out.println(temp);
		String[] caracteres = partes[0].split("<");
		//for(int i=0;i<caracteres.length;i++){
		//	System.out.println("Caracter 1."+i+" :"+caracteres[i]);
		//}
		//System.out.println("parte 2: "+partes[1]);
		temp = frase;
		frase = "";
		while(temp.length()>0){
			for(int i = 0;i<caracteres.length;i++){
				if(temp.startsWith(caracteres[i].substring(1))){
					frase+=caracteres[i].charAt(0);
					temp = temp.substring(caracteres[i].length()-1);
					break;
				}
			}
		}
		return frase;
	}

	public String toChar(String s){
		int tam = 0;
		//System.out.println("Antes:   "+s);
		if((s.length()%8)!=0){
			do{
				s+='0';
				tam++;
			}while(s.length()%8!=0);
		}
		//System.out.println("Despues: "+s);
		//System.out.println("Original: "+s);
		String temp = s;
		//System.out.println(temp.length());
		s = "";
		while(temp.length()>0){
			s = s+toCharFromBinario(temp.substring(0,8));
			//System.out.println("s= "+s);
			temp = temp.substring(8);
		}
		//System.out.println("Original.:"+Integer.toBinaryString(s.charAt(1)));
		//System.out.println("Leido : "+s);
		//System.out.println("return: "+s+"////"+tam);
		return s+"////"+tam;
	}	

	public char toCharFromBinario(String cad){
		//System.out.println("Cadena binario: "+cad);
		int val = 0;
		int cont = 0;
		for(int i=7;i>=0;i--){
			val = (int) (val+(Integer.parseInt(cad.charAt(cont)+"")*Math.pow(2, i)));
			cont++;
		}
		return (char)val;
	}

	public String toBinarioFromDecimal(int decimal){
		String bin = "";
		while ( decimal >0 ) {
			bin = decimal % 2 + bin;
			decimal /= 2;
		}

			while(bin.length()<8){
				bin = "0"+bin;
			}
		return bin; 
	}
}