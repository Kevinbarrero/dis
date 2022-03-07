package ru.itis.Uber;

public class Driver {
    private String phone;
    private Object Car;
    Driver(String phone, Object Car){
        this.phone = phone;
        this.Car = Car;
    }
    static class Car{
        private String model;
        private String number;
        Car(String model, String number){
            this.model = model;
            this.number = number;
        }
        public String getModel() {
            return model;
        }

        public String getNumber() {
            return number;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        @Override
        public String toString() {
            return "Car{" +
                    "model='" + model + '\'' +
                    ", number='" + number + '\'' +
                    '}';
        }
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getPhone() {
        return phone;
    }
    public Object getCar() {
        return Car;
    }

    public void setCar(Object car) {
        Car = car;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "phone='" + phone + '\'' + Car +
                '}';
    }
}
