import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        String s = br.readLine();
        int res = 0;
        Pattern pattern = Pattern.compile("(IO)+I");
        Matcher matcher = pattern.matcher(s);
        String group;
        int cntO;

        while (matcher.find()) {
            group = matcher.group();
            cntO = group.length() / 2;

            if (cntO >= N) {
                res += group.length() / 2 - N + 1;
            }
        }

        System.out.println(res);

        br.close();
    }
}
