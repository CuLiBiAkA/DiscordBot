package bot.config;

public enum Сommand {

    IZADANYCOM("язадаником"),
    STATA("!статистика"),
    CLEAN("!сброс"),
    WINNER("!кастомка+"),
    FAIL("!кастомка-"),
    CRING("!кринж"),
    BADJOKE("!шутка-"),
    GOODJOKE("!шутка+"),
    RANG("!ранг"),
    EBALMAMKU("!мамкаеб"),

    OHUEVSHAIA("!охуевшая"),

    POOK("!засранец");

    private String comand;

    Сommand(String command) {
        this.comand = command;
    }

    public String getCommand() {
        return comand;
    }
}
