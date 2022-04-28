Shows cancelling activity (from signal) allowing acivity to do cleanup

Running:
1. run worker:

   mvn compile exec:java -Dexec.mainClass="io.temporaldemo.TemporalWorker"
   
2. run starter

   mvn compile exec:java -Dexec.mainClass="io.temporaldemo.TemporalStarter"