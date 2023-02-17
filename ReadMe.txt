* Lawsuits pages web automation is developed using Selenium+Java+TestNG framework
* This framework includes below things -
	1. It can take screenshot of failed testcases using TestNg listners
	2. It can generate extent reports for all passed testcaes & attach screenshot of failed testcaes using TestNg listners
	3. It can be run from Maven commands or using xml file
	4. It can be run on chrome, firefox or Edge browsers also it supports headless mode
	5. Browser can be set from config.properties file
	6. Used data providers, assertations & Page Object model etc to build the framework
	7. Code can fetch all Recent Lawsuits links and open each link in separate tab
		- Accept cookies
		- then from each tab it fetches Plaintiff & Defendant information
		- click next page and it repeats the same process for all page

Note - 
	1. As no expected data is provided against which we can assert the actual & excepted output, for demo we have only assert the header of each page
	2. Too remove execution time we have set limit to execute only 2 links from first two pages only.
	3. if you want to check more pagination & articles per page then you can change limit of variables "pageCount & articleCount" 
	   in LawsuitsPages.java file under dd.fleetstudio.PageObjects package

* How to run the framework -
	1. Can be run using testng.xml file given in the project
	2. Can be run using ‘mvn test’ command from maven command line
	4. Set browser values from maven usig ‘mvn test -Dbrowser=Chrome' command
	5. After execution, under 'reports' folder 'index.html' file will be generated. Also screenshot of failed test cases can be found under this folder

		


 