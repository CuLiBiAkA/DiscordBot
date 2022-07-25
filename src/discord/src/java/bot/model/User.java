package bot.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Data
@Entity
@Table(name = "discord_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private Long serverId;
    private String username;
    private String userServerName;
    private Long countMassageOnServer;
//    private Long countUpdateMassageInServer;
    private Long ballCringe;
    private Long ballBayan;
    private Long ballBadJoke;
    private Long ballGoodJoke;
    private Long ballWinnerCustomGame;
    private Long ballFailCustomerGame;
    private Long ballFuckYourMam;
    private String pogonialo;
    private String urlPhotoProfile;
    private String ranked;
    private Long countPook;
    private Long respect;

    @Override
    public String toString() {
        return "Расклад по тебе такой " +
                "," +
                "твое имя  " + username +
                "\n" +
                "уважение : " + respect +
                "\n" +
                "твоя кликуха на сервере  " + userServerName +
                "\n" +
                "количество сообщений на сервере : " + countMassageOnServer +
                "\n" +
                "балы за кринж : " + ballCringe +
                "\n" +
                "балы за баян : " + ballBayan +
                "\n" +
                "плохих шуток  : " + ballBadJoke +
                "\n" +
                "респект за шутки : " + ballGoodJoke +
                "\n" +
                "смачных пердежей : " + + countPook +
                "\n" +
                "сколько раз победил в кастомке : " + ballWinnerCustomGame +
                "\n" +
                "сколько раз проиграл в кастомке : " + ballFailCustomerGame +
                "\n" +
                "сколько мамок отжарил : " + ballFuckYourMam +
                "\n" +
                "кликуха по жизни : " + pogonialo +
                "\n" +
                "ты по жизни : " + ranked +
                "\n" +
                "\n" +
                     urlPhotoProfile;
    }
}