package com.studentmanagement.note_service;

import com.studentmanagement.note_service.converter.DtoConverter;
import com.studentmanagement.note_service.model.Grade;
import com.studentmanagement.note_service.repository.GradeRepository;
import com.studentmanagement.note_service.request.RequestForCreateNote;
import com.studentmanagement.note_service.service.GradeService;
import org.example.GradeDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NoteServiceApplicationTests {

	private GradeService gradeService;
	private GradeRepository gradeRepository;
	private DtoConverter converter;

	@BeforeEach
	public void setUp(){

		gradeRepository = Mockito.mock(GradeRepository.class);
		converter = Mockito.mock(DtoConverter.class);
		gradeService = new GradeService(gradeRepository,converter);
	}

	@Test
	void shouldAddGradeAndReturnGradeDto_WhenGradeDoesNotExistForStudent() {

		String courseId = "testCourseId";
		String studentId = "testStudentId";
		String studentNumber="testStudentNumber";
		String courseName="testCourseName";
		int grade = 85;
		RequestForCreateNote request = new RequestForCreateNote(grade);

		Grade newGrade = new Grade(grade, studentId, courseId);
		GradeDto gradeDto = new GradeDto(studentNumber,courseName,grade,"BB");

		Mockito.when(gradeRepository.findByStudentIdAndCourseId(studentId, courseId)).thenReturn(Optional.empty());
		Mockito.when(gradeRepository.save(newGrade)).thenReturn(newGrade);
		Mockito.when(converter.convert(newGrade)).thenReturn(gradeDto);

		GradeDto result = gradeService.addGradeOfStudent(courseId, studentId, request);

		assertEquals(gradeDto, result);
		Mockito.verify(gradeRepository).findByStudentIdAndCourseId(studentId, courseId);
		Mockito.verify(gradeRepository).save(newGrade);
		Mockito.verify(converter).convert(newGrade);
	}

	@Test
	void shouldUpdateGradeAndReturnGradeDto_WhenGradeAlreadyExistsForStudent() {

		String courseId = "testCourseId";
		String studentId = "testStudentId";
		String studentNumber="testStudentNumber";
		String courseName="testCourseName";
		int newGrade = 90;
		RequestForCreateNote request = new RequestForCreateNote(newGrade);

		Grade existingGrade = new Grade(80, studentId, courseId); // Önceden 80 alınmış
		existingGrade.updateGrade(newGrade); // Yeni notu güncelliyoruz
		existingGrade.upgradeLetterGrade(existingGrade.getGrade()); // Harf notu güncelleniyor

		GradeDto gradeDto = new GradeDto(studentNumber,courseName,newGrade,"BB");

		Mockito.when(gradeRepository.findByStudentIdAndCourseId(studentId, courseId)).thenReturn(Optional.of(existingGrade));
		Mockito.when(gradeRepository.save(existingGrade)).thenReturn(existingGrade);
		Mockito.when(converter.convert(existingGrade)).thenReturn(gradeDto);

		GradeDto result = gradeService.addGradeOfStudent(courseId, studentId, request);

		assertEquals(gradeDto, result);
		Mockito.verify(gradeRepository).findByStudentIdAndCourseId(studentId, courseId);
		Mockito.verify(gradeRepository).save(existingGrade);
		Mockito.verify(converter).convert(existingGrade);
	}


}
