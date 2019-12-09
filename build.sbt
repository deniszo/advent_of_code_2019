lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.faliah",
      scalaVersion := "2.13.1"
    )),
    name := "scalatest-example"
  )

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % Test
