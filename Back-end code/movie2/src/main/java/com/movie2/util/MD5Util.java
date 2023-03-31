package com.movie2.util;

import java.security.MessageDigest;

public class MD5Util {
    //有关索引的大小，与二维数组的大小，可以使用配置文件（xml文件配置），让用户自行配置处理
    public  static  final  int TOTAL = 10;
    private static char[][] hexDigits = {//集合中的字母随便写,类似于第8行
            { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' },
            { '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', '0', '1', '2', '3', '4', '5', '6' },
            { '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', '0', '1', '2', '3' },
            { '0', '1', '2', '3', '4', '5', 'a', 'b', 'c', 'd', 'e', 'f', '6', '7', '8', '9' },
            { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' },
            { 'a', 'b', 'c', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'd', 'e', 'f' },
            { '0', '1', '2', '3', 'b', 'c', 'd', '4', '5', '6', '7', '8', '9', 'a', 'e', 'f' },
            { 'g', 'i', 'h', 'e', 'f', 'z', 'x', 'o', 'l', 'm', '@', '!', '-', 'a', 'b', 'c' },
            { '0', '1', '2', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', '3', '4', '5', '6' },
            { '6', '7', '8', '9', 'a', '0', '1', '2', '3', '4', '5', 'b', 'c', 'd', 'e', 'f' }
    };

    /**
     *
     * 密码都是123456
     * 盐：aaaa
     * index ：2
     * 加密后：0EC07ABAB52629E7DDED3F7EEB1A26F7
     * @param s 明文的密码
     * @param salt 盐
     * @param index 加密后显示的字符数组
     * @return 加密后的密码
     */
    public final static String md5To32String(String s,String salt,int index){
        //修改字符集，可以修改生成的md5的串，自己可以替换顺序，用于简单的加密
        if(index>=hexDigits.length){
            throw new RuntimeException("Md5加密的索引范围是（0-"+(hexDigits.length-1)+")");
        }
        try
        {
            //加盐
//            s = salt + s + salt;//加盐的方式
//            byte[] strTemp = s.getBytes();//转换为字节数组

            int length = s.length()>salt.length()?salt.length():s.length();//获得短的
            StringBuffer sb = new StringBuffer();
            char[] pwdChar = s.toCharArray();
            char[] saltChar = s.toCharArray();

            for (int i = 0; i <length ; i++) {
                sb.append(pwdChar[i]);
                sb.append(saltChar[i]);
            }
            byte[] strTemp = sb.toString().getBytes();

            //https://blog.csdn.net/hudashi/article/details/8394158
            //https://www.cnblogs.com/zt007/p/5945733.html
            //https://www.cnblogs.com/xiaowangxiao/p/10961384.html  md5加盐处理
            //https://blog.csdn.net/T_james/article/details/79528085 加盐
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];//处理字符串的长度是32位的
            int k = 0;
            for (int i = 0; i < j; i++)
            {
                byte byte0 = md[i];
                str[k++] = hexDigits[index][byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[index][byte0 & 0xf];
            }
            String result = new String(str).toUpperCase();
           // System.out.println(result);
            //转换为大写字母
            return result;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(md5To32String("123456","",0));
    }
}
