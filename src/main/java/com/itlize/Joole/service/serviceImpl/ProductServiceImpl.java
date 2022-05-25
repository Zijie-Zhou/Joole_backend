package com.itlize.Joole.service.serviceImpl;

import com.itlize.Joole.entity.Product;
import com.itlize.Joole.entity.Project;
import com.itlize.Joole.repository.ProductRepository;
import com.itlize.Joole.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Override
    public int addProduct(Product product) {
        product.setTimeCreated(LocalDateTime.now());
        return productRepository.save(product).getProductId();
    }

    @Override
    public int deleteProduct(Product product) {
        productRepository.delete(product);
        return 0;
    }



    @Override
    public List<Product> searchByType(String name) {
        return productRepository.findByTypeIgnoreCaseContaining(name);
    }

    @Override
    public List<Product> filter(String userType, String application, String mountingLocation, String accessories, int modelYear, double airflow, double maxPower, double soundAtMaxSpeed, double fanSweepDiameter, double height) {
        return productRepository.findByUserTypeAndApplicationAndMountingLocationAndAccessoriesAndModelYearAndAirflowAndMaxPowerAndSoundAtMaxSpeedAndFanSweepDiameterAndHeight( userType,  application,  mountingLocation,  accessories,  modelYear,  airflow,  maxPower,  soundAtMaxSpeed,  fanSweepDiameter,  height);
    }


    @Override
    public int updateProduct(Product product,int productId) {
        Product product1=productRepository.findById(productId).orElse(null);
        product1.setProductName(product.getProductName());
        product1.setType(product.getType());
        return 0;
    }

    @Override
    public Product findById(Integer productId) {
        return null;
    }

    @Override
    public List<Product> findAllProduct() {
        return null;
    }

    @Override
    public List<Product> findByName(String productName) {
        return null;
    }

    @Override
    public List<Project> getProjectFromProduct(Product product) {
        return null;
    }

}
