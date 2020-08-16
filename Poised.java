/**
 * This program is a Project Management system for a small structural engineering firm. 
 * It allows the user to capture and create new Project information an to keep track of 
 * the many projects on which the firm work.
 * The status and any other Project information can be modified at any time
 * <p>
 * @author Geraldine Dzinoreva
*/

package task24;

//Import packages necessary for this code
import javax.swing.*;					
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;	
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.*;


public class Poised {
	
	public static void main (String [] args){				//Main method declaration
		
		JFrame f = displayWelcomeMessage();
				
		List <Project> projObjList = new ArrayList<Project>();		//create empty Project Object list
		
		/**
		 * Read from text file and add objects to list
		 */
		try {
			File file = new File("Poised_Projects.txt");
			Scanner projFile = new Scanner(file);
			
			while (projFile.hasNextLine()) {						//add project objects to list oobtained from text file
				createListFromTextFile(projObjList, projFile);
			} 
		}
		catch(FileNotFoundException e) {				//Error handling for text file not found
			System.out.println("\nError reading file"); 
		}	
		
		
		/**
		 * Declare and initialize variables required to create an Object
		 */
		Person customer;
		Person architect;
		Person contractor;
		String personName;
		String telNumber;
		String personEmail;
		String personAddress;
		String projNum;
		String projName;
		String projBuilding;
		String projAddress;
		String projERF;
		String projCost;
		String projPaid;
		String projdeadline;
		int projNumInt = 0;
		int projERFInt = 0;
		double projCostDbl = 0;
		double projPaidDbl = 0;
		
		
		/**
		 * First Menu of three, View existing Projects or Create new ones
		 */
		System.out.println("\n________________POISED ENGINEERING PROJECT MANAGEMENT________________\n");
		Scanner menu = new Scanner(System.in);			//gets string menu input from user
		System.out.println("\n***START MEMU***");
		System.out.println("1	- View existing Projects");
		System.out.println("2	- Create new Project");
		System.out.println("0	- Exit to Edit Menu");
		
		
		/**
		 * Loop to create as many Project objects as required and add to list or view.
		 */
		int menuNum = 1;
		try {											//while loop error handling, only numbers accepted for menu input
		while (menuNum != 0) {								
		
			menuOptionSelection();
			menuNum = menu.nextInt();
			
			if (menuNum == 1) {							//View all Objects in List
				viewExistingProjects(projObjList);
		
			} else if (menuNum == 2) {					//Create new Project Object
				/**
				 * get input from user required to define Project Object
				 */
				JOptionPane.showMessageDialog(f, "To create a new Project, please enter Customer's details first: ");
				personName = JOptionPane.showInputDialog("Enter the Name of the Customer:");
				telNumber = JOptionPane.showInputDialog("Enter Telephone Number of the Customer");
				personEmail = JOptionPane.showInputDialog("Enter Email Address:");
				personAddress = JOptionPane.showInputDialog("Enter physical address:");
				
				//empty String error handling if user leaves blank section
				if (personName.length() == 0 || telNumber.length() == 0 || personEmail.length() == 0 || personAddress.length() == 0) {
					emptyStringHandling(f);
				}
				
				customer = new Person(personName, telNumber, personEmail, personAddress);			//Create 'customer' Person object
				JOptionPane.showMessageDialog(f, "Customer details Captured! Enter Project Details: ");
				
				//get instances for Project class from user
				projNum = JOptionPane.showInputDialog("Enter the project Number:");
				try {																//error handling for data type other than int
					projNumInt = Integer.parseInt(projNum);
				}
				catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(f, "Invalid Project Number entered! Project will not be documented correctly");
				}
				
				projName = JOptionPane.showInputDialog("Enter the Name of the project:");
				projBuilding = JOptionPane.showInputDialog("Enter the type of Building for this project:");
				projAddress = JOptionPane.showInputDialog("Enter the Address for this project:");
				
				projERF = JOptionPane.showInputDialog("Enter the ERF Number for this project:");
				try {																//error handling for data type other than int
					projERFInt = Integer.parseInt(projERF);
				}
				catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(f, "Invalid ERF Number entered! Project will not be documented correctly");
				}
				
				projCost = JOptionPane.showInputDialog("Enter the Total Cost for this project for this project:");
				try {																//error handling for data type other than double
					projCostDbl = Double.parseDouble(projCost);
				}
				catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(f, "Invalid Total Cost entered! Project will not be documented correctly");
				}
				
				projPaid = JOptionPane.showInputDialog("Enter the Amount Paid to date for this project:");
				try {																//error handling for data type other than double
					projPaidDbl = Double.parseDouble(projPaid);
				}
				catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(f, "Invalid Amount entered! Project will not be documented correctly");
				}
				
