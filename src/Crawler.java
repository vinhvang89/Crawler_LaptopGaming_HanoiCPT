import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Crawler {
        private static Crawler crawler;
        private Crawler(){

        }
        public static Crawler getInstance(){
            if(crawler == null)
                    crawler = new Crawler();
            return  crawler;
        }
        // Phương thức truyền vào pattern , link cần cào , Mảng hứng kết quả.
        public void crawlerStart(Pattern pattern, String link,List<String> list) throws IOException {
                URL url = new URL(link);
                Scanner scanner = new Scanner(new InputStreamReader(url.openStream()));
                scanner.useDelimiter("\\Z");
                String content = scanner.next();
                Matcher matcher = pattern.matcher(content);
                while (matcher.find()){
                        String line = matcher.group(1);
                        list.add(line);
                }
        }


//        Lấy link của trang tiếp theo để cào
        public String getNextPageLink(String link) throws IOException{
                URL url = new URL(link);
                Scanner scanner = new Scanner(new InputStreamReader(url.openStream()));
                scanner.useDelimiter("\\Z");
                String content = scanner.next();
                Pattern pattern = Pattern.compile("<a href=\"(.*?) >Next</a>");
                String line = null;
                Matcher matcher = pattern.matcher(content);
                while (matcher.find()){
                        line = matcher.group(1);
                }
                if(line != null)
                line ="https://www.hanoicomputer.vn".concat(line.substring(0,line.length()-2));

                return line;
        }

        // phương thức truyền vào link , pattern mẫu để cào và mảng hứng kết quả thu được
        public void getResult(String link,Pattern pattern,List<String> list) throws IOException {
                while (link != null) {
                        this.crawlerStart(pattern, link,list);
                        link = this.getNextPageLink(link);
                }
                list.removeIf(string -> string.contains("logo"));
        }


}
