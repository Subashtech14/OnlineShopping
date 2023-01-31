package org.example;

import java.sql.*;
import java.util.Scanner;

public class Authentication {
     int id;
    public int fetchId() throws SQLException {
        Connection connection = null;
        try{
         connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/online_shopping","root","Subash27@");
        Statement statement=connection.createStatement();
        ResultSet rs=statement.executeQuery("select * from account");
        while(rs.next()) {
            id=rs.getInt(1);
        }

        }
        catch (SQLException e){
            System.out.println(" Error Occurred "+e);
        }
        finally {
            connection.close();

        }
        return id;
    }
    private String Username,Password,Role,Description,Phone_Number,Email;
    public void signUp() throws SQLException {
        id=fetchId()+1;
        Scanner input=new Scanner(System.in);
        System.out.println("Enter the User Name ");
        Username=input.nextLine();
        System.out.println("Enter the Password ");
        Password=input.nextLine();
        System.out.println("Enter the Email ");
        Email=input.nextLine();
        System.out.println("Enter the Phone Number ");
        Phone_Number=input.nextLine();
        System.out.println("""
                Enter the Role 
                Seller
                Buyer""");
        Role=input.nextLine();
        System.out.println("Description ");
        Description=input.nextLine();
        ValidatorAccount v=new ValidatorAccount(Username,Password,Role,Description,Phone_Number,Email);
        if(v.validate()){
           try{ String Query= """
                   INSERT INTO `online_shopping`.`account`
                   (`id`,
                   `Username`,
                   `Password`,
                   `Role`,
                   `Description`,
                   `Phone_Number`,
                   `Email`)
                   VALUES
                   (%d,
                   "%s","%s","%s","%s","%s","%s");
                   """.formatted(id,Username,Password,Role,Description,Phone_Number,Email);
               System.out.println(Query);
               Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/online_shopping","root","Subash27@");
               Statement statement=connection.createStatement();
               if(statement.executeUpdate(Query) == 1)
               {
                   System.out.println("Run successfully");
               }

               connection.close();
           }
           catch (SQLException e){

           }
            System.out.println("""
                    Account Created Successfully
                    Please Re-Login""");
                login();
        }
        else{
            System.out.println("Invalid input \n Please re-enter valid inputs  ");
            Main.getStarted();
        }
    }

    public void login() throws SQLException {
        String name,password;
        Scanner sc =new Scanner(System.in);
        System.out.println("Enter the UserName");
        name=sc.nextLine();
        System.out.println("Enter the Password");
        password=sc.nextLine();
        String LoginUserName,LoginPassword;
        Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/online_shopping","root","Subash27@");
        Statement statement=connection.createStatement();
        ResultSet rs=statement.executeQuery("select * from account");
        while(rs.next()) {
             LoginUserName=rs.getString(2);
            LoginPassword=rs.getString(3);
            if(validate(LoginUserName,LoginPassword,name,password)){
               new Buyer().viewOurProduct(rs.getString(4));
               break;
            }

        }
    }

    private boolean validate(String userName, String password, String loginUserName, String loginPassword) {
            if(userName.equals(loginUserName) && password.equals(loginPassword)){
                System.out.println("Account Logged in successfully");
                return true;
            }

            return false;
    }
}
