package u04lab
import u03.Sequences.* 
import Sequence.*

import u03.Optionals.*
import Optional.*

/*  Exercise 5: 
 *  - Generalise by ad-hoc polymorphism logAll, such that:
 *  -- it can be called on Sequences but also on Optional, or others... 
 *  -- it does not necessarily call log, but any function with analogous type
 *  - Hint: introduce a type class Traversable[T[_]]], capturing the ability of calling a
 *    "consumer function" on all elements (with type A) of a datastructure T[A] 
 *    Note Traversable is a 2-kinded trait (similar to Filterable, or Monad)
 *  - Write givens for Traversable[Optional] and Traversable[Sequence]
 *  - Show you can use the generalisation of logAll to:
 *  -- log all elements of an Optional, or of a Traversable
 *  -- println(_) all elements of an Optional, or of a Traversable
 */

object Ex5Traversable:

  def log[A](a: A): Unit = println("The next element is: "+a)

  def logAll[A](seq: Sequence[A]): Unit = seq match
    case Cons(h, t) => log(h); logAll(t)
    case _ => ()

  trait Traversable[T[_]]:
    def traverse[A](ta: T[A], f: A => Unit): Unit

  given Traversable[Optional] with
    def traverse[A](opt: Optional[A], f: A => Unit): Unit = opt match
      case Just(a) => f(a)
      case Empty() => ()
  
  given Traversable[Sequence] with
    def traverse[A](seq: Sequence[A], f: A => Unit): Unit = seq match
      case Cons(h, t) => f(h); traverse(t, f)
      case _ => ()

  object Ex5Traversable:
    def log[A](a: A): Unit = println("The next element is: " + a)

    def logAll[T[_] : Traversable, A](data: T[A]): Unit =
      summon[Traversable[T]].traverse(data, log)

  val optionalData: Optional[Int] = Just(10)
  Ex5Traversable.logAll(optionalData)

  val sequenceData: Sequence[Int] = Cons(1, Cons(2, Cons(3, Nil())))
  Ex5Traversable.logAll(sequenceData)
