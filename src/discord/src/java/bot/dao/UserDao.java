package bot.dao;

import bot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByServerId(Long id);
    List<User> findAllByCountUpdateMassageInServer(Long countUpdateMassageInServer);
}
