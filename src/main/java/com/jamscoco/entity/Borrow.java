package com.jamscoco.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("record_borrow")
@ApiModel(value="Borrow对象", description="")
public class Borrow implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "id")
      private String id;

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
