package controller;

import model.Admin;
import model.Customer;
import model.User;
import util.UserType;
import util.Message;
import java.util.Scanner;

public class controller {
   public void Adminop() {
     while(true) {
       //输入用户信息
       Scanner info1 =  new Scanner(System.in);
       System.out.println("First, please enter your name! ");
       String name = info1.nextLine();
       Scanner info2 =  new Scanner(System.in);
       System.out.println("Second, please enter your password! ");
       String password = info2.nextLine();
       User user = User.login(name, password);
       System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
       //进入管理员界面
       if(user.getUserType() == UserType.Admin) {
         //操作选择
         Admin admin = (Admin) user;
         System.out.println("Welcome to Administator's operation page!!");
         System.out.println("The two options are presented as follows!");
         System.out.println("1: Create a Book!");
         System.out.println("2: Delete a Book!");
         System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
         boolean e = true;
         while(e) {
           Scanner choice = new Scanner(System.in);
           System.out.println("Now, you can make your option. 1 or 2. 1 for creation and 2 for deletion");
           System.out.println();
           int a = choice.nextInt();
           switch(a) {
             //创建书
             case 1:
             boolean b = true;
             while(b) {
               System.out.println("Begin to createBook!");
               Scanner info3 =  new Scanner(System.in);

               System.out.println("First, please enter the name of the book! ");
               String Name = info3.nextLine();

               System.out.println("Second, please enter the author of the book! ");
               String Author = info3.nextLine();
               System.out.println("Third, please enter the price of the book! ");
               float Price = info3.nextFloat();

               System.out.println("Fourth, please enter your the availibility of the book!");
               System.out.println("Attention!!!! Make sure you enter the number 1");
               int Available = info3.nextInt();

               System.out.println("Fifth, please enter the number of the storage of the book! ");
               int Storage = info3.nextInt();
               admin.createBook(Name, Author, Price, Available, Storage);

               System.out.println("Message.Success" + Message.Success);
               System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
               System.out.println("Do you want to create one more book? True or False");
               boolean c = info3.nextBoolean();
               if(b != c)
               break;
             }
             break;
             //删除书
             case 2:
             boolean d = true;
             while(d) {
               System.out.println("The list of the book is showe as follows");
               System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
               for(int i = admin.getAllBook().size(); i<=0; i--)
               {
                 System.out.println(admin.getAllBook().get(i).getBookNumber() + admin.getAllBook().get(i).getName() + admin.getAllBook().get(i).getAuthor() + admin.getAllBook().get(i).getPrice() + admin.getAllBook().get(i).getStorage());
               }
               Scanner info4 = new Scanner(System.in);
               System.out.println("Please enter the number of the book you want to delete! Take care!");
               int bookNum = info4.nextInt();
               int id = admin.getAllBook().get(bookNum).getBookNumber();
               admin.deleteBook(id);

               System.out.println("Message.Success" + Message.Success);
               System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
               System.out.println("Do you want to delete one more book? Please enter True or False!");
               Scanner info5 = new Scanner(System.in);
               boolean f = info5.nextBoolean();

               if(f != d)
               break;
             }
             break;
           }

           System.out.println("Do you want to make another option? 1 for continue, 0 for logout!!");
           Scanner info5 = new Scanner(System.in);
           boolean g = info5.nextBoolean();
           if(g != e)
           break;
         }
         admin.logOut();
         System.out.println("logouting..............");
         break;
       }
       else {
         System.out.println("Message.Error" + Message.Error);
         System.out.println("Please try to enter the true name and the true password!!!!!!");
       }
     }
   }

   public void Customerop() {
     while(true) {
       //输入用户信息
       Scanner info1 =  new Scanner(System.in);
       System.out.println("First, please enter your name! ");
       String name = info1.nextLine();
       Scanner info2 =  new Scanner(System.in);
       System.out.println("Second, please enter your password! ");
       String password = info2.nextLine();
       User user = User.login(name, password);
       System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
       //进入客户界面
       if(user.getUserType() == UserType.Customer) {
         Customer customer = (Customer) user;
         System.out.println("Welcome to Customer's operation page!!");
         System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
         Boolean e = true;
         while(e) {
           System.out.println("We are glad to show you all books' information in our store.");
           System.out.println();
           System.out.println();
           System.out.println();
           System.out.println();
           System.out.println("The list of the book is showe as follows");
           System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
           for(int i = customer.getAllBook().size(); i<=0; i--)
           {
             System.out.println(customer.getAllBook().get(i).getBookNumber() + customer.getAllBook().get(i).getName() + customer.getAllBook().get(i).getAuthor() + customer.getAllBook().get(i).getPrice() + customer.getAllBook().get(i).getStorage());
           }
           boolean d = true;
           while(d) {
             System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
             System.out.println("Do you want to buy one book?");
             Scanner info4 = new Scanner(System.in);
             System.out.println("Please enter the ID of the book you want to buy! Thank you!");
             int bookNum = info4.nextInt();
             int id = customer.getAllBook().get(bookNum).getBookNumber();
             System.out.println("Please enter the number of the book you want to buy! Thank you!");
             int number = info4.nextInt();
             customer.purchase(id, number);
             System.out.println("Message.Success" + Message.Success);
             System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
             System.out.println("Do you want to buy one more book? Please enter true or false");
             Scanner info5 = new Scanner(System.in);
             Boolean f = info5.nextBoolean();
             if(f != d)
             break;
           }
           System.out.println("Do you want to make another option? 1 for continue, 0 for logout!!");
           Scanner info5 = new Scanner(System.in);
           Boolean g = info5.nextBoolean();
           if(g != e)
           break;
         }
         customer.logOut();
         System.out.println("logouting..............");
         break;
       }
       else {
         System.out.println("Message.Error" + Message.Error);
         System.out.println("Please try to enter the true name and the true password!!!!!!");
       }
     }
   }
}
