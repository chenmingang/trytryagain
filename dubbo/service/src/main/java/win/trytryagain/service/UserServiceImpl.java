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
public class UserServiceImpl implements UserService {
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
    public User saveSelective(User tryTryAgain) {
        if (tryTryAgain == null) {
            return null;
        }
        if (tryTryAgain.getId() == null) {
            userMapper.insertSelective(tryTryAgain);
        } else {
            userMapper.updateByPrimaryKeySelective(tryTryAgain);
        }
        return tryTryAgain;
    }

    @Override
    public User save(User tryTryAgain) {
        if (tryTryAgain == null) {
            return null;
        }
        if (tryTryAgain.getId() == null) {
            userMapper.insert(tryTryAgain);
        } else {
            userMapper.updateByPrimaryKey(tryTryAgain);
        }
        return tryTryAgain;
    }
}
