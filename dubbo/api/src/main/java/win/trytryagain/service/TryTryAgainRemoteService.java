package win.trytryagain.service;

import win.trytryagain.model.TryTryAgain;
import win.trytryagain.model.TryTryAgainCriteria;

import java.util.List;

/**
 * Created by momo on 15-12-1.
 */
public interface TryTryAgainRemoteService {
    List<TryTryAgain> query(TryTryAgainCriteria criteria);

    TryTryAgain get(Integer id);

    TryTryAgain saveSelective(TryTryAgain tryTryAgain);

    TryTryAgain save(TryTryAgain tryTryAgain);
}
