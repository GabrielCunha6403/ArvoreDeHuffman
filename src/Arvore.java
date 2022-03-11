public class Arvore {
	public No raiz;

	public Arvore() {
		this.raiz = null;
	}
	
	public void preOrdem() {
		if(raiz != null) {
			preOrdem(raiz);
		}
	}
	
	public void preOrdem(No raiz) {
		if(raiz.Folha())
			System.out.println(raiz.letra + " - " + raiz.quantidade + " - " + raiz.binario);
		if(raiz.esquerda != null) 
			preOrdem(raiz.esquerda);
		
		if(raiz.direita != null) 
			preOrdem(raiz.direita);
	}
	
	String cod = "";
	public void codHuffman() {
	    String cod = "";
	    codHuffman(raiz, cod);
	}
	private void codHuffman(No raiz, String codigo) {
	    if (raiz.esquerda == raiz.direita)
	        raiz.binario = codigo;
	    else {
	    	codHuffman(raiz.esquerda, codigo + "0");
	    	codHuffman(raiz.direita, codigo + "1");
	    }
	}
	
	public String retornaCodHuffman(char letra) {
		if(raiz.letra == letra) {
			return raiz.binario;
		} else {
			return retornaCodHuffman(raiz, letra);
		}
	}
	
	String bin1 = "";
	public String retornaCodHuffman(No raiz, char letra) {
		if(!raiz.esquerda.Folha()) {
			retornaCodHuffman(raiz.esquerda, letra);
		}
		if(!raiz.direita.Folha()) {
			retornaCodHuffman(raiz.direita, letra);
		}
		if(raiz.esquerda.letra == letra) {
			bin1 = raiz.esquerda.binario;
		}
		if(raiz.direita.letra == letra) {
			bin1 = raiz.direita.binario;
		}
		return bin1;
	}
	
	public String compactacao(String arquivo) {     
		String codbin = "";
		if(raiz != null)
			codbin +=compactacao(raiz, codbin);
		
		for (int i = 0; i < arquivo.length(); i++) {
			
			codbin += retornaCodHuffman(arquivo.charAt(i));
		}
		return codbin;
	}
	
	public String compactacao(No raiz, String bin) {
		String codbin = "";
		if(!raiz.Folha()) {	
			if(!raiz.esquerda.Folha()) {
				codbin += "0";
				codbin += compactacao(raiz.esquerda, bin + "0");
			} else
				codbin += ("1" + String.format("%8s", Integer.toBinaryString(raiz.esquerda.letra)).replace(" ", "0"));
			if(!raiz.direita.Folha()) {
				codbin += "0";
				codbin += compactacao(raiz.direita, bin + "0");
			} else 
				codbin += ("1" + String.format("%8s", Integer.toBinaryString(raiz.direita.letra)).replace(" ", "0"));
		} else
			codbin += ("1" + String.format("%8s", Integer.toBinaryString(raiz.direita.letra)).replace(" ", "0"));
		return codbin;
	}

	public String binarioString(String bin) {
		String letra = "";
		String temp = bin.substring(0, 8);
		int num = Integer.parseInt(temp,2);
	    char caractere = (char) num;
	    letra = letra + caractere;
	    return letra;
	}
	
	private char[] retornaCaracteres(String arquivo) {
	        char[] letras = new char[arquivo.length()];
	        arquivo.getChars(0, arquivo.length(), letras, 0);
	        return letras;
	}
	
	public String decode(String cod) { 
        No novo = raiz;

        StringBuilder result = new StringBuilder();
        for (char bin : retornaCaracteres(cod)) {
            if (bin == '0') {
            	novo = novo.esquerda;
            } else {
            	novo = novo.direita;
            }

            if (novo.Folha()) {
                result.append(novo.letra);
                novo = raiz;
            }
        }
        return result.toString();
    }
	
	String mensagem = "";
	public String descompactacao(String arquivo) {
		String mensagem = "";
		arquivo = arquivo.substring(1);
		if(this.raiz == null) {
			raiz = new No();
		} 
		mensagem += descompactacao(this.raiz, null, arquivo);
		
		return decode(mensagem);
	}
	public String descompactacao(No raiz, No anterior, String arquivo) {
		int aux = 0;
		while(aux >= 0) {
			if(arquivo.charAt(0) == '0') {
				if(raiz.esquerda == null) {
					aux++;
					arquivo = arquivo.substring(1);
					raiz.esquerda = new No();
					raiz.esquerda.anterior = raiz;
					raiz = raiz.esquerda;
				} else if(raiz.direita == null) {
					aux++;
					arquivo = arquivo.substring(1);
					raiz.direita = new No();
					raiz.direita.anterior = raiz;
					raiz = raiz.direita;
				} else {
					aux--;
					raiz = raiz.anterior;
				}
			} else {
				if(raiz.esquerda == null) {
					String letra = arquivo.substring(1, 9);
					raiz.esquerda = new No(binarioString(letra).charAt(0));
					arquivo = arquivo.substring(9);
				} else if(raiz.direita == null) {
					String letra = arquivo.substring(1, 9);
					raiz.direita = new No(binarioString(letra).charAt(0));
					arquivo = arquivo.substring(9);
				} else {
					aux--;
					raiz = raiz.anterior;
				}
			}
		}
		return arquivo;
	}
}