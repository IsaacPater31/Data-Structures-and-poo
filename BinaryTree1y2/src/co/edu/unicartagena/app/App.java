//ISAAC DAVID PATERNINA NOBLE 0222220022
package co.edu.unicartagena.app;
import co.edu.unicartagena.binarytree.*;
public class App {
	public static void main (String args[]){
    BinaryTree miArbol = new BinaryTree();

    Integer[] datos = {50, 17, 76, 9, 23, 54, 14, 19, 72, 12, 67}; 

    for(Integer dato : datos) {
        miArbol.adicionar(dato, miArbol.getRaiz(), 1);
    }

    System.out.println("Total de aristas: " + (miArbol.getNumeroNodos() - 1));
    System.out.println("Altura del arbol: " + miArbol.getAltura());
    System.out.println("Nodos totales: " + miArbol.getNumeroNodos());
    
       miArbol.imprimirCompleto();
       miArbol.imprimirLleno();


    System.out.print("Recorrido Pre-order: ");
    miArbol.preorder(miArbol.getRaiz());
    System.out.println();

    System.out.print("Recorrido In-order: ");
    miArbol.inorder(miArbol.getRaiz());
    System.out.println();

    System.out.print("Recorrido Post-order: ");
    miArbol.postorder(miArbol.getRaiz());
    System.out.println();
    
    System.out.print("Por nivel (BFS): ");
    miArbol.recorridoPorNiveles(miArbol.getRaiz());
    System.out.println();
    
 

}
		
}
