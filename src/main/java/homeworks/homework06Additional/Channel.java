package homeworks.homework06Additional;

import homeworks.homework05.Utils;

import java.util.Objects;

public class Channel {
    private String name;
    private int no;
    private Programm programm;

    public Channel(String name, int no, Programm programm) {
        this.name = name;
        this.no = no;
        this.programm = programm;
    }

    public Channel() {
        this(generateRandomName(), Utils.getRandomInt(), new Programm());
    }

    private static String generateRandomName() {
        return "Канал " + Utils.getRandomInt();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Programm getProgramm() {
        return programm;
    }

    public void setProgramm(Programm programm) {
        this.programm = programm;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "name='" + name + '\'' +
                ", no=" + no +
                ", programm=" + programm +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Channel channel)) return false;
        return getNo() == channel.getNo() && Objects.equals(getName(), channel.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getNo());
    }
}
