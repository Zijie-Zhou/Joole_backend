package com.itlize.Joole.service.serviceImpl;

import com.itlize.Joole.entity.Project;
import com.itlize.Joole.entity.ProjectProduct;
import com.itlize.Joole.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ProjectManage {

    @Autowired ProjectRepository projectRep;

    public int addProductToProject(int productId, int projectId)
    {
        Set<ProjectProduct> ppSet = new HashSet<ProjectProduct>();


        Project project = projectRep.findById(projectId).orElse(null);

        ProjectProduct pp = new ProjectProduct();

        pp.setProject(project);

        ppSet.add(pp);

        project.setProjectProduct(ppSet);

        return 1;

    }
}
