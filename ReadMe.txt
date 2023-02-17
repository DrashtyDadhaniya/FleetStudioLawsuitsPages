* Insure Web Automation is developed using Selenium+Java+TestNG framework
* This framework includes below things -
	1. It can take screenshot of failed testcases using TestNg listners
	2. It can generate extent reports for all passed testcaes & attache screenshot of failed testcaes using TestNg listners
	3. It can be run from Maven commands or using xml file
	4. It can be run chrome, firefox or Edge browsers
	5. Browser can be set from config.properties file
	6. Used data providers, assertations & Page Object model etc to build the framework
	7. It can test if logo is displayed or not, help menu, welcome note, select

* How to run the framework -
	1. Can be run using testng.xml file
	2. Can be run using ‘mvn test’ command from maven command line
	4. Set browser values from maven usig ‘mvn test -Dbrowser=Chrome' command
	5. After execution, under 'reports' folder 'index.html' file will be generated. Also screenshot of failed test cases can be found under this folder

		


