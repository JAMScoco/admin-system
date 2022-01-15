package com.jamscoco.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@ApiModel(value="Message对象", description="")
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "id")
      private String id;

    @ApiModelProperty(value = "留言用户id")
    private String userId;

    @ApiModelProperty(value = "留言图书")
    private String bookId;

    @ApiModelProperty(value = "留言内容")
    private String content;

    @ApiModelProperty(value = "留言时间")
    private LocalDateTime createTime;


}
