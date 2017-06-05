package principal;


public class Lista {
	public Nodo inicio;
    public Lista() {
    	inicio = null;
    }

    public String recorrer(){
    	Nodo aux= inicio;
    	String cad = "";
    	while (aux != null){
    		cad+=aux.letra+aux.dato+"<";
    		aux = aux.sig;
    	}
    	return cad.substring(0,cad.length()-1);
    }
    
    public int buscaElemento(char c){
    	Nodo aux = inicio;
    	int posicion = 0;
    	int contador = 1;
    	while (aux != null && posicion==0){
    		if (aux.letra==c){
    			posicion = contador;
    		} else {
    			aux = aux.sig;
    			contador++;
    		}
    	}
    	return posicion;
    }

    public Nodo retornaPosicion(int val){ //Retorna 0 cuando la lista está vacía, retorna 1 si se busca un elemento con una posición inexistente
    	Nodo aux = inicio;
    	Nodo elemento = null;
    	int contador = 1;
    	while (aux != null && elemento==null){
    		if (contador==val){
    			elemento = aux;
    		} else {
    			aux = aux.sig;
    			contador++;
    		}
    	}
		return elemento;
    }

   public void insertaFinal(char c, String d){
    	Nodo nuevo = new Nodo(c, d);
    	if (inicio==null){
    		inicio = nuevo;
    	} else {
    		Nodo aux=inicio;
    		while (aux.sig != null){
    			aux = aux.sig;
    		}

    		aux.sig = nuevo;
    	}
    }
}