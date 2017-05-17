package ru.javawebinar.topjava.web;

import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static ru.javawebinar.topjava.UserTestData.USER;
import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

/**
 * Created by Валера on 14.05.2017.
 */
public class ResourceControllerTest extends AbstractControllerTest {

    public void testStyles() throws Exception {

        mockMvc.perform(get("/resources/css/style.css"))
               .andDo(print())
                .andExpect(status().isOk());
                //.andExpect(content().contentType(MediaType.TEXT_HTML));

    }

}
