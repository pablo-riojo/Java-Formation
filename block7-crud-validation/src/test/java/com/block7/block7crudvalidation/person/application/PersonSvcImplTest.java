package com.block7.block7crudvalidation.person.application;

import com.block7.block7crudvalidation.person.domain.Person;
import com.block7.block7crudvalidation.person.infrastructure.repository.PersonRepository;
import com.block7.block7crudvalidation.professor.domain.Professor;
import com.block7.block7crudvalidation.shared.exception.entityNotFound.EntityNotFoundException;
import com.block7.block7crudvalidation.shared.exception.unprocessableEntity.UnprocessableEntityException;
import com.block7.block7crudvalidation.student.domain.Student;
import com.block7.block7crudvalidation.student.infrastructure.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonSvcImplTest {
    @InjectMocks
    private PersonSvc underTest = new PersonSvcImpl();
    @Mock
    private PersonRepository repository;
    @Mock
    private StudentRepository studentRepository;

    private final UUID uuid = UUID.randomUUID();
    private final Person person = new Person();
    private final ArrayList<Person> personList = new ArrayList<>();
    private final PageImpl<Person> pagePerson = new PageImpl<>(personList);


    @BeforeEach
    void setUp() throws MalformedURLException {
        MockitoAnnotations.openMocks(this);


        person.setId(uuid);
        person.setUser("PabloRDev");
        person.setPassword("password");
        person.setName("Pablo");
        person.setSurname("Riojo");
        person.setEmail("pablo@test.com");
        person.setCompanyEmail("pablo@bosonit.com");
        person.setCity("Madrid");
        person.setImageUrl(new URL("https://github.com/maestre1056/EjerciciosAgosto/blob/main/ej15-testingNew/src/test/java/com/bosonit3/mongo/controller/person/application/PersonServiceImpTest.java"));
        person.setActive(true);

        repository.save(person);
        personList.add(person);
    }

    @Test
    void findAll() {
        underTest.findAll();

        verify(repository).findAll();
        when(underTest.findAll()).thenReturn(personList);
        assertNotNull(underTest.findAll());
        assertThat(underTest.findAll()).isExactlyInstanceOf(ArrayList.class);
        assertEquals(personList.size(), underTest.findAll().size());
    }

    @Test
    void findAllPaginated() {
        underTest.findAllPaginated(0, 10);

        verify(repository).findAll(PageRequest.of(0, 10));
        when(repository.findAll(PageRequest.of(0, 10))).thenReturn(pagePerson);
        assertNotNull(underTest.findAllPaginated(0, 10));
        assertThat(underTest.findAllPaginated(0, 10)).isExactlyInstanceOf(PageImpl.class);
    }

    @Test
    void findById() {
        given(repository.findById(person.getId())).willReturn(Optional.of(person));

        underTest.findById(person.getId());

        verify(repository).findById(person.getId());
        assertInstanceOf(Person.class, underTest.findById(person.getId()));
    }

    @Test
    void findByUser() {
        given(repository.findByUser(person.getUser())).willReturn(Optional.of(person));

        underTest.findByUser(person.getUser());

        verify(repository).findByUser(person.getUser());
        assertInstanceOf(Person.class, underTest.findByUser(person.getUser()));
    }

    @Test
    void findByName() {
        given(repository.findByName(person.getName())).willReturn(Optional.of(personList));

        underTest.findByName(person.getName());

        verify(repository).findByName(person.getName());
        assertInstanceOf(List.class, underTest.findByName(person.getName()));
    }

    @Test
    void findBySurname() {
        given(repository.findBySurname(person.getSurname())).willReturn(Optional.of(personList));

        underTest.findBySurname(person.getSurname());

        verify(repository).findBySurname(person.getSurname());
        assertInstanceOf(List.class, underTest.findBySurname(person.getSurname()));
    }

    @Test
    void findByGreaterCreation() {
        given(repository.findByGreaterCreation(person.getCreatedAt())).willReturn(Optional.of(personList));

        underTest.findByGreaterCreation(person.getCreatedAt());

        verify(repository).findByGreaterCreation(person.getCreatedAt());
        assertInstanceOf(List.class, underTest.findByGreaterCreation(person.getCreatedAt()));
    }

    @Test
    void findByLowerCreation() {
        given(repository.findByLowerCreation(person.getCreatedAt())).willReturn(Optional.of(personList));

        underTest.findByLowerCreation(person.getCreatedAt());

        verify(repository).findByLowerCreation(person.getCreatedAt());
        assertInstanceOf(List.class, underTest.findByLowerCreation(person.getCreatedAt()));
    }

    @Test
    void findProfessor() {
        Professor professor = new Professor();
        given(repository.findById(person.getId())).willReturn(Optional.of(person));
        person.setIsProfessor(true);

        underTest.findProfessor(person.getId());

        verify(repository).findById(person.getId());
    }

    @Test
    void findIsStudent() {
        given(repository.findById(person.getId())).willReturn(Optional.of(person));
        person.setIsStudent(true);

        underTest.save(person);

        assertThat(underTest.findById(person.getId()).getIsStudent()).isEqualTo(true);
    }

    @Test
    void idNotFoundThrowEntityNotFoundException() {
        assertThrows(EntityNotFoundException.class, () -> underTest.findById(person.getId()));
        verify(repository).findById(person.getId());
    }

    @Test
    void update() {
        given(repository.findById(person.getId())).willReturn(Optional.of(person));

        Person newPerson = new Person();

        newPerson.setId(person.getId());
        newPerson.setName("Paco");
        newPerson.setSurname("Porras");
        newPerson.setUser("PacoPorr");
        newPerson.setPassword("password");
        newPerson.setEmail(person.getEmail());
        newPerson.setCompanyEmail(person.getCompanyEmail());
        newPerson.setCity("Sevilla");
        newPerson.setActive(person.getActive());
        newPerson.setImageUrl(person.getImageUrl());


        underTest.update(newPerson, person.getId());

        verify(repository).save(newPerson);
        assertInstanceOf(Person.class, underTest.findById(person.getId()));
        assertEquals(underTest.save(newPerson), repository.save(newPerson));
    }

    @Test
    void throwsExceptionWhenChangeEmailInUpdate() {
        Person newPerson = new Person();
        newPerson.setId(person.getId());
        newPerson.setName(person.getName());
        newPerson.setSurname(person.getSurname());
        newPerson.setUser(person.getUser());
        newPerson.setPassword(person.getPassword());
        newPerson.setEmail("xxx@test.com");
        newPerson.setCompanyEmail(person.getCompanyEmail());
        newPerson.setCity(person.getCity());
        newPerson.setActive(person.getActive());
        newPerson.setImageUrl(person.getImageUrl());

        given(repository.findById(person.getId())).willReturn(Optional.of(person));

        assertThatThrownBy(() -> underTest.update(newPerson, person.getId()))
                .isInstanceOf(UnprocessableEntityException.class)
                .hasMessageContaining("Cannot update. It must be same email: " + person.getEmail());
    }

    @Test
    void delete() {
        given(repository.findById(person.getId())).willReturn(Optional.of(person));

        underTest.delete(person.getId());
        verify(repository).deleteById(person.getId());
        assertThat(person).isNotIn(repository.findById(person.getId()));
    }

    @Test
    void deleteIsStudent() {
        given(repository.findById(person.getId())).willReturn(Optional.of(person));
        given(studentRepository.findByPersonId(person.getId())).willReturn( Optional.of(new Student()));

        person.setIsStudent(true);

        underTest.delete(person.getId());
        verify(repository).deleteById(person.getId());
    }

    @Test
    void save() {
        ArgumentCaptor<Person> personArgumentCaptor =
                ArgumentCaptor.forClass(Person.class);

        underTest.save(person);

        verify(repository, atLeastOnce())
                .save(personArgumentCaptor.capture());
        when(underTest.save(person)).thenReturn(person);
        assertEquals(repository.save(person), underTest.save(person));
        assertEquals(
                personArgumentCaptor.getValue().getEmail(),
                person.getEmail()
        );
    }
}