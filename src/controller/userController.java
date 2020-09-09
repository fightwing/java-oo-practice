package controller;

import com.twu.Main;
import po.Event;
import po.User;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author Boyu Yuan
 * @date 2020/9/9 12:52
 */
public class userController {

    public static void userAction(List<Event> eventList){

        System.out.println("来者何人，可否留下姓名？");
        Scanner in = new Scanner(System.in);
        String name = in.nextLine();
        User user = new User(name);
        userOperation(eventList,user);

    }

    public static void userOperation(List<Event> eventList,User user) {
        Scanner in = new Scanner(System.in);
        if (eventList.size() != 0 && eventList != null) {
            printUserInfo(user.getName(), true);
            int choose = in.nextInt();
            switch (choose) {
                case 1:
                    showEventList(eventList);
                    userOperation(eventList,user);
                    break;
                case 2:
                    voteForEvent(eventList, user);
                    userOperation(eventList,user);
                    break;
                case 3:
                    buyEvent(eventList,user);
                    userOperation(eventList,user);
                    break;
                case 4:
                    addEvent(eventList);
                    userOperation(eventList,user);
                    break;
                case 5:
                    Main.chooseIdentity(eventList);
                    break;
            }
        } else {
            printUserInfo(user.getName(), false);
            int choose = in.nextInt();
            switch (choose) {
                case 1:
                    addEvent(eventList);
                    userOperation(eventList,user);
                    break;
                case 2:
                    Main.chooseIdentity(eventList);
                    break;
            }
        }
    }


    public static void printUserInfo(String name, boolean flag) {
        if (flag) {
            System.out.println("你好，"+name+"，你可以：\n1.查看热搜排行榜\n2.给热搜事件投票\n3.购买热搜\n4.添加热搜\n5.退出");
        }else {
            System.out.println("你好，"+name+"，暂无热搜数据，你可以：\n1.添加热搜\n2.退出");
        }
    }

    public static void showEventList(List<Event> eventList){
        List<Event> sortedEventList = sortEvent(eventList);
        for (int i = 0; i < sortedEventList.size(); i++) {
            System.out.println(sortedEventList.get(i).getRank()+ " " + sortedEventList.get(i).getName() + " " + sortedEventList.get(i).getHeat());
        }

    }

    public static void voteForEvent(List<Event> eventList,User user){
        System.out.println("请输入你要投票的热搜名字：");
        Scanner in = new Scanner(System.in);
        String name = in.nextLine();
        List<Event> targetEvent = eventList.stream().filter(event -> event.getName().equalsIgnoreCase(name)).collect(Collectors.toList());
        System.out.println("请输入你要投的票数（目前还剩"+ user.getVote() +"票）：");
        int vote = in.nextInt();
        if (targetEvent.get(0).isSupperHot()){
            targetEvent.get(0).setHeat(vote * 2);
        }else {
            targetEvent.get(0).setHeat(vote);
        }
        user.setVote(user.getVote()-vote);
        System.out.println("投票成功");

    }
    public static void buyEvent(List<Event> eventList,User user){
        Scanner in = new Scanner(System.in);
        System.out.println("请输入要购买的热搜排名：");
        int rank = in.nextInt();
        // 接收 \n
        String enter = in.nextLine();
        System.out.println("请输入热搜名字：");
        String name = in.nextLine();
        System.out.println("请输入出价(现剩余钱数："+ user.getMoney() +")：");
        int price = in.nextInt();
        List<Event> targetEvent = eventList.stream().filter(event -> event.getRank() == rank).collect(Collectors.toList());
        if (targetEvent.get(0).getPrice() < price){
            targetEvent.get(0).setName(name);
            targetEvent.get(0).setPrice(price);
            targetEvent.get(0).setHeat(0);
            user.setMoney(user.getMoney()-price);
            System.out.println("购买成功");
        }else {
            System.out.println("您的出价不够哦");
            userOperation(eventList,user);
        }
    }
    public static void addEvent(List<Event> eventList){
        System.out.println("请输入热搜名字：");
        Scanner in = new Scanner(System.in);
        String name = in.nextLine();
        Event event = new Event(name);
        event.setRank(eventList.size()+1);
        eventList.add(event);
        System.out.println("添加成功");
    }
    public static List<Event> sortEvent(List<Event> eventList){
        //购买的热搜
        List<Event> purchasedEvent = eventList.stream().filter(event -> event.getPrice() > 0).collect(Collectors.toList());
        //未购买的热搜
        List<Event> unPurchasedEvent = eventList.stream().filter(event -> event.getPrice() == 0).collect(Collectors.toList());
        //对未被购买的按热度排序
        List<Event> sortedEventList = unPurchasedEvent.stream().sorted(Comparator.comparing(Event::getHeat).reversed()).collect(Collectors.toList());
        for(int i = 0; i < purchasedEvent.size(); i++){
            sortedEventList.add(purchasedEvent.get(i).getRank()-1,purchasedEvent.get(i));
        }
        sortedEventList.stream().forEach(event -> event.setRank(sortedEventList.indexOf(event)+1));
        return sortedEventList;
    }



}
