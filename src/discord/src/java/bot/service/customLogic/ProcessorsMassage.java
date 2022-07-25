package bot.service.customLogic;

import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import discord4j.core.object.presence.ClientActivity;
import discord4j.core.object.presence.Presence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProcessorsMassage {

    @Autowired
    UserService userService;


    User user;
    String content;

    String massage;

    public Message getProcessor(Message messagForDiscord) {
        var user1 = messagForDiscord.getAuthor().get();
        var content1 = messagForDiscord.getContent();
        var massageData = messagForDiscord.getData();
        var reactionList = messagForDiscord.getReactions();

        userService.saveUser(user1);
        user=user1;
        content=content1;
        return messagForDiscord;
    }

    public String stringProcessor() {
        massage = userService.switchContainer(user,content);
       return massage;
    }


    public String kostiliFormasseg(){
        return massage;
    }
}
