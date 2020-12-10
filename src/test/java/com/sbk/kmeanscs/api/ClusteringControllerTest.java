package com.sbk.kmeanscs.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbk.kmeanscs.api.request.GenerateRequest;
import com.sbk.kmeanscs.api.response.GenerateResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class ClusteringControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void generateData_validRequest() throws Exception {
        GenerateRequest content = new GenerateRequest(3,
                new int[][]{{100, 200}, {100, 200},
                            {350, 550}, {350, 550},
                            {2000, 2500}, {2000, 2500}});
        MvcResult result = this.mockMvc
                .perform(post("/generatedata")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(content))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        GenerateResponse res = objectMapper.readValue(result.getResponse().getContentAsByteArray(), GenerateResponse.class);
        assertTrue(res.data.length > 1);

    }

    @Test
    public void generateData_noBounds() throws Exception {
        GenerateRequest content = new GenerateRequest(3, null);
        this.mockMvc
                .perform(post("/generatedata")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(content))
                )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}