package com.twu;

import controller.adminController;
import controller.userController;
import po.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        List<Event> eventList = new ArrayList<>();
        chooseIdentity(eventList);
    }

    public static void chooseIdentity(List<Event> eventList){
        printInfo();
        adminController ac = new adminController();
        userController uc = new userController();
        Scanner in = new Scanner(System.in);
        int identity = in.nextInt();
        switch (identity){
            case 1 :
                uc.userAction(eventList);
                break;
            case 2 :
                ac.adminAction(eventList);
                break;
            case 0 :
                break;

        };

    }

    public static void printInfo(){
        System.out.println("欢迎来到热搜排行榜！\n 请选择身份: \n 1.普通用户 \n 2.管理员 \n 0.退出");
    }
}
