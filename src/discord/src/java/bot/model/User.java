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
}