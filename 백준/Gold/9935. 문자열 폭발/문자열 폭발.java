import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        String bombStr = br.readLine();
        int bombLen = bombStr.length();
        StringBuilder sb = new StringBuilder();
        int len = 0;
        char ch;
        boolean isMatched;

        for (int i = 0; i < str.length(); i++) {
            ch = str.charAt(i);
            sb.append(ch);
            len++;

            if (len >= bombLen) {
                isMatched = true;

                for (int j = 1; j <= bombLen; j++) {
                    if (sb.charAt(len - j) != bombStr.charAt(bombLen - j) ) {
                        isMatched = false;
                        break;
                    }
                }

                if (isMatched) {
                    sb.delete(len - bombLen, len);
                    len -= bombLen;
                }
            }
        }

        System.out.println(sb.length() != 0 ? sb : "FRULA");

        br.close();
    }
}
