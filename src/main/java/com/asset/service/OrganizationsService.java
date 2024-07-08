package com.asset.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.asset.entity.Organizations;
import com.asset.model.respone.OrganizationVo;

@Component
public interface OrganizationsService {
    public List<Organizations> findAll();
    public Optional<Organizations> findById(long Id);
    public void save(Organizations organization);
    public boolean update(Organizations organization, long id);
    public void deleteById(long id);
    public void deleteAll();
    public List<OrganizationVo> getOrganizationNameAndId();
    public long findTotalOrganization();
}
