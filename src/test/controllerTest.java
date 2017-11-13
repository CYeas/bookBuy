package test;

import controller.controller;
import model.Admin;
import model.Customer;
import util.DatabaseUtil;

public class controllerTest {
  public static void main(String args[]) {
    new DatabaseUtil().initDatabase();
    new Admin(0 ,"cyea", "1234", 0).signUp();
    new Admin(0 ,"cyea2", "1234", 0).signUp();
    new Customer(0 ,"customer", "1234", 1).signUp();
    controller Controller = new controller();
    Controller.Adminop();
    Controller.Customerop();
  }
}
