import java.io.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        String path="D:\\Java\\sources.list";

      String content=readFileByBufferedReader(path);
        //替换字符串里Fix为haha
       String s= content.replace("Fix","haha");
       boolean successful= writeByBufferedWriter(s,path);
        System.out.println(successful);
    }
//    文件读取:通过BufferedReader读取数据
    public static String readByBufferedReader(String fileName) {
        String read=null;
        try {
            File file = new File(fileName);
            // 读取文件，并且以utf-8的形式写出去
            BufferedReader bufread;

            bufread = new BufferedReader(new FileReader(file));
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

    public static boolean writeByBufferedWriter(String content,String fileName){
        try {
            File file= new File(fileName);

            if (!file.exists()){
                file.createNewFile();
            }
            //true时允许追加
            //FileWriter fileWriter=new FileWriter(file,true);
            FileWriter fileWriter=new FileWriter(file);

            BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
            bufferedWriter.write(content);
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }
}
