//ISAAC DAVID PATERNINA NOBLE 0222220022 

package app;

import BinaryTree.*;

public class App {
    public static void main(String[] argumentos) {
        BalancedBinaryTree miArbolBalanceado = new BalancedBinaryTree();

        Integer[] elementos = {9, 12, 14, 17, 19, 23, 50, 54, 67, 72, 76};
        for (Integer valor : elementos) {
            miArbolBalanceado.raiz = miArbolBalanceado.insert(miArbolBalanceado.raiz, valor);
        }

        System.out.println("Cantidad de aristas: " + (miArbolBalanceado.getNumeroNodos() - 1));
        System.out.println("Altura total: " + miArbolBalanceado.getAltura());
        System.out.println("Total de nodos: " + miArbolBalanceado.getNumeroNodos());

        miArbolBalanceado.imprimirLleno();
        miArbolBalanceado.imprimirCompleto();

        System.out.print("Recorrido Pre-order: ");
        miArbolBalanceado.preorder(miArbolBalanceado.getRaiz());
        System.out.println();
         
       System.out.print("Recorrido In-order: ");
        miArbolBalanceado.inorder(miArbolBalanceado.getRaiz());
        System.out.println();

        System.out.print("Recorrido Post-order: ");
        miArbolBalanceado.postorder(miArbolBalanceado.getRaiz());
        System.out.println();

        System.out.print("Por nivel (BFS): ");
        miArbolBalanceado.levelOrder(miArbolBalanceado.getRaiz());
        System.out.println();
    }
}
