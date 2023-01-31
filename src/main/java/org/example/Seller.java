package org.example;
import java.sql.*;
import java.util.Scanner;

public class Seller {
    static int id=0;
    static Connection connection = null;
    public static void addProduct() throws SQLException {
        id=getid()+1;
        int id = 0,No,Price;
        String ProductName, Brand, Model, ProductDescription;
        double Rating;
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the Product Name");
        ProductName=scanner.nextLine();
        System.out.println("Enter the Product Brand");
        Brand=scanner.nextLine();
        System.out.println("Enter the Product Model");
        Model=scanner.nextLine();
        System.out.println("Enter the Product Description");
        ProductDescription=scanner.nextLine();
        System.out.println("Enter the Product Price in  R.S ");
        Price=scanner.nextInt();
        System.out.println("Enter the Rating");
        Rating=scanner.nextDouble();
        System.out.println("Enter the Quantity");
        No=scanner.nextInt();
        scanner.nextLine();
        String Query= """
                INSERT INTO `online_shopping`.`product`
                (`id`,
                `ProductName`,
                `Brand`,
                `Model`,
                `ProductDescription`,
                `Price`,
                `Rating`,
                `Quantity`)
                VALUES
              (
                %d,"%s","%s","%s","%s",%d,%.2f,%d);
                """.formatted(id,ProductName,Brand,Model,ProductDescription,Price,Rating,No);
        Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/online_shopping","root","Subash27@");
        Statement statement=connection.createStatement();
        if(statement.executeUpdate(Query) == 1)
        {
            System.out.println("Product added Successfully");
        }


    }

    private static int getid() {
        try {

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/online_shopping", "root", "Subash27@");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from product");
            while (rs.next()) {
                id = rs.getInt(1);
            }
        }catch (Exception e){

        }
        return id;
    }
}
