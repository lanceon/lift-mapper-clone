package com.webitoria

import net.liftweb.mapper.BaseMappedField

/**
 * For cloning a mapper instance.
 * Copies everything from an existing instance to a new instance (except the primary key)
 *
 * See: https://groups.google.com/forum/#!msg/liftweb/i2DODLi2b3A/Er4I__uGjBMJ
 */
trait ClonableMetaMapper[M <: AppMapper[M]] {
  self: AppMetaMapper[M] =>

  def cloneInstance(in: M): M = {
    // the new instance
    val out = create

    // copy from existing instance to new instance
    def cp(bmf: BaseMappedField) {
      for { imf <- in.fieldByName[M](bmf.name); omf <- out.fieldByName[M](bmf.name) }
        omf.apply(imf.is)
    }
    mappedFields.filterNot(bmf => columnPrimaryKey_?(bmf.name)).foreach(cp _)

    out // return the new instance
  }

}

trait ClonableMapper[M <: AppMapper[M]] {
  self: AppMapper[M] {
    def getSingleton: AppMetaMapper[M] with ClonableMetaMapper[M]
  } =>

  override def clone() = getSingleton.cloneInstance(this)
}