package edu.hunau.crowd.mvc.handler;

import edu.hunau.crowd.entity.Menu;
import edu.hunau.crowd.service.api.MenuService;
import edu.hunau.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MenuHandler {

    @Autowired
    private MenuService menuService;

    /**
     * 这里是将树规格化
     * @return 根结点
     */
    @RequestMapping("/menu/get/whole/tree")
    public ResultEntity<Menu> getWholeTree() {
        List<Menu> all = menuService.getAll();
        Menu root = null;
        Map<Integer, Menu> menuMap = new HashMap<>();
        for (Menu menu : all) {
            Integer pid = menu.getPid();
            if (pid == null) {
                root = menu;
            }
            Integer id = menu.getId();
            menuMap.put(id, menu);
            Menu father;
            if ((father = menuMap.get(pid))!=null) {
                father.getChildren().add(menu);
            }
        }
        return ResultEntity.successWithData(root);
    }
    @RequestMapping("/menu/remove")
    public ResultEntity<String> removeMenu(@RequestParam("id") Integer id) {

        menuService.removeMenu(id);

        return ResultEntity.successWithoutData();
    }
    @RequestMapping("/menu/update")
    public ResultEntity<String> updateMenu(Menu menu) {

        menuService.updateMenu(menu);

        return ResultEntity.successWithoutData();
    }

    @RequestMapping("/menu/save")
    public ResultEntity<String> saveMenu(Menu menu) {

        menuService.saveMenu(menu);

        return ResultEntity.successWithoutData();
    }
}
