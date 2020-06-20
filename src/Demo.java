import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Demo {
    public static void main(String[] args) throws IOException {
        List<String> laptopDetails = new ArrayList<>();
        String link = "https://www.hanoicomputer.vn/laptop-may-tinh-xach-tay/c159.html?filter=%2C3926%2C";
        Pattern pattern = Pattern.compile("alt=\"(.*?)\">");

        Crawler crawler = Crawler.getInstance();
        crawler.getResult(link,pattern,laptopDetails);

        for (String string : laptopDetails) {
            System.out.println(string);
        }

    }
}
