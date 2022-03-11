import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Arquivo {
    
    public static String Ler(String caminho) {
    	String mensagem = "";
    	Path arquivo = Paths.get(caminho);
    	try {
    		byte[] texto = Files.readAllBytes(arquivo);
    		mensagem = new String(texto);
    	} catch(Exception erro) {
    		System.out.println("Arquivo não encontrado!");
    	}
    	return mensagem;
    }
    
    public static void Escrever(String caminho, String texto) {
    	try {
    		FileWriter arquivo = new FileWriter(caminho);
    		PrintWriter escreveArquivo = new PrintWriter(arquivo);
    		escreveArquivo.print(texto);
    		escreveArquivo.close();
    	} catch(IOException e) {
    		System.out.println(e.getMessage());
    	}
    }
}
