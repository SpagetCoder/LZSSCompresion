import java.util.Arrays;

public class Decompression
{
    String finalText = "";

    String runDecompresion(String comText)
    {
        String[] firstCharacter = comText.split("\\.");
        char[] fill = new char[Compresion.dictionarySize];
        Arrays.fill(fill, firstCharacter[0].charAt(0));

        String dictionary = new String(fill);
        
        for (String block : comText.split(" ; "))
        {
            String[] letters = block.split(",");

            if (letters.length == 2)
            {
                finalText += letters[1];
                dictionary = (dictionary + letters[1]).substring(1);
            }

            else if (letters.length == 3)
            {
                int offset = Integer.parseInt(letters[1]);
                int length = Integer.parseInt(letters[2]);
                int start = Compresion.dictionarySize - offset - 1;
                int end = start + length;
                String text = dictionary.substring(start, end);
                finalText += text;
                dictionary = (dictionary + text).substring(text.length());
            }

        }

        System.out.println(finalText);
        return finalText;
    }

}
