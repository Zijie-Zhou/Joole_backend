package com.itlize.Joole.repository;

import com.itlize.Joole.entity.Product;
import com.itlize.Joole.entity.Project;
import com.itlize.Joole.entity.ProjectProduct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProjectProductRepositoryTest {

    @Autowired
    ProjectProductRepository ppRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    ProductRepository productRepository;



    @Test
    void findByProject() {
        Project project=new Project();
        project.setProjectName("projectname");
        Project projectReturn=projectRepository.save(project);
        Product product=new Product();
        product.setProductName("productname");
        product.setType("type");
        product.setBrand("brand");
        Product productReturn=productRepository.save(product);
        ProjectProduct projectProduct=new ProjectProduct();
        projectProduct.setProduct(product);
        projectProduct.setProject(project);
        ppRepository.save(projectProduct);
        List<ProjectProduct> pp = ppRepository.findByProject(projectReturn);

        assertThat(pp).isNotEmpty();

    }

    @Test
    void findByProduct() {
        Project project=new Project();
        project.setProjectName("projectname");
        Project projectReturn=projectRepository.save(project);
        Product product=new Product();
        product.setProductName("productname");
        product.setType("type");
        Product productReturn=productRepository.save(product);
        ProjectProduct projectProduct=new ProjectProduct();
        projectProduct.setProduct(product);
        projectProduct.setProject(project);
        ppRepository.save(projectProduct);
        List<ProjectProduct> pp = ppRepository.findByProduct(productReturn);

        assertThat(pp).isNotEmpty();

    }

    @Test
    public void saveProjectProduct(){
        Project project=new Project();
        project.setProjectName("projectname");
        Project projectReturn=projectRepository.save(project);
        Product product=new Product();
        product.setProductName("productname");
        product.setType("type");
        Product productReturn=productRepository.save(product);
        ProjectProduct projectProduct=new ProjectProduct();
        projectProduct.setProduct(product);
        projectProduct.setProject(project);
        ppRepository.save(projectProduct);
        assert (ppRepository.findById(projectProduct.getId()).isPresent());

    }
}