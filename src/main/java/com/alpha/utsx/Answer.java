package com.alpha.utsx;

public class Answer {

    private final String base;
    private final String askedExchange;
    private final Double todayCost;
    private final Double yesterdayCost;
    private final String type;
    private final String url;
    private final String errorMsg;


    public Answer(String base, String askedExchange, Double todayCost, Double yesterdayCost, String type, String url, String errorMsg) {
        this.base = base;
        this.askedExchange = askedExchange;
        this.todayCost = todayCost;
        this.yesterdayCost = yesterdayCost;
        this.type = type;
        this.url = url;
        this.errorMsg = errorMsg;
    }

    public String getUrl() {
        return url;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "<br><br><br><br>{<br>" +
                "base='" + base + '\'' +
                ",<br> askedExchange='" + askedExchange + '\'' +
                ",<br> todayCost=" + todayCost +
                ",<br> yesterdayCost=" + yesterdayCost +
                ",<br> type='" + type + '\'' +
                "<br>}";
    }
}
