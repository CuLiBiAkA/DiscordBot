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

    @Override
    public String toString() {
        return "Расклад по тебе такой " +
                "," +
                " твое имя = " + username +
                "," +
                " Твоя кликуха на сервере = " + userServerName +
                "," +
                " количество сообщений на сервере = " + countMassageOnServer +
                "," +
                " балы за кринж = " + ballCringe +
                "," +
                " балы за баян = " + ballBayan +
                "," +
                " плохих шуток  = " + ballBadJoke +
                "," +
                " респект за шутки = " + ballGoodJoke +
                "," +
                "  сколько раз победил в кастомке = " + ballWinnerCustomGame +
                "," +
                " сколько раз проиграл в кастомке = " + ballFailCustomerGame +
                "," +
                " сколько мамок отжарил = " + ballFuckYourMam +
                "," +
                " кликуха по жизни = " + pogonialo +
                "," +
                " ты по жизни = " + ranked +
                "," +
                " смачных пердежей = " + + countPook +
                     urlPhotoProfile;
    }
}