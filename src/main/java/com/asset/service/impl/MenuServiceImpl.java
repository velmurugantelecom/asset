package com.asset.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asset.entity.Menu;
import com.asset.repositories.MenuRepository;
import com.asset.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {
	
	   @Autowired
	    MenuRepository menuRepository;

	   public List<Menu> findAllByOrderByIdAsc()
	    {
	        return menuRepository.findAllByOrderByIdAsc();
	    }

	    public Optional<Menu> findById(long id)
	    {
	        return menuRepository.findById(id);
	    }
	    public List<Menu> findByRoleName(String roleName)
	    {
	    	return menuRepository.findByRoleOrderByIdAsc(roleName);
	    }
	    public void save(Menu menu)
	    {
	    	menuRepository.save(menu);
	    }
	    public boolean update(Menu menu, long id)
	    {
	        Optional<Menu> organizationData = menuRepository.findById(id);
	        if (organizationData.isPresent()) {
	        	menu.setId(id);
	            menuRepository.save(menu);
	            return true;
	        }
	        return false;
	    }
	    public void deleteById(long id)
	    {
	    	menuRepository.deleteById(id);
	    }
	    public void deleteAll()
	    {
	    	menuRepository.deleteAll();
	    }

		@Override
		public long findTotalMenu() {
			return menuRepository.count();
		}
}
