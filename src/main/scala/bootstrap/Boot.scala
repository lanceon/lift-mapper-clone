package bootstrap.liftweb

import net.liftweb.http.{S, Html5Properties, LiftRules, Req}
import net.liftweb.sitemap.{Menu, SiteMap}
import net.liftweb.db.{DefaultConnectionIdentifier, DB, StandardDBVendor}
import net.liftweb.util.Props
import net.liftweb.common.Empty
import com.webitoria.model.Color
import net.liftweb.mapper.Schemifier

/**
 * A class that's instantiated early and run.  It allows the application
 * to modify lift's environment
 */
class Boot {
  def boot {

    LiftRules.addToPackages("com.webitoria")

    // Build SiteMap
    def sitemap(): SiteMap = SiteMap(
      Menu.i("Home") / "index"
    )

    // Use HTML5 for rendering
    LiftRules.htmlProperties.default.set((r: Req) => new Html5Properties(r.userAgent))

    // init database connection
    object DBVendor extends StandardDBVendor(
      "org.h2.Driver",
      "jdbc:h2:mem:test_database",
      Empty, Empty
    )
    DB.defineConnectionManager(DefaultConnectionIdentifier, DBVendor)
    LiftRules.unloadHooks.append( () => DBVendor.closeAllConnections_!() )
    S.addAround(DB.buildLoanWrapper())

    Schemifier.schemify(true, Schemifier.infoF _, DefaultConnectionIdentifier,
      Color)


  }
}
