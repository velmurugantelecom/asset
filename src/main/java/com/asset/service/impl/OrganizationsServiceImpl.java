package com.asset.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asset.entity.Organizations;
import com.asset.model.respone.OrganizationVo;
import com.asset.repositories.OrganizationsRepository;
import com.asset.service.OrganizationsService;

@Service
public class OrganizationsServiceImpl implements OrganizationsService {

    @Autowired
    OrganizationsRepository organizationsRepository;

    public List<Organizations> findAll()
    {
        return organizationsRepository.findAll();
    }

    public Optional<Organizations> findById(long id)
    {
        return organizationsRepository.findById(id);
    }
    public void save(Organizations organization)
    {
        organizationsRepository.save(organization);
    }
    public boolean update(Organizations organization, long id)
    {
        Optional<Organizations> organizationData = organizationsRepository.findById(id);
        if (organizationData.isPresent()) {
            organization.setID(id);
            organizationsRepository.save(organization);
            return true;
        }
        return false;
    }
    public void deleteById(long id)
    {
        organizationsRepository.deleteById(id);
    }
    public void deleteAll()
    {
        organizationsRepository.deleteAll();
    }
    
    public List<OrganizationVo> getOrganizationNameAndId() {
    	return organizationsRepository.getOrganizationNameAndId();
    }

	@Override
	public long findTotalOrganization() {
		return organizationsRepository.count();
	}
}

