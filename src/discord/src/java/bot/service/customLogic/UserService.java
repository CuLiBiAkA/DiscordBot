package bot.service.customLogic;

import bot.config.Rank;
import bot.dao.UserDao;
import bot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
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
//            userEntity.setCountUpdateMassageInServer(1L);
            userEntity.setUserServerName(user.getUserData().username());
            userEntity.setCountMassageOnServer(1L);
//            userEntity.setCountUpdateMassageInServer(0L);
            userEntity.setBallCringe(0L);
            userEntity.setBallBayan(0L);
            userEntity.setBallBadJoke(0L);
            userEntity.setBallGoodJoke(0L);
            userEntity.setBallWinnerCustomGame(0L);
            userEntity.setBallFailCustomerGame(0L);
            userEntity.setBallFuckYourMam(0L);
            userEntity.setPogonialo("");
            userEntity.setCountPook(0L);
            userDao.save(userEntity);
            System.out.println("Сохранен");
        } else {
            userEntity1.setCountMassageOnServer(userEntity1.getCountMassageOnServer() + 1L);
            userDao.save(userEntity1);
            System.out.println("Количество сообщений: " + userDao.findById(userEntity1.getId()).get().getCountMassageOnServer() + " Name:" + userEntity1.getUsername());
        }
    }


    public String switchContainer(discord4j.core.object.entity.User user, String content) {

        Pattern pattern = Pattern.compile("\\d{18}", Pattern.CASE_INSENSITIVE);

        if (content != null) {
            switch (content.toLowerCase()) {
                case ("даник мой ранг"):
                    String name = user.getUsername();
                    return  (name + " по жизни " + userDao.findByUsername(name).getRanked());
                case ("даник дай расклад по мне"):
                    return userDao.findByUsername(user.getUsername()).toString();
                case ("бля"):


                    return "бля";
            }
        }
        return null;
    }
    public void assistantToServerId(String content){

      //  userDao.findByServerId()
    }
}