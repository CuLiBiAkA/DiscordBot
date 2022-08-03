package bot.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
    private Long countUpdateMassageInServer;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user1 = (User) o;
        return Objects.equals(id, user1.id) && Objects.equals(serverId, user1.serverId) && Objects.equals(username, user1.username) && Objects.equals(userServerName, user1.userServerName) && Objects.equals(countMassageOnServer, user1.countMassageOnServer) && Objects.equals(countUpdateMassageInServer, user1.countUpdateMassageInServer) && Objects.equals(ballCringe, user1.ballCringe) && Objects.equals(ballBayan, user1.ballBayan) && Objects.equals(ballBadJoke, user1.ballBadJoke) && Objects.equals(ballGoodJoke, user1.ballGoodJoke) && Objects.equals(ballWinnerCustomGame, user1.ballWinnerCustomGame) && Objects.equals(ballFailCustomerGame, user1.ballFailCustomerGame) && Objects.equals(ballFuckYourMam, user1.ballFuckYourMam) && Objects.equals(pogonialo, user1.pogonialo) && Objects.equals(urlPhotoProfile, user1.urlPhotoProfile) && Objects.equals(ranked, user1.ranked) && Objects.equals(countPook, user1.countPook) && Objects.equals(respect, user1.respect);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, serverId, username, userServerName, countMassageOnServer, countUpdateMassageInServer, ballCringe, ballBayan, ballBadJoke, ballGoodJoke, ballWinnerCustomGame, ballFailCustomerGame, ballFuckYourMam, pogonialo, urlPhotoProfile, ranked, countPook, respect);
    }


}