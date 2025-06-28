////ISAAC DAVID PATERNINA NOBLE 0222220022

package co.edu.unicartagena.binarytree;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {
	public Node raiz;
	public Node izquierdo;
	public Node derecho;
	public int altura=0;
	
	public long numeroMenor = 1001;
	public long numeroMayor = 0;
	
	public int nivelNumeroMenor=0;
	public int nivelNumeroMayor=0;
	
	
	public int nivel=0;
	public int nodos=0;
	
	public BinaryTree(){
	}
	
	public void adicionar (long valor, Node raiz, int nivel){
		if (raiz==null){
			this.raiz=new Node(valor);
			nodos = nodos + 1;
			updateAltura(this.altura,nivel);
			updateNivelNumeroMayor(valor,this.numeroMayor,nivel);
			updateNivelNumeroMenor(valor,this.numeroMenor,nivel);
		}
		else{
			if (valor<raiz.getDato()){
				if(raiz.izquierdo==null){
					raiz.izquierdo=new Node(valor);
					nodos = nodos + 1;
					updateAltura(this.altura,nivel+1);
					updateNivelNumeroMenor(valor,this.numeroMenor,nivel+1);
				}
				else{
					adicionar (valor,raiz.izquierdo,nivel+1);
				}	
			}
			if(valor>raiz.getDato()) {
				if(raiz.derecho==null){
					raiz.derecho=new Node(valor);
					nodos = nodos + 1;
					updateAltura(this.altura,nivel+1);
					updateNivelNumeroMayor(valor,this.numeroMayor,nivel+1);
					
				}
				else{
					adicionar (valor,raiz.derecho,nivel+1);
				}
			}
		} 
	}
	
	public int getNumeroNodos(){
		return this.nodos;
	}
	
	public int getAltura(){
		return this.altura;
	}
	
	public int getNivelNumeroMayor(){
		return this.nivelNumeroMayor;
	}
	
	public int getNivelNumeroMenor(){
		return this.nivelNumeroMenor;
	}
	
	
	public void updateAltura(int mayor, int nivel){
		if(mayor<nivel){
			this.altura=nivel;
		}
	}
	
	public void updateNivelNumeroMayor(long valor, long numeroMayor, int nivel){
		if(numeroMayor<valor ){
			this.numeroMayor=valor;
			this.nivelNumeroMayor = nivel;
		}
		
			
	}
	
	public void updateNivelNumeroMenor(long valor, long numeroMenor, int nivel){
		if(numeroMenor>valor){
			this.numeroMenor=valor;
			this.nivelNumeroMenor = nivel;
		}
	}
	
	public void inorder(Node raiz){
		if(raiz!=null){
			inorder(raiz.izquierdo);
			System.out.print(raiz.getDato()+" - ");
			inorder(raiz.derecho);
		}
	}
	
	public void postorder(Node raiz){
		if(raiz!=null){
			postorder(raiz.izquierdo);
			postorder(raiz.derecho);
			System.out.print(raiz.getDato()+" - ");
			
		}
	}
	
	public void preorder(Node raiz){
		if(raiz!=null){
			System.out.print(raiz.getDato()+" - ");
			preorder(raiz.izquierdo);
			preorder(raiz.derecho);
		}
	}
	
	public Node getRaiz(){
		return this.raiz;
	}
        
public void recorridoPorNiveles(Node raiz) {
    if (raiz == null) return;
    Queue<Node> queue = new LinkedList<>();
    queue.add(raiz);
    while (!queue.isEmpty()) {
        Node actual = queue.poll();
        System.out.print(actual.getDato() + " - ");
        if (actual.izquierdo != null) queue.add(actual.izquierdo);
        if (actual.derecho != null) queue.add(actual.derecho);
    }
}


public boolean esLleno(Node raiz) {
    if (raiz == null) return true;
    if ((raiz.izquierdo == null && raiz.derecho != null) ||
        (raiz.izquierdo != null && raiz.derecho == null)) {
        return false;
    }
    return esLleno(raiz.izquierdo) && esLleno(raiz.derecho);
}


public boolean esCompleto(Node raiz) {
    if (raiz == null) return true;
    Queue<Node> queue = new LinkedList<>();
    queue.add(raiz);
    boolean encontradoHijoFaltante = false;
    while (!queue.isEmpty()) {
        Node actual = queue.poll();
        if (actual.izquierdo != null) {
            if (encontradoHijoFaltante) return false;
            queue.add(actual.izquierdo);
        } else {
            encontradoHijoFaltante = true;
        }
        if (actual.derecho != null) {
            if (encontradoHijoFaltante) return false;
            queue.add(actual.derecho);
        } else {
            encontradoHijoFaltante = true;
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
        System.out.println("El arbol es completo. Todos los niveles están llenos (sin contar el último).");
    } else {
        System.out.println("El arbol no es completo. Hay huecos en los niveles del arbol.");
    }
}

}