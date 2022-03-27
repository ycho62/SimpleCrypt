import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static java.lang.Character.isLowerCase;
import static java.lang.Character.isUpperCase;
import static java.lang.Character.toLowerCase;

public class ROT13  {
Character cs;
Character cf;
static Integer shift;

    ROT13(Character cs, Character cf) {
    this.cs = cs;
    this.cf= cf;
    shift = cf - cs;
    }

//    String res = "";
//        for (Character ch : word.toCharArray()) {
//        res = res + (char) ('a' + (((ch + 13) - 'a') % 26));
//    }
//        return res;

    ROT13() {this('a','n');
    }


    public static String crypt(String text) throws UnsupportedOperationException {

        return encrypt(text);
    }

    public static String encrypt(String text) {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < text.length(); i++ ){
            char s = text.charAt(i);
            if (text.charAt(i) >= 'a' && text.charAt(i) <= 'm'){
                s += shift;
            }else if(text.charAt(i) >= 'A' && s <= 'M') {
                s += shift;
            }else if (text.charAt(i)>= 'n' && s <= 'z'){
                s -= shift;
            }else if (s >= 'N' && s <= 'Z') {
                s -= shift;
            }
            builder.append(s);
        }
        return builder.toString();
    }

    public String decrypt(String text) {
        return encrypt(text);
    }

    public static String rotate(String s, Character c) {
       int index = s.indexOf(c);
       StringBuilder sb = new StringBuilder();
       sb.append(s.substring(index, s.length()));
       sb.append(s.substring(0,index));
        return sb.toString();
    }

    public static void createFile() {
        try {
            File myFile = new File("/User/yun/dev/SimpleCrypt/sonnet18.enc");
            if (myFile.createNewFile()){
                System.out.println("File was created");
            }
            System.out.println("File already exist");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String fileReader(){
        String line = "";
        try (Scanner scanner = new Scanner(new File("/User/yun/dev/SimpleCrypt/sonnet18.txt"))) {
            while(scanner.hasNext()) {
                line += scanner.nextLine() + "\n";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return line;

    }
    public static String fileReader2(){
        String line2 = "";
        try (Scanner scanner = new Scanner(new File("/User/yun/dev/SimpleCrypt/sonnet18.enc"))) {
            while(scanner.hasNext()) {
                line2 += scanner.nextLine() + "\n";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return line2;

    }
    public static void writeToFile(String text){
        createFile();
        try {
            FileWriter fileWriter = new FileWriter("/User/yun/dev/SimpleCrypt/sonnet18.enc");
            fileWriter.write(text);
            fileWriter.close();
            } catch (IOException e) {
            e.printStackTrace();
        }
    }


   public static void encode() throws FileNotFoundException {
        String line = "";

        fileReader();
        String line2 = crypt(line);
        writeToFile(line2);
   }

}
