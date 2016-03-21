package win.trytryagain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import win.trytryagain.mapper.TryTryAgainMapper;
import win.trytryagain.model.TryTryAgain;
import win.trytryagain.model.TryTryAgainCriteria;

import java.util.Date;
import java.util.List;

/**
 * Created by momo on 15-12-1.
 */
@Service
public class TryTryAgainRemoteServiceImpl implements TryTryAgainRemoteService {

    @Autowired
    TryTryAgainMapper tryTryAgainMapper;

    @Override
    public List<TryTryAgain> query(TryTryAgainCriteria criteria) {
        return tryTryAgainMapper.selectByExample(criteria);
    }

    @Override
    public TryTryAgain get(Integer id) {
        return tryTryAgainMapper.selectByPrimaryKey(id);
    }

    @Override
    public TryTryAgain saveSelective(TryTryAgain tryTryAgain) {
        if (tryTryAgain == null) {
            return null;
        }
        if (tryTryAgain.getId() == null) {
            tryTryAgainMapper.insertSelective(tryTryAgain);
        } else {
            tryTryAgainMapper.updateByPrimaryKeySelective(tryTryAgain);
        }
        return tryTryAgain;
    }

    @Override
    public TryTryAgain save(TryTryAgain tryTryAgain) {
        if (tryTryAgain == null) {
            return null;
        }
        Date now = new Date();
        tryTryAgain.setUpdateTime(now);
        if (tryTryAgain.getId() == null) {
            tryTryAgain.setCreateTime(now);
            tryTryAgainMapper.insert(tryTryAgain);
        } else {
            tryTryAgainMapper.updateByPrimaryKeyWithBLOBs(tryTryAgain);
        }
        return tryTryAgain;
    }
}
