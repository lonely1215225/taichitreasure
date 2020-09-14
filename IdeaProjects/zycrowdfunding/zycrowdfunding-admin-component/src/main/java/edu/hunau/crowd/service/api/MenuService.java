package edu.hunau.crowd.service.api;

import edu.hunau.crowd.entity.Menu;

import java.util.List;

public interface MenuService {
    List<Menu> getAll();

    void removeMenu(Integer id);

    void updateMenu(Menu menu);

    void saveMenu(Menu menu);
}
