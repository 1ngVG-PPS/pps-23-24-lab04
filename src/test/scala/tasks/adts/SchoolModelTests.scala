package tasks.adts

import org.junit.*
import org.junit.Assert.*
import tasks.adts.SchoolModel.*
import tasks.adts.SchoolModel.BasicSchoolModule
import tasks.adts.SchoolModel.BasicSchoolModule.*
import u03.Sequences.*
import u03.Sequences.Sequence.*
import u03.Optionals.*
import u03.Optionals.Optional.*

class SchoolTests:
  val schoolModule: SchoolModule = BasicSchoolModule

  val course1: Course = "pps"
  val course2: Course = "ppc"
  val course3: Course = "Cyber"

  val t1: Teacher = ( "Viroli", Cons(course1, Nil()))
  val t2: Teacher = ( "Ricci", Cons(course2, Nil()))
  val t3: Teacher = ( "D'Angelo", Cons(course3, Nil()))

  var school: School = (Cons(t1, Cons(t2, Cons(t3, Nil()))), Cons(course1, Cons(course2, Cons(course3, Nil()))))
  var school2: School =(Cons(t1, Nil()), Cons(course2,Cons(course1, Nil())))

  @Test def addTeacherTest() =
   assertEquals(school.addTeacher("Valerio"), (Cons(("Valerio", Nil()), Cons(t1, Cons(t2, Cons(t3, Nil())))), Cons(course1, Cons(course2, Cons(course3, Nil())))) )

  @Test def addCourseTest() =
    assertEquals(school.addCourse("Corsone Bellone"), (Cons(t1, Cons(t2, Cons(t3, Nil()))), Cons("Corsone Bellone", Cons(course1, Cons(course2, Cons(course3, Nil()))))))

  @Test def teacherByNameTest() =
    assertEquals(school.teacherByName("Viroli"), Just(t1))

  @Test def courseByNameTest() =
    assertEquals(school.courseByName("pps"), Just(course1))

  @Test def nameOfTeacherTest() =
    assertEquals(school.nameOfTeacher(t1), "Viroli")

  @Test def nameOfCourseTest() =
    assertEquals(school.nameOfCourse(course1), "pps")

  //@Test def setTeacherToCourseTest() =
    //assertEquals(school2.setTeacherToCourse(t1, course2), Cons(("Viroli", Cons(course2, Cons(course1, Nil())), Nil()), Cons(course2, Cons(course1, Nil()))))

  @Test def coursesOfATeacherTest() =
    assertEquals(school.coursesOfATeacher(t1), Cons("pps", Nil()))
