package com.atguigu.spzx.cloud.user.mapper;

import com.atguigu.spzx.cloud.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    /**
     * 根据用户主键查询用户实体对象
     * @param id 主键
     * @return 用户实体对象
     */
    User findUserByUserId(Long id);
}
