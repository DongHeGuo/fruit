package com.boot.common.beans;

import lombok.Data;

/**
 * jwt实体类
 * @author ziv
 * @date 2019-05-06
 */
@Data
public class TokenInfo {
    private Integer userId;

    private String userName;

    private String token;
}
