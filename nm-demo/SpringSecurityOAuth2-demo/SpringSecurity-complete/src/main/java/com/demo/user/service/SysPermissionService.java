package com.demo.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.user.model.SysPermission;

import java.util.List;

/**
 * 权限
 * @author: stars
 * @date 2020年 07月 09日 12:02
 **/
public interface SysPermissionService extends IService<SysPermission> {

    /**
     * 通过用户id查询所拥有权限
     * @param userId
     * @return
     */
    List<SysPermission> findByUserId(Long userId);

    /***
     * 查询所有权限数据
     * @return
     */
    List<SysPermission> findPermissionAll();

}