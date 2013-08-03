package com.webitoria.snippet

import com.webitoria.model.Color
import net.liftweb.common.Loggable
import net.liftweb.util.Helpers
import Helpers._

/**
 * Created with IntelliJ IDEA.
 * User: User
 * Date: 03.08.13
 * Time: 16:54
 * To change this template use File | Settings | File Templates.
 */
class CloneTestSnippet extends Loggable {

  def render = {

    Color.bulkDelete_!!()

    val colors = Seq("red", "green", "blue").map(c => Color.create.name(c).saveMe())
    logger.info("colors = " + colors)

    val clones = colors.map(c => Color.cloneInstance(c))
    logger.info("clones = " + clones)

    "#orig *" #> colors.mkString("\n") &
    "#copy *" #> clones.mkString("\n")

  }

}
