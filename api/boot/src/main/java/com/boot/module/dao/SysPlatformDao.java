package com.boot.module.dao;

import com.boot.module.entity.SysPlatform;
import org.apache.ibatis.annotations.Mapper;

/**
 * 平台设置数据持久层
 * @author ziv
 * @date 2019-05-07
 */
@Mapper
public interface SysPlatformDao {

    /**
     * 获取平台西信息
     * @param platformType 平台类型
     * @return SysPlatform
     */
    SysPlatform getInfo(Integer platformType);

    /**
     * 添加平台信息
     * @param platform 平台信息
     */
    void insert(SysPlatform platform);

    /**
     * 更新平台信息
     * @param platform 平台信息
     */
    void update(SysPlatform platform);
}
