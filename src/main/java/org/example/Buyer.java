package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Buyer {

    ArrayList<Product> products=new ArrayList<>();
    ArrayList<Product> checkOutProducts=new ArrayList<>();
    public void viewOurProduct(String Role) throws SQLException {
        System.out.println(Role);
        if (Role.equalsIgnoreCase("Seller")){
           addProduct();
        }
        else{
        Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/online_shopping","root","Subash27@");
        Statement statement=connection.createStatement();
        ResultSet resulset=statement.executeQuery("select * from product");
        while(resulset.next()){
            System.out.println("""
                    Id =  %d
                    Product Name = %s
                    Brand = %s
                    Model = %s
                    Product Description = %s
                    Price = %d
                    Rating = %s
                    Quantity = %d
                    """.formatted(resulset.getInt(1),resulset.getString(2),resulset.getString(3),resulset.getString(4),resulset.getString(5),
                    resulset.getInt(6),resulset.getBigDecimal(7).toString(),resulset.getInt(8)));
            products.add(new Product(resulset.getInt(1),resulset.getString(2),resulset.getString(3),resulset.getString(4),resulset.getString(5),
                    resulset.getInt(6),resulset.getBigDecimal(7).toString(),resulset.getInt(8)));
        }}
        if(Role.equalsIgnoreCase("View")) {
            viewAndBuy();
        }
        else {
            System.out.println("Login to Continue");
            Main.getStarted();
        }
    }

    private void addProduct() throws SQLException {
        System.out.println("""
                1 -> Do you want to addProduct ?
                2 -> Buy the Other Products ?
                """);
        int op;
        op=new Scanner(System.in).nextInt();
        switch (op){
            case 1 -> Seller.addProduct();
            case 2 -> viewOurProduct("DEMO");
            default -> System.out.println("Invalid Option");
        }

    }

    public void viewAndBuy() {
        int id,input;
        String option;
        Scanner scanner=new Scanner(System.in);
        do{

        System.out.println("Enter the Product id to Buy");
        id= scanner.nextInt();
        scanner.nextLine();
        checkOutProducts.add(products.get(id-1));
        System.out.println("Do You wish add more Products");
        option=scanner.nextLine();
        }while (option.equalsIgnoreCase("y"));
        System.out.println(checkOutProducts.toString());
        System.out.println("""
                Press 
                1 -> To check out this items
                2 -> Continue
                """);
        input=scanner.nextInt();
        switch(input){
            case 1 -> checkout();
            case 2 -> viewAndBuy();
        }

    }

    private void checkout() {
        int total=0;
        for(Product p:checkOutProducts){
        System.out.println("""
                Product Name =  %s ,
                Brand = %s ,
                Model = %s ,
                Price = %d
               
                """.formatted(p.ProductName(),p.Brand(),p.Model(),p.Price()));
        total= total+p.Price();
    }
        System.out.println("Total Price "+total);
    }
}
