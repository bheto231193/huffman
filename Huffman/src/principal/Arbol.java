package principal;


public class Arbol {
	public NodoArbol raiz;
	public Arbol sig;
	public String re;
	public Lista camino;

    public Arbol(char c, int v) {
    	raiz = new NodoArbol(c, v);
    	sig = null;
    	re="";
    	camino= new Lista();
    }

	public String eliminarUltimo(String re){
		return re.substring(0, re.length()-1);
	}

 	public void establecerCaminos(String c, NodoArbol r){
 		re+=c;
    	if (r!=null){
    		if(r.letra!='\0'){ 
    			camino.insertaFinal(r.letra, re);
    		}
    		establecerCaminos("0", r.izq);
			if(r.izq!=null){
				re = eliminarUltimo(re);
			}
    		establecerCaminos("1", r.der);
    		if(r.der!=null){ 
	    		re = eliminarUltimo(re);
			}
    	} else {
    		re = eliminarUltimo(re);
    	}
    }

    public Lista getCaminos(){
    	return camino;
    }


    public String toCodigoHuffman(String frase, Lista caminos){
    	String cadena = "";
    	Nodo nodo;
    	for(int i=0; i<frase.length(); i++){ 
    		nodo = caminos.retornaPosicion(caminos.buscaElemento(frase.charAt(i)));
			cadena+=nodo.dato+"";
    	}
    	return cadena;
    }


    public void preorden(NodoArbol r){
    	if (r!=null){
    		System.out.print("("+r.letra+", "+r.dato+"),");
    		preorden(r.izq);
    		preorden(r.der);
    	}
    }
}