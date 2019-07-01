import java.io.*;
import java.util.stream.Stream;

import static java.lang.Character.isLowerCase;
import static java.lang.Character.isUpperCase;
import static java.lang.Character.toLowerCase;

public class ROT13 {
    Character cs;
    Character cf;


    ROT13(Character cs, Character cf) {
        this.cs = cs;
        this.cf = cf;
    }

    ROT13() {
    }

    public static void main(String[] args) {
        char c = 'z';
        char c1 = 'a';
        System.out.println(Character.getNumericValue(c));
        System.out.println(Character.getNumericValue(c1));
    }




    public String crypt(String text) throws UnsupportedOperationException {
        return encrypt(text);
    }

    public String encrypt(String text) {
        String[] alphLower = "abcdefghijklmnopqrstuvwxyz".split("");
        String[] alphUpper = "abcdefghijklmnopqrstuvwxyz".toUpperCase().split("");
        String[] check = text.split("");
        String rslt = "";

        for (String char1: check
        ) {
            if(Character.isLetter(char1.charAt(0))) {
                for (int i = 0; i < 26; i++) {
                    if (char1.equals(alphLower[i])) {
                        int newPosition = (i + 13) % 26;
                        rslt += alphLower[newPosition];
                    } else if (char1.equals(alphUpper[i])) {
                        int newPosition = (i + 13) % 26;
                        rslt += alphUpper[newPosition];
                    }
                }
            }
            else {
                rslt += char1;
            }
        }
        return rslt;
    }

    public String decrypt(String text) {
        return crypt(text);
    }

    public static String rotate(String text, Character c) {
        int rotateNum = Character.getNumericValue(c) - Character.getNumericValue('a');
        String[] alphLower = "abcdefghijklmnopqrstuvwxyz".split("");
        String[] alphUpper = "abcdefghijklmnopqrstuvwxyz".toUpperCase().split("");
        String[] check = text.split("");
        String rslt = "";
        Integer stringLength = text.length();

        for (String char1 : check
        ) {
            if (Character.isLetter(char1.charAt(0))) {
                for (int i = 0; i < stringLength; i++) {
                    if (char1.equals(alphLower[i])) {
                        int newPosition = (i + rotateNum) % stringLength;
                        rslt += alphLower[newPosition];
                    } else if (char1.equals(alphUpper[i])) {
                        int newPosition = (i + rotateNum) % stringLength;
                        rslt += alphUpper[newPosition];
                    }
                }
            } else {
                rslt += char1;
            }
        }
        return rslt;
    }

    public String readFile (String path) {
        String rslt = "";
        try(BufferedReader br = new BufferedReader(new FileReader(path))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            rslt = sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rslt;
    }

    public void writeFile () {
        try {
            File file = new File("t.txt");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(encrypt(readFile("sonnet18.txt")));
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}


