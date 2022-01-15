package com.jamscoco.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author JAMScoco
 * @since 2022-01-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="RecordDonate对象", description="")
public class RecordDonate implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "捐赠用户id")
    private String userId;

    @ApiModelProperty(value = "捐赠图书id")
    private String bookId;

    @ApiModelProperty(value = "捐赠时间")
    private LocalDateTime donateTime;


}
