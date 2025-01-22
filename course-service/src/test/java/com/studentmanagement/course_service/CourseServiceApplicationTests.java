package com.studentmanagement.course_service;

import com.studentmanagement.course_service.converter.CourseDtoConverter;
import com.studentmanagement.course_service.model.Course;
import com.studentmanagement.course_service.repository.CourseRepository;
import com.studentmanagement.course_service.request.RequestForCreateCourse;
import com.studentmanagement.course_service.service.CourseService;
import org.example.CourseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseServiceApplicationTests {

	private CourseService courseService;
	private CourseRepository courseRepository;
	private CourseDtoConverter converter;

	@BeforeEach
	public void setUp(){

		courseRepository = Mockito.mock(CourseRepository.class);
		converter = Mockito.mock(CourseDtoConverter.class);
		courseService = new CourseService(courseRepository,converter);
	}

	@Test
	void shouldReturnCourseDto_WhenCourseNotExistInCourseRepository() {

		RequestForCreateCourse request=new RequestForCreateCourse("Math");
		Course course=new Course("Math");
		CourseDto courseDto=new CourseDto("Math",new ArrayList<>());

		Mockito.when(courseRepository.findByName(request.getCourseName())).thenReturn(Optional.empty());
		Mockito.when(courseRepository.save(course)).thenReturn(course);
		Mockito.when(converter.convert(course)).thenReturn(courseDto);

		CourseDto result=courseService.createNewCourse(request);

		assertEquals(courseDto,result);

		Mockito.verify(courseRepository).findByName(request.getCourseName());
		Mockito.verify(converter).convert(course);

	}

	@Test
	void shouldThrowException_WhenCourseAlreadyExist(){

		RequestForCreateCourse request=new RequestForCreateCourse("Math");

		Mockito.when(courseRepository.findByName(request.getCourseName())).thenThrow(new IllegalArgumentException("Already Exist"));

		assertThrows(IllegalArgumentException.class,
				()->courseService.createNewCourse(request));

		Mockito.verify(courseRepository).findByName(request.getCourseName());
	}

	@Test
	void shouldReturnCourseDto_WhenFindCourseByCourseId(){

		String courseId="test";
		Course course=new Course("Math");
		CourseDto courseDto=new CourseDto("Math",new ArrayList<>());

		Mockito.when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));
		Mockito.when(converter.convert(course)).thenReturn(courseDto);

		CourseDto result=courseService.getCourseById(courseId);

		assertEquals(courseDto,result);

		Mockito.verify(courseRepository).findById(courseId);
		Mockito.verify(converter).convert(course);

	}

	@Test
	void shouldThrowException_WhenFindCourseByCourseId(){
		String courseId="test";

		Mockito.when(courseRepository.findById(courseId)).thenThrow(new IllegalArgumentException());

		assertThrows(IllegalArgumentException.class,
				()->courseService.getCourseById(courseId));

		Mockito.verify(courseRepository).findById(courseId);
	}

	@Test
	void shouldReturnCourseDto_WhenRequestForAddStudentOfExistingCourse(){

		String courseId = "testCourseId";
		String studentNumber = "testStudentNumber";

		List<String> initialStudents = new ArrayList<>();
		List<String> updatedStudents = new ArrayList<>(initialStudents);
		updatedStudents.add(studentNumber);

		Course course = new Course("Math", initialStudents);
		Course updatedCourse = new Course("Math", updatedStudents);

		CourseDto courseDto = new CourseDto("Math", updatedStudents);

		Mockito.when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));
		Mockito.when(courseRepository.save(course)).thenReturn(updatedCourse);
		Mockito.when(converter.convert(updatedCourse)).thenReturn(courseDto);

		CourseDto result = courseService.addStudentOfCourse(courseId, studentNumber);

		assertEquals(courseDto, result);
		Mockito.verify(courseRepository).findById(courseId);
		Mockito.verify(courseRepository).save(course);
		Mockito.verify(converter).convert(updatedCourse);

	}

	@Test
	void shouldThrowException_WhenStudentAlreadyInCourse() {

		String courseId = "testCourseId";
		String studentNumber = "testStudentNumber";

		List<String> students = new ArrayList<>(List.of(studentNumber));

		Course course = new Course("Math", students);

		Mockito.when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));


		RuntimeException exception = assertThrows(RuntimeException.class,
				() -> courseService.addStudentOfCourse(courseId, studentNumber));

		assertEquals("This student already take this course ! ", exception.getMessage());
		Mockito.verify(courseRepository).findById(courseId);
		Mockito.verifyNoInteractions(converter);
		Mockito.verifyNoMoreInteractions(courseRepository);
	}

}
