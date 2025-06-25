# SanjaySDETIntAssessmentCode
This is the code for SDET Intermediate assessment conducted by Techademy.
I have use chromediver and edge driver to demo the skills.
Tests can run on both the browser without any issues.

To manage the data I had used data.properties file.
currently not many parameters addd there but testBrowser and url is addeded for demo purpose.

Log 4j Logger is also implemented and repot generated has logs printed in it.

How to execute the code.
This project has homePage.java file.
It has two tests:
1. Base page navigation
2. Find high and low stock prices.

You can execute these individually from eclipse or intellij by click run in eclipse or play button in intellij.
You can execute then through POM.xml file using mvn test command.

#Parallel execution:
you can also use testng.xml file if you want to execute some testcases parallelly.
I had created a separate java class to demo parallel execuiton called parallelTests.java
just right click on testng.xml and see two chrome drivers open up and execute testcases parallelly.


Note: NSE website sometimes does not works and we get connection reset error. so you need to wait a whild and run the testcases again in case you see connection reset eror.

