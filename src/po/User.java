package po;

/**
 * @author Boyu Yuan
 * @date 2020/9/9 13:38
 */
public class User {
    String name ;
    int money = 100 ;
    int vote =10 ;


    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }
}
