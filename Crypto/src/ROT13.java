import java.util.regex.Pattern;

import static java.lang.Character.*;

public class ROT13  {
    Character cs;
    Character cf;

    ROT13(Character cs, Character cf) {
        this.cs  = cs;
        this.cf = cf;
    }

    ROT13() {
    }

    public int cryptKey() {
        return cf - cs;
    }


    public String crypt(String text) throws UnsupportedOperationException {
        return encrypt(text);
    }

    public String encrypt(String text) {

        StringBuilder ns = new StringBuilder();
        int key = cryptKey();


        for (int i = 0; i < text.length(); i++) {
            char ch;
            if (Pattern.compile("[^a-zA-Z]").matcher(String.valueOf(text.charAt(i))).matches()) {
                ch = text.charAt(i);
                ns.append(ch);
            } else {

                boolean upperCase = false;

                upperCase = isUpperCase(text.charAt(i));
                if (upperCase) {
                    ch = toLowerCase(text.charAt(i));
                } else {
                    ch = text.charAt(i);
                }

                //if ch equals a space or special character ignore.

                int j = (((ch - 97) + key) % 26) + 97; //modulo so that when it goes to z go back to a
                char newch = (char) j;
                if (upperCase) {
                    newch = toUpperCase(newch);
                }
                ns.append(newch);
            }
        }
        return ns.toString();
    }

    public String decrypt(String text) {

        return encrypt(text);
    }

    public static String rotate(String s, Character c) {
        ROT13 rotate = new ROT13('a', c);

        return rotate.crypt(s);
    }

}
