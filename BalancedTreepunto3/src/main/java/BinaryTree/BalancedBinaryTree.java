//ISAAC DAVID PATERNINA NOBLE 0222220022 
package BinaryTree;

import java.util.LinkedList;
import java.util.Queue;

public class BalancedBinaryTree {
    public Nodo raiz;

    public BalancedBinaryTree() {
        this.raiz = null;
    }
    
    int altura(Nodo N) {
        if (N == null)
            return 0;
        return N.altura;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }

    public int getNumeroNodos() {
        return contarNodos(this.raiz);
    }

    private int contarNodos(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        return 1 + contarNodos(nodo.izquierdo) + contarNodos(nodo.derecho);
    }

    public int getAltura() {
        return calcularAltura(this.raiz);
    }
    
    Nodo rightRotate(Nodo y) {
        Nodo x = y.izquierdo;
        Nodo T2 = x.derecho;
        x.derecho = y;
        y.izquierdo = T2;
        y.altura = Math.max(altura(y.izquierdo), altura(y.derecho)) + 1;
        x.altura = Math.max(altura(x.izquierdo), altura(x.derecho)) + 1;
        return x;
    }
    
    Nodo leftRotate(Nodo x) {
        Nodo y = x.derecho;
        Nodo T2 = y.izquierdo;
        y.izquierdo = x;
        x.derecho = T2;
        x.altura = Math.max(altura(x.izquierdo), altura(x.derecho)) + 1;
        y.altura = Math.max(altura(y.izquierdo), altura(y.derecho)) + 1;
        return y;
    }
    
    int getBalance(Nodo N) {
        if (N == null)
            return 0;
        return altura(N.izquierdo) - altura(N.derecho);
    }
    
    public Nodo insert(Nodo node, int value) {
        if (node == null)
            return (new Nodo(value));
        if (value < node.getDato())
            node.izquierdo = insert(node.izquierdo, value);
        else if (value > node.getDato())
            node.derecho = insert(node.derecho, value);
        else
            return node;

        node.altura = 1 + Math.max(altura(node.izquierdo), altura(node.derecho));
        int balance = getBalance(node);

        if (balance > 1 && value < node.izquierdo.getDato())
            return rightRotate(node);
        if (balance < -1 && value > node.derecho.getDato())
            return leftRotate(node);
        if (balance > 1 && value > node.izquierdo.getDato()) {
            node.izquierdo = leftRotate(node.izquierdo);
            return rightRotate(node);
        }
        if (balance < -1 && value < node.derecho.getDato()) {
            node.derecho = rightRotate(node.derecho);
            return leftRotate(node);
        }

        return node;
    }
    
    private int calcularAltura(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        int alturaIzquierda = calcularAltura(nodo.izquierdo);
        int alturaDerecha = calcularAltura(nodo.derecho);
        return 1 + Math.max(alturaIzquierda, alturaDerecha);
    }

    public void inorder(Nodo nodo) {
        if (nodo != null) {
            inorder(nodo.izquierdo);
            System.out.print(nodo.getDato() + " - ");
            inorder(nodo.derecho);
        }
    }

    public void postorder(Nodo nodo) {
        if (nodo != null) {
            postorder(nodo.izquierdo);
            postorder(nodo.derecho);
            System.out.print(nodo.getDato() + " - ");
        }
    }

    public void preorder(Nodo nodo) {
        if (nodo != null) {
            System.out.print(nodo.getDato() + " - ");
            preorder(nodo.izquierdo);
            preorder(nodo.derecho);
        }
    }

    public void levelOrder(Nodo nodo) {
        if (nodo == null) {
            return;
        }
        Queue<Nodo> queue = new LinkedList<>();
        queue.add(nodo);
        while (!queue.isEmpty()) {
            Nodo current = queue.poll();
            System.out.print(current.getDato() + " - ");
            if (current.izquierdo != null) {
                queue.add(current.izquierdo);
            }
            if (current.derecho != null) {
                queue.add(current.derecho);
            }
        }
    }

    public Nodo getRaiz() {
        return this.raiz;
    }

    public boolean esLleno(Nodo raiz) {
        if (raiz == null) {
            return true;
        }
        if ((raiz.izquierdo == null && raiz.derecho != null) || (raiz.izquierdo != null && raiz.derecho == null)) {
            return false;
        }
        return esLleno(raiz.izquierdo) && esLleno(raiz.derecho);
    }

    public boolean esCompleto(Nodo raiz) {
        if (raiz == null) {
            return true;
        }
        Queue<Nodo> queue = new LinkedList<>();
        queue.add(raiz);
        boolean flag = false;
        while (!queue.isEmpty()) {
            Nodo temp = queue.poll();
            if (temp.izquierdo != null) {
                if (flag) {
                    return false;
                }
                queue.add(temp.izquierdo);
            } else {
                flag = true;
            }
            if (temp.derecho != null) {
                if (flag) {
                    return false;
                }
                queue.add(temp.derecho);
            } else {
                flag = true;
            }
        }
        return true;
    }
    
  public void imprimirLleno() {
    if (esLleno(this.raiz)) {
        System.out.println("El arbol es lleno. Todos los nodos tienen 0 o 2 hijos.");
    } else {
        System.out.println("El arbol no es lleno. Hay nodos con solo un hijo.");
    }
}

public void imprimirCompleto() {
    if (esCompleto(this.raiz)) {
        System.out.println("El arbol es completo. Todos los niveles están llenos(sin contar el ultimo)");
    } else {
        System.out.println("El arbol no es completo. Hay huecos en los niveles del arbol.");
    }
}


}
