package com.cedis.db.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author jjq
 * @version 1.0
 * @date 2022/9/5
 * @desc
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RedisInfo implements Serializable {

    private Integer id;

    private String name;

    private String rVersion;

    private Date createTime;

    private LocalDateTime  updateTime;


}
