package com.atguigu.crowd.service.impl;

import com.atguigu.crowd.mapper.MenuMapper;
import com.atguigu.crowd.po.Menu;
import com.atguigu.crowd.po.MenuExample;
import com.atguigu.crowd.service.api.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 10185
 * @create 2021/2/7 18:33
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    MenuMapper menuMapper;

    @Override
    public List<Menu> getAllMenu() {

        return menuMapper.selectByExample(new MenuExample());
    }
}
