package controller;

import com.twu.Main;
import po.Admin;
import po.Event;

import java.util.List;
import java.util.Scanner;

/**
 * @author Boyu Yuan
 * @date 2020/9/9 12:52
 */
public class adminController {

    public static void adminAction(List<Event> eventList){
        Scanner in = new Scanner(System.in);
        System.out.println("请输入管理员名字：");
        String name = in.nextLine();
        System.out.println("请输入密码：");
        String password = in.nextLine();
        Admin admin = new Admin();
        if (name.equalsIgnoreCase(admin.getAdminName()) && password.equalsIgnoreCase(admin.getAdminPassword())){
            System.out.println("欢迎管理员"+ admin.getAdminName() +"登录");
            adminOperation(eventList,admin);
        }else {
            System.out.println("账号或密码错误");
            Main.chooseIdentity(eventList);
        }
    }

    public static void adminOperation(List<Event> eventList ,Admin admin){
        printAdminInfo(admin.getAdminName());
        Scanner in = new Scanner(System.in);
        int choose = in.nextInt();
        switch (choose){
            case 1:
                userController.showEventList(eventList);
                adminOperation(eventList,admin);
                break;
            case 2:
                userController.addEvent(eventList);
                adminOperation(eventList,admin);
                break;
            case 3:
                addSupperHotEvent(eventList);
                adminOperation(eventList,admin);
                break;
            case 4:
                Main.chooseIdentity(eventList);
                break;
        }
    }

    public static void printAdminInfo(String name){
            System.out.println("你好，"+ name +"，你可以：\n 1.查看热搜排行榜 \n2.添加热搜 \n3.添加超级热搜 \n4.退出");
    }

    public static void addSupperHotEvent(List<Event> eventList){
        System.out.println("请输入要添加的超级热搜名字：");
        Scanner in = new Scanner(System.in);
        String name = in.nextLine();
        Event event = new Event(name);
        event.setSupperHot(true);
        event.setRank(eventList.size()+1);
        eventList.add(event);
        System.out.println("添加成功");
    }
}
