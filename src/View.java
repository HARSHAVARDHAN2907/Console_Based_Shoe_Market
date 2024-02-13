import java.util.Scanner;

class SignUp{
    String s;
    DBFiles db=new DBFiles();
    Scanner in=new Scanner(System.in);
    public void signup(){
        int n=2;
            do {
                System.out.println("Press 1 for SignUp or 0 for Login or 3 to Exit");
                n = in.nextInt();
                System.out.println();
                if (n == 1) {
                    getUserDetails();
                } else if (n == 0) {
                    getLoginDetails();
                }
                else if(n==3){
                    System.out.println("Exiting...");
                    return;
                } 
                else {
                    System.out.println("Invalid Choice\nTry Again");
                }
            } while (n != 0 && n != 1);
        }
        public void getUserDetails() {
            System.out.println("Enter your Email:");
            s = in.next();
            if (!s.contains("@gmail.com")) {
                while (!s.contains("@gmail.com")) {
                    System.out.println();
                    System.out.println("Invalid Email\nTry Again");
                    s = null;
                    System.out.println("Enter your Email:");
                    s = in.next();
                    System.out.println();
                }
        }
        System.out.println();
        System.out.println("Enter your Username:");
        String user=in.next();
        System.out.println();
        System.out.println("Enter your Phone Number:");
        String phone=in.next();
        if(phone.length()!=10){
            while(phone.length()!=10){
                System.out.println();
                 System.out.println("Invalid Phone Number\nTry Again");
                phone=null;
                System.out.println("Enter your Phone Number:");
                phone=in.next();
                System.out.println();
            }
        }
        System.out.println();
        System.out.println("Enter DOB");
        System.out.print("Date: ");
        int date=in.nextInt();
        System.out.println();
    
        System.out.print("Month: ");
        String month=in.next();
        System.out.println();
        
        System.out.print("Year: ");
        String year=in.next();
        System.out.println();
    
        String dob=year+"-"+month+"-"+Integer.toString(date);
    
        System.out.println("Choose Your Role:");
        System.out.println("Press 1 for User 2 for Vehicle Lender");
        System.out.println("1.) User");
        System.out.println("2.) Admin");
        int h=in.nextInt();
        String g="";
        if(( h!=1 && h!=2)){
            while( h!=1 || h!=2){
                System.err.println();
                System.err.println("Invalid Choice\nTry Again");
                System.out.println();
                System.out.println("Choose Your Role:");
                System.out.println("Press 1 for User 2 for Vehicle Lender");
                System.out.println("1.) User");
                System.out.println("2.) Admin");
                h=0;
                h=in.nextInt();
                if( h==1 || h==2){
                    break;
                }
            }
        }
        if(h==1){
            g+="User";
        }
        else if(h==2){
            g+="Admin";
        }
        System.out.println();
        System.out.println("Choose Your Gender:");
        System.out.println("Press 1 for Male 2 for Female 3 for Others");
        System.out.println("1.) Male");
        System.out.println("2.) Female");
        System.out.println("3.) Others");
        int p=in.nextInt();
        if(p!=1 || p!=2 || p!=3) {
            while(p!=1 && p!=2 && p!=3){
                System.err.println();
                System.err.println("Invalid Choice\nTry Again");
                System.out.println();
                System.out.println("Choose Your Gender:");
                System.out.println("Press 1 for Male 2 for Female 3 for Others");
                System.out.println("1.) Male");
                System.out.println("2.) Female");
                System.out.println("3.) Others");
                p=in.nextInt();
                if(p==1 || p==2 || p==3) {
                    break;
                }
            }
        }
        String m="";
        if(p==1){
            m+="Male";
        }
        else if(p==2){
            m+="Female";
        }
        else if(p==3){
            m+="Others";
        }
        System.out.println();
        System.out.println("Enter your password(Must be of 8 characters):");
        String pass=in.next();
        System.out.println();
        System.out.println("Confirm Password:");
        String pass1=in.next();
        System.out.println();
        if((pass.length()!=8)){
            while((pass.length()!=8)){
                System.out.println("Password must be of 8 characters");
                pass=null;
                pass1=null;
                System.out.println("Enter your password(Must be of 8 characters):");
                pass=in.next();
                System.out.println();
                System.out.println("Confirm Password:");
                pass1=in.next();
                System.out.println();
            }
        }
        if(!pass.equals(pass1)){
            while(!pass.equals(pass1)){
                System.out.println("Password and Confirm password must match");
                pass=null;
                pass1=null;
                System.out.println("Enter your password(Must be of 8 characters):");
                pass=in.next();
                System.out.println();
                System.out.println("Confirm Password:");
                pass1=in.next();
                System.out.println();
            }
        }
        
        int f=db.PutSignUpDetials(s, user,phone, g, pass, m, dob);
        if(f==1){
            System.out.println("Registered Successfully");
            if(g.equals("Admin")){
                AfterPage af=new AfterPage();
                af.AdminAfterPage();
            }
            else{
            int k=db.getUserId(s);
            AfterPage af=new AfterPage();
            af.UserAfterPage(k);
            }
        }
        if(f==9){
            System.out.println("Email already registered!! Try Again");
        }
        if(f==10){
            System.out.println("Phone number already registered!! Try Again");
        }
        }
        public void getLoginDetails(){
            System.out.println("Enter your Email:");
                String s = in.next();
                if (!s.contains("@gmail.com")) {
                    while (!s.contains("@gmail.com")) {
                        System.out.println();
                        System.out.println("Invalid Email\nTry Again");
                        s = null;
                        System.out.println("Enter your Email:");
                        s = in.next();
                        System.out.println();
                    }
                }
                System.out.println();
                System.out.println("Enter Password:");
                String pass=in.next();
                if((pass.length()!=8)){
                while((pass.length()!=8)){
                    System.out.println("Password must be of 8 characters");
                    pass=null;
                    System.out.println("Enter Password:");
                    pass=in.next();
                    System.out.println();
                }
            }
            int h=db.CheckLoginDetails(s,pass);
            if(h==1){
                System.out.println("Login Succesfull");
                int k=db.getUserId(s);
                AfterPage af=new AfterPage();
                af.UserAfterPage(k);
            }
            if(h==2){
                System.out.println("Hello Admin!!");
                AfterPage af=new AfterPage();
                af.AdminAfterPage();
            }
            else if(h==0){
                System.out.println("Wrong Password!!!Try Again");
                getLoginDetails();
            }
            else if(h==-1){
                System.out.println("Invalid Email!!!Try Again");
                getLoginDetails();
            }
            }
        }
