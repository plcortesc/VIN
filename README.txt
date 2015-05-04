README

First of all we have to check if we have all required software for this application. I guess java is already configured, but just in case, I will outline how to be able to execute the whole program. 

First, download the “jackson-all-1.9.11.jar” and “commons-cli-1.2.jar” files. Those libraries can be found on the “src” directory of my project.

I have been working with MAC OS, since it is a UNIX OS, will perfectly work on Linux.
Once we have it downloaded, we can proceed to compile and execute the program with these commands:

To compile the program:

$ cd VIN\ Project/vin/src

$ javac -classpath ./jackson-all-1.9.11.jar:./commons-cli-1.2.jar: edu/iit/cs445/vin/*.java

To execute:

$ java -cp ./jackson-all-1.9.11.jar:./commons-cli-1.2.jar: edu.iit.cs445.vin.Main

and the corresponding commands for each use case that are described as follows:

-subscriber : commands for subscriber
-shipments: commands for shipments
-notes : commands for notes
-wines : commands for wine
-admin : commands for admin
-partner : commands for partner

-add : add some stuff
-modify : modify some stuff
-view : view some stuff
—load : load some file
-search : search some coincidence
-delete : delete some stuff
-rank : display wine ranking
-revenue : display revenue
-add_monthly_selection : add a monthly selection
-view_monthly_selection : display monthly selection
-subscriber_list : display subscribers to deliver
-add_receipt : add receipt
-view_receipt : display receipts
		
-n : name
-e : email
-a : street
-c : city
-s : state
-z : zip
-h : phone
-f : facebook/file (file for add_monthly_selection and load)
-t : twitter
-k : keyword
-r : rating
-m : message content
-age : receiver's age
-nr : receiver's name
-uid : subscriber id
-aid : admin id
-sid : shipment id
-rid : receipt id
-nid : note id
-wid : wine id
-mid : monthly selection id
-mst : monthly selection type string
-ym : ms date string, yyyy-mm format