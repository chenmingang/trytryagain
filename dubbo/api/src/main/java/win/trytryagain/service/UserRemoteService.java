package win.trytryagain.service;

import win.trytryagain.model.User;
import win.trytryagain.model.UserCriteria;

import java.util.List;

/**
 * Created by momo on 15-11-25.
 */
public interface UserRemoteService {
    List<User> query(UserCriteria criteria);

    User get(Integer id);

    User saveSelective(User tryTryAgain);

    User save(User tryTryAgain);
}