class AfterPage{
    DBFiles db=new DBFiles();
    SignUp sup = new SignUp();
    Scanner in = new Scanner(System.in);
   void UserAfterPage(int id){
    System.out.println();
    System.out.println("Hey Sneakerhead your User ID is "+id);
    System.out.println();
    int n;
    do{
        System.out.println("What would you like to do...");
        System.out.println("1.Search Sneakers");
        System.out.println("2.Order Sneakers");
        System.out.println("3.Add a sneaker to Cart");
        System.out.println("4.Check Order History");
        System.out.println("5.View My Cart");
        System.out.println("6.Logout");
        System.out.println();
        System.out.println("Enter a value");
        n=in.nextInt();
        if(!(n>=1 && n<=8)){
            System.out.println("Invalid Input...Try Again");
        }
        }while(!(n>=1 && n<=8));
        if(n==6){
            sup.signup();
            return;
        }
        if(n==1){
            int val;
            do{
                System.out.println();
                System.out.println("1.Search By Brand");
                System.out.println("2.Search By Size");
                System.out.println("3.Search by Sex");
                System.out.println("4.Search by Price");
                System.out.println();
                System.out.println("Enter value");
                val=in.nextInt();
                if(!(val>=1 && val<=4)){
                    System.out.println("Invalid Input...Try Again");
                }
                }while(!(val>=1 && val<=4));
                if(val==1){
                    System.out.print("Enter Brand of the shoe: ");
                    int k=db.viewByBrand(in.next());
                    if(k!=1){
                    System.out.println("No record found");
                    }
                }
                if(val==2){
                    System.out.print("Enter Size of the shoe: ");
                    int k=db.viewBySize(in.nextInt());
                    if(k!=1){
                    System.out.println("No record found");
                    }
                }
                if(val==3){
                    System.out.print("Enter sex: ");
                    String sex=in.next();
                    int k=db.viewBySex(sex.toLowerCase());
                    if(k==99){
                        System.out.println("No record found");
                    }
                }
                if(val==4){
                    System.out.print("Enter the maximum price: ");
                    String price=in.next();
                    String f="";
                    for(int k=0;k<price.length();k++){
                    if(price.charAt(k)>='0' && price.charAt(k)<='9'){
                        f+=""+price.charAt(k);
                    }
                    }
                    System.out.println(f);
                    int k=db.viewByPrice(Integer.parseInt(f));
                    if(k==99){
                        System.out.println("No record found");
                    }
                }
                
        }
        if(n==5){
            System.err.println();
            int k = db.viewCart(id); 
            if(k==0){
                System.out.println("Your Cart is Empty");
            }
        }
        if(n==2){
            System.out.print("Enter sneaker ID to place an order: ");
            int c=in.nextInt();
            int k=db.place(c,id);
            if(k==-1){
                System.out.println("No record found");
            }
            else if(k==200){
                System.out.println();
                System.out.println("Order Placed Successfully for Sneaker ID: "+c);
                System.out.println("Happy Kicking...");
            }
            else{
                System.out.println("failed");
            }
        }
        if(n==4){
            System.err.println();
            int k = db.viewOrders(id); 
            if(k==0){
                System.out.println("Your Order History is Blank");
            }
        }
        if(n==3){
            System.out.print("Enter sneaker ID to add it to cart: ");
            int c=in.nextInt();
            int k=db.addtoCart(c,id);
            if(k==-1){
                System.out.println("No record found");
            }
            else if(k==200){
                System.out.println();
                System.out.println("Added to Cart");
            }
            else if(k==999){
                System.out.println("Already Exists in Cart");
            }
            else{
                System.out.println("failed");
            }
        }
        if(n==6){
            System.out.println("Enter sneaker ID you want to return or ");
        }
        int choice;
        do{
            System.out.println();
            System.out.println("Do you want to continue....");
            System.out.println("0.Continue");
            System.out.println("1.Logout");
            System.out.print("Enter value: ");
            choice=in.nextInt();
            if(choice==0){
               UserAfterPage(id);
            }
            else if(choice==1){
                sup.signup();
            }
            else{
                System.out.println("Invalid Try Again");
            }
            }while(choice!=0 && choice!=1);
   }
   void AdminAfterPage(){
    System.out.println("Hello Admin......");
    int n;
    do{
    System.out.println("What would you like to do...");
    System.out.println("1.Insert details of a shoe");
    System.out.println("2.Delete details of a shoe");
    System.out.println("3.Update details of a shoe");
    System.out.println("4.View details of shoes");
    System.out.println("5.LogOut");
    System.out.println();
    System.out.println("Enter a value");
    n=in.nextInt();
    if(!(n>=0 && n<=5)){
        System.out.println("Invalid Input...Try Again");
    }
    }while(!(n>=0 && n<=5));
    if(n==5){
        sup.signup();
        return;
    }
    if(n==1){
        in.nextLine();
        System.out.print("Enter shoe name: ");
        String sname=in.nextLine();
        System.out.println();
        System.out.print("Enter shoe brand: ");
        String sbrand=in.next();
        System.out.println();
        System.out.print("Enter shoe size: ");
        int size = in.nextInt();
        System.out.println();
        System.out.print(("Enter shoe price: "));
        int price=in.nextInt();
        System.out.println();
        System.out.print(("Enter stock: "));
        int count=in.nextInt();
        System.out.println();
        System.out.print("Enter gender: ");
        String gender=in.next();
        int check = db.putShoeDetails(sname,sbrand,size,price,count,gender);
        if(check==1){
            System.out.println("Inserted Successfully");
        }
        else if(check==99){
            System.out.println("You can update the record as it already exists....");
        }
        else{
            System.out.println("Couldn't Insert");
        }System.out.println();
        int choice;
        do{
            System.out.println();
        System.out.println("Do you want to continue....");
        System.out.println("0.Continue");
        System.out.println("1.Logout");
        System.out.print("Enter value: ");
        choice=in.nextInt();
        if(choice==0){
            AdminAfterPage();
            return;
        }
        else if(choice==1){
            sup.signup();
            return;
        }
        else{
            System.out.println("Invalid Try Again");
        }
        }while(choice!=0 && choice!=1);
    }
    if(n==2){
        int val;
        do{
            System.out.println("1.Delete By ID");
            System.out.println("2.Delete by Brand");
            System.out.println("3.Delete by Shoe Size");
            System.out.print("Enter value: ");
            val=in.nextInt();
            if(val==1){
            System.out.println();
            System.out.print("Enter shoe id: ");
            int k=db.deletebyId(in.nextInt());
            if(k==1){
                System.out.println("Deleted Successfully");
            }
            else{
                System.out.println("Couldn't find record");
            }
            break;
            }
            else if(val==2){
            System.out.println();
            System.out.print("Enter brand name: ");
            int k=db.deleteByBrand(in.next());
            if(k==1){
                System.out.println("Deleted Successfully");
            }
            else{
                System.out.println("Couldn't find record");
            }
            break;
            }
            else if(val==3){
            System.out.println();
            System.out.print("Enter size: ");
            int k=db.deleteBySize(in.nextInt());
            if(k==1){
                System.out.println("Deleted Successfully");
            }
            else{
                System.out.println("Couldn't find record");
            }
            break;
            }
            else{
                System.out.println("Invalid Input...Try Again....");
            }
        }while(!(val>=1 && val<=3));
        int choice;
        do{
            System.out.println();
            System.out.println("Do you want to continue....");
            System.out.println("0.Continue");
            System.out.println("1.Logout");
            System.out.print("Enter value: ");
            choice=in.nextInt();
            if(choice==0){
                AdminAfterPage();
            }
            else if(choice==1){
                sup.signup();
            }
            else{
                System.out.println("Invalid Try Again");
            }
            }while(choice!=0 && choice!=1);
    }
    if(n==3){
    System.out.print("Enter Shoe ID to Update: ");
    int up=in.nextInt();
    System.out.println();
    System.out.print("Enter the field you want to update: ");
    String s=in.next();
    System.out.println();
    System.out.print("Enter the value to be updated: ");
    in.nextLine();
    String upd=in.nextLine();
    System.out.println();
    if(s.equals("sbrand") || s.equals("sname") || s.equals("gender")){
        int k=db.updateValue(up, s, upd);
        if(k==1){
            System.out.println("Updated Successfully");
        }
        else{
            System.out.println("No record found");
        }
    }
    else{
        int k=db.updateIntValue(up, s, Integer.parseInt(upd));
        if(k==1){
            System.out.println("Updated Successfully");
        }
        else{
            System.out.println("No record found");
        }
    }
    int choice;
        do{
            System.out.println();
            System.out.println("Do you want to continue....");
            System.out.println("0.Continue");
            System.out.println("1.Logout");
            System.out.print("Enter value: ");
            choice=in.nextInt();
            if(choice==0){
                AdminAfterPage();
            }
            else if(choice==1){
                sup.signup();
            }
            else{
                System.out.println("Invalid Try Again");
            }
            }while(choice!=0 && choice!=1);
    }
    if(n==4){
        int val;
        do{
        System.out.println("1.View By ID");
        System.out.println("2.View By Brand");
        System.out.println("3.View By Size");
        System.out.print("Enter value: ");
        val = in.nextInt();
        if(val==1){
            System.out.print("Enter ID of the shoe: ");
            int k=db.viewById(in.nextInt());
            if(k!=1){
            System.out.println("No record found");
            }
            break;
        }
        else if(val==2){
            System.out.print("Enter Brand of the shoe: ");
            int k=db.viewByBrand(in.next());
            if(k!=1){
            System.out.println("No record found");
            }
            break;
        }
        else if(val==3){
            System.out.print("Enter Size of the shoe: ");
            int k=db.viewBySize(in.nextInt());
            if(k!=1){
            System.out.println("No record found");
            }
            break;
        }
        else{
            System.out.println("Invalid Input...Try Again...");
        }
        }while(!(val>=1 && val<=3));
        int choice;
        do{
            System.out.println();
            System.out.println("Do you want to continue....");
            System.out.println("0.Continue");
            System.out.println("1.Logout");
            System.out.print("Enter value: ");
            choice=in.nextInt();
            if(choice==0){
                AdminAfterPage();
            }
            else if(choice==1){
                sup.signup();
            }
            else{
                System.out.println("Invalid Try Again");
            }
            }while(choice!=0 && choice!=1);
    }
}
}
public class View {
    public static void main(String[] args) {
        System.out.println("Welcome to DR4NZY Shoe Market");
        SignUp sign = new SignUp();
        sign.signup();
    }
}
