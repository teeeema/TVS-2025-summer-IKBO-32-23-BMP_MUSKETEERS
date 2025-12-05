import java.time.LocalDate;

class Car {
    private String model;
    private String license;
    private String color;
    private int year;
    private String ownerName;
    private String insuranceNumber;
    protected String engineType;
    private final int now = LocalDate.now().getYear();

    public Car() {
        this.model = "ERROR";
        this.license = "ERROR";
        this.color = "ERROR";
        this.year = 0;
    }

    public Car(String model, String license, String color, int year) {
        this.model = model;
        this.license = license;
        this.color = color;
        this.year = year;
    }

    public Car(String model, int year) {
        this.model = model;
        this.year = year;
        this.license = "ERROR";
        this.color = "ERROR";
    }

    public String toString() {
        return "Model: " + model + "\n" +
                "License plate: " + license + "\n" +
                "Color: " + color + "\n" +
                "Year: " + year + "\n" +
                "Owner: " + (ownerName != null ? ownerName : "none") + "\n" +
                "Insurance: " + (insuranceNumber != null ? insuranceNumber : "none") + "\n" +
                "Engine: " + (engineType != null ? engineType : "not specified") + "\n";
    }

    public int getAge() {
        // Имитация нагрузки
        int sum = 0;
        for (int i = 0; i < 1000; i++) {
            sum += i;
        }
        return now - year;
    }
}

class ElectricCar extends Car {
    private int batteryCapacity;

    public ElectricCar(String model, String license, String color, int year, int batteryCapacity) {
        super(model, license, color, year);
        this.batteryCapacity = batteryCapacity;
        this.engineType = "Electric motor";
    }

    public int getBatteryCapacity() { return batteryCapacity; }
    public void setBatteryCapacity(int batteryCapacity) { this.batteryCapacity = batteryCapacity; }

    public String toString() {
        // Имитация нагрузки
        String result = super.toString();
        for (int i = 0; i < 500; i++) {
            result.length(); // Просто вызов
        }
        return result + "Battery: " + batteryCapacity + " kWh\n";
    }
}

public class rrrr {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Программа запущена. PID: " + ProcessHandle.current().pid());
        System.out.println("Работает 60 секунд...");
        System.out.println("=== ПОДКЛЮЧАЙТЕ АНАЛИЗАТОР СЕЙЧАС ===");

        long startTime = System.currentTimeMillis();
        long duration = 60000; // 60 секунд

        // Создаем много объектов для анализа памяти
        int objectCount = 0;
        while (System.currentTimeMillis() - startTime < duration) {
            Car car = new Car("MAZDA_" + objectCount, "A" + objectCount + "BC", "black", 2022);
            car.getAge(); // Вызываем метод

            if (objectCount % 1000 == 0) {
                long elapsed = System.currentTimeMillis() - startTime;
                System.out.println("Создано объектов: " + objectCount +
                        ", Прошло времени: " + (elapsed/1000) + " сек");
            }

            objectCount++;

            // Небольшая пауза каждые 100 объектов
            if (objectCount % 100 == 0) {
                Thread.sleep(10);
            }
        }

        System.out.println("=== ИТОГО ===");
        System.out.println("Создано объектов Car: " + objectCount);
        System.out.println("Завершено!");

        // Дополнительная пауза перед завершением
        Thread.sleep(5000);
    }
}