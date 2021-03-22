/**
 * Loh Fah Yao Jaryl A0202023H
 */

public class Player {
    private int uid;
    private int state;

    public Player(int uid, int state) {
        this.uid = uid;
        this.state = state;
    }

    public int getUID() {
        return this.uid;
    }

    public void setUID(int uid) {
        this.uid = uid;
    }

    public int getState() {
        return this.state;
    }

    public void setState(int state) {
        this.state = state;
    }
}