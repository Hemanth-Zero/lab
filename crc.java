package week3;
import java.util.*;


class crc{

    public static String longdiv(String msg,String key ){
        char[] b = msg.toCharArray();
        char[] a = key.toCharArray();
        int n = a.length;
        for(int i = 0; i<= b.length - n ; i++){
            if(b[i] == '1'){
                for(int j=0;j<n;j++){
                    b[i+j]= (b[i+j] == a[j] ) ? '0' : '1'; 
                }
            }
        }
        String rem = new String(b).substring(b.length - n+1);
        return rem;
    }
    public static void main(String[] args){
        String msg = "1101011011";
        String dmsg = msg;
        String key = "10011";
        int n = key.length();

        for(int i=0;i<n-1;i++) msg=msg+"0";

        String rem = longdiv(msg,key);
        String sent_msg = dmsg+rem;
        System.out.print("sent Msg :"+msg+" Key "+key+" rem "+rem+" sending msg :"+sent_msg+"\n");

        System.out.print("check msg "+sent_msg+" Key "+key+" rem "+longdiv(sent_msg,key)+"\n");
        
        int ridx = new Random().nextInt(sent_msg.length());
        char[] c = sent_msg.toCharArray();
        c[ridx] = (c[ridx] == '0')?'1':'0';
        String error_msg = new String(c);
        System.out.print("Error msg "+error_msg+" Key "+key+" rem "+longdiv(error_msg,key));
        
    }
}