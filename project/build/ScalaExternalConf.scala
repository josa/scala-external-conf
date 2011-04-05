import sbt._

class ScalaExternalConf(info: ProjectInfo) extends DefaultProject(info) {

  override def libraryDependencies = Set(
    "junit" % "junit" % "4.5" % "test->default"
  ) ++ super.libraryDependencies

  val scalatest = "org.scalatest" % "scalatest" % "1.3"

  val slf4j = "org.slf4j" % "slf4j-api" % "1.6.1"
  val l4jbind = "org.slf4j" % "slf4j-log4j12" % "1.6.1" % "runtime"



}