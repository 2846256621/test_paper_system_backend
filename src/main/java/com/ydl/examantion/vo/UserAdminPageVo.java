package com.ydl.examantion.vo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserAdminPageVo implements Serializable {

    private String userName;

    private Integer currentPage = 1;

    private Integer pageSize = 10;
}
