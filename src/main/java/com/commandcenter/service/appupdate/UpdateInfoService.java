package com.commandcenter.service.appupdate;

import java.util.Map;

public interface UpdateInfoService {
    Map<String, Object> selectAllInfo();
    int update(Map<String, Object> map);
    int insert(Map<String, Object> map);
}
