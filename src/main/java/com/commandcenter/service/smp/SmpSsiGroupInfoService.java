package com.commandcenter.service.smp;

import com.commandcenter.model.contacts.GroupForApp;
import com.commandcenter.model.smp.SmpSsiGroupInfo;

import java.util.List;
import java.util.Map;

public interface SmpSsiGroupInfoService {
    List<GroupForApp> selectAllGroupInfo(String orgGuid);
    SmpSsiGroupInfo selectGroupInfoById(String guid);

    List<GroupForApp> selectGroupInfoByMap(Map<String,Object> map);
}
