package io.filestorm.api;

import io.filestorm.api.utils.AESUtils;
import io.filestorm.api.utils.FileUtils;
import org.storm3j.crypto.Credentials;
import org.storm3j.crypto.ECKeyPair;

import java.io.File;

public class Encrypt {
    public  String createEncryptFile(File file,String path,Fstorm fstorm) throws Exception {
        String s = FileUtils.encodeHexFile(file);
        Credentials credentials = fstorm.getCredentials();
        ECKeyPair ecKeyPair = credentials.getEcKeyPair();
        String privateKey = ecKeyPair.getPrivateKey().toString(16);
        String encrypt = AESUtils.encrypt(s,privateKey);
        FileUtils.toFile(encrypt.getBytes(),path);
        return path;
    }
    public String decoderFile(File file ,String path,Fstorm fstorm) throws Exception {
        String content = FileUtils.encodeStringFile(file);
        Credentials credentials = fstorm.getCredentials();
        ECKeyPair ecKeyPair = credentials.getEcKeyPair();
        String privateKey = ecKeyPair.getPrivateKey().toString(16);
        String s3 = AESUtils.decrypt(content, privateKey);
        FileUtils.toFile( FileUtils.hexStringToByte(s3),path);
        return path;
    }
}
