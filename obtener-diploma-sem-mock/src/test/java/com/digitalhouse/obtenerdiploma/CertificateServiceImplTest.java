package com.digitalhouse.obtenerdiploma;

import com.digitalhouse.obtenerdiploma.dto.CertificateDTO;
import com.digitalhouse.obtenerdiploma.dto.StudentDTO;
import com.digitalhouse.obtenerdiploma.dto.SubjectDTO;
import com.digitalhouse.obtenerdiploma.service.CertificateServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
class CertificateServiceImplTest {

    CertificateServiceImpl certificateService = new CertificateServiceImpl();

    static StudentDTO studentDTO;

    @BeforeAll
    static void init(){
        studentDTO = new StudentDTO();
        List<SubjectDTO> subjects = new ArrayList<>();
        subjects.add(new SubjectDTO("Banco de dados", 8));
        subjects.add(new SubjectDTO("Estrutura de dados lineares", 7));
        subjects.add(new SubjectDTO("Matematica aplicada", 6));
        studentDTO.setName("Gustavo");
        studentDTO.setSubjects(subjects);
    }

    @Test
    void testAnalyzeNotesAverageAssertEquals(){
        //arrange
        CertificateDTO certificateDTO = certificateService.analyzeNotes(studentDTO);

        //act
        double average = certificateDTO.getAverage();

        //assert
        assertEquals(average, 7.0);
    }

    @Test
    void testAnalyzeNotesAverageAssertNotEquals() {
        List<SubjectDTO> subjects = new ArrayList<>();
        subjects.add(new SubjectDTO("Banco de dados", 8));
        subjects.add(new SubjectDTO("Estrutura de dados lineares", 7));
        subjects.add(new SubjectDTO("Matematica aplicada", 6));

        StudentDTO studentDTO = new StudentDTO("Gustavo", subjects);

        CertificateDTO certificateDTO = certificateService.analyzeNotes(studentDTO);

        double average = certificateDTO.getAverage();

        assertNotEquals(average, 7.30);
    }

    @Test
    void testCalculateAverageAssertEquals(){
        List<SubjectDTO> subjects = new ArrayList<>();
        subjects.add(new SubjectDTO("Banco de dados", 8));
        subjects.add(new SubjectDTO("Estrutura de dados lineares", 7));
        subjects.add(new SubjectDTO("Matematica aplicada", 6));

        StudentDTO studentDTO = new StudentDTO("Gustavo", subjects);

        double average = certificateService.calculateAverage(studentDTO);

        assertEquals(average, 7.0);
    }

    @Test
    void testCalculateAverageAssertNotEquals(){
        List<SubjectDTO> subjects = new ArrayList<>();
        subjects.add(new SubjectDTO("Banco de dados", 8));
        subjects.add(new SubjectDTO("Estrutura de dados lineares", 7));
        subjects.add(new SubjectDTO("Matematica aplicada", 6));

        StudentDTO studentDTO = new StudentDTO("Gustavo", subjects);

        double average = certificateService.calculateAverage(studentDTO);

        assertNotEquals(average, 7.50);
    }

    @Test
    void testWriteDiplomaAverageAssertEquals(){
        List<SubjectDTO> subjects = new ArrayList<>();
        subjects.add(new SubjectDTO("Banco de dados", 8));
        subjects.add(new SubjectDTO("Estrutura de dados lineares", 7));
        subjects.add(new SubjectDTO("Matematica aplicada", 6));

        StudentDTO studentDTO = new StudentDTO("Gustavo", subjects);

        String textDiploma = certificateService.writeDiploma(studentDTO);

        assertEquals(textDiploma, "Gustavo usted ha conseguido el promedio de 7.0");
    }

    @Test
    void testWriteDiplomaAverageAssertNotEquals(){
        List<SubjectDTO> subjects = new ArrayList<>();
        subjects.add(new SubjectDTO("Banco de dados", 8));
        subjects.add(new SubjectDTO("Estrutura de dados lineares", 7));
        subjects.add(new SubjectDTO("Matematica aplicada", 6));

        StudentDTO studentDTO = new StudentDTO("Gustavo", subjects);

        String textDiploma = certificateService.writeDiploma(studentDTO);

        assertNotEquals(textDiploma, "Andre usted ha conseguido el promedio de 7.20");
    }

    @Test
    void testWithHonorsAssertEquals(){

        String textHonors = certificateService.withHonors(7.25, "Gustavo");

        assertEquals(textHonors, "¡Felicitaciones Gustavo! Usted tiene el gran promedio de 7.25");
    }
    @Test
    void testWithHonorsAssertNotEquals(){

        String textHonors = certificateService.withHonors(9.0, "Gustavo");

        assertNotEquals(textHonors, "¡Felicitaciones Gustavo! Usted tiene el gran promedio de 9.50");
    }
}
