package ru.job4j.auth;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.auth.model.Person;
import ru.job4j.auth.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class PersonControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonRepository persons;

    @Test
    @WithMockUser
    public void shouldCreatePerson() throws Exception {
        Mockito.when(persons.save(Mockito.any())).thenReturn(new Person(1, "User_1", "123"));
        this.mockMvc.perform(post("/person/")
                .contentType("application/json")
                .content("{\"login\":\"User_1\",\"password\":\"123\"}"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string("{\"id\":1,\"login\":\"User_1\",\"password\":\"123\"}"));
    }

    @Test
    @WithMockUser
    public void shouldReturnPersonById() throws Exception {
        String expected = "{\"id\":1,\"login\":\"User_1\",\"password\":\"123\"}";
        Mockito.when(persons.findById(Mockito.any()))
                .thenReturn(Optional.of(new Person(1, "User_1", "123")));
        this.mockMvc.perform(get("/person/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(expected));
    }

    @Test
    @WithMockUser
    public void shouldReturnAllPersons() throws Exception {
        List<Person> listPersons = List.of(new Person(1, "User_1", "123"),
                new Person(2, "User_2", "123"));
        String expected = "[{\"id\":1,\"login\":\"User_1\",\"password\":\"123\"},"
                + "{\"id\":2,\"login\":\"User_2\",\"password\":\"123\"}]";
        Mockito.when(persons.findAll()).thenReturn(listPersons);
        this.mockMvc.perform(get("/person/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(expected));
    }

    @Test
    @WithMockUser
    public void whenPersonNotFound() throws Exception {
        Mockito.when(persons.findById(Mockito.any())).thenReturn(Optional.empty());
        this.mockMvc.perform(get("/person/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser
    public void shouldUpdatePerson() throws Exception {
        this.mockMvc.perform(put("/person/")
                .contentType("application/json")
                .content("{\"id\":1,\"login\":\"updateUser\",\"password\":\"123\"}"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void shouldDeletePersonById() throws Exception {
        this.mockMvc.perform(delete("/person/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
