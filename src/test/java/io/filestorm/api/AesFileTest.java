package io.filestorm.api;

import io.filestorm.api.utils.AESUtils;
import io.filestorm.api.utils.FileUtils;
import org.junit.Test;

import java.io.File;

public class AesFileTest {
    Fstorm fstorm = new Fstorm("/ip4/127.0.0.1/tcp/5001","http://stormchain.icode.link","0xb5a369b10ce738b129d1515d95ed09308d72eba35890bb5b69e953b826275a40");
    Encrypt encrypt1 = new Encrypt();

    @Test
    public void test1() throws Exception {
        File file = new File("D:\\Downloads\\d6cbea167647bfcc0e1c60071d4088a.jpeg");
        encrypt1.createEncryptFile(file,"D:\\Downloads\\test.jpeg",fstorm);
    }
    @Test
    public void test2() throws Exception {
        File file = new File("D:\\Downloads\\test.jpeg");
        encrypt1.decoderFile(file,"D:\\Downloads\\success.jpeg",fstorm);
    }
}
