import java.io.*;

public class Main
{

    public static void main(String[] args)
    {
        Compresion compresion = new Compresion();
        Decompression decompression = new Decompression();
        String text = "";
        String text2 = "";

        try
        {
            FileReader input = new FileReader("Input.txt");
            BufferedReader bufferedReader = new BufferedReader(input);
            String line;

            while ((line = bufferedReader.readLine()) != null)
            {
                text = line;
            }

            bufferedReader.close();
        }
        catch (FileNotFoundException x)
        {
            System.out.println("File not found");
        }
        catch (IOException x)
        {
            System.out.println("Error reading a file");
        }

        try
        {
            FileWriter output = new FileWriter("Output.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(output);
            bufferedWriter.write(compresion.run(text));
            bufferedWriter.close();
        }
        catch (IOException x)
        {
            System.out.println("Error while wrtiting to a file");
        }

        try
        {
            FileReader input = new FileReader("Output.txt");
            BufferedReader bufferedReader = new BufferedReader(input);
            String line;

            while ((line = bufferedReader.readLine()) != null)
            {
                text2 = line;
            }

            bufferedReader.close();
        }
        catch (FileNotFoundException x)
        {
            System.out.println("File not found");
        }
        catch (IOException x)
        {
            System.out.println("Error reading a file");
        }

        try
        {
            FileWriter output = new FileWriter("DecompresionOutput.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(output);
            bufferedWriter.write(decompression.runDecompresion("A . " + text2));
            bufferedWriter.close();
        }
        catch (IOException x)
        {
            System.out.println("Error while wrtiting to a file");
        }

    }
}
