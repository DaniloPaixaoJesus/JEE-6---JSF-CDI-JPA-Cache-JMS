package br.com.livrariaonline.loja.security;

import java.security.MessageDigest;
import org.jboss.security.Base64Encoder;


public class PassGenerator {
	
	//pmWkWSBCL51Bfkhn79xPuKBKHz//H6B+mY6G9/eieuM=
	public static void main(String[] args) {
        System.out.println(new PassGenerator().generate("123"));
    }

	public String generate(String senhaTexto) {

        try {
        	
            byte[] digest = MessageDigest.getInstance("sha-256").digest(senhaTexto.getBytes());
            //return "";
            return Base64Encoder.encode(digest);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
	
}
