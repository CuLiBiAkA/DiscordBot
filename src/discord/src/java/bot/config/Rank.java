package bot.config;

public enum Rank {

    LOH("ЛОХ"),
    CERT("ЧЁРТ"),
    JOKER("ШУТНИК"),
    CHEPUSHILA("ЧЕПУШИЛА"),
    VAFLER("ВАФЛЕР"),
    KILLER("МУЖЧИНА"),
    MAMKAEB("МАМКАЕБ"),
    GAY("ЧУВАК"),
    FRAER("ФРАЕР"),
    HUILO("ХУЙЛО"),
    SLADKAIYAPOPKA("СЛАДЕНЬКИЙ"),
    ROVNYI("РОВНЫЙ ПОЦ");

    private String rank;
    Rank(String rank){
        this.rank = rank;
    }
    public String getRank(){
        return rank;
    }
}
