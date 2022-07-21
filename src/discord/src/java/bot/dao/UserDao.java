package bot.dao;

import bot.model.User;
import discord4j.common.util.Snowflake;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByServerId(Long id);
}
