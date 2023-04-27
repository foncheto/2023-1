package App2;

public class Cat {
    int age;
    String name;

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String nombre) {
        this.name = nombre;
    }

    public void meow() {
        System.out.println("Meow!");
    }

    public void sleep() {
        System.out.println("Zzz...");
    }

    public void eat() {
        System.out.println("Yum yum yum");
    }
}
