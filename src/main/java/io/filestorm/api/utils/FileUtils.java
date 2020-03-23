package io.filestorm.api.utils;
import java.io.*;
import java.math.BigInteger;

public class FileUtils {
    /**
     *
     * @param file 读取文件
     * @return  返回十六进制字符串
     * @throws IOException  文件读取异常
     */
    public static String encodeHexFile(File file) throws IOException {
        FileInputStream inputFile = new FileInputStream(file);
        byte[] buffer = new byte[(int) file.length()];
        inputFile.read(buffer);
        inputFile.close();
        return  new BigInteger(1, buffer).toString(16);
    }

    /**
     *
     * @param file  读取文件
     * @return  返回字符串
     * @throws IOException  文件读取异常
     */
    public static String encodeStringFile(File file) throws IOException {
        FileInputStream inputFile = new FileInputStream(file);
        byte[] buffer = new byte[(int) file.length()];
        inputFile.read(buffer);
        inputFile.close();
        return new String(buffer, "utf-8");
    }

    /**
     * 十六进制字符串转字符串
     * @param s    输入十六进制字符串
     * @return  返回字符串
     */
    public static String hexStringToString(String s) {
        if (s == null || s.equals("")) {
            return null;
        }
        s = s.replace(" ", "");
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            s = new String(baKeyword, "UTF-8");
            new String();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return s;
    }

    /**
     *  十六进制字符串转字节数组
     * @param hex   十六进制字符串
     * @return  返回字节数组
     */
    public static byte[] hexStringToByte(String hex) {
        byte[] b = new byte[hex.length() / 2];
        int j = 0;
        for (int i = 0; i < b.length; i++) {
            char c0 = hex.charAt(j++);
            char c1 = hex.charAt(j++);
            b[i] = (byte) ((parse(c0) << 4) | parse(c1));
        }
        return b;
    }

    /**
     *
     * @param c char c
     * @return  返回int
     */
    private static int parse(char c) {
        if (c >= 'a')
            return (c - 'a' + 10) & 0x0f;
        if (c >= 'A')
            return (c - 'A' + 10) & 0x0f;
        return (c - '0') & 0x0f;

    }


    /**
     * 字节数组写入文件
     * @param hexCode 字节数组
     * @param targetPath    目标路劲
     * @throws IOException 写入文件异常
     */
    public static void toFile(byte[] hexCode, String targetPath) throws IOException {
        FileOutputStream out = new FileOutputStream(targetPath);
        out.write(hexCode);
        out.close();
    }


    /**
     * 文件数据写入（如果文件夹和文件不存在，则先创建，再写入）
     *
     * @param filePath  文件路劲
     * @param content   内容
     * @param flag     true:如果文件存在且存在内容，则内容换行追加；false:如果文件存在且存在内容，则内容替换
     */
    public static String fileLinesWrite(String filePath, String content, boolean flag) {
        String filedo = "write";
        FileWriter fw = null;
        try {
            File file = new File(filePath);
            //如果文件夹不存在，则创建文件夹
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {//如果文件不存在，则创建文件,写入第一行内容
                file.createNewFile();
                fw = new FileWriter(file);
                filedo = "create";
            } else {//如果文件存在,则追加或替换内容
                fw = new FileWriter(file, flag);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fw);
        pw.println(content);
        pw.flush();
        try {
            fw.flush();
            pw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filedo;
    }

}
