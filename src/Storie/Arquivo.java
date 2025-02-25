package Storie;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Arquivo {
    private static File archive;
    private static final String CONTADOR_FILE = "contador.txt";

    // pega o próximo número de jogo e atualiza o contador
    private static int getNextGameNumber() {
        int numero = 1; // Padrão se o arquivo não existir

        try {
            if (Files.exists(Paths.get(CONTADOR_FILE))) {
                String conteudo = Files.readString(Paths.get(CONTADOR_FILE)).trim();
                numero = Integer.parseInt(conteudo) + 1; // Incrementa o número
            }

            // Salva o novo número no arquivo
            Files.writeString(Paths.get(CONTADOR_FILE), String.valueOf(numero));

        } catch (IOException | NumberFormatException e) {
            System.out.println("Erro ao manipular contador: " + e.getMessage());
        }

        return numero;
    }

    // Método para iniciar um novo jogo e criar um novo arquivo numerado
    public static void startNewGame() {
        int gameNumber = getNextGameNumber();
        archive = new File("jogo_" + gameNumber + ".txt");

        try {
            if (archive.createNewFile()) {
                System.out.println("Novo jogo iniciado: " + archive.getName());
            } else {
                System.out.println("Erro ao criar novo arquivo de jogo.");
            }
        } catch (IOException e) {
            System.out.println("Erro ao criar arquivo: " + e.getMessage());
        }
    }

    // Adiciona texto ao arquivo (movimentos do jogo)
    public static void addText(String texto) {
        if (archive == null) {
            System.out.println("Erro: Nenhum jogo iniciado. Chame startNewGame() primeiro.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archive, true))) {
            writer.write(texto);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao adicionar texto no arquivo: " + e.getMessage());
        }
    }
}