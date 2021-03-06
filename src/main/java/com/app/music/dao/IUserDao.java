package com.app.music.dao;

import com.app.music.entity.User;

import java.util.List;
import java.util.Map;

public interface IUserDao {
    /**
     * 查询user
     *
     * @return User
     */
    User queryUserByUserCode(String userCode);

    /**
     * 查询user
     *
     * @return User
     */
    User queryUserByUserId(int userId);

    /**
     * 查询所有用户
     *
     * @return List<User>
     */
    List<User> queryAllUser();

    /**
     * 新增用户
     *
     * @return Boolean
     */
    Boolean insertUser(User user);

    /**
     * 修改用户
     *
     * @return Boolean
     */
    Boolean updateUser(Map map);

    /**
     * 删除userById
     * @param userId
     * @return
     */
    Boolean deleteUserByUserId(String userId);

    /**
     * 批量删除
     * @param userIds
     */
    void deleteUserByBatch(int[] userIds);
}
