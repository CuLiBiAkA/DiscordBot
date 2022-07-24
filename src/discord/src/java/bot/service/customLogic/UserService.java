package bot.service.customLogic;

import bot.config.Rank;
import bot.dao.UserDao;
import bot.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService {

    @Autowired
    UserDao userDao;

    public void saveUser(discord4j.core.object.entity.User user) {
        var userEntity1 = userDao.findByUsername(user.getUsername());
        if (userEntity1 == null) {
            User userEntity = new User();
            userEntity.setServerId(user.getId().asLong());
            userEntity.setUsername(user.getUsername());
            userEntity.setRanked(Rank.FRAER.getRank());
            userEntity.setUrlPhotoProfile(user.getAvatarUrl());
            userEntity.setUserServerName(user.getUserData().username());
            userEntity.setCountMassageOnServer(1L);
            userEntity.setBallCringe(0L);
            userEntity.setRespect(0L);
            userEntity.setBallBayan(0L);
            userEntity.setBallBadJoke(0L);
            userEntity.setBallGoodJoke(0L);
            userEntity.setBallWinnerCustomGame(0L);
            userEntity.setBallFailCustomerGame(0L);
            userEntity.setBallFuckYourMam(0L);
            userEntity.setPogonialo("");
            userEntity.setCountPook(0L);
            userDao.save(userEntity);
            log.info("Сохранен");
        } else {
            userEntity1.setCountMassageOnServer(userEntity1.getCountMassageOnServer() + 1L);

            userDao.save(userEntity1);
        }
    }

    public String switchContainer(discord4j.core.object.entity.User user, String content) {
        if (content != null) {
            var users = assistantToUserServerId(content);
            var userDb = userDao.findByUsername(user.getUsername());
            if (users.size() != 0 && users.stream().noneMatch(user1 -> user1.equals(userDb))) {
                return switchContainerForUserDB(content, users);
            }
            switch (content.toLowerCase()) {
                case ("!ранг"):
                    var userDB = userDao.findByUsername(user.getUsername());
                    return (user.getUsername() + " по жизни " + userDB.getRanked() + "\n" + "Уважение: " + userDB.getRespect());
                case ("!статистика"):
                    return userDao.findByUsername(user.getUsername()).toString();
                case ("!сброс"):
                    var userS = userDao.findByUsername(user.getUsername());
                    userS.setRespect(userS.getRespect()+userS.getBallWinnerCustomGame()+userS.getBallGoodJoke());
                    userS.setBallWinnerCustomGame(0L);
                    userS.setBallFuckYourMam(0L);
                    userS.setBallFailCustomerGame(0L);
                    userS.setBallGoodJoke(0L);
                    userS.setBallBadJoke(0L);
                    userS.setBallCringe(0L);
                    userS.setBallBayan(0L);
                    userS.setCountMassageOnServer(0L);
                    userDao.save(userS);
                    return "Сброшен";
            }
        }
        return null;
    }

    public String switchContainerForUserDB(String content, Set<User> users) {
        for (User user : users) {
            content = content.replace(user.getServerId().toString(), "");
        }
        content = content.toLowerCase().replace("<@", "").replace(">", "").replace(" ", "");
        switch (content.toLowerCase()) {
            case ("!ранг"):
                var s = users.stream().map(user -> user.getUsername() + " по жизни " + user.getRanked()).collect(Collectors.joining("\n"));
                return (s);

            case ("!статистика"):
                var s1 = users.stream().map(User::toString).collect(Collectors.joining("\n" + "\n" + "\n"));
                return s1;

            case ("!победилвкастомке"):
                if (users.size() != 1) {
                    return "Как они все победили, они что ебланы?";
                }
                User user = users.iterator().next();
                user.setBallWinnerCustomGame(user.getBallWinnerCustomGame() + 1L);
                user.setRespect(user.getRespect() + 5L);
                userDao.save(user);
                return "Поздравляю " + user.getUserServerName() + "\n" + "ты победил уже целый(х) " + user.getBallWinnerCustomGame() + " раз(а)";

            case ("!ебалмамку"):
                if (users.size() != 1) {
                    while (users.iterator().hasNext()) {
                        User user2 = users.iterator().next();
                        user2.setRespect(user2.getRespect() - 10L);
                        userDao.save(user2);
                    }
                    return "Это что за извращения??? мы за традиционный секс всех покарать!!!";
                }

                    var user3 = users.iterator().next();
                    user3.setRespect(user3.getRespect());
                    userDao.save(user3);
                    return "Не одобряем и не осуждаем его, так сказать.";
        }
        return null;
    }

    public Set<User> assistantToUserServerId(String content) {
        return Arrays.stream(content.split(" "))
                .map(s -> s.replace("<@", ""))
                .map(s -> s.replace(">", ""))
                .filter(s -> s.matches("\\d{18}"))
                .map(Long::parseLong)
                .distinct()
                .map(id -> userDao.findByServerId(id))
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }
}