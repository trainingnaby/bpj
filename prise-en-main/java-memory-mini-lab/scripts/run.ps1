param([string]$cls = "com.example.minilab.Menu")
mvn -q -DskipTests package
& java -Xms128m -Xmx128m -cp target/java-memory-mini-lab-1.0.0.jar $cls
