java -Xmx512M -Xss2M -XX:+CMSClassUnloadingEnabled -Dexternal.conf.path=./src/main/resources -jar `dirname $0`/sbt-launcher.jar "$@"
