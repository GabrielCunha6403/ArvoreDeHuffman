public class No {
	public char letra;
	public int quantidade = 1;
	public String binario;
	No proximo;
	No anterior;
	No direita;
	No esquerda;
	
	public No() {
		this.direita = null;
		this.esquerda = null;
		this.anterior = null;
		this.proximo = null;
	}
	
	public No(char letra) {
		this.letra = letra;
		this.direita = null;
		this.esquerda = null;
		this.anterior = null;
		this.proximo = null;
	}
	
	public No(int quantidade, No direita, No esquerda) {
		this.quantidade = quantidade;
		this.direita = direita;
		this.esquerda = esquerda;
		this.anterior = null;
		this.proximo = null;
	}
	public No(No direita, No esquerda) {
		this.direita = direita;
		this.esquerda = esquerda;
		this.anterior = null;
		this.proximo = null;
	}
	
	public boolean Folha() {
		if(direita == null && esquerda == null) 
			return true;
		else
			return false;
	}
}
