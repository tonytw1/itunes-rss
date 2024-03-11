organization  := "com.gu"
description   := "podcasts RSS feed"
scalaVersion  := "2.12.7"
scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked")
routesGenerator := InjectedRoutesGenerator

val root = Project("podcasts-rss", file("."))
  .enablePlugins(PlayScala, UniversalPlugin, DockerPlugin)
  .settings(
    libraryDependencies ++= Seq(
      "org.jsoup" % "jsoup" % "1.15.4",
      "com.gu" %% "content-api-client" % "19.4.0",
      "com.squareup.okhttp3" % "okhttp" % "4.12.0", // SNYK-JAVA-ORGJETBRAINSKOTLIN-2393744, SNYK-JAVA-COMSQUAREUPOKIO-5820002
      "org.scalactic" %% "scalactic" % "3.2.15",
      "org.scalatest" %% "scalatest" % "3.2.15" % "test",
      "net.logstash.logback" % "logstash-logback-encoder" % "7.3",
      "com.gu" %% "content-api-models-json" % "17.7.0" % "test",
    )
  )

dependencyOverrides ++=Seq(
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.12.7.1",
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.12.7",
  "io.netty" % "netty-handler" % "4.1.94.Final",
  "io.netty" % "netty-codec-http2" % "4.1.100.Final", // SNYK-JAVA-IONETTY-5953332
  "ch.qos.logback" % "logback-classic" % "1.4.14",
)

dockerBaseImage := "openjdk:11-jdk"
dockerExposedPorts := Seq(9000)
