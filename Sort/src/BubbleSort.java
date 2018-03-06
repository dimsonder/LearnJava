import java.io.*;
import java.util.Arrays;

/**
 * @program: FileIO
 * @description: 冒泡排序
 * @author: cherubim-qq0313
 * @create: 2018-03-05 21:02
 **/
public class BubbleSort {
    public static void main(String[] args) {
        String path = "D:\\Java\\sources.list";

        //   String s = readFileByInputStream("D:\\Java\\sources.list");

        String s = FileIOUtils.readByBufferedReader(path);

        String[] strings = s.split(",");
        int[] arr = Arrays.stream(strings).mapToInt(Integer::valueOf).toArray();

        System.out.println("排序前读取的数组为：");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();

        for (int i = 0; i < arr.length - 1; i++)
            for (int j = 0; j < arr.length - i - 1; j++) {

                if (arr[j] > arr[j + 1]) {

                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }

        System.out.println("排序后的数组为：");
        for (int num : arr) {
            System.out.print(num + " ");

        }
    }

    /**
     * @描述：字节流的数据读取方式：每次读取一定长度的字节，建议使用
     * @创建时间：
     */
    public static String readFileByInputStream(String fileName) {

        String arr = null;
        FileInputStream fis = null;

        try {

            fis = new FileInputStream(fileName);
            // byte[] bs=new byte[1024*4];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int len = 0;
            while ((len = fis.read()) != -1) {
                baos.write(len);

            }

            arr = baos.toString();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("系统找不到指定的文件");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return arr;

    }

}
