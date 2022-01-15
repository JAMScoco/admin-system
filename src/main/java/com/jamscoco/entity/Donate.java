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
@TableName("record_donate")
@ApiModel(value="Donate对象", description="")
public class Donate implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "id")
      private String id;

    @ApiModelProperty(value = "捐赠用户id")
    private String userId;

    @ApiModelProperty(value = "捐赠图书id")
    private String bookId;

    @ApiModelProperty(value = "捐赠时间")
    private LocalDateTime donateTime;


}
