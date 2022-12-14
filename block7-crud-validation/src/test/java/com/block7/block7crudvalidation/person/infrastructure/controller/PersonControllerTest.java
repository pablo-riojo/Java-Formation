package com.block7.block7crudvalidation.person.infrastructure.controller;

import com.block7.block7crudvalidation.person.domain.Person;
import com.block7.block7crudvalidation.person.infrastructure.dto.PersonInputDTO;
import com.block7.block7crudvalidation.person.infrastructure.dto.PersonMapper;
import com.block7.block7crudvalidation.person.infrastructure.dto.PersonOutputDTO;
import com.block7.block7crudvalidation.person.infrastructure.repository.PersonRepository;
import com.block7.block7crudvalidation.professor.application.ProfessorSvcImpl;
import com.block7.block7crudvalidation.professor.domain.Professor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PersonControllerTest {
    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @Autowired private PersonRepository repository;
    @Autowired private ProfessorSvcImpl professorSvc;

    private final UUID uuid = UUID.randomUUID();
    private final Person person = new Person();
    private final Professor professor = new Professor();

    private static final PersonInputDTO personInputDTO = new PersonInputDTO();
    private final PersonOutputDTO personOutputDTO = new PersonOutputDTO();
    private final List<PersonOutputDTO> outputDTOList = new ArrayList<>();

    @BeforeEach
    void setUp() throws MalformedURLException {
        personInputDTO.setUser("PabloRDev");
        personInputDTO.setPassword("password");
        personInputDTO.setName("Pablo");
        personInputDTO.setSurname("Riojo");
        personInputDTO.setEmail("pablo@test.com");
        personInputDTO.setCompanyEmail("pablo@bosonit.com");
        personInputDTO.setCity("Madrid");
        personInputDTO.setImageUrl(new URL("https://github.com/maestre1056/EjerciciosAgosto/blob/main/ej15-testingNew/src/test/java/com/bosonit3/mongo/controller/person/application/PersonServiceImpTest.java"));
        personInputDTO.setActive(true);

        professor.setBranch("Frontend");
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void getAllPersons() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/person/all")
                .accept(MediaType.APPLICATION_JSON);


        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    void getAllPaginated() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/person?offset=0&pageSize=10")
                .accept(MediaType.APPLICATION_JSON);


        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    void getPersonById() throws Exception {
        UUID id = repository.save(PersonMapper.Instance.personInputDTOToPerson(personInputDTO)).getId();

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/person/" + id)
                .accept(MediaType.APPLICATION_JSON);


        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    void getPersonByUser() throws Exception {
        String user = repository.save(PersonMapper.Instance.personInputDTOToPerson(personInputDTO)).getUser();

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/person/user/" + user)
                .accept(MediaType.APPLICATION_JSON);


        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    void getPersonByName() throws Exception {
        String name = repository.save(PersonMapper.Instance.personInputDTOToPerson(personInputDTO)).getName();

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/person/name/" + name)
                .accept(MediaType.APPLICATION_JSON);


        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    void getPersonBySurname() throws Exception {
        String surname = repository.save(PersonMapper.Instance.personInputDTOToPerson(personInputDTO)).getSurname();

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/person/surname/" + surname)
                .accept(MediaType.APPLICATION_JSON);


        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    void getPersonByCreation() throws Exception {
        Date date = repository.save(PersonMapper.Instance.personInputDTOToPerson(personInputDTO)).getCreatedAt();

        RequestBuilder afterRequestBuilder = MockMvcRequestBuilders
                .get("/person/creation?after=10/10/2010")
                .accept(MediaType.APPLICATION_JSON);
        RequestBuilder beforeRequestBuilder = MockMvcRequestBuilders
                .get("/person/creation?before=10/10/2010")
                .accept(MediaType.APPLICATION_JSON);


        mockMvc.perform(afterRequestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        mockMvc.perform(beforeRequestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    void getProfessor() throws Exception {
        professor.setPerson(PersonMapper.Instance.personInputDTOToPerson(personInputDTO));
        Professor professorDb = professorSvc.save(professor);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/person/professor/" + professorDb.getPerson().getId())
                .accept(MediaType.APPLICATION_JSON);


        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    void createPerson() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/person")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(personInputDTO));

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();
    }

    @Test
    void updatePerson() throws Exception {
        Person person = repository.save(PersonMapper.Instance.personInputDTOToPerson(personInputDTO));
        personInputDTO.setName("PRD");
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/person/" + person.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(personInputDTO));

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();
    }

    @Test
    void deletePersonById() throws Exception {
        Person person = repository.save(PersonMapper.Instance.personInputDTOToPerson(personInputDTO));
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/person/" + person.getId())
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}