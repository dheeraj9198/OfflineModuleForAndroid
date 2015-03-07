import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * Created with IntelliJ IDEA.
 * User: windows 7
 * Date: 3/7/15
 * Time: 5:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class MD5 {

    public  static void main(String[] args) throws Exception{
       // 0480aa34aa3db358b37cde2ab6b65326
        String password = "Thisisatest";

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());

        byte byteData[] = md.digest();

        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        System.out.println("Digest(in hex format):: " + sb.toString());
    }



}
