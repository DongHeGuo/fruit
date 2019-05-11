package com.boot.module.service;

import com.boot.module.dao.SysPlatformDao;
import com.boot.module.entity.SysPlatform;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 平台设置业务逻辑类
 * @author ziv
 * @date 2019-05-07
 */
@Slf4j
@Service
public class SysPlatformService {

    @Resource
    private SysPlatformDao platformDao;

    /**
     * 获取平台西信息
     * @param platformType 平台类型
     * @return SysPlatform
     */
    public SysPlatform getInfo(Integer platformType) {
        return platformDao.getInfo(platformType);
    }

    /**
     * 保存平台信息
     * @param platform
     */
    public void savePlatform(SysPlatform platform) {
        if (platform.getPlatformId() != null) {
            platformDao.update(platform);
        } else {
            platformDao.insert(platform);
        }
    }
}
