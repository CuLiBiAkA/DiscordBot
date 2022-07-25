package bot.service.customLogic;

import bot.config.Rank;
import bot.config.Сommand;
import bot.dao.UserDao;
import bot.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService {

    @Autowired
    UserDao userDao;

    private static final String PRIVET = "Привет, я бот даник. \n" +
            "мои команды: \n" +
            Сommand.RANG.getCommand() + " Твой ранг" + "\n" +
            Сommand.STATA.getCommand() + " Твоя статистика" + "\n" +
            Сommand.CLEAN.getCommand() + " Сбросить статистику(кроме уважения)" + "\n" +
            "@Чепух(и) " + Сommand.GOODJOKE.getCommand() + " Плюс бал за шутку" + "\n" +
            "@Чепух(и) " + Сommand.BADJOKE.getCommand() + " Минус бал за шутку" + "\n" +
            "@Чепух(и) " + Сommand.WINNER.getCommand() + " Плюс бал за победу в кастомке" + "\n" +
            "@Чепух(и) " + Сommand.FAIL.getCommand() + " Минус бал за проигрыш в кастомке" + "\n" +
            "@Чепух(и) " + Сommand.CRING.getCommand() + " Минус бал за кринж" + "\n" +
            "@Чепух(и) " + Сommand.EBALMAMKU.getCommand() + " Уфф" + "\n" +
            "@Чепух(и) " + Сommand.POOK.getCommand() + " Балл за смачный пердеж" + "\n" +
            "@Чепух(и) " + Сommand.RANG.getCommand() + " Ранг чепуха" + "\n" +
            "";

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
            userEntity.setCountUpdateMassageInServer(0L);
            userEntity.setBallFailCustomerGame(0L);
            userEntity.setBallFuckYourMam(0L);
            userEntity.setPogonialo("");
            userEntity.setCountPook(0L);
            userDao.save(userEntity);
            log.info("Сохранен");
        } else {
            userEntity1.setRanked(countRaced(userEntity1.getRespect()));
            userEntity1.setCountMassageOnServer(userEntity1.getCountMassageOnServer() + 1L);
            if (userEntity1.getBallFuckYourMam() != 0) {
                userEntity1.setPogonialo(Rank.MAMKAEB.getRank());
            } else {
                userEntity1.setPogonialo(Rank.ROVNYI.getRank());
            }
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
            var etalon = content.toLowerCase().replace(" ", "");
            if (etalon.equals(Сommand.RANG.getCommand())) {
                var userDB = userDao.findByUsername(user.getUsername());
                return (user.getUsername() + " по жизни " + userDB.getRanked() + "\n" + "Уважение: " + userDB.getRespect());
            }
            if (etalon.equals(Сommand.STATA.getCommand())) {
                return userDao.findByUsername(user.getUsername()).toString();
            }

            if (etalon.equals(Сommand.CLEAN.getCommand())) {
                var userS = userDao.findByUsername(user.getUsername());
                userS.setRespect(userS.getRespect() + userS.getBallWinnerCustomGame() + userS.getBallGoodJoke());
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

            if (etalon.equals(Сommand.IZADANYCOM.getCommand())) {
                return PRIVET;
            }
            if (etalon.equals(Сommand.OHUEVSHAIA.getCommand())) {
                var list = userDao.findAllByCountUpdateMassageInServer(0L);
                StringBuilder s = new StringBuilder();
                list.sort((o1, o2) -> o2.getRespect().compareTo(o1.getRespect()));
                var countChar = list
                        .stream()
                        .map(user1 -> user1.getUserServerName()
                                .chars()
                                .count())
                        .max(Comparator.naturalOrder())
                        .get();
                String sss = " ";
                list.stream()
                        .peek(user1 -> user1.setUserServerName(user1.getUserServerName()+sss.repeat((int) (countChar.intValue()-user1.getUserServerName().chars().count()))))
                        .forEach(user1 -> s
                                .append(user1.getUserServerName().toLowerCase())
                                .append(" | ")
                                .append(user1.getRespect().toString())
                                .append("cm").append("\n"));
                return s.toString();
            }
        }
        return null;
    }

    public String switchContainerForUserDB(String content, Set<User> users) {
        for (User user : users) {
            user.setRanked(countRaced(user.getRespect()));
            userDao.save(user);
            content = content.replace(user.getServerId().toString(), "");
        }
        var etalon = content.toLowerCase().replace("<@", "").replace(">", "").replace(" ", "");

        if (etalon.equals(Сommand.RANG.getCommand())) {
            var s = users.stream().map(user -> user.getUsername() + " по жизни " + user.getRanked()).collect(Collectors.joining("\n"));
            return (s);
        }

//            case ("!статистика"):
//                var s1 = users.stream().map(User::toString).collect(Collectors.joining("\n" + "\n" + "\n"));
//                return s1;

        if (etalon.equals(Сommand.WINNER.getCommand())) {
            if (users.size() != 1) {
                return "Как они все победили, они что ебланы?";
            }
            User user = users.iterator().next();
            user.setRespect(user.getRespect() + 5L);
            user.setBallWinnerCustomGame(user.getBallWinnerCustomGame() + 1L);
            userDao.save(user);
            return "Поздравляю " + user.getUserServerName() + "\n" + "ты победил уже целый(х) " + user.getBallWinnerCustomGame() + " раз(а)";
        }

        if (etalon.equals(Сommand.FAIL.getCommand())) {
            if (users.size() != 1) {
                users.stream().peek(user3 -> user3.setBallFailCustomerGame(user3.getBallFailCustomerGame() + 1L)).peek(user1 -> user1.setRespect(user1.getRespect() - 1L)).forEach(user11 -> userDao.save(user11));
                return "Все проебали лузеры";
            }
            User user33 = users.iterator().next();
            user33.setRespect(user33.getRespect() - 1L);
            user33.setBallFailCustomerGame(user33.getBallFailCustomerGame() + 1L);
            userDao.save(user33);
            return user33.getUserServerName() + "\n" + "ты проебал уже целый(х) " + user33.getBallFailCustomerGame() + " раз(а)";
        }


        if (etalon.equals(Сommand.EBALMAMKU.getCommand())) {
            if (users.size() != 1) {
                while (users.iterator().hasNext()) {
                    User user2 = users.iterator().next();
                    user2.setRespect(user2.getRespect() - 5L);
                    user2.setBallFuckYourMam(user2.getBallFuckYourMam() + 1L);
                    userDao.save(user2);
                }
                return "Это что за извращения??? \n Фу мерзко наверняка яичками стукались!!";
            }
            var user2 = users.iterator().next();
            user2.setRespect(user2.getRespect() - 5L);
            user2.setBallFuckYourMam(user2.getBallFuckYourMam() + 1L);
            userDao.save(user2);
            return "Не одобряем и не осуждаем его, так сказать.";
        }


        if (etalon.equals(Сommand.CRING.getCommand())) {
            if (users.size() != 1) {
                return "Одному только могу поставить за это бал";
            }
            var userCring = users.iterator().next();
            userCring.setRespect(userCring.getRespect() - 2L);
            userCring.setBallCringe(userCring.getBallCringe() + 1L);
            userDao.save(userCring);
            return "В копилочку залетело";
        }

        if (etalon.equals(Сommand.BADJOKE.getCommand())) {
            if (users.size() != 1) {
                return "Одному только могу поставить за это бал";
            }
            var userBadJoke = users.iterator().next();
            userBadJoke.setRespect(userBadJoke.getRespect() - 1L);
            userBadJoke.setBallBadJoke(userBadJoke.getBallGoodJoke() + 1L);
            userDao.save(userBadJoke);
            return "В копилочку залетело";
        }

        if (etalon.equals(Сommand.GOODJOKE.getCommand())) {
            if (users.size() != 1) {
                return "Одному только могу поставить за это бал";
            }
            var userGodJoke = users.iterator().next();
            userGodJoke.setRespect(userGodJoke.getRespect() + 1L);
            userGodJoke.setBallGoodJoke(userGodJoke.getBallGoodJoke() + 1L);
            userDao.save(userGodJoke);
            return "В копилочку залетело";
        }

        if (etalon.equals(Сommand.POOK.getCommand())) {
            if (users.size() != 1) {
                return "Одному только могу поставить за это бал";
            }
            var userPerdeg = users.iterator().next();
            userPerdeg.setRespect(userPerdeg.getRespect() + 1L);
            userPerdeg.setCountPook(userPerdeg.getCountPook() + 1L);
            userDao.save(userPerdeg);
            return "В копилочку залетело";
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

    public String countRaced(Long repa) {

        if (repa > 31L) {
            return Rank.KILLER.getRank();
        }
        if (repa > 21L && repa < 30L) {
            return Rank.JOKER.getRank();
        }
        if (repa > 11L && repa < 20L) {
            return Rank.SLADKAIYAPOPKA.getRank();
        }
        if (repa < 10L && repa > -10L) {
            return Rank.GAY.getRank();
        }
        if (repa < -11L && repa > -20L) {
            return Rank.CHEPUSHILA.getRank();
        }
        if (repa < -21L && repa > -30L) {
            return Rank.LOH.getRank();
        }
        if (repa < -31L && repa > -40L) {
            return Rank.CERT.getRank();
        }
        if (repa < -41L) {
            return Rank.VAFLER.getRank();
        }
        return "Агент Смит";
    }
}