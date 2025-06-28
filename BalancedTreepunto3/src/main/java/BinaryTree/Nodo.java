//ISAAC DAVID PATERNINA NOBLE 0222220022 

package BinaryTree;

public class Nodo {
    public Integer datos,altura;
    public Nodo izquierdo;
    public Nodo derecho;

    public Nodo(Integer dato) {
        this.datos = dato;
        this.altura = 1;
    }

    public long getDato() {
        return this.datos;
    }
}
