package com.webitoria.model

import com.webitoria.{ClonableMapper, ClonableMetaMapper, AppMetaMapper, AppMapper}
import net.liftweb.mapper.{MappedString, LongKeyedMetaMapper, IdPK}

/**
 * Created with IntelliJ IDEA.
 * User: User
 * Date: 03.08.13
 * Time: 16:55
 * To change this template use File | Settings | File Templates.
 */
class Color extends AppMapper[Color]
  with ClonableMapper[Color]
  with IdPK
{
  def getSingleton = Color

  object name extends MappedString(this, 32)
}

object Color extends Color
  with AppMetaMapper[Color]
  with ClonableMetaMapper[Color]
{

}
