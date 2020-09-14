package edu.hunau.crowd.service.impl;

import edu.hunau.crowd.entity.Menu;
import edu.hunau.crowd.entity.MenuExample;
import edu.hunau.crowd.mapper.MenuMapper;
import edu.hunau.crowd.service.api.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {


    @Autowired
    private MenuMapper menuMapper;

    @Override
    public void saveMenu(Menu menu) {
        menuMapper.insertSelective(menu);
    }

    @Override
    public void updateMenu(Menu menu) {
        menuMapper.updateByPrimaryKeySelective(menu);
    }

    @Override
    public void removeMenu(Integer id) {
        menuMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Menu> getAll() {
        return menuMapper.selectByExample(new MenuExample());
    }
}
