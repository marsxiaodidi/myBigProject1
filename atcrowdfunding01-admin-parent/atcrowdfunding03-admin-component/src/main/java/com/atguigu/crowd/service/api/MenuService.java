package com.atguigu.crowd.service.api;

import com.atguigu.crowd.po.Menu;

import java.util.List;

/**
 * @author 10185
 * @create 2021/2/7 18:32
 */

public interface MenuService {
    List<Menu> getAllMenu();

    /**
     * 添加menu
     * @param menu
     */
    void addMenu(Menu menu);

    /**
     * 更新menu
     *
     * @param menu
     */
    void updateMenu(Menu menu);

    void deleteMenu(Integer id);
}
