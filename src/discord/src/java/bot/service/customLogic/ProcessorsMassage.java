package bot.service.customLogic;

import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicInteger;


@Service
public class ProcessorsMassage {

    @Autowired
    UserService userService;

    int count = 0;

    User user;
    String content;

    String massage;

    AtomicInteger atomicInteger = new AtomicInteger(count);

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
