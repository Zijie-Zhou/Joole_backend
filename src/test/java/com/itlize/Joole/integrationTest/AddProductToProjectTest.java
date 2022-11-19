package com.itlize.Joole.integrationTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itlize.Joole.entity.Project;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
public class AddProductToProjectTest {

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
    public void addProductToProject() throws Exception{
        login();

        String productId = "4";
        String projectId = "1";

        String token=Files.readString(Path.of("src/test/resources/jwt"), StandardCharsets.ISO_8859_1);
        MvcResult mvcResult=mockMvc.perform(post("/project/add_product_to_project")
                .header("Authorization","Bearer "+token).param("project_id", projectId).param("product_id",productId)).andReturn();
        String type=mvcResult.getResponse().getContentType();
        String response=mvcResult.getResponse().getContentAsString();
        System.out.println(type+response);
    }

}
