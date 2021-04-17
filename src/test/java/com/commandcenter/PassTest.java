package com.commandcenter;

import com.commandcenter.common.utils.DateUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Date;

/**
 * @author r25437
 * @create 2018-08-02 14:50
 * @desc 测试
 **/
public class PassTest {
    public static void main(String[] args){
        /*
        System.out.println(new Sha256Hash("123456", "admin").toHex());
        System.out.println(TokenGenerator.generateValue());
        System.out.println("politicsTop.jpg".split("\\.")[1]);
        String aa = "201809990087654";
        System.out.println("hashcode = "+aa.hashCode());
        */
        String dataStr = "2018-12-25 19:51:47.727";

        Date date = DateUtil.parse(dataStr,"yyyy-MM-dd HH:mm:ss");
        System.out.println(date);
        try {
            File file = new File("F:\\360Downloads\\consumeTimeLog-2018-12-24.0.log");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String str2 = br.readLine();
            while(str2 != null) {
                String[] str = str2.split(" ");
                for(String line : str){
//                    System.out.println("line =========== " +line+",--------------- linestr======" + str2);
                }
//                System.out.println(str[3] + "," + str[str.length - 1]);
                str2 = br.readLine();
            }
            br.close();
            fr.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
