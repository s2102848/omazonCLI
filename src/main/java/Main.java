
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

public class Main {
    public static boolean loggedIn=false;
    public static boolean selling=false;
    public static boolean checkingShoppingCart=false;
    public static boolean managingAccount = false;
    public static User activeUser = greetingscreen();

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println(activeUser.getUsername()+" is logged in.");
        mainscreen();
     //   Product prod = Product.ReadFromFile("testu\\PRODUCTS\\Pencil");
        //todo: fix the shopping cart
     //   prod.putIntoCart(activeUser);
     //   prod = Product.ReadFromFile("testu\\PRODUCTS\\dragon");
     //   prod.putIntoCart(activeUser);
        while(loggedIn){
            System.out.println("\t\t\t\t**==============================================================**");
            System.out.println("\t\t\t\t Current user: "+activeUser.getUsername()+"!");
            System.out.println("\t\t\t\t 1. Sell product");
            System.out.println("\t\t\t\t 2. Buy product (Go to Homepage)");
            System.out.println("\t\t\t\t 3. List products");
            System.out.println("\t\t\t\t 4. Check balance");
            System.out.println("\t\t\t\t 5. Check shopping cart");
            System.out.println("\t\t\t\t 7. Manage the account");
            System.out.println("\t\t\t\t 0. EXIT");
            System.out.println("\t\t\t\t**==============================================================**");
            String answer = s.next();
            if(answer.equals("1")){
                selling=true;
                sell();
            }
            if (answer.equals("2")){

            }
            if(answer.equals("0")){
                loggedIn=false;
            }
            if(answer.equals("5")){
                checkingShoppingCart=true;
                shoppingCart();

            }
            if(answer.equals("7")){
                managingAccount=true;
                manageaccount();
            }

        }

    }

    public static User greetingscreen(){
        Scanner s = new Scanner(System.in);
        User blankUser = new User("Guest","Guest", "Guest");
        System.out.println("\t\t\t\t============================================");
        System.out.println("\t\t\t\t Welcome user! Please login or register");
        System.out.println("\t\t\t\t 1. Login");
        System.out.println("\t\t\t\t 2. Register");
        System.out.println("\t\t\t\t Press any other key to quit.");
        System.out.println("\t\t\t\t============================================");
        if(s.next().equals("1")){
            return login();
        } else if(s.next().equals("2")){
            register();
        }else{
            System.exit(0);
        }
        return blankUser;
    }
    public static User login(){
        String username;
        String password;
        Scanner s = new Scanner(System.in);
        User blankUser = new User("Blank","blank", "blankk");
        System.out.println("\t\t\t\t**==============================================================**");
        System.out.println("\t\t\t\t Welcome user! Please enter your username and then password");
        System.out.println("\t\t\t\t**==============================================================**");
        System.out.println("Please enter your username: ");
        username = s.next();
        System.out.println("Please enter your password: ");
        password = s.next();
        File folder = new File("Testu\\USERNAMES");
        for(File fileEntry : folder.listFiles()){
            User u = (User) User.ReadFromFile(fileEntry.getAbsolutePath());
            if(username.equals(u.getUsername()) && password.equals(u.getPassword())) {
                    loggedIn=true;
                    return u;
            }else{
                System.out.println("Wrong username or password!");
                System.out.println("Please Retry");
                loggedIn=false;
                greetingscreen();
            }

        }
    return blankUser;

    }
    public static void register(){
        Scanner s = new Scanner(System.in);
        System.out.println("\t\t\t\t**==========================================================================**");
        System.out.println("\t\t\t\t Welcome user! Please enter your username, password, and email to register!");
        System.out.println("\t\t\t\t**==========================================================================**");
        String username;
        String password;
        String email;
        File folder = new File("Testu\\USERNAMES");
        System.out.println("Please enter your username: ");
        username = s.next();
        for(File fileEntry : folder.listFiles()){
            User u = (User) User.ReadFromFile(fileEntry.getAbsolutePath());
            if(username.equals(u.getUsername())){
                System.out.println("Username taken.");
                register();
            }
        }
        System.out.println("Please enter your email: ");
        email = s.next();
        for(File fileEntry : folder.listFiles()){
            User u = (User) User.ReadFromFile(fileEntry.getAbsolutePath());
            if(username.equals(u.getEmail())){
                System.out.println("Email taken.");
                register();
            }
        }
        System.out.println("Please enter your new password: ");
        password = s.next();

        User user = new User(username, password, email);
        User.SaveToFile(user);

        login();
    }

    public static void mainscreen(){
        System.out.println("\t\t\t\t**==============================================================**");
        System.out.println("\t\t\t\t Welcome "+activeUser.getUsername()+"! Please enjoy your stay!");
        System.out.println("\t\t\t\t**==============================================================**");
    }

    //todo:Remove product option is not available
    public static void sell(){
        while(loggedIn&&selling) {
            Scanner s = new Scanner(System.in);
            System.out.println("\t\t\t\t**==============================================================**");
            System.out.println("\t\t\t\t Welcome user to the seller perspective!");
            System.out.println("\t\t\t\t 1. Put up product");
            System.out.println("\t\t\t\t 2. View existing listings");
            System.out.println("\t\t\t\t 3. Edit existing listings");
            System.out.println("\t\t\t\t 4. Go back");
            System.out.println("\t\t\t\t 0. Exit");
            System.out.println("\t\t\t\t**==============================================================**");
            String answer = s.next();
            if(answer.equals("1")){
                String productName;
                String description;
                String category;
                double price;
                int stockCount;
                int salescount = 0;

                System.out.print("Please type the product name:");
                productName = s.next();
                System.out.print("Please type the product description:");
                String catchline = s.nextLine();
                description = s.nextLine();
                System.out.print("Please type the product price:");
                price = Double.parseDouble(s.next());
                System.out.print("Please type the product stock count:");
                stockCount = Integer.parseInt(s.next());


                //category
                System.out.println("\t\t\t\t**==============================================================**");
                System.out.println("\t\t\t\tCategories:");
                System.out.println("\t\t\t\t 1. Sports and Outdoor");
                System.out.println("\t\t\t\t 2. Games and Hobbies");
                System.out.println("\t\t\t\t 3. Machines and Gadgets");
                System.out.println("\t\t\t\t 4. Fashion and Accessories (men)");
                System.out.println("\t\t\t\t 5. Fashion and Accessories (women)");
                System.out.println("\t\t\t\t 6. Home and Living");
                System.out.println("\t\t\t\t 0. Other");
                System.out.println("\t\t\t\t**==============================================================**");
                System.out.print("Choose a category for your products:");
                category=s.next();
                //-------------------------------//
                Product createdProduct = new Product(productName,description,price,stockCount,salescount,category,activeUser.getUsername());
                Product.SaveToFile(createdProduct);
                User.SaveToFile(activeUser);

            }
            if(answer.equals("2")){

                File folder = new File("Testu\\PRODUCTS");
                System.out.println("\t\t\t\t =======BELOW LIE YOUR PRODUCTS=========");
                int i = 0;
                for(File fileEntry : folder.listFiles()){
                    Product p = Product.ReadFromFile(fileEntry.getAbsolutePath());
                    if(p.getOwnerName().equals(activeUser.getUsername())) {
                        System.out.println((i+1)+". "+p.getProductName());
                        i++;
                    }
                }
            }
            if(answer.equals("3")){
                File folder = new File("Testu\\PRODUCTS");
                System.out.println("\t\t\t\t =======BELOW LIE YOUR PRODUCTS=========");
                Scanner scanner = new Scanner(System.in);
                String ans;
                int i = 0;
                for(File fileEntry : folder.listFiles()){
                    Product p = Product.ReadFromFile(fileEntry.getAbsolutePath());
                    if(p.getOwnerName().equals(activeUser.getUsername()))
                        System.out.println((i+1)+". "+p.getProductName());
                        i++;
                }
                System.out.println("\t\t\t\t =======WRITE THE FULL NAME OF PRODUCT TO EDIT=========");
                System.out.println("\t\t\t\t Enter 0 to go back");
                ans = s.next();
                if(ans.equals("0")){
                    sell();
                }else{
                    for(File fileEntry : folder.listFiles()){
                        Product p = Product.ReadFromFile(fileEntry.getAbsolutePath());
                        if(p.getOwnerName().equals(activeUser.getUsername()) && p.getProductName().equalsIgnoreCase(ans)){
                            String description, category;
                            double price;
                            int stockCount;

                            System.out.println("Please type the product description:");
                            String prodName = s.nextLine();
                            System.out.println("Please type the product description:");
                            String catchline = s.nextLine();
                            description = s.nextLine();
                            System.out.println("Please type the product price:");
                            price = Double.parseDouble(s.next());
                            System.out.println("Please type the product stock count:");
                            stockCount = Integer.parseInt(s.next());

                            System.out.println("\t\t\t\t**==============================================================**");
                            System.out.println("\t\t\t\tCategories:");
                            System.out.println("\t\t\t\t 1. Sports and Outdoor");
                            System.out.println("\t\t\t\t 2. Games and Hobbies");
                            System.out.println("\t\t\t\t 3. Machines and Gadgets");
                            System.out.println("\t\t\t\t 4. Fashion and Accessories (men)");
                            System.out.println("\t\t\t\t 5. Fashion and Accessories (women)");
                            System.out.println("\t\t\t\t 6. Home and Living");
                            System.out.println("\t\t\t\t 0. Other");
                            System.out.println("\t\t\t\t**==============================================================**");
                            System.out.println("Choose a category for your products:");
                            category=s.next();

                            p.setProductName(prodName);
                            p.setCategory(category);
                            p.setDescription(description);
                            p.setPrice(price);
                            p.setStockCount(stockCount);
                            Product.SaveToFile(p);
                        }
                    }
                }
            }
            if(answer.equals("4")){
                selling=false;
            }
            if(answer.equals("0")){
                System.exit(0);
            }
        }
    }
        public static void checkTransactionsAndProfits(){
        while(loggedIn&&managingAccount){
             Scanner keyboard = new Scanner(System.in);
             String answer;
             ArrayList<Double> profitList = new ArrayList<Double>();
             for(int i=0;i<profitList.size();i++){
                 profitList.add(activeUser.getProfit());
             }
             System.out.println("\t\t\t\t**==============================================================**");
             System.out.println("\t\t\t\t1. Would you like to view the transaction list?");
             System.out.println("\t\t\t\t2. Woould you like to view profits for the your products?");
             System.out.println("\t\t\t\t0. Go Back");
             System.out.println("\t\t\t\t**==============================================================**");
             answer=keyboard.next();
             if(answer.equals("1")){
                 System.out.println("\t\t\t\tThe List of your Transaction: "+ activeUser.getTransactionHistory());
             }
             else if(answer.equals("2")){
                 System.out.println("\t\t\t\t The List of Your Product profits: "+ profitList);
             }
             else if(answer.equals(0)){
                 managingAccount=false;
             }
             else{
                 System.out.println("Please enter a value from the given options");
             }
        }
    }

    //todo: Remove product from shopping cart proceed to pay not available
    public static void shoppingCart(){
        while(loggedIn&&checkingShoppingCart){
            Scanner s = new Scanner(System.in);
            String answer;
            System.out.println("\t\t\t\t**==============================================================**");
            System.out.println("\t\t\t\t Below is the list of the products in your shopping cart!");
            System.out.println("\t\t\t\t**==============================================================**");

            for(String string : activeUser.getShoppingCart()){
                if(string == null){

                }else{
                    System.out.println(string);
                }
            }
            System.out.println("\t\t\t\t Press 0 to go back");
            answer = s.next();
            if(answer.equals("0")){
                checkingShoppingCart=false;
            }else{
                shoppingCart();
            }
        }
    }

    public static void manageaccount(){
        while(loggedIn&&managingAccount){
            Scanner s = new Scanner(System.in);
            String answer;
            System.out.println("\t\t\t\t**==============================================================**");
            System.out.println("\t\t\t\t Editing the user: "+activeUser.getUsername()+"!");
            System.out.println("\t\t\t\t 1. Edit username/password/email");
            System.out.println("\t\t\t\t 2. Set/Edit payment password");
            System.out.println("\t\t\t\t 3. Delete account");
            System.out.println("\t\t\t\t 0. Go back");
            System.out.println("\t\t\t\t**==============================================================**");
            answer=s.next();

            if(answer.equals("1")){
                System.out.println("\t\t\t\t 1. Edit username");
                System.out.println("\t\t\t\t 2. Edit password");
                System.out.println("\t\t\t\t 3. Edit email");
                System.out.println("\t\t\t\t 0. Go back");
                answer=s.next();
                if(answer.equals("1")){
                    System.out.println("Please enter your password to confirm.");
                    if(activeUser.getPassword().equals(s.next())){
                        System.out.println("Please enter a new user name:");
                        activeUser.setUsername(s.next());
                        User.SaveToFile(activeUser);
                        System.out.println("Username changed successfully!");
                    }else{
                        System.out.println("Wrong password! Please try again");
                        manageaccount();
                    }
                }
                if(answer.equals("2")){
                    //todo: make it harder to change the password by requiring the last password
                        System.out.println("Please enter your old password");
                        if (activeUser.getPassword().equals(s.next())) {
                            System.out.println("Enter a new password");
                            String newPassword = s.next();
                            activeUser.setPassword(newPassword);
                            User.SaveToFile(activeUser);
                            System.out.println("Password changed successfully!");
                        } else {
                            System.out.println("Wrong password! Please try again");
                            manageaccount();
                        }
                }
                if(answer.equals("3")){
                    System.out.println("Please enter your password to confirm.");
                    if(activeUser.getPassword().equals(s.next())){
                        System.out.println("Please enter a new email address:");
                        activeUser.setEmail(s.next());
                        User.SaveToFile(activeUser);
                        System.out.println("Email changed successfully!");
                    }else{
                        System.out.println("Wrong password! Please try again");
                        manageaccount();
                    }

                }
                if(answer.equals("0")){
                    manageaccount();
                }
            }
            if (answer.equals("2")){
                System.out.println("Please enter your password");
                if (activeUser.getPassword().equals(s.next())) {
                    System.out.println("Enter a new payment password (must be an integer)");
                    int newPassword = s.nextInt();
                    activeUser.setPaymentPassword(newPassword);
                    User.SaveToFile(activeUser);
                    System.out.println("Payment Password changed successfully!");
                } else {
                    System.out.println("Wrong password! Please try again");
                    manageaccount();
                }
            }
            if(answer.equals("3")){
                System.out.println("Are you sure that you want to delete your account?");
                System.out.println("Please enter your password to confirm.");
                if(activeUser.getPassword().equals(s.next())){
                    File thisUser = new File("Testu\\USERNAMES\\"+activeUser.getUsername());
                    thisUser.delete();
                    System.out.println("Successfully deleted your account");
                    loggedIn=false;
                    greetingscreen();
                }else{
                    System.out.println("Wrong password! Please try again");
                    manageaccount();
                }
            }
            if(answer.equals("0")){
                managingAccount=false;
            }
        }
    }

}
