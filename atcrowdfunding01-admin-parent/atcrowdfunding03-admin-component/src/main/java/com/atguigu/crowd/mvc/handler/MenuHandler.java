package com.atguigu.crowd.mvc.handler;

import com.atguigu.crowd.po.Menu;
import com.atguigu.crowd.service.api.MenuService;
import com.atguigu.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

/**
 * @author 10185
 * @create 2021/2/7 18:28
 */
@Controller
@RequestMapping("/menu")
public class MenuHandler {
    @Autowired
    MenuService menuService;


    @RequestMapping("/getMenuList/ssm.json")
    @ResponseBody
    public ResultEntity<Menu> getMenuList() {
        List<Menu> allMenu = menuService.getAllMenu();
        //把数据存入hashmap,同时选定root节点
        Menu root = null;
        HashMap<Integer, Menu> objectObjectHashMap = new HashMap<>();
        for (Menu menu : allMenu) {
            if (menu.getPid() == null) {
                root = menu;
            }
            objectObjectHashMap.put(menu.getId(), menu);
        }
        for (Menu menu : allMenu) {
            //如果当前menu中的pid等于map集合中的id,那么就加入当map集合中的id属于的那个Menu集合中的children属性
            if (menu.getPid() == null) {
                continue;
            }
            objectObjectHashMap.get(menu.getPid()).getChildren().add(menu);

        }



        return ResultEntity.successWithData(root);

    }
}
