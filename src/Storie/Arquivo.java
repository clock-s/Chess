package Storie;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Arquivo {
    private static File archive = new File("game.txt");


    public static void addText(String texto){
        try{
            FileWriter writer = new FileWriter(archive, true);
            writer.write(texto + "\n");
            writer.close();
            System.out.println("deu bom" + texto);
        }
        catch(IOException e){
            System.out.println("erro ao adicionar texto no arquivo");
        }
    }
    //metodo para adicionar movimento da peça  no arquivo

}
