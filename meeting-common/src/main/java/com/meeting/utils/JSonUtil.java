package com.meeting.utils;

import com.alibaba.fastjson.JSON;

import com.meeting.pojo.User;

import java.io.*;

/**
 * User: FlowingFire
 * Date: 2017/9/30 0030
 * Time: 10:45
 * Description:
 */
public class JSonUtil {

    static String urlUser="user/";
    static String urlAgenda="agenda/";
    /**
     * @description
     * @method
     * @param object 存储对象
     * @return
     * @date: 2017年09月30日 10:51:48
     * @author:刘烨
     */
    public static void saveUser(User object){
        String s = JSON.toJSONString(object);
        File file = new File(urlUser + object.geterId() + ".txt");

        if (!file.getParentFile().exists()) {
            boolean result = file.getParentFile().mkdirs();
            if (!result) {
                System.out.println("创建失败");
            }
        }
        System.out.println(file.getAbsoluteFile());
        try {
        Writer w=new FileWriter(file);

        BufferedWriter buffWriter=new BufferedWriter(w);
        buffWriter.write(s);

        buffWriter.close();
        w.close();
        } catch (FileNotFoundException e) {
            System.out.println("要读取的文件不存在："+e.getMessage());
        } catch (IOException e) {
            System.out.println("文件读取错误："+e.getMessage());
        }
       // System.out.println("写入成功！");
    }


    /**
     * @description
     * @method
     * @param name 读取对象
     * @return
     * @date: 2017年09月30日 10:51:48
     * @author:刘烨
     */
    public static User loadUser(String name){
        User user=null;
        try {
            File file = new File(urlUser + name + ".txt");





            System.out.println(file.getAbsoluteFile());
            Reader r=new FileReader(file);
            BufferedReader buffReader=new BufferedReader(r);

            String s1 = buffReader.readLine();
            System.out.println(s1);
            user=JSON.parseObject(s1, User.class);
            buffReader.close();
            r.close();
        } catch (FileNotFoundException e) {
            System.out.println("要读取的文件不存在："+e.getMessage());
        } catch (IOException e) {
            System.out.println("文件读取错误："+e.getMessage());
        }
        //System.out.println("写入成功！");

        return user;
    }



}
