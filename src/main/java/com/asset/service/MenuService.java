package com.asset.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.asset.entity.Menu;

@Component
public interface MenuService {
    public List<Menu> findAllByOrderByIdAsc();
    public Optional<Menu> findById(long Id);
    public List<Menu> findByRoleName(String roleName);
    public void save(Menu menus);
    public boolean update(Menu menus, long id);
    public void deleteById(long id);
    public void deleteAll();
    public long findTotalMenu();

}
