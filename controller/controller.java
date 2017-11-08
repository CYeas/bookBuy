package controller;

import model.Admin;
import model.Customer;
import model.User;
import model.Book;
import util.DatabaseUtil;
import util.UserType;
import util.Message;
import java.util.*;

class controller {
   public void Adminop() {
     while(1) {
       //输入用户信息
       Scanner info1 =  new Scanner(System.in);
       System.out.println("First, please enter your name! ");
       String name = info1.nextString();
       Scanner info2 =  new Scanner(System.in);
       System.out.println("Second, please enter your password! ");
       String password = info2.nextString();
       User user = new User.login(String name, String password);
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
         int e = 1;
         while(e) {
           Scanner choice = new Scanner(System.in);
           System.out.println("Now, you can make your option. 1 or 2. 1 for creation and 2 for deletion");
           System.out.println();
           int a = choice.nextInt();
           switch(a) {
             //创建书
             case 1:
             int b = 1;
             while(b) {
               System.out.println("Begin to createBook!");
               Scanner info3 =  new Scanner(System.in);

               System.out.println("First, please enter the name of the book! ");
               String Name = info3.nextString();

               System.out.println("Second, please enter the author of the book! ");
               String Author = info3.nextString();

               System.out.println("Third, please enter the price of the book! ");
               float Price = info3.nextFloat();

               System.out.println("Fourth, please enter your the availablity of the book!");
               System.out.println("Attention!!!! Make sure you enter the number 1");s
               int Available = info3.nextInt();

               System.out.println("Fifth, please enter the number of the storage of the book! ");
               int Storage = info3.nextInt();
               admin.createBook(Name, Author, Price, Available, Storage);

               System.out.println("Message.Success" + Message.Success);
               System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
               System.out.println("Do you want to create one more book? Yes for 1, No for 0");
               int c = info3.nextInt();

               if(b != c)
               break;
             }
             break;
             //删除书
             case 2:
             int d = 1;
             while(d) {
               System.out.println("The list of the book is showe as follows");
               System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
               for(int i = admin.getAllBook().size(); i<=0; i--)
               {
                 System.out.println(admin.getAllBook(i).getBookNumber() + admin.getAllBook(i).getName() + admin.getAllBook(i).getAuthor() + admin.getAllBook(i).getPrice() + admin.getAllBook(i).getStorage());
               }
               Scanner info4 = new Scanner(System.in);
               System.out.println("Please enter the number of the book you want to delete! Take care!");
               int bookNum = info4.nextInt();
               int id = admin.getAllBook().get(bookNum).getBookNumber();
               admin.deleteBook(id);

               System.out.println("Message.Success" + Message.Success);
               System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
               System.out.println("Do you want to delete one more book? 1 for yes, 0 for no!!");
               Scanner info4 = new Scanner(System.in);
               int f = info4.nextInt();

               if(f != d)
               break;
             }
             break;
           }

           System.out.println("Do you want to make another option? 1 for continue, 0 for logout!!");
           Scanner info5 = new Scanner(System.in);
           int g = info5.nextInt();

           if(g != e)
           break;
         }
         break;
       }
       admin.logOut();
       System.out.println("logouting..............");
       else {
         System.out.println("Message.Error" + Message.Error);
         System.out.println("Please try to enter the true name and the true password!!!!!!");
       }
     }
   }

   public void Customerop() {
     while(1) {
       //输入用户信息
       Scanner info1 =  new Scanner(System.in);
       System.out.println("First, please enter your name! ");
       String name = info1.nextString();
       Scanner info2 =  new Scanner(System.in);
       System.out.println("Second, please enter your password! ");
       String password = info2.nextString();
       User user = new User.login(String name, String password);
       System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
       //进入客户界面
       if(user.getUserType() == UserType.Customer) {
         Customer customer = (Customer) user;
         System.out.println("Welcome to Customer's operation page!!");
         System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
         int e = 1;
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
             System.out.println(customer.getAllBook(i).getBookNumber() + customer.getAllBook(i).getName() + customer.getAllBook(i).getAuthor() + customer.getAllBook(i).getPrice() + customer.getAllBook(i).getStorage());
           }
           int d = 1;
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
             System.out.println("Do you want to buy one more book? 1 for yes, 0 for no!!");
             Scanner info4 = new Scanner(System.in);
             int f = info4.nextInt();
             if(f != d)
             break;
           }
           System.out.println("Do you want to make another option? 1 for continue, 0 for logout!!");
           Scanner info5 = new Scanner(System.in);
           int g = info5.nextInt();
           if(g != e)
           break;
         }
         break;
       }
       customer.logOut();
       System.out.println("logouting..............");
       else {
         System.out.println("Message.Error" + Message.Error);
         System.out.println("Please try to enter the true name and the true password!!!!!!");
       }
     }
   }
}
