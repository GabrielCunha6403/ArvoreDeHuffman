import java.util.Scanner;

public class Result {
	public static void main(String[] args) {
		Arvore arvore = new Arvore();
		FilaDePrio fila = new FilaDePrio();
		Scanner prompt = new Scanner(System.in);
		
		System.out.println("QUAL OPERAÇÃO VOCÊ DESEJA?");
		System.out.println("1. COMPACTAR;");
		System.out.println("2. DESCOMPACTAR;");
		int select = prompt.nextInt();
		String caminho;
		String frase;
		String mensagem;
		
		switch(select) {
		case 1:
			System.out.println("Digite o endereço do arquivo para compactá-lo:");
			caminho = prompt.next();
			
			//RECEBE TEXTO DO ARQUIVO
			frase = Arquivo.Ler(caminho);
			
			//ADICIONA NA LISTA DE PRIO
			for (int i = 0; i < frase.length(); i++) {
				char letra = frase.charAt(i);
				No novo = new No(letra);
				for (int j = i + 1; j < frase.length(); j++) {
					if(letra == frase.charAt(j)) {
						novo.quantidade++;
					}
				}
				if(fila.verifica(novo)) {
					fila.adicionar(novo);	
				}
			}
						
			//TRANSFORMA LISTA DE PRIO EM ÁRVORE
			while(fila.contador > 1) {
				int freq = fila.primeiro.quantidade + fila.primeiro.proximo.quantidade;
				No esquerda = fila.primeiro;
				No direita = fila.primeiro.proximo;
				fila.excluir();
				fila.excluir();
				fila.adicionar(new No(freq, direita, esquerda));
			}
			arvore.raiz = fila.primeiro;
			
			//DECLARAÇÃO DE CÓDIGOS HUFFMAN DE CADA CARACTERE
			arvore.codHuffman();
			arvore.preOrdem();
			
			//CRIAÇÃO DO ARQUIVO .TXT CONTENDO O TEXTO COMPACTADO
			Arquivo.Escrever("ArquivoCompactado.txt", "0" + arvore.compactacao(frase));
			
			System.out.println("COMPACTAÇÃO CONCLUÍDA!");
			
			break;
			
		case 2:
			System.out.println("Digite o endereço do arquivo para descompactá-lo:");
			caminho = prompt.next();
			
			//LEITURA DO CÓDIGO BINÁRIO 
			frase = Arquivo.Ler(caminho);
			
			//CRIAÇÃO DO ARQUIVO .TXT A PARTIR DA DESCOMPACTAÇÃO DO CÓDIGO BINÁRIO
			mensagem = arvore.descompactacao(frase);
			Arquivo.Escrever("ArquivoDescompactado.txt", mensagem);
			
			System.out.println("ARQUIVO DESCOMPACTADO!");
			
			break;
		}
	}
}
