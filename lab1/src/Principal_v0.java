/**
 * Lab0: Leitura de Base de Dados Não-Distribuida
 * 
 * Nome: João Pedro Padoan
 * Modificado de: Lucio A. Rocha
 * 
 * Referencias: 
 * https://docs.oracle.com/javase/tutorial/essential/io
 * 
 */

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Principal_v0 {

	public final static Path path = Paths			
			.get("src\\fortune-br.txt");
	private int NUM_FORTUNES = 0;

	public class FileReader {

		public int countFortunes() throws FileNotFoundException {

			int lineCount = 0;

			InputStream is = new BufferedInputStream(new FileInputStream(
					path.toString()));
			try (BufferedReader br = new BufferedReader(new InputStreamReader(
					is))) {

				String line = "";
				while (!(line == null)) {

					if (line.equals("%"))
						lineCount++;

					line = br.readLine();

				}// fim while

				System.out.println(lineCount);
			} catch (IOException e) {
				System.out.println("SHOW: Excecao na leitura do arquivo.");
			}
			return lineCount;
		}

		public void parser(HashMap<Integer, String> hm)
				throws FileNotFoundException {

			InputStream is = new BufferedInputStream(new FileInputStream(
					path.toString()));
			try (BufferedReader br = new BufferedReader(new InputStreamReader(
					is))) {

				int lineCount = 0;

				String line = "";
				while (!(line == null)) {

					if (line.equals("%"))
						lineCount++;

					line = br.readLine();
					StringBuffer fortune = new StringBuffer();
					while (!(line == null) && !line.equals("%")) {
						fortune.append(line + "\n");
						line = br.readLine();
						// System.out.print(lineCount + ".");
					}

					hm.put(lineCount, fortune.toString());
					System.out.println(fortune.toString());

					System.out.println(lineCount);
				}// fim while

			} catch (IOException e) {
				System.out.println("SHOW: Excecao na leitura do arquivo.");
			}
		}

		public void read(HashMap<Integer, String> hm)
				throws FileNotFoundException {

					SecureRandom random = new SecureRandom();
					int randomIndex = random.nextInt(NUM_FORTUNES) + 1;
				
					String fortune = hm.get(randomIndex);
			
					System.out.println("Fortuna aleatória:");
					System.out.println(fortune);
		}

		public void write(HashMap<Integer, String> hm, String nova_fortuna)
				throws IOException {
					try (BufferedWriter writer = new BufferedWriter(new FileWriter(path.toString(), true))) {
						int novaLinha = hm.size() + 1; 
						writer.write("%\n"); 
						writer.write(nova_fortuna + "\n"); 
						hm.put(novaLinha, nova_fortuna); 
						System.out.println("Nova fortuna adicionada com sucesso!");
					} catch (IOException e) {
						System.out.println("SHOW: Excecao na escrita do arquivo.");
						throw e; // Propaga a exceção para o método chamador, se necessário
					}
		}
	}

	public void iniciar() throws IOException {

		FileReader fr = new FileReader();
		try {
			NUM_FORTUNES = fr.countFortunes();
			HashMap hm = new HashMap<Integer, String>();
			fr.parser(hm);
			fr.read(hm);
			fr.write(hm, "Testando nova fortuna.");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws IOException {
		new Principal_v0().iniciar();
	}

}
