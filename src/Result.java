import java.util.Scanner;

public class Result {
	public static void main(String[] args) {
		Arvore arvore = new Arvore();
		FilaDePrio fila = new FilaDePrio();
		Scanner prompt = new Scanner(System.in);
		
		System.out.println("QUAL OPERA��O VOC� DESEJA?");
		System.out.println("1. COMPACTAR;");
		System.out.println("2. DESCOMPACTAR;");
		int select = prompt.nextInt();
		String caminho;
		String frase;
		String mensagem;
		
		switch(select) {
		case 1:
			System.out.println("Digite o endere�o do arquivo para compact�-lo:");
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
						
			//TRANSFORMA LISTA DE PRIO EM �RVORE
			while(fila.contador > 1) {
				int freq = fila.primeiro.quantidade + fila.primeiro.proximo.quantidade;
				No esquerda = fila.primeiro;
				No direita = fila.primeiro.proximo;
				fila.excluir();
				fila.excluir();
				fila.adicionar(new No(freq, direita, esquerda));
			}
			arvore.raiz = fila.primeiro;
			
			//DECLARA��O DE C�DIGOS HUFFMAN DE CADA CARACTERE
			arvore.codHuffman();
			arvore.preOrdem();
			
			//CRIA��O DO ARQUIVO .TXT CONTENDO O TEXTO COMPACTADO
			Arquivo.Escrever("ArquivoCompactado.txt", "0" + arvore.compactacao(frase));
			
			System.out.println("COMPACTA��O CONCLU�DA!");
			
			break;
			
		case 2:
			System.out.println("Digite o endere�o do arquivo para descompact�-lo:");
			caminho = prompt.next();
			
			//LEITURA DO C�DIGO BIN�RIO 
			frase = Arquivo.Ler(caminho);
			
			//CRIA��O DO ARQUIVO .TXT A PARTIR DA DESCOMPACTA��O DO C�DIGO BIN�RIO
			mensagem = arvore.descompactacao(frase);
			Arquivo.Escrever("ArquivoDescompactado.txt", mensagem);
			
			System.out.println("ARQUIVO DESCOMPACTADO!");
			
			break;
		}
	}
}
