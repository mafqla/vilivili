package com.yali.vilivili.service;

import com.yali.vilivili.model.entity.UserEntity;
import com.yali.vilivili.model.ro.UserSelectRO;
import com.yali.vilivili.model.ro.deleteByUserIdRO;
import com.yali.vilivili.model.ro.updateAndSaveUserRO;

import java.util.List;

/**
 * @Description 用户管理
 * @Date 2023.1.6 22:58
 * @Author fuqianlin
 */
public interface UserService {


    /**
     * 更新和保存用户
     *
     * @param ro 用户信息
     */
    void updateAndSaveUser(updateAndSaveUserRO ro);


    /**
     * 删除用户
     *
     * @param ro 用户id
     */
    void deleteById(deleteByUserIdRO ro);

    List<UserEntity> findUser(UserSelectRO ro);

    /**
     * 分页查询用户列表
     *
     * @param page 页码
     * @param size 每页数量
     */
    List<UserEntity> findAllUserByPage(Integer page, Integer size);
}
