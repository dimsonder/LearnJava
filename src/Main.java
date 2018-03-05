import java.io.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        String path="D:\\Java\\sources.list";

      String content=readFileByBytes(path);
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

    //文件读取：节点流FileInputStream读取字节流
    public static String readFileByBytes(String fileName) {
        String content=null;
        try {
            FileInputStream fileInputStream;

            File file = new File(fileName);
            if (!file.exists()) {

                file.createNewFile();
            }
            byte[] buffer = new byte[2048];
            fileInputStream = new FileInputStream(file);
// byte//byteRead表示一次读取到buffers中的数量
            int byteRead = 0;
            while ((byteRead = fileInputStream.read(buffer)) != -1) {
                content=new String(buffer,0,byteRead);
                System.out.write(buffer, 0, byteRead);
            }

        } catch (IOException e) {

            e.printStackTrace();
        }

        return content;

    }
//    文件读取:节点流FileReader读取字符流  其实FileReader也是需要FileInputStream对象转换的，要借助于InputStreamReader转换流。
    public static void readFileByChars(String fileName) {
        FileReader reader = null;
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            reader = new FileReader(file);
            char[] buffer = new char[1024];
            int charread = 0;
            while ((charread = reader.read(buffer)) != -1) {
                System.out.print(buffer);
            }
        } catch (IOException e) {
            // TODO: handle exception

        } finally {

            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    public static boolean writeByBufferedWriter(String content,String fileName){
        try {
            File file= new File(fileName);

            if (!file.exists()){
                file.createNewFile();
            }
//         true时允许追加
 //           FileWriter fileWriter=new FileWriter(file,true);
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
