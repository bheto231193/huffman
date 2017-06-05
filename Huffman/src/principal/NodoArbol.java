package principal;

public class NodoArbol {
	int dato;
	char letra;
	NodoArbol der;
	NodoArbol izq;

    public NodoArbol(char c,int d) {
    	dato=d;
    	letra=c;
    	der=null;
    	izq=null;
    }
}