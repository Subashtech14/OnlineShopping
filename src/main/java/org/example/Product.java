package org.example;

public record Product(int id,String ProductName, String Brand, String Model, String ProductDescription, int Price, String Rating, int No) {

    @Override
    public String toString() {
        return "Product Name = " + ProductName  +
                "\nBrand = " + Brand  +
                "\nModel = " + Model  +
                "\nProduct Description = " + ProductDescription  +
                "\nPrice = " + Price +
                "\nRating = " + Rating +
                "\nQuantity = " + No +"\n";
    }


}