				projdeadline = JOptionPane.showInputDialog("Enter the deadline for this project as yyyy-mm-dd:");
				
				
				//empty String error handling
				if (projName.length() == 0 || projBuilding.length() == 0 || projAddress.length() == 0) {
					emptyStringHandling(f);
				}
				
				
				/**
				 * if project name was not provided get customer surname and project building, assign to Project name.
				 */
				try {																//empty string input error handling
					if (projName.length() == 0 && personName.length() >= 2){
						projName = createProjectName(personName, projBuilding);
					}
				}
				catch (java.lang.ArrayIndexOutOfBoundsException e) {
					JOptionPane.showMessageDialog(f, "Project Name field left blank");
				}
				
				
				//get input to make up Person object from user
				JOptionPane.showMessageDialog(f, "Project details Captured! Enter the details of the Architect on the Project: ");
				personName = JOptionPane.showInputDialog("Enter the Name of the Architect:");
				telNumber = JOptionPane.showInputDialog("Enter Telephone Number of the Architect");
				personEmail = JOptionPane.showInputDialog("Enter Email Address:");
				personAddress = JOptionPane.showInputDialog("Enter physical address:");
				
				if (personName.length() == 0 || telNumber.length() == 0 || personEmail.length() == 0 || personAddress.length() == 0) {
					emptyStringHandling(f);
				}
				
				/**
				 * Create 'architect' Person object
				 */
				architect = new Person(personName, telNumber, personEmail, personAddress);
				displayMessage(f);
				
				
				//get instances of Person class from user
				personName = JOptionPane.showInputDialog("Enter the Name of the Contractor:");
				telNumber = JOptionPane.showInputDialog("Enter Telephone Number of the Contractor");
				personEmail = JOptionPane.showInputDialog("Enter Email Address:");
				personAddress = JOptionPane.showInputDialog("Enter physical address:");
				
				if (personName.length() == 0 || telNumber.length() == 0 || personEmail.length() == 0 || personAddress.length() == 0) {
					emptyStringHandling(f);
				}
				
				/**
				 * Create 'contractor' Person object
				 */
				contractor = new Person(personName, telNumber, personEmail, personAddress);
				
				
				/**
				 * Create new Project object
				 *@param projectNew
				 */
				Project projectNew = new Project (projNumInt, projName, projBuilding, projAddress, projERFInt, projCostDbl, 
						projPaidDbl, projdeadline, "Incomplete", architect, contractor, customer, "");
				
