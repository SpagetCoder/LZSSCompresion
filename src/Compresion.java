import java.io.*;
import java.util.Arrays;

public class Compresion
{
    static int dictionarySize = 8;
    int bufforSize = 4;
    char[] Buffor = new char[bufforSize];
    String text;
    char dictionary[] = new char[dictionarySize];
    StringBuilder outCode = new StringBuilder();

    void fillDictionary()
    {
        char firstLetter = text.charAt(0);
        Arrays.fill(dictionary, firstLetter);
    }

    void fillBuffor()
    {
        for (int i = 0; i < bufforSize && i < text.length(); i++)
        {
            Buffor[i] = text.charAt(i);
        }
        text = text.substring(bufforSize);
    }

    void searchingForLongestMatch()
    {
        int lenght = 0;
        int index = 0;

        for (int i = dictionarySize - 1; i >= 0; i--)
        {
            for (int k = 0; k < bufforSize && i + k < dictionarySize; k++)
            {
                if (Buffor[k] == dictionary[i + k])
                {
                    if (lenght < k + 1)
                    {
                        lenght = k + 1;
                        index = i;
                    }
                } else
                    break;
            }
        }
        if (lenght != 0)
        {
            int first = 0;
            outCode.append(first).append(",").append(dictionarySize - index - 1).append(",").append(lenght).append(" ; ");
            replaceReceiver(dictionary, Buffor, lenght);
            replaceReceiver(Buffor, getAndDelete(lenght), lenght);

        } else {
            int first = 1;
            outCode.append(first).append(",").append(Buffor[0]).append(" ; ");
            replaceReceiver(dictionary, Buffor, 1);
            replaceReceiver(Buffor, getAndDelete(1), 1);
        }
    }

    private static void replaceReceiver(char[] receiver, char[] sender, int amount)
    {
        for (int i = 0; i < receiver.length; i++)
        {
            if (i < receiver.length - amount)
            {
                receiver[i] = receiver[i + amount];
            }
            else
            {
                if ((i - (receiver.length - amount)) < sender.length)
                {
                    receiver[i] = sender[i - (receiver.length - amount)];
                } else
                    receiver[i] = '\0';
            }
        }
    }

    char[] getAndDelete(int number)
    {
        String tempChars;
        if (text.length() >= number)
        {
            tempChars = text.substring(0, number);
            text = text.substring(number);
        }
        else {
            tempChars = text;
            text = "";
        }

        return tempChars.toCharArray();
    }

    String run(String text)
    {
        this.text = text;
        fillDictionary();
        fillBuffor();

        while (Buffor[0] != '\0')
        {
            searchingForLongestMatch();
        }

        System.out.println(outCode.toString());
        return outCode.toString();
    }

}