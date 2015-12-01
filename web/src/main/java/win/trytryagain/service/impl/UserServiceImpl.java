package win.trytryagain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import win.trytryagain.service.UserService;
import win.trytryagain.model.User;
import win.trytryagain.model.UserCriteria;
import win.trytryagain.service.UserRemoteService;

import java.util.List;

/**
 * Created by momo on 15-11-25.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRemoteService userRemoteService;

    @Override
    public List<User> query(UserCriteria criteria) {
        return userRemoteService.query(criteria);
    }

    @Override
    public User get(Integer id) {
        return userRemoteService.get(id);
    }

    @Override
    public User saveSelective(User user) {
        User user1 = userRemoteService.saveSelective(user);
        return user1;
    }

    @Override
    public User save(User user) {
        User user1 = userRemoteService.save(user);
        return user1;
    }
}
