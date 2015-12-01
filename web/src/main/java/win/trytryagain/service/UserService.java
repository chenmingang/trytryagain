package win.trytryagain.service;

import win.trytryagain.model.User;
import win.trytryagain.model.UserCriteria;

import java.util.List;

/**
 * Created by momo on 15-12-1.
 */
public interface UserService {
    List<User> query(UserCriteria criteria);

    User get(Integer id);

    User saveSelective(User user);

    User save(User user);
}
