package com.se.team00.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;

import com.se.team00.demo.entity.StudentEntity;
import com.se.team00.demo.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DemoApplicationTests {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TestEntityManager entityManager;

    private Validator validator;

    @Before
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testStudentFirstNameCannotBeNull() {
        StudentEntity s = new StudentEntity();
        s.setFirstName(null);
        s.setLastName("Abcd");
        s.setStudentId("B59000000");

        try {
            entityManager.persist(s);
            entityManager.flush();

            fail("Should not pass to this line");
        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
        }
    }

    @Test
    public void testStudentLastNameCannotBeNull() {
        StudentEntity s = new StudentEntity();
        s.setFirstName("Cheese");
        s.setLastName(null);
        s.setStudentId("B59000000");

        try {
            entityManager.persist(s);
            entityManager.flush();

            fail("Should not pass to this line");
        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
        }
    }

    @Test
    public void testStudentIDNotABC() {
        StudentEntity s = new StudentEntity();
        s.setFirstName("zzzz");
        s.setLastName("Abcd");
        s.setStudentId("D59000000");

        try {
            entityManager.persist(s);
            entityManager.flush();

            fail("Should not pass to this line");
        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
        }
    }

    @Test
    public void testStudentIDSize() {
        StudentEntity s = new StudentEntity();
        s.setFirstName("zzzz");
        s.setLastName("Abcd");
        s.setStudentId("B5900000000");

        try {
            entityManager.persist(s);
            entityManager.flush();

            fail("Should not pass to this line");
        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
        }
    }

    @Test
    public void testStudentIDNotInt() {
        StudentEntity s = new StudentEntity();
        s.setFirstName("zzzz");
        s.setLastName("Abcd");
        s.setStudentId("B5900C0000");

        try {
            entityManager.persist(s);
            entityManager.flush();

            fail("Should not pass to this line");
        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
        }
    }

    @Test(expected = javax.persistence.PersistenceException.class)
    public void testIdMustBeUnique() {
        StudentEntity s1 = new StudentEntity();
        s1.setFirstName("Abcd");
        s1.setLastName("Abcd");
        s1.setStudentId("B59000000");
        entityManager.persist(s1);
        entityManager.flush();

        StudentEntity s2 = new StudentEntity();
        s2.setFirstName("Defg");
        s2.setLastName("Defg");
        s2.setStudentId("B59000000");

        entityManager.persist(s2);
        entityManager.flush();

        fail("Should not pass to this line");
    }


}