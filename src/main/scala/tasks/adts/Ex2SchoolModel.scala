package tasks.adts
import u03.Sequences.*
import u03.Sequences.Sequence.*
import u03.Optionals.*
import u03.Optionals.Optional.*
import u02.AlgebraicDataTypes.Person

import scala.annotation.tailrec

/*  Exercise 2: 
 *  Implement the below trait, and write a meaningful test.
 *  Suggestion: 
 *  - reuse Sequences and Optionals as imported above
 *  - Course is a simple case classes with just the name
 *  - Teacher is a case class with name and sequence of courses
 *  - School is a case class with (sequences of) teachers and courses
 *  - add/set methods below create the new school 
 */

object SchoolModel:

  trait SchoolModule:
    type School
    type Teacher
    type Course
    extension (school: School)
      def addTeacher(name: String): School
      def addCourse(name: String): School
      def teacherByName(name: String): Optional[Teacher]
      def courseByName(name: String): Optional[Course]
      def nameOfTeacher(teacher: Teacher): String
      def nameOfCourse(course: Course): String
      //def setTeacherToCourse(teacher: Teacher, course: Course): School
      def coursesOfATeacher(teacher: Teacher): Sequence[Course]

  object BasicSchoolModule extends SchoolModule:
    type School = (Sequence[Teacher], Sequence[Course])
    type Teacher = (String, Sequence[Course])
    type Course = String

    extension (school: School)
      def addTeacher(name: String): School =
        var t: Teacher = (name, Nil())
        school match
          case (Cons(h, e), c) => (Cons(t, Cons(h, e)), c )
          case (Nil(), c) => (Cons(t, Nil()), c )

      def addCourse(name: String): School =
        school match
          case (i, c) => (i, Cons(name, c))


      @tailrec
      def loop[A](s: Sequence[A])(name: String): Optional[A]  = s match
        case Cons(h, t) => h match
          case (n, _) if n == name => Just(h)
          case (n, _) if n != name => loop(t)(name)
        case Nil() => Empty()

      def teacherByName(name: String): Optional[Teacher] = school match
        case (st, _) => loop(st)(name)

      @tailrec
      private def loopC[A](s: Sequence[A])(name: String): Optional[A] = s match
        case Cons(h, t) if h == name => Just(h)
        case Cons(h, t) if h != name => loopC(t)(name)
        case Nil() => Empty()

      def courseByName(name: String): Optional[Course] = school match
        case (_, sc) =>  loopC(sc)(name)

      def nameOfTeacher(teacher: Teacher): String = teacher match
        case (n, _) => n

      def nameOfCourse(course: Course): String =
        course

      //def setTeacherToCourse(teacher: Teacher, course: Course): School = ???

      def coursesOfATeacher(teacher: Teacher): Sequence[Course] = teacher match
        case (_, sc) => sc