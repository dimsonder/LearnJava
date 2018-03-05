import java.io.*;

/**
 * @program: FileIO
 * @description: 文件IO工具类
 * @author: cherubim-qq0313
 * @create: 2018-03-05 21:53
 **/
public class FileIOUtils {

    /**
     * 文件读写，分别介绍几种组合方式，供参考
     * <p>
     * 一般用BufferedReader和BufferedWriter，使用它们的一般流程是，先创建File对象（可以对路径和文件进行更多的操作），
     * 然后通过File对象创建FileReader（FileWriter）【当然在这里也可以通过创建InputStreamReader（new InputStream）来获取reader对象，看个人爱好】
     * 然后通过FileReader（FileWriter）获得BufferedReader（BufferedWriter）对象。对于读入文件，还可以使用Scanner对读取的文件进行操作，这个比较方便
     * Scanner也是处理流，因此也是需要节点流作为其参数的。
     */

    //第一种

    //文件读取:通过BufferedReader读取数据
    public static String readByBufferedReader(String fileName) {
        StringBuffer sb = new StringBuffer();
        try {
            File file = new File(fileName);
            // 读取文件，并且以utf-8的形式写出去
            BufferedReader bufread;
            bufread = new BufferedReader(new FileReader(file));
            String read;
            while ((read = bufread.readLine()) != null) {
                sb.append(read);
            }
            bufread.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return sb.toString();
    }

    // 采用BufferedWriter向文件中写数据
    public static boolean writeByBufferedWriter(String content, String fileName) {
        try {
            File file = new File(fileName);

            if (!file.exists()) {
                file.createNewFile();
            }
            // true时允许追加
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

    //第二种

    // 节点流FileInputStream读取字节流
    public static String readFileByBytes(String fileName) {
        // 一般先创建file对象
        FileInputStream fileInput = null;

        //写入字符串
        String content = null;
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            byte[] buffer = new byte[1024];
            fileInput = new FileInputStream(file);
            int byteRead = 0;
            // byteread表示一次读取到buffers中的数量。
            while ((byteRead = fileInput.read(buffer)) != -1) {
                System.out.write(buffer, 0, byteRead);
                content = new String(buffer, 0, byteRead);
            }

        } catch (FileNotFoundException e) {
            // TODO: handle exception
            System.err.println("系统找不到指定的文件");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                if (fileInput != null) {
                    fileInput.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return content;
    }

    // 字节流FileOutputStream写入数据
    public void writeByFileOutputStream() {

        FileOutputStream fop = null;
        File file;
        String content = "This is the text content";
        try {
            file = new File("c:/newfile.txt");
            fop = new FileOutputStream(file);
            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
            // get the content in bytes
            byte[] contentInBytes = content.getBytes();

            fop.write(contentInBytes);
            fop.flush();
            fop.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fop != null) {
                    fop.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //第三种

    // 节点流FileReader读取字符流

    public static String readFileByChars(String fileName) {

        // 其实FileReader也是需要FileInputStream对象转换的，要借助于InputStreamReader转换流。
        FileReader reader = null;

        //写入字符串
        String content = null;
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
        } catch (FileNotFoundException e) {
            // TODO: handle exception
            System.err.println("系统找不到指定的文件");
        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();

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
        return content;
    }

    // FileWriter写入数据
    public static void writeByFileWriter() {
        try {
            String data = " This content will append to the end of the file";

            File file = new File("javaio-appendfile.txt");
            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            // 注意使用FileWriter("fileName"，true）可以往文件后面追加内容，否则就直接覆盖了
            // true = append file
            FileWriter fileWritter = new FileWriter(file.getName(), true);
            fileWritter.write(data);
            fileWritter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
