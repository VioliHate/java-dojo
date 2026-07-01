package main.violihate.javadojo.day02oop;

/*
========================================
JAVA DOJO - DAY 02
Exercise 03 - Car Rental
Difficulty: ⭐⭐
========================================

DESCRIPTION

Create a Car class.

Fields:

- plate
- brand
- model
- available

IMPLEMENT

- rent()
- returnCar()

RULES

- A rented car cannot be rented again.
- Returning an available car should not be allowed.

REQUIREMENTS

- Throw an exception for invalid operations.

EXTRA

- Add a Customer class and assign the current renter.
*/

import javax.management.OperationsException;

public class E03_CarRental {

    static class Car {
        private final String plate;
        private final String brand;
        private final String model;
        private boolean available;

        public Car(String plate, String brand, String model) {
            this.plate = plate;
            this.brand = brand;
            this.model = model;
            this.available = true;
        }

        public String getPlate() {
            return plate;
        }

        public String getBrand() {
            return brand;
        }

        public String getModel() {
            return model;
        }

        public boolean isAvailable() {
            return available;
        }

        private void setAvailable(boolean available) {
            this.available = available;
        }

        @Override
        public String toString() {
            return "Car [plate= " + plate + ", brand= " + brand + ", model= " + model + ", available= " + available + "]";
        }

        public void rent() {
            if (!this.isAvailable()) {
                throw new IllegalStateException("Car is not available");
            }
            this.setAvailable(false);
        }

        public void returnCar() {
            if (this.isAvailable()) {
                throw new IllegalStateException("Car is not available");
            }
            this.setAvailable(true);
        }

    }

    static class Customer {
        private final String name;
        private Car rentedCar;

        public Customer(String name) {
            this.name = name;
            this.rentedCar = null;
        }

        @Override
        public String toString() {
            String carInfo = (rentedCar == null) ? "no car" : rentedCar.toString();
            return "Customer [name= " + name + ", " + carInfo + "]";
        }

        public String getName() {
            return name;
        }

        public Car getRentedCar() {
            return rentedCar;
        }

        public void rentCar(Car rentCar) {
            if (rentedCar != null) {
                throw new IllegalStateException(name + " already has a rented car: " + rentedCar.getPlate());
            }
            rentCar.rent();
            this.rentedCar = rentCar;
        }

        public void returnCar() {
            if (rentedCar == null) {
                throw new IllegalStateException(name + " has no car to return");
            }
            rentedCar.returnCar();
            this.rentedCar = null;
        }

    }

    public static void main(String[] args) {
        Car fiatPunto = new Car("GG777GG", "Fiat", "Punto");
        Customer mario = new Customer("Mario");

        System.out.println("====== START ======");
        System.out.println(mario);

        try {
            mario.rentCar(fiatPunto);
            System.out.println(mario);
            mario.returnCar();
            System.out.println(mario);
            mario.returnCar();

        } catch (IllegalStateException e) {
            System.out.println("\n ERROR: " + e.getMessage());
        }
    }


}
