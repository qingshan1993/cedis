package com.cedis.db.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author jjq
 * @version 1.0
 * @date 2020/8/23
 * @desc
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RedisInfo {

    private Integer id;

    private String name;

    private String rVersion;

    private Date createTime;

    private LocalDateTime  updateTime;


}
