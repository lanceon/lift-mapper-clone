organization := "com.webitoria"

name := "Cloning of mapper instances"

version := "0.1"

scalaVersion := "2.10.2"

scalacOptions ++= Seq("-deprecation", "-unchecked")

seq(com.github.siasia.WebPlugin.webSettings :_*)

libraryDependencies ++= {
  val liftVersion = "2.5"
  Seq(
    "net.liftweb"       %% "lift-webkit" % liftVersion % "compile",
    "org.eclipse.jetty" % "jetty-webapp" % "8.1.7.v20120910" % "container,test",
    "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" % "container,compile" artifacts Artifact("javax.servlet", "jar", "jar"),
    "net.liftweb"       % "lift-mapper_2.10"    % liftVersion        % "compile",
    "ch.qos.logback"    % "logback-classic"     % "1.0.13",
    "com.h2database"    % "h2"                  % "1.3.172"
  )
}

port in container.Configuration := 8081