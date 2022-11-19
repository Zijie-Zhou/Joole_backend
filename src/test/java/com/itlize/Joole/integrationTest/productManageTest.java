package com.itlize.Joole.integrationTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.itlize.Joole.entity.Product;
import com.itlize.Joole.entity.Project;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;

@SpringBootTest
@AutoConfigureMockMvc
public class productManageTest {
    @Autowired
    MockMvc mockMvc;


    @Test
    public void login() throws Exception{

        String username = "username1";
        String password = "password";
        ObjectMapper objectMapper= new ObjectMapper();
        MvcResult mvcResult=mockMvc.perform(get("/user/login").param("username",username).param("password",password))
                .andExpect(content().contentType(MediaType.valueOf("text/plain;charset=ISO-8859-1"))).andReturn();
        String token=mvcResult.getResponse().getContentAsString();
        Files.writeString(Path.of("src/test/resources/jwt"),token,
                StandardCharsets.ISO_8859_1, StandardOpenOption.WRITE);
    }
    @Test
    public void addProduct() throws Exception{

        login();

        String token = Files.readString(Path.of("src/test/resources/jwt"), StandardCharsets.ISO_8859_1);

        Product product=new Product();
        product.setProductId(1);
        product.setProductName("productnamefortest");
        product.setBrand("brand");
        product.setType("searchType");
        product.setCertificate("certificate");
        product.setModelYear(2012);
        product.setAirflow(1);
        product.setApplication("application");
        product.setHeight(1);
        product.setMaxPower(1);
        product.setMountingLocation("location");
        product.setManufacturer("manufacturer");
        product.setUserType("usertype");
        product.setFanSweepDiameter(1);
        product.setCertificate("certificate");
        product.setAccessories("accessories");
        product.setType("nameforsearch");
        ObjectMapper objectMapper= JsonMapper.builder().addModule(new JavaTimeModule()).build();
        mockMvc.perform(post("/product/add_product").header("Authorization","Bearer "+token).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(product)));
    }


}
