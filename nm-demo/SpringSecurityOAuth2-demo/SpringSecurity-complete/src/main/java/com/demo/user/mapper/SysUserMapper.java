package com.demo.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.user.model.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * 实现MyBatis-Plus封装的 BaseMapper<T> 接口,它有很多对 T 表的数据操作方法
 * @author: stars
 * @date 2020年 07月 09日 11:49
 **/
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
}
