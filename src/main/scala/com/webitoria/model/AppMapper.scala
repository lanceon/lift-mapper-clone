package com.webitoria

import net.liftweb.mapper.{LongKeyedMapper, LongKeyedMetaMapper}

/**
 * Additional layer on Mapper
 */

trait AppMapper[T <: AppMapper[T]] extends LongKeyedMapper[T]{
  self: T =>

  def getSingleton: AppMetaMapper[T] with LongKeyedMetaMapper[T]
}


trait AppMetaMapper[T <: AppMapper[T]] extends LongKeyedMetaMapper[T] {
  self: T =>

}