				projObjList.add(projectNew);				//add Object to List
				
				
			} else if (menuNum == 0) {						//exit loop condition
				terminateLoop();
				break;
				
			} else {										//invalid selection error handling
				System.out.println("Invalid menu option");
			}
		}

		} catch(InputMismatchException e){					//while loop error handling, only numbers accepted
			System.out.println("Invalid input, digit expected");
		}
		
		
		
		
		/**
		 * Second Menu of three, Edit Projects
		 */
		System.out.println("\n***EDIT MEMU***");
		System.out.println("d	- Modify Deadline of a Project");
		System.out.println("a	- Modify the Amount paid to date");
		System.out.println("c	- Modify contact details of a contractor");
		System.out.println("e	- Exit Menu without editing");
		
		/**
		 * This loop will allow user to edit multiple projects or perform multiple operations until user exits
		 */
		Scanner selectionIn = new Scanner (System.in);
		String selection = "";
		
		while (!selection.equalsIgnoreCase("exit"))	{
			menuOptionSelection();
			selection = selectionIn.nextLine();
			
			/**
			 * Edit date option
			 */
			if (selection.equalsIgnoreCase("d")){
				editDeadline(projObjList);
			
			/**
			 * Edit amount Paid Option
			 */
			} else if (selection.equalsIgnoreCase("a")){
				editAmountPaid(projObjList);
			
			/**
			 * Edit Contractor information
			 */
			} else if (selection.equalsIgnoreCase("c")){
				editContractorDetails(projObjList);
			
			//terminate Edit loop
			} else if (selection.equalsIgnoreCase("e")) {
				terminateLoop();
				break;
				
			} else {
				invalidSelection();
			}
		}
			
			
			
		/**
		 * Third Menu of three, Finalize Projects
		 */	
		System.out.println("\n***FINALISATION MENU***");
		System.out.println("fn	- Finalise Projects");
		System.out.println("in	- View Incomplete Projects");
		System.out.println("ov	- View Overdue Projects");
		System.out.println("pr	- Find a specicific Project");
		System.out.println("al	- View ALL Projects");
		System.out.println("e	- Exit");
		
		
		Scanner finaliseIn = new Scanner (System.in);
		String finaliseOption = "";
		
		/**
		 * This loop will allow user to finalize multiple projects or perform multiple operations until user exits
		 */
		while (!finaliseOption.equalsIgnoreCase("e"))	{//loop as long as condition is not met
			menuOptionSelection();
			finaliseOption = finaliseIn.nextLine();
			
			/**
			 * Finalize project Option:
			 */
			if (finaliseOption.equalsIgnoreCase("fn")){
				finalizeProject(projObjList);
			
			/**
			 * Option to view Incomplete Projects
			 */
			} else if (finaliseOption.equalsIgnoreCase("in")){
				viewIncompleteProjects(projObjList);
			
			/**
			 * Option to view Overdue Projects
			 */
			} else if (finaliseOption.equalsIgnoreCase("ov")){
				viewOverdueProjects(projObjList);
			
			/**
			 * Option to find and view a particular Project
			 */
			} else if (finaliseOption.equalsIgnoreCase("pr")){
				findProject(projObjList);
			
			/**
			 * Option to View all projects
			 */
			} else if (finaliseOption.equalsIgnoreCase("al")){
				viewAllProjects(projObjList);
			
			//Terminate loop
			} else if (finaliseOption.equalsIgnoreCase("e")) {
				terminateLoop();
				break;
				
			} else {
				invalidResponse();
			}
		}
		
		
		
		/**
		 * write Completed Projects to file
		 */
		try {
			writeCompletedProjectToFile(projObjList);
		}
		
		catch (Exception e) {				//Error Handling
			errorWritingToFile();
		}
	}




	// Refractored Class Methods
	
	private static void invalidResponse() {
		System.out.println("Invalid Response!");
	}


	
	
	private static void errorWritingToFile() {
		System.out.println("Error writing to file");
	}



	/**
	 * @param projObjList
	 * @throws FileNotFoundException
	 */
	private static void writeCompletedProjectToFile(List<Project> projObjList) throws FileNotFoundException {
		Formatter fOut = new Formatter("Completed project.txt");
		
		for (Project element: projObjList) {
			if (element.getProjectStatus().equalsIgnoreCase("Finalised")) {
				fOut.format("%s%s %s%s %s%s %s%s %s%s %s%s %s%s %s%s %s%s %s%s %s%s %s%s %s%s %s%s %s%s %s%s %s%s %s%s %s%s %s%s %s%s %s %s", 
						Integer.toString(element.getProjectNumber()), ",", element.getProjectName(), ",", element.getProjectBuilding(), ",", 
						element.getProjectAddress(), ",", Integer.toString(element.getProjectNumberERF()), ",", Double.toString(element.getProjectFee()), ",", Double.toString(element.getAmountPaid()), ",", 
						element.getDeadline(), ",", element.getProjectStatus(), ",", element.getPersonArchitect().personName, ",", element.getPersonArchitect().telNumber, ",", element.getPersonArchitect().personEmail, ",",
						element.getPersonArchitect().personAddress, ",", element.getPersonContractor().personName, ",", element.getPersonContractor().telNumber, ",", element.getPersonContractor().personEmail, ",", 
						element.getPersonContractor().personAddress, ",", element.getPersonCustomer().personName, ",", element.getPersonCustomer().telNumber, ",", element.getPersonCustomer().personEmail, ",", 
						element.getPersonCustomer().personAddress, ",", element.getPersonCompletionDate(), "\r\n");
			}
		}
fOut.close();
	}



	/**
	 * @param projObjList
	 */
	private static void viewAllProjects(List<Project> projObjList) {
		System.out.println("Are you sure you want to display all Projects?! (Yes/No)");
		Scanner projViewIn = new Scanner (System.in);
		String allProj = projViewIn.nextLine();
		
		//Verify selection
		if (allProj.equalsIgnoreCase("Yes")) {
			System.out.println(projObjList);
		} else if (allProj.equalsIgnoreCase("No")) {
			System.out.println("Projects will not be displayed at this time.");
		} else {
			invalidResponse();
		}
	}





	/**
	 * @param projObjList
	 */
	private static void findProject(List<Project> projObjList) {
		System.out.println("Enter project number or project you are trying to find:");
		Scanner projSelectIn = new Scanner (System.in);
		String projSelect = projSelectIn.nextLine();
		
		for (Project element: projObjList) {
			if ((element.getProjectName().equalsIgnoreCase(projSelect) || Integer.toString(element.getProjectNumber()).equalsIgnoreCase(projSelect))) {
				System.out.println(element);
			}
		}
	}





	/**
	 * 
	 */
	private static void menuOptionSelection() {
		System.out.println("\nPlease enter Menu selection: ");
	}





	/**
	 * @param projObjList
	 */
	private static void viewOverdueProjects(List<Project> projObjList) {
		System.out.println("\n***OVERDUE PROJECTS***");
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = new Date();
		
		int countDue = 0;
		for (Project element: projObjList) {
			try {
				String DueDateStr = element.getDeadline();			//Convert Deadline to Data data type for comparison
				Date DueDate = dateformat.parse(DueDateStr);
				
				if (date1.compareTo(DueDate)>0 && element.getProjectStatus().equalsIgnoreCase("Incomplete")) {
					System.out.println(element);
					countDue += 1;
				}
			}
			catch(ParseException e) {								//Error handling for conversion to Date
				System.out.println("Date format error when this Project was captured");
			}
		}
		
		/**
		 * If Overdue projects do not exist
		 */
		if (countDue == 0) {
			System.out.println("\nNo overdue projects at this time!");
		}
	}





	/**
	 * @param projObjList
	 */
	private static void viewIncompleteProjects(List<Project> projObjList) {
		System.out.println("\n****INCOMPLETE PROJECTS***");
		for (Project element: projObjList) {
			if (element.getProjectStatus().equalsIgnoreCase("Incomplete")) {
				System.out.println(element);
			}	
		}
	}





	/**
	 * @param projObjList
	 */
	private static void finalizeProject(List<Project> projObjList) {
		System.out.println("\nPlease Enter Project Number or Project Name of Project you wish to edit:");
		Scanner projSelectIn = new Scanner (System.in);
		String projSelect = projSelectIn.nextLine();
		
		for (Project element: projObjList) {
			if ((element.getProjectName().equalsIgnoreCase(projSelect) || Integer.toString(element.getProjectNumber()).equalsIgnoreCase(projSelect))
					&& element.getProjectStatus().equalsIgnoreCase("Finalised")){
				
				double arrearsAmount = element.getProjectFee() - element.getAmountPaid();				//calculate outstanding amount
				
				/**
				 * if amount is greater than 0 generate an invoice for Customer
				 */
				if (arrearsAmount > 0){
					System.out.println("\n________________CUSTOMER INVOICE:________________\n");
					System.out.println(element.getPersonCustomer());
					System.out.println("Outstanding Amount:\tR" + arrearsAmount);
				} else {
					noOutstandingAmount();
				}
				
				/**
				 * add completion date to project
				 */
				System.out.println("\nEnter Completion Date of the Project: ");
				Scanner dateComplete = new Scanner (System.in);
				String completeDate = dateComplete.nextLine();
				element.setProjectStatus("Finalised");
				element.setcompletionDate(completeDate);
				
				System.out.println("\n***PROJECT SUMMARY***");
				System.out.println(element);
			
			/**
			 * If Project is Incomplete it cannot be finalized
			 */
			} else if ((element.getProjectName().equalsIgnoreCase(projSelect) || Integer.toString(element.getProjectNumber()).equalsIgnoreCase(projSelect))
					&& element.getProjectStatus().equalsIgnoreCase("Incomplete")){
				System.out.println("\nProject "+ element.getProjectName() + " is Incomplete therefore cannot be finalized");
			}
		}
	}





	/**
	 * 
	 */
	private static void invalidSelection() {
		System.out.println("\nInvalid Option selected!");
	}





	/**
	 * 
	 */
	private static void terminateLoop() {
		System.out.println("_____________________________________________________________________\n");
	}





	/**
	 * @param projObjList
	 */
	private static void editContractorDetails(List<Project> projObjList) {
		System.out.println("\nPlease Enter Project Number or Project Name of Project you wish to edit:");
		Scanner projSelectIn = new Scanner (System.in);
		String projSelect = projSelectIn.nextLine();
		
		//Iterate through Objects to find selected and edit Contractor information
		for (Project element: projObjList) {
			if (element.getProjectName().equalsIgnoreCase(projSelect) || Integer.toString(element.getProjectNumber()).equalsIgnoreCase(projSelect)) {
				Person editedContractor = element.getPersonContractor();
				
				System.out.println("Enter the Contractor's new Telephone number: ");
				Scanner telephoneIn = new Scanner (System.in);
				String telephoneInNew = telephoneIn.nextLine();
				editedContractor.setTelNumbers(telephoneInNew);
				
				System.out.println("Enter the Contractor's new Email Address: ");
				Scanner emailIn = new Scanner (System.in);
				String EmailInNew = emailIn.nextLine();
				editedContractor.setTersonEmail(EmailInNew);
				
				System.out.println("Enter the Contractor's new Physical Address: ");
				Scanner addressIn = new Scanner (System.in);
				String AddNew = addressIn.nextLine();
				editedContractor.setPersonAddress(AddNew);;
				element.setContractor(editedContractor);
				System.out.println("\n***PROJECT SUMMARY***");
				System.out.println(element);

			} 
		}
	}





	/**
	 * @param projObjList
	 */
	private static void editAmountPaid(List<Project> projObjList) {
		System.out.println("Please Enter Project Number or Project Name of Project you wish to edit: ");
		Scanner projSelectIn = new Scanner (System.in);
		String projSelect = projSelectIn.nextLine();
		
		//Iterate through Objects to find selected and edit Amount Paid
		for (Project element: projObjList) {
			if (element.getProjectName().equalsIgnoreCase(projSelect) || Integer.toString(element.getProjectNumber()).equalsIgnoreCase(projSelect)) {
				System.out.println("Enter the Amount Paid to date for this project:");
				Scanner amount = new Scanner (System.in);
				double newAmount = amount.nextDouble();
				element.setAmountpaid(newAmount);
				System.out.println("\n***PROJECT SUMMARY***");
				System.out.println(element);
			}
		}
	}





	/**
	 * @param projObjList
	 */
	private static void editDeadline(List<Project> projObjList) {
		System.out.println("\nPlease Enter Project Number or Project Name of Project you wish to edit:");
		Scanner projSelectIn = new Scanner (System.in);
		String projSelect = projSelectIn.nextLine();
		
		//Iterate through Objects to find selected and edit Deadline
		for (Project element: projObjList) {
			if (element.getProjectName().equalsIgnoreCase(projSelect) || Integer.toString(element.getProjectNumber()).equalsIgnoreCase(projSelect)) {
				System.out.println("Enter the new Deadline Date for this Project:");
				Scanner dateIn = new Scanner (System.in);
				String dateNew = dateIn.nextLine();
				element.setDeadline(dateNew);
				System.out.println("\n***PROJECT SUMMARY***");
				System.out.println(element);
			}
		}
	}

	
	


	/**
	 * @param projObjList
	 */
	private static void viewExistingProjects(List<Project> projObjList) {
		System.out.println(projObjList);
	}



	/**
	 * @param projObjList
	 * @param projFile
	 */
	private static void createListFromTextFile(List<Project> projObjList, Scanner projFile) {
		String line = projFile.nextLine();
		String[] projInfo = line.split(", ");				//temp String Array list
		Person architect = new Person(projInfo[9], projInfo[10], projInfo[11], projInfo[12]);
		Person contractor = new Person(projInfo[13], projInfo[14], projInfo[15], projInfo[16]);
		Person customer = new Person(projInfo[17], projInfo[18], projInfo[19], projInfo[20]);
		
		//Create new Project Object 
		Project objectNew = new Project(Integer.parseInt(projInfo[0]), projInfo[1], projInfo[2], projInfo[3], Integer.parseInt(projInfo[4]), 
				Double.parseDouble(projInfo[5]), Double.parseDouble(projInfo[6]), projInfo[7], projInfo[8], architect, contractor, customer, 
				projInfo[21]);
		
		projObjList.add(objectNew);				//add Object to List
	}

	
	
	
	private static JFrame displayWelcomeMessage() {
		JFrame f = new JFrame();
		JOptionPane.showMessageDialog(f, "Welcome to Poised Project Management System!");
		return f;
	}
	
	private static void displayMessage(JFrame f) {
		JOptionPane.showMessageDialog(f, "Architect's details Captured! Enter the details of the Contractor on the Project: ");
	}
	
	private static void emptyStringHandling(JFrame f) {
		JOptionPane.showMessageDialog(f, "One or more fields left blank! Project will not be documented correctly");
	}
	
	private static void noOutstandingAmount() {
		System.out.println("\nPayment has been paid in full. No invoice will be generated at this point.");
	}
	
	private static String createProjectName(String personName, String projBuilding) {
		String projName;
		String[] personSurname = personName.split(" ");
		projName = projBuilding + " " + personSurname[1];
		return projName;
	}
}