package trytryagain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trytryagain.service.TryTryAgainService;
import win.trytryagain.model.TryTryAgain;
import win.trytryagain.model.TryTryAgainCriteria;
import win.trytryagain.service.TryTryAgainRemoteService;

import java.util.List;

/**
 * Created by momo on 15-12-1.
 */
@Service
public class TryTryAgainServiceImpl implements TryTryAgainService {

    @Autowired
    TryTryAgainRemoteService tryTryAgainRemoteService;

    @Override
    public List<TryTryAgain> query(TryTryAgainCriteria criteria) {
        return tryTryAgainRemoteService.query(criteria);
    }

    @Override
    public TryTryAgain get(Integer id) {
        return tryTryAgainRemoteService.get(id);
    }

    @Override
    public TryTryAgain saveSelective(TryTryAgain tryTryAgain) {
        TryTryAgain tryTryAgain1 = tryTryAgainRemoteService.saveSelective(tryTryAgain);
        return tryTryAgain1;
    }

    @Override
    public TryTryAgain save(TryTryAgain tryTryAgain) {
        TryTryAgain tryTryAgain1 = tryTryAgainRemoteService.save(tryTryAgain);
        return tryTryAgain1;
    }
}
