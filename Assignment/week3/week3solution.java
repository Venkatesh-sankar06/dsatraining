// Interface for Power Control (ISP)
interface PowerSwitch {
    void turnOn();
    void turnOff();
}

// Interface for Display (ISP)
interface Displayable {
    void displayStatus();
}

// Base Device Class (SRP)
class Device {
    private String deviceName;
    private String powerStatus;

    public Device(String deviceName) {
        this.deviceName = deviceName;
        this.powerStatus = "OFF";
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getPowerStatus() {
        return powerStatus;
    }

    public void setPowerStatus(String status) {
        this.powerStatus = status;
    }
}

// Light Class
class Light extends Device implements PowerSwitch, Displayable {
    private int brightness;

    public Light(String name, int brightness) {
        super(name);
        this.brightness = brightness;
    }

    @Override
    public void turnOn() {
        setPowerStatus("ON");
        System.out.println(getDeviceName() + " turned ON");
    }

    @Override
    public void turnOff() {
        setPowerStatus("OFF");
        System.out.println(getDeviceName() + " turned OFF");
    }

    @Override
    public void displayStatus() {
        System.out.println("----- Light Status -----");
        System.out.println("Device Name: " + getDeviceName());
        System.out.println("Power: " + getPowerStatus());
        System.out.println("Brightness: " + brightness);
    }
}

// Thermostat Class
class Thermostat extends Device implements PowerSwitch, Displayable {
    private int temperature;

    public Thermostat(String name, int temperature) {
        super(name);
        this.temperature = temperature;
    }

    @Override
    public void turnOn() {
        setPowerStatus("ON");
        System.out.println(getDeviceName() + " turned ON");
    }

    @Override
    public void turnOff() {
        setPowerStatus("OFF");
        System.out.println(getDeviceName() + " turned OFF");
    }

    @Override
    public void displayStatus() {
        System.out.println("----- Thermostat Status -----");
        System.out.println("Device Name: " + getDeviceName());
        System.out.println("Power: " + getPowerStatus());
        System.out.println("Temperature: " + temperature + "°C");
    }
}

// Controller Class (DIP)
class SmartHomeController {
    private PowerSwitch device;

    public SmartHomeController(PowerSwitch device) {
        this.device = device;
    }

    public void powerOnDevice() {
        device.turnOn();
    }

    public void powerOffDevice() {
        device.turnOff();
    }
}

// Payment Interface
interface PaymentMethod {
    void processPayment(double amount);
}

// Credit Card
class CreditCardPayment implements PaymentMethod {
    private String cardNumber;

    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void processPayment(double amount) {
        System.out.println("Processing Credit Card Payment of Rs." + amount);
        System.out.println("Card Number: " + cardNumber);
    }
}

// PayPal
class PayPalPayment implements PaymentMethod {
    private String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    public void processPayment(double amount) {
        System.out.println("Processing PayPal Payment of Rs." + amount);
        System.out.println("PayPal Email: " + email);
    }
}

// UPI
class UPIPayment implements PaymentMethod {
    private String upiId;

    public UPIPayment(String upiId) {
        this.upiId = upiId;
    }

    public void processPayment(double amount) {
        System.out.println("Processing UPI Payment of Rs." + amount);
        System.out.println("UPI ID: " + upiId);
    }
}

// Payment Processor (DIP)
class PaymentProcessor {
    private PaymentMethod paymentMethod;

    public PaymentProcessor(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void process(double amount) {
        paymentMethod.processPayment(amount);
    }
}

// Notification Interfaces
interface EmailSender {
    void sendEmail(String message, String email);
}

interface SMSSender {
    void sendSMS(String message, String phoneNumber);
}

interface PushNotificationSender {
    void sendPushNotification(String message, String deviceId);
}

// Email Notification
class EmailNotification implements EmailSender {
    public void sendEmail(String message, String email) {
        System.out.println("Sending Email to " + email + ": " + message);
    }
}

// SMS Notification
class SMSNotification implements SMSSender {
    public void sendSMS(String message, String phoneNumber) {
        System.out.println("Sending SMS to " + phoneNumber + ": " + message);
    }
}

// Push Notification
class MobileAppNotification implements PushNotificationSender {
    public void sendPushNotification(String message, String deviceId) {
        System.out.println("Sending Push Notification to Device " + deviceId + ": " + message);
    }
}

// Main Class
public class week3solution {
    public static void main(String[] args) {

        // Devices
        Light light = new Light("Living Room Light", 75);
        Thermostat thermostat = new Thermostat("Bedroom Thermostat", 26);

        SmartHomeController lightController = new SmartHomeController(light);
        SmartHomeController thermoController = new SmartHomeController(thermostat);

        lightController.powerOnDevice();
        thermoController.powerOnDevice();

        light.displayStatus();
        System.out.println();
        thermostat.displayStatus();

        lightController.powerOffDevice();
        thermoController.powerOffDevice();

        System.out.println("\nAfter Turning OFF:\n");

        light.displayStatus();
        System.out.println();
        thermostat.displayStatus();

        // Payments
        PaymentProcessor processor1 = new PaymentProcessor(new CreditCardPayment("1234-5678-9876"));
        processor1.process(5000);

        System.out.println("----------------------");

        PaymentProcessor processor2 = new PaymentProcessor(new PayPalPayment("user@example.com"));
        processor2.process(2500);

        System.out.println("----------------------");

        PaymentProcessor processor3 = new PaymentProcessor(new UPIPayment("venkatesh@upi"));
        processor3.process(1500);

        // Notifications
        EmailSender emailSender = new EmailNotification();
        SMSSender smsSender = new SMSNotification();
        PushNotificationSender pushSender = new MobileAppNotification();

        emailSender.sendEmail("Welcome!", "user@example.com");
        smsSender.sendSMS("Your OTP is 123456", "9876543210");
        pushSender.sendPushNotification("New message!", "DEVICE123");
    }
}