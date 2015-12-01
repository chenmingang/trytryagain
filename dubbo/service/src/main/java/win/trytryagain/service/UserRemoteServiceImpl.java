package win.trytryagain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import win.trytryagain.mapper.UserMapper;
import win.trytryagain.model.User;
import win.trytryagain.model.UserCriteria;

import java.util.List;

/**
 * Created by momo on 15-11-25.
 */
@Service
public class UserRemoteServiceImpl implements UserRemoteService {
    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> query(UserCriteria criteria) {
        return userMapper.selectByExample(criteria);
    }

    @Override
    public User get(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User saveSelective(User user) {
        if (user == null) {
            return null;
        }
        if (user.getId() == null) {
            userMapper.insertSelective(user);
        } else {
            userMapper.updateByPrimaryKeySelective(user);
        }
        return user;
    }

    @Override
    public User save(User user) {
        if (user == null) {
            return null;
        }
        if (user.getId() == null) {
            userMapper.insert(user);
        } else {
            userMapper.updateByPrimaryKey(user);
        }
        return user;
    }
}
