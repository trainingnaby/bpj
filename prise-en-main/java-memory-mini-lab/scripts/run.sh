#!/usr/bin/env bash
CLS=${1:-com.example.minilab.Menu}
mvn -q -DskipTests package || exit 1
java -Xms128m -Xmx128m -cp target/java-memory-mini-lab-1.0.0.jar $CLS
