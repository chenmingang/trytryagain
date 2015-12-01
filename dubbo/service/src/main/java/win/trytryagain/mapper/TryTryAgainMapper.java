package win.trytryagain.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import win.trytryagain.model.TryTryAgain;
import win.trytryagain.model.TryTryAgainCriteria;

public interface TryTryAgainMapper {
    int countByExample(TryTryAgainCriteria example);

    int deleteByExample(TryTryAgainCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(TryTryAgain record);

    int insertSelective(TryTryAgain record);

    List<TryTryAgain> selectByExampleWithBLOBs(TryTryAgainCriteria example);

    List<TryTryAgain> selectByExample(TryTryAgainCriteria example);

    TryTryAgain selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TryTryAgain record, @Param("example") TryTryAgainCriteria example);

    int updateByExampleWithBLOBs(@Param("record") TryTryAgain record, @Param("example") TryTryAgainCriteria example);

    int updateByExample(@Param("record") TryTryAgain record, @Param("example") TryTryAgainCriteria example);

    int updateByPrimaryKeySelective(TryTryAgain record);

    int updateByPrimaryKeyWithBLOBs(TryTryAgain record);

    int updateByPrimaryKey(TryTryAgain record);
}