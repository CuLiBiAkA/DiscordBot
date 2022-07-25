package bot.service.customLogic;

import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessorsMassage {

    @Autowired
    UserService userService;


    User user;
    String content;

    String massage;

    public Message getProcessor(Message message) {
        var user1 = message.getAuthor().get();
        var content1 = message.getContent();
        var massageData = message.getData();
        var reactionList = message.getReactions();
        userService.saveUser(user1);
        user=user1;
        content=content1;
        return message;
    }

    public String stringProcessor() {
        massage = userService.switchContainer(user,content);
       return massage;
    }


    public String kostiliFormasseg(){
        return massage;
    }
}
