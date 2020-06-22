package wang.JUC;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

public class ffff {
    public static void main(String[] args) {
        List<String> strs = new ArrayList<String>();
        Pattern pattern = Pattern.compile("[a-zA-Z']{4,6}");
        Matcher m = pattern.matcher("Evening is full of the linnet's wings");
        while(m.find()) {
            System.out.println(m.group());
            strs.add(m.group());
        }




    }
}
