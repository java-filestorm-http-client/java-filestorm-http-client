package io.filestorm.api;

import io.ipfs.multibase.*;
import io.ipfs.multihash.Multihash;
import org.junit.*;
import java.io.*;
import java.net.*;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class AddTest {
    @Test
    public void test() throws InterruptedException, ExecutionException, IOException {
        for (int i = 0; i <11 ; i++) {
            Fstorm fstorm = new Fstorm("/ip4/127.0.0.1/tcp/5001","http://stormchain.icode.link","0xb5a369b10ce738b129d1515d95ed09308d72eba35890bb5b69e953b826275a40");
            NamedStreamable.FileWrapper savefile = new NamedStreamable.FileWrapper(new File("D:\\Downloads\\sstap.zip"));
            String result = fstorm.add(savefile,false);
            System.out.println(result);
        }

    }
    @Test
    public void testRm() throws InterruptedException, ExecutionException, IOException {
        Fstorm fstorm = new Fstorm("/ip4/127.0.0.1/tcp/5001","http://stormchain.icode.link","0xb5a369b10ce738b129d1515d95ed09308d72eba35890bb5b69e953b826275a40");
        Fstorm.Pin pin = fstorm.pin;
        MerkleNode merkleNode1 = new MerkleNode("QmNvmLU5BgUjXhG59ywziURdcXgGXqyYaChf5S7diVghuS");
        Multihash hash = merkleNode1.hash;
        List<Multihash> rm = pin.rm(hash);
        System.out.println(rm.get(0));
    }
    @Test
    public void testPinAdd() throws InterruptedException, ExecutionException, IOException {
        Fstorm fstorm = new Fstorm("/ip4/127.0.0.1/tcp/5001","http://stormchain.icode.link","0xb5a369b10ce738b129d1515d95ed09308d72eba35890bb5b69e953b826275a40");

        Fstorm.Pin pin = fstorm.pin;
        MerkleNode merkleNode1 = new MerkleNode("QmQc3UdKHSDPmrQQyZXcnJecG7GC34QCVAYjaRqe5rsUBw");
        Multihash hash = merkleNode1.hash;
        List<Multihash> add = pin.add(hash);
        System.out.println(add.get(0));
    }
//    @Test
//    public void testFile(){
//        String s = FileUtils.readFileByLines("a.txt");
//        System.out.println(s);
//        System.out.println(s.substring(0,s.length()));
//
//    }
}
