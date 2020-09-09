package po;

/**
 * @author Boyu Yuan
 * @date 2020/9/9 12:34
 */
public class Event {
    String name;
    int heat = 0;
    int price = 0;
    int rank = 0;
    boolean isSupperHot = false;

    public Event(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeat() {
        return heat;
    }

    public void setHeat(int heat) {
        this.heat = heat;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public boolean isSupperHot() {
        return isSupperHot;
    }

    public void setSupperHot(boolean supperHot) {
        isSupperHot = supperHot;
    }
}
