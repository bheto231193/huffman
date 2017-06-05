package principal;

public class ListaArbol {
	public Arbol inicio;
	public int cont;

    public ListaArbol() {
    	inicio = null;
    	cont = 0;
    }

    public void eliminaPosicion(int posicion){
    	Arbol aux = this.inicio;
    	Arbol ant = null;
    	if(aux!=null){
    		int contador = 0;
    		while (aux!=null){
    			contador++;
    			if (contador==posicion){
    				if (ant==null){
    					this.inicio = aux.sig;
    					aux.sig = null;
    					aux=null;
    				} else {
    					ant.sig=aux.sig;
    					aux.sig=null;
    					aux=null;
    				}
    				cont--;
    			} else {
    				ant = aux;
    				aux = aux.sig;
    			}

    		}
    	}
    }

    public void recorrer(){
    	Arbol aux= inicio;
    	int contador = 1;
    	System.out.print("Raices: ");
    	while (aux != null){
    		System.out.print("Raiz "+contador+": ");
    		aux.preorden(aux.raiz);
    		aux = aux.sig;
    		contador++;
    	}
    	System.out.println("");
    }

    public void insertarOrdenada (Arbol nuevo){
    	if (inicio==null){
    		inicio=nuevo;
    	} else {
    		Arbol aux=inicio;
    		Arbol anterior= null;
    		while ((aux!=null)&&(nuevo.raiz.dato>=aux.raiz.dato)){
    			anterior=aux;
    			aux=aux.sig;
    		}
    		if (anterior==null){ 
    			nuevo.sig=inicio;
    			inicio=nuevo;
    		} else {
    			anterior.sig=nuevo;
    			nuevo.sig=aux;
    		}
    	}
    	cont++;
    }


	public void generarArbol(){
		while (this.cont>1){
			Arbol union = unir(arbolEnLaPosicion(1), arbolEnLaPosicion(2));
			insertarOrdenada(union);
			eliminaPosicion(1);
			if(this.cont!=1){
				eliminaPosicion(1);
			}
		}
	}

    public Arbol unir(Arbol uno, Arbol dos){
    	Arbol union = new Arbol('\0', uno.raiz.dato+dos.raiz.dato);
    	union.raiz.izq = uno.raiz;
    	union.raiz.der = dos.raiz;
    	//System.out.print("Union: ");
    	//union.preorden(union.raiz); //imprimimos la unión creada
    	//System.out.println("");
    	return union;
    }


    public int buscaElemento(char c){
    	Arbol aux = inicio;
    	int posicion = 0;
    	int contador = 1;
    	while (aux != null && posicion == 0){
    		if (aux.raiz.letra == c){
    			posicion = contador;
    			break;
    		} else {
    			aux = aux.sig;
    			contador++;
    		}
    	}
    	return posicion;
    }

    public void toListaArbol(String cadena){
    	for(int i=0; i<cadena.length(); i++){
			if(buscaElemento(cadena.charAt(i))==0){
				int repeticiones = 0;
				for (int j=0; j<cadena.length(); j++){
					if(cadena.charAt(j)==cadena.charAt(i)){
						repeticiones++;
					}
				}
				Arbol nuevo= new Arbol(cadena.charAt(i), repeticiones);
				insertarOrdenada(nuevo);
			}
    	}
    }

    public Arbol arbolEnLaPosicion(int v){ //Retorna 0 cuando la lista está vacía, retorna 1 si se busca un elemento con una posición inexistente
    	Arbol aux= inicio;
    	Arbol elemento=null; //lista vacía
    	int contador=1;

    	while (aux != null && elemento==null){ //mientras no se llegue al final de la lista y no se haya encontrado el elemento
    		if (contador==v){ //si encontramos la posición
    			elemento=aux;
    		} else { //si no encontramos la posición señalada, seguimos buscando
    			aux = aux.sig;
    			contador++;
    		}
    	}
		return elemento;
    }
}