public class FilaDePrio {
	public No primeiro;
	public No ultimo;
	public int contador = 0;
	
	public FilaDePrio() {
		this.primeiro = null;
		this.ultimo = null;
	}
	
	public void adicionar(No novo) {
		if(primeiro == null) {
			primeiro = novo;
			ultimo = novo;
		} else {
			No atual = primeiro;
			while(atual != null && novo.quantidade > atual.quantidade) {			
				atual = atual.proximo;
			}
			if(atual == primeiro) {
				primeiro.anterior = novo;
				novo.anterior = null;
				novo.proximo = primeiro;
				primeiro = novo;
			} else if (atual == null) {
				novo.anterior = ultimo;
				novo.proximo = null;
				ultimo.proximo = novo;
				ultimo = novo;
			} else {
				novo.proximo = atual;
				atual.anterior.proximo = novo;
				novo.anterior = atual.anterior;
				atual.anterior = novo;
			}
		}
		this.contador++;
	}
	
	public void excluir() {
		if(primeiro == null) {
			System.out.println("Nã há o que excluir!");
		} else {
			primeiro = primeiro.proximo;
		}
		this.contador--;
	}
	
	public void exibir() {
		No atual = primeiro;
		while(atual != null) {
			System.out.println(atual.letra + " - " + atual.quantidade);
			atual = atual.proximo;
		}
	}
	
	public boolean verifica(No novo) {
		No atual = primeiro;
		boolean tem = true;
		while(atual != null) {
			if(atual.letra == novo.letra) {
				tem = false;
			}
			atual = atual.proximo;
		}
		return tem;
	}
}
