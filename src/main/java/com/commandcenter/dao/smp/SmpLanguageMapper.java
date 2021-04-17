package com.commandcenter.dao.smp;

import com.commandcenter.model.dictmodel.SmptLanguage;
import com.commandcenter.model.smp.SmpLanguage;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SmpLanguageMapper {
    int deleteByPrimaryKey(String guid);

    int insert(SmptLanguage record);

    int insertSelective(SmptLanguage record);

    SmpLanguage selectByPrimaryKey(String guid);

    int updateByPrimaryKeySelective(SmptLanguage record);

    int updateByPrimaryKey(SmptLanguage record);

    void deleteAllSmpLanguageInfo();
    int selectLanguageCount();
    int selectLanguageMaxVersion();

}