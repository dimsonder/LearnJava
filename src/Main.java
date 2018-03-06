import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        String path = "D:\\Java\\sources.list";

        String content = readByBufferedReader(path);
        //替换字符串里Fix为haha

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String curTime = sdf.format(date);
        String s = content.replaceAll("^[123]$", curTime);
        boolean successful = writeByBufferedWriter(s, path);
        System.out.println(successful);
    }

    //    文件读取:通过BufferedReader读取数据
    public static String readByBufferedReader(String fileName) {
        String read = null;
        try {
            File file = new File(fileName);
            // 读取文件，并且以utf-8的形式写出去
            FileReader fileReader = new FileReader(file);
            BufferedReader bufread = new BufferedReader(fileReader);
            while ((read = bufread.readLine()) != null) {
                System.out.println(read);
            }
            bufread.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return read;
    }

    public static boolean writeByBufferedWriter(String content, String fileName) {
        try {
            File file = new File(fileName);

            if (!file.exists()) {
                file.createNewFile();
            }
            //true时允许追加
            //FileWriter fileWriter=new FileWriter(file,true);
            FileWriter fileWriter = new FileWriter(file);

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(content);
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
