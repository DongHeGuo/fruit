package com.boot.module.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 平台设置实体类
 * @author ziv
 * @date 2019-05-07
 */
@Data
@ApiModel(value = "平台设置")
public class SysPlatform {

    /**
     * 平台主键
     */
    @ApiModelProperty(value = "平台主键", example = "1")
    private Integer platformId;

    /**
     * 平台类型：1-水果2-手机
     */
    @ApiModelProperty(value = "平台类型：1-水果2-手机", example = "1", required = true)
    private String platFormType;

    /**
     * 平台名称
     */
    @ApiModelProperty(value = "平台名称", example = "平台名称")
    private String platformName;

    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话", example = "15174191677")
    private String phoneNumber;

    /**
     * 配送范围
     */
    @ApiModelProperty(value = "配送范围", example = "12")
    private Double postageArea;

    /**
     * 配送费
     */
    @ApiModelProperty(value = "配送费", example = "3.6")
    private Double postageFee;

    /**
     * 广告图片
     */
    @ApiModelProperty(value = "广告图片", example = "/img/test.png")
    private String advImg;

    /**
     * 省份
     */
    @ApiModelProperty(value = "省份", example = "江苏")
    private String province;

    /**
     * 市
     */
    @ApiModelProperty(value = "市", example = "无锡")
    private String city;

    /**
     * 区
     */
    @ApiModelProperty(value = "区", example = "惠山")
    private String area;

    /**
     * 详细地址
     */
    @ApiModelProperty(value = "详细地址", example = "北京西站南广场东")
    private String address;

    /**
     * 经度
     */
    @ApiModelProperty(value = "经度", example = "21.035")
    private Double longitude;

    /**
     * 纬度
     */
    @ApiModelProperty(value = "纬度", example = "-12.586")
    private Double latitude;
}
