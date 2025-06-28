//ISAAC DAVID PATERNINA NOBLE 0222220022
package co.edu.unicartagena.binarytree;

public class Node {
    public long datos;
    public Node izquierdo;
    public Node derecho;

    public Node(long dato) {
        this.datos = dato;
        this.izquierdo = null;
        this.derecho = null;
    }

    public long getDato() {
        return this.datos;
    }
}
