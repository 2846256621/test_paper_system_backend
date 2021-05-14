package com.ydl.examantion.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ydl.examantion.model.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ydl.examantion.vo.UserAdminPageVo;
import com.ydl.examantion.vo.UserRegisterVo;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YDL
 * @since 2021-01-09
 */
public interface UsersMapper extends BaseMapper<Users> {


    public List<Users> selectUserLoginList(@Param("userAdminPageVo") UserAdminPageVo userAdminPageVo, Page page);

    public Integer registerUser(Users users);

    public List<Users> selectUserIfExists(String userName);

    public Users selectUserLogin(UserRegisterVo registerVo);

    Integer usersStatistic();

}
