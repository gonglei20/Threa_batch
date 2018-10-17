package com.gonglei;

import com.gonglei.domain.User;
import com.gonglei.utils.ListUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 需求：某电商拥有10w+用户，请给这些用户一一发送祝福短信。
 * 数据量过大，仅用于模拟
 */
class userThread extends Thread{
    private List<User> list;
    
    public userThread(List<User> list) {
        this.list = list;
    }

    @Override
    public void run() {
        for (User user : list) {
            System.out.println("threadName:"+Thread.currentThread().getName()
                    +"用户id："+user.getUserId()+"用户名："+user.getUsername());

           //调用第三方短信api
        }
    }
}
public class Demo {

    public static void main(String[] args) {
        //1.初始化用户数据
        List<User> listUser = initUser();
        //2.计算创建创建多少个线程并且每一个线程需要执行“分批发送短信用户”
        //每一个线程分批跑多少
        int userCount = 2;
        //3.计算所有线程数
        List<List<User>> splitUserList  = ListUtils.splitList(listUser, userCount);
        for (int i = 0; i < splitUserList.size(); i++) {
            List<User> users = splitUserList.get(i);
            Thread thread = new userThread(users);
            thread.start();
        }
    }

    /**
     * 初始化用户数据（用于模拟）
     * @return
     */
    public static List<User> initUser(){
        List<User> listUser = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            listUser.add(new User("id"+i,"name"+i));
        }
        return listUser;
    }
}
