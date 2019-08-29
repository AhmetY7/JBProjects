package encryptdecrypt;

public class Main {

    private static void decryption(String message, int key) {
        for(int i=0; i<message.length(); i++) {
            char temp = message.charAt(i);
            temp -=key;
            System.out.print(temp);
        }
    }

    private static void encryption(String message, int key) {
        for(int i=0; i<message.length(); i++) {
            char temp = message.charAt(i);
            temp +=key;
            System.out.print(temp);
        }
    }

    public static void main(String[] args) {
        String mode = "enc";
        String data = "";
        int key = 0;
        for(int i=0; i<args.length; i++){
            if("-mode".equals(args[i])) {
                mode = args[++i];
            } else if("-data".equals(args[i])) {
                data = args[++i];
            } else if("-key".equals(args[i])) {
                key = Integer.parseInt(args[++i]);
            }
        }
        if("enc".equals(mode)){
            encryption(data, key);
        } else {
            decryption(data, key);
        }
    }
}

