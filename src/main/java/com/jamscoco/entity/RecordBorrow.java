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
@ApiModel(value="RecordBorrow对象", description="")
public class RecordBorrow implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "借书用户id")
    private String userId;

    @ApiModelProperty(value = "图书id")
    private String bookId;

    @ApiModelProperty(value = "借书时间")
    private LocalDateTime borrowTime;

    @ApiModelProperty(value = "应还书时间")
    private LocalDateTime planReturnTime;

    @ApiModelProperty(value = "实际还书时间")
    private LocalDateTime actReturnTime;

    @ApiModelProperty(value = "还书状态")
    private String returnStatus;


}
