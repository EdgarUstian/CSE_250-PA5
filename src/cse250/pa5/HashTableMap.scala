/**
 * cse250.pa5.HashTableMap.scala
 *
 * Copyright 2019 Andrew Hughes (ahughes6@buffalo.edu)
 *
 * This work is licensed under the Creative Commons
 * Attribution-NonCommercial-ShareAlike 4.0 International License.
 * To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-sa/4.0/.
 *
 * Submission author
 * UBIT: edgarust
 * Person#: 50230866
 *
 * Collaborators (include UBIT name of each, comma separated):
 * UBIT: edgarust
 */
package cse250.pa5

import scala.collection.mutable.ListBuffer
import scala.util.hashing.Hashing

class HashTableMap[K, V]()(implicit hash: Hashing[K]) extends cse250.objects.Map[K, V] {
  var n = 0
  var N = 10
  var alpha: Double = 0.0
  val alphaMax: Double = 0.6

  var bucketArray: Array[ListBuffer[(K, V)]] = Array.fill[ListBuffer[(K,V)]](N)(ListBuffer[(K,V)]())

  def rehash(newSize: Int): Unit = {
    if (newSize > N) {
      val oldBucketArray = bucketArray
      n = 0
      N = newSize
      bucketArray = Array.fill[ListBuffer[(K,V)]](N)(ListBuffer[(K,V)]())
      alpha = n / N
      for (bucket <- oldBucketArray; elem <- bucket) addOne(elem)
    }
  }

  override def addOne(elem: (K, V)): Unit = {
    //Create the placement indX using the hash method
    val insertIndX = hash.hash(elem._1) % N

  }

  override def get(key: K): Option[V] = {
    val lookupIndX = hash.hash(key) % N
    for (elem <- bucketArray(lookupIndX)) {
      if (elem._1 == key) return Some(elem._2)
    }
    None
  }

  override def iterator: Iterator[(K, V)] = new Iterator[(K, V)] {
    override def hasNext: Boolean = {
      false
    }

    override def next(): (K, V) = {

    }
  }
}
