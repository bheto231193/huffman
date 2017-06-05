package principal;

public class Nodo {
	//Camino
	public String dato;
	public char letra;
	public Nodo sig;
    public Nodo(char c, String d) {
    	sig = null;
    	letra=c;
    	dato = d;
    }
}