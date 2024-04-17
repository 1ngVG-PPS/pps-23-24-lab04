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

  val school: School = (Cons(t1, Cons(t2, Cons(t3, Nil()))), Cons(course1, Cons(course2, Cons(course3, Nil()))))

@Test def addTeacher() = ???
@Test def addCourse() = ???
@Test def teacherByName() = ???
@Test def courseByName() = ???
@Test def nameOfTeacher() = ???
@Test def nameOfCourse() = ???
@Test def setTeacherToCourse() = ???
@Test def coursesOfATeacher() = ???
