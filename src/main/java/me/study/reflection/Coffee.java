package me.study.reflection;

public class Coffee {

    private static String SWEET_TASTE = "단맛";
    private static final String BITTER_TASTE = "쓴맛";

    public String name = "아이스 아메리카노";
    protected String temperature = "차가움";
    private String smell = "좋음";

    public Coffee() {
    }

    public Coffee(String name, String temperature, String smell) {
        this.name = name;
        this.temperature = temperature;
        this.smell = smell;
    }

    private void extract() {
        System.out.println("커피가 추출된다.");
    }

    public void make() {
        System.out.println("커피가 만들어 진다.");
    }

    public int pay() {
        System.out.println("2000원 입니다.");
        return 100;
    }

}
