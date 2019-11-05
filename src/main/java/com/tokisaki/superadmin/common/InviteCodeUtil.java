package com.tokisaki.superadmin.common;

import java.util.Random;
import java.util.UUID;

public class InviteCodeUtil {

    /** 自定义进制(0,1没有加入,容易与o,l混淆) */  
    private static final char[] r = new char[]{'q', 'w', 'e', '8', 'a', 's', '2', 'd', 'z',  
        'x', '9', 'c', '7', 'p', '5', 'i', 'k', '3', 'm', 'j', 'u', 'f', 'r', '4', 'v', 'y',   
        'l', 't', 'n', '6', 'b', 'g', 'h'};  
    private static final char b='o'; /** (不能与自定义进制有重复) */  
    private static final int binLen = r.length;/** 进制长度 */  
    private static final int s =8;/** 序列最小长度 */  
    /** 
     * 更加id 生产6为随机码 
     * @param id 
     * @return 
     */  
    public static String toSerialCode(long id){  
        char[] buf = new char[32];  
        int charPos = 32;  
        while((id/binLen)>0){  
            int intid = (int) (id%binLen);  
            buf[--charPos] = r[intid];  
            id/=binLen;  
        }  
        String str = new String(buf,charPos,(32-charPos));  
        //不够长度的自动随机补全  
        if(str.length()<s){  
            StringBuilder sb = new StringBuilder();  
            sb.append(b);  
            Random random = new Random();  
            for(int i=1;i<s-str.length();i++){  
                sb.append(r[random.nextInt(binLen)]);  
            }  
            str+=sb.toString();  
        }  
        return str;  
    }  
    public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f",  
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",  
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",  
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",  
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",  
            "W", "X", "Y", "Z" };  
  
  
public static String generateShortUuid() {  
    StringBuffer shortBuffer = new StringBuffer();  
    String uuid = UUID.randomUUID().toString().replace("-", "");  
    for (int i = 0; i < 8; i++) {  
        String str = uuid.substring(i * 4, i * 4 + 4);  
        int x = Integer.parseInt(str, 16);  
        shortBuffer.append(chars[x % 0x3E]);  
    }  
    return shortBuffer.toString();  
  
}  
 
}
