import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Application {
    static String filePath;
    static File folderName;
    
    public static void main(String[] args) {
        System.out.println("Welcome to LockedMe");
        filePath = System.getProperty("user.dir");
        folderName = new File(filePath+"/files");
        if (!folderName.exists())
            folderName.mkdirs();
        System.out.println("File Name : "+ folderName.getAbsolutePath());
        Application menu = new Application();
        menu.showPrimaryMenu();
    }

    void showPrimaryMenu() {
        System.out.println("\nMAIN MENU - Select an Option: \n"+
                "1 : List files in directory\n"+
                "2 : Add, Delete or Search\n"+
                "3 : Exit Program");
        try{
            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            switch (option){
                case 1 : {
                    showFiles();
                    showPrimaryMenu();
                }
                case 2 : {
                    showSecondaryMenu();
                }
                case 3 : {
                    System.out.println("THANK YOU");
                    System.exit(0);
                }
                default: showPrimaryMenu();
            }
        }
        catch (Exception e){
            System.out.println("Please enter 1, 2 or 3");
            showPrimaryMenu();
        }
    }

    void showSecondaryMenu() {
        System.out.println("   \nSelect an Option: \n"+
                "   a : Add a file\n"+
                "   b : Delete a file\n"+
                "   c : Search a file\n"+
                "   d : GoBack");
        try{
            Scanner scanner = new Scanner(System.in);
            char[] input = scanner.nextLine().toLowerCase().trim().toCharArray();
            char option = input[0];

            switch (option){
                case 'a' : {
                    System.out.print("Please Enter a File Name To ADD : ");
                    String filename = scanner.next().trim().toLowerCase();
                    addFile(filename);
                    break;
                }
                case 'b' : {
                    System.out.print("Please Enter a File Name To DELETE : ");
                    String filename = scanner.next().trim();
                    deleteFile(filename);
                    break;
                }
                case 'c' : {
                    System.out.print("Please Enter a File Name To SEARCH : ");
                    String filename = scanner.next().trim();
                    searchFile(filename);
                    break;
                }
                case 'd' : {
                    System.out.println("Going Back to MAIN menu");
                    showPrimaryMenu();
                    break;
                }
                default : System.out.println("Please enter a, b, c or d");
            }
            showSecondaryMenu();
        }
        catch (Exception e){
            System.out.println("Please enter a, b, c or d");
            showSecondaryMenu();
        }
    }

    void showFiles() {
        if (folderName.list().length==0)
            System.out.println("The folder is empty");
        else {
            String[] list = folderName.list();
            System.out.println("The files in "+ folderName +" are :");
            Arrays.sort(list);
            for (String str:list) {
                System.out.println(str);
            }
        }
    }

    void addFile(String filename) throws IOException {
        File filepath = new File(folderName +"/"+filename);
        String[] list = folderName.list();
        for (String file: list) {
            if (filename.equalsIgnoreCase(file)) {
                System.out.println("File " + filename + " already exists at " + folderName);
                return;
            }
        }
        filepath.createNewFile();
        System.out.println("File "+filename+" added to "+ folderName);
    }

    void deleteFile(String filename) {
        File filepath = new File(folderName +"/"+filename);
        String[] list = folderName.list();
        for (String file: list) {
            if (filename.equals(file) && filepath.delete()) {
                System.out.println("File " + filename + " deleted from " + folderName);
                return;
            }
        }
        System.out.println("Delete Operation failed. FILE NOT FOUND");
    }

    void searchFile(String filename) {
        String[] list = folderName.list();
        for (String file: list) {
            if (filename.equals(file)) {
                System.out.println("FOUND : File " + filename + " exists at " + folderName);
                return;
            }
        }
        System.out.println("File NOT found (FNF)");
    }

    
}