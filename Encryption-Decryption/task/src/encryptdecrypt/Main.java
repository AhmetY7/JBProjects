package encryptdecrypt;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    private static String readMessage(String source) throws IOException {
        return new String(Files.readAllBytes(Paths.get(source)));
    }
    private static void writeMessage(String message, String destination) {
        File file = new File(destination);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(message);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    private static void decryption(int key, String readTxt, String writeTxt, String algorithm) {
        String message = "";
        String willWriteMessage = "";
        try {
            message = readMessage(readTxt);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }

        for(int i=0; i<message.length(); i++) {
            char temp = message.charAt(i);
            if("shift".equals(algorithm)){
                if(temp>=97 && temp<=122) {
                    temp -= key;
                    if (temp < 97) {
                        temp = (char) (123 - (97 - temp));
                    }
                    willWriteMessage += temp;
                } else if(temp == ' '){
                    willWriteMessage += " ";
                } else {
                    willWriteMessage += temp;
                }
            } else {
                temp -=key;
                willWriteMessage += temp;
            }
        }
        if("".equals(writeTxt)){
            System.out.println(willWriteMessage);
        } else {
            writeMessage(willWriteMessage, writeTxt);
        }
    }

    private static void encryption(int key, String readTxt, String writeTxt, String algorithm) {
        String message = "";
        String willWriteMessage = "";
        try {
            message = readMessage(readTxt);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }

        for(int i=0; i<message.length(); i++) {
            char temp = message.charAt(i);
            if("shift".equals(algorithm)){
                if(temp>=97 && temp<=122) {
                    temp += key;
                    if (temp > 122) {
                        temp = (char) (96 + (temp - 122));
                    }
                    willWriteMessage += temp;
                } else if(temp == ' '){
                    willWriteMessage += " ";
                } else {
                    willWriteMessage += temp;
                }
            } else {
                temp +=key;
                willWriteMessage += temp;
            }
        }
        if("".equals(writeTxt)){
            System.out.println(willWriteMessage);
        } else {
            writeMessage(willWriteMessage, writeTxt);
        }
    }

    public static void main(String[] args) {
        String mode = "enc";
        String readTxt = "";
        String writeTxt = "";
        String algorithm = "" ;
        int key = 0;
        for(int i=0; i<args.length; i++){
            if("-mode".equals(args[i])) {
                mode = args[++i];
            } else if("-key".equals(args[i])) {
                key = Integer.parseInt(args[++i]);
            } else if("-in".equals(args[i])){
                readTxt = args[++i];
            } else if("-out".equals(args[i])) {
                writeTxt = args[++i];
            } else if("-alg".equals(args[i])) {
                algorithm = args[++i];
            }
        }
        if("enc".equals(mode)){
            encryption(key, readTxt, writeTxt, algorithm);
        } else {
            decryption(key, readTxt, writeTxt, algorithm);
        }
    }
}

