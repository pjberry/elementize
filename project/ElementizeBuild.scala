import sbt._
import sbt.Keys._

object ElementizeBuild extends Build {

  lazy val elementize = Project(
    id = "elementize",
    base = file("."),
    settings = Project.defaultSettings ++
      Seq(
        name := "elementize",
        organization := "com.bitmotif",
        version := "0.1-SNAPSHOT",
        scalaVersion := "2.10.0"
        // add other settings here
      )  ++
      Seq(libraryDependencies += "org.scalatest" % "scalatest_2.10" % "1.9.1" % "test")
  )
}
