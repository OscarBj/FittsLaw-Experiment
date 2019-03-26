# FittsLaw-Experiment
Fitt's law experiment application written in Java (JavaFX GUI), in a Maven project. 
- The goal of the user experience is to click on a target as quickly as possible and with minimal error.Once the user clicks on the target, the
next target turns red. The next target is opposite to the current target. When the user has finished clicking on the targets, a new configuration appears on the
screen (different width and distances). The experiment and the application end when the
configurations have been completed. The order of configurations is generated randomly.
-  User's number, the width of the target and the distance between the
targets, the completion time, the number of clicks errors are recorded and stored in a csv file. 

The application can be launched in two modes:
-Dev mode is launched by default and lets the user configure parameters:
	-Number of targets
	-Number of target configurations
	-Participant id
	-Log location 
-Experiment mode is launched with the command line arg -experimentmode *log file path*  
	-This mode restricts the user view and displays only necessary information
	
TODO:
-Async file writer
-UI fixes
