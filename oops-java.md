# OOP in Java — Classes, Objects, Inheritance & Polymorphism

Prerequisite: [Java Fundamentals](./fundamentals.md) (variables, methods, `if`/`switch`, `Scanner`).  
Local setup: see [setup.md](./setup.md) (JDK + IntelliJ IDEA).

---

## Table of contents

1. [What is OOP?](#1-what-is-oop)
2. [Classes](#2-classes)
3. [Objects](#3-objects)
4. [Constructors](#4-constructors)
5. [Encapsulation](#5-encapsulation)
6. [Inheritance](#6-inheritance)
7. [Polymorphism](#7-polymorphism)
8. [Mini project: OOP Calculator](#8-mini-project-oop-calculator)

---

## 1. What is OOP?

**Object-Oriented Programming (OOP)** models real-world things as **objects** that bundle **data** (fields) and **behavior** (methods).

| Pillar | Idea | Java keyword / feature |
|--------|------|-------------------------|
| **Encapsulation** | Hide internal data; expose safe methods | `private`, getters/setters |
| **Inheritance** | Reuse and extend a parent class | `extends` |
| **Polymorphism** | One interface, many implementations | method overriding, parent-type references |

Until now you wrote everything inside `Main`. With OOP you split code into **multiple classes**, each with a clear responsibility.

```java
// Without OOP — everything in Main
public class Main {
    public static void main(String[] args) {
        String name = "Riya";
        int rollNo = 101;
        System.out.println(rollNo + ": " + name);
    }
}

// With OOP — Student is its own blueprint
public class Student {
    String name;
    int rollNo;

    void display() {
        System.out.println(rollNo + ": " + name);
    }
}
```

### Exercise

**Task:** In one sentence each, define **class** and **object**.

**Solution:**

- **Class** — A blueprint (template) that describes what fields and methods objects of that type will have.
- **Object** — A real instance created from a class, with its own values in memory.

---

## 2. Classes

A **class** is a template. It can contain:

- **Fields** (attributes / state) — data the object holds
- **Methods** (behavior) — what the object can do

```java
package org.fullstack;

public class Student {
    // fields (state)
    String name;
    int rollNo;
    float cgpa;

    // method (behavior)
    void display() {
        System.out.println("Roll " + rollNo + ": " + name + ", CGPA " + cgpa);
    }
}
```

| Part | Meaning |
|------|--------|
| `public class Student` | Declares a class named `Student` |
| `String name` | Field — each `Student` object can have its own `name` |
| `void display()` | Instance method — called on a specific object |

**File rule:** `public` top-level class name must match the filename → `Student.java`.

**Access modifiers** (who can see a member):

| Modifier | Same class | Same package | Subclass | Anywhere |
|----------|------------|--------------|----------|----------|
| `private` | yes | no | no | no |
| (default) | yes | yes | no | no |
| `protected` | yes | yes | yes | no |
| `public` | yes | yes | yes | yes |

For fields, prefer `private` and expose through methods (see [Encapsulation](#5-encapsulation)).

### 2.1 `static` vs instance members

```java
public class Counter {
    static int totalObjects = 0;   // belongs to the CLASS (one copy)
    int id;                        // belongs to each OBJECT

    Counter() {
        totalObjects++;
        id = totalObjects;
    }
}
```

- **Instance** field/method — needs an object: `student.display()`
- **Static** field/method — belongs to the class: `Counter.totalObjects`

### Exercise

**Task:** Create a class `Book` with fields `title` (String) and `pages` (int), and a method `summary()` that prints `Title: <title>, Pages: <pages>`.

**Solution:**

```java
public class Book {
    String title;
    int pages;

    void summary() {
        System.out.println("Title: " + title + ", Pages: " + pages);
    }
}
```

---

## 3. Objects

An **object** is a concrete instance of a class — created with `new`.

```java
// syntax: ClassName objectName = new ClassName();
Student s1 = new Student();
Student s2 = new Student();
```

`new` allocates memory and calls the constructor (see [§4](#4-constructors)).

### 3.1 Setting and reading fields

```java
Student s1 = new Student();
s1.name = "Riya";
s1.rollNo = 101;
s1.cgpa = 8.5f;
s1.display();   // Roll 101: Riya, CGPA 8.5
```

Each object has **its own copy** of instance fields:

```java
Student s1 = new Student();
s1.name = "Riya";

Student s2 = new Student();
s2.name = "Aman";

System.out.println(s1.name);   // Riya
System.out.println(s2.name);   // Aman
```

### 3.2 Reference variables

```java
Student s1 = new Student();
Student s2 = s1;      // s2 points to the SAME object as s1

s2.name = "Changed";
System.out.println(s1.name);   // Changed
```

`s1` and `s2` are **references** (addresses), not copies of the whole object.

### 3.3 `null`

```java
Student s = null;
// s.display();   // NullPointerException — no object to call on
```

Always ensure a reference points to a real object before using it.

### Exercise

**Task:** Create two `Book` objects. Set `title` and `pages` for each, then call `summary()` on both.

**Solution:**

```java
public static void main(String[] args) {
    Book b1 = new Book();
    b1.title = "Java Basics";
    b1.pages = 320;
    b1.summary();

    Book b2 = new Book();
    b2.title = "OOP in Java";
    b2.pages = 280;
    b2.summary();
}
```

Expected output:

```
Title: Java Basics, Pages: 320
Title: OOP in Java, Pages: 280
```

---

## 4. Constructors

A **constructor** runs automatically when you write `new ClassName()`. It initializes the object.

```java
public class Student {
    String name;
    int rollNo;

    // constructor — same name as class, no return type
    public Student(String name, int rollNo) {
        this.name = name;
        this.rollNo = rollNo;
    }

    void display() {
        System.out.println(rollNo + ": " + name);
    }
}
```

Usage:

```java
Student s = new Student("Riya", 101);
s.display();   // 101: Riya
```

| Concept | Explanation |
|---------|-------------|
| `this` | Refers to the **current** object |
| `this.name = name` | Assigns parameter `name` to this object's field |
| Default constructor | If you write **no** constructor, Java adds `public ClassName() {}` |
| Constructor overloading | Multiple constructors with different parameters |

```java
public class Student {
    String name;
    int rollNo;

    public Student() {
        this("Unknown", 0);   // calls the 2-arg constructor
    }

    public Student(String name, int rollNo) {
        this.name = name;
        this.rollNo = rollNo;
    }
}
```

### Exercise

**Task:** Add a constructor `Book(String title, int pages)` and create a book with `new Book("Algorithms", 450)`.

**Solution:**

```java
public class Book {
    String title;
    int pages;

    public Book(String title, int pages) {
        this.title = title;
        this.pages = pages;
    }

    void summary() {
        System.out.println("Title: " + title + ", Pages: " + pages);
    }
}

// in main:
Book b = new Book("Algorithms", 450);
b.summary();   // Title: Algorithms, Pages: 450
```

---

## 5. Encapsulation

**Encapsulation** = keep fields **private** and control access through **public methods** (getters/setters).

```java
public class BankAccount {
    private double balance;   // hidden from outside

    public BankAccount(double initialBalance) {
        if (initialBalance >= 0) {
            balance = initialBalance;
        }
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }
}
```

```java
BankAccount acc = new BankAccount(1000);
acc.deposit(500);
acc.withdraw(200);
System.out.println(acc.getBalance());   // 1300
// acc.balance = 99999;   // compile error — balance is private
```

**Why?** You validate input, keep invariants (balance never negative), and can change internal storage later without breaking callers.

### Exercise

**Task:** Make `Book`'s fields `private`. Add a constructor, `getTitle()`, and `getPages()`. Print title from `main` using the getter.

**Solution:**

```java
public class Book {
    private String title;
    private int pages;

    public Book(String title, int pages) {
        this.title = title;
        this.pages = pages;
    }

    public String getTitle() {
        return title;
    }

    public int getPages() {
        return pages;
    }
}

// main:
Book b = new Book("Java", 300);
System.out.println(b.getTitle());   // Java
```

---

## 6. Inheritance

**Inheritance** lets a **child class** reuse and extend a **parent class** (superclass).

```java
// parent (superclass)
public class Animal {
    String name;

    public Animal(String name) {
        this.name = name;
    }

    void eat() {
        System.out.println(name + " is eating");
    }
}

// child (subclass)
public class Dog extends Animal {
    String breed;

    public Dog(String name, String breed) {
        super(name);          // call parent constructor
        this.breed = breed;
    }

    void bark() {
        System.out.println(name + " says Woof!");
    }
}
```

```java
Dog d = new Dog("Bruno", "Labrador");
d.eat();    // inherited from Animal
d.bark();   // defined in Dog
```

| Keyword | Use |
|---------|-----|
| `extends` | Child inherits from parent |
| `super(...)` | Call parent constructor |
| `super.method()` | Call parent's version of a method |

### 6.1 `is-a` relationship

A `Dog` **is an** `Animal`. Inheritance models that relationship.

```
        Animal
          |
          Dog
```

### 6.2 Method overriding (preview)

Child can **replace** parent behavior:

```java
public class Animal {
    void speak() {
        System.out.println("Some sound");
    }
}

public class Dog extends Animal {
    @Override
    void speak() {
        System.out.println("Woof!");
    }
}
```

`@Override` is optional but recommended — compiler checks the signature matches the parent.

### 6.3 Single inheritance

Java allows **one direct parent** per class:

```java
public class Dog extends Animal { }   // OK
// public class Dog extends Animal, Pet { }   // NOT allowed
```

### 6.4 `Object` — root of all classes

Every class implicitly extends `java.lang.Object`:

```java
public class Student { }   // same as: public class Student extends Object { }
```

Useful inherited methods: `toString()`, `equals()`, `hashCode()`.

### Exercise

**Task:** Create `Vehicle` with `brand` and method `start()`. Create `Car extends Vehicle` with `start()` printing `Car started: <brand>`.

**Solution:**

```java
public class Vehicle {
    String brand;

    public Vehicle(String brand) {
        this.brand = brand;
    }

    void start() {
        System.out.println("Vehicle started: " + brand);
    }
}

public class Car extends Vehicle {
    public Car(String brand) {
        super(brand);
    }

    @Override
    void start() {
        System.out.println("Car started: " + brand);
    }
}

// main:
Car c = new Car("Toyota");
c.start();   // Car started: Toyota
```

---

## 7. Polymorphism

**Polymorphism** ("many forms") — the same type reference can point to different subclass objects, and the **correct method runs at runtime**.

### 7.1 Compile-time vs runtime

| Type | Example |
|------|---------|
| **Compile-time** (overloading) | Two methods named `add` with different parameters |
| **Runtime** (overriding) | Parent reference calls child's overridden method |

This section focuses on **runtime polymorphism** via inheritance.

### 7.2 Upcasting

```java
Animal a = new Dog("Bruno", "Lab");   // Dog IS-A Animal — valid
a.eat();                              // Animal method

// a.bark();   // compile error — reference type is Animal
```

The **reference type** decides what you can call at compile time. The **actual object** decides which overridden method runs.

### 7.3 Overridden method at runtime

```java
public class Animal {
    void speak() {
        System.out.println("Animal sound");
    }
}

public class Dog extends Animal {
    @Override
    void speak() {
        System.out.println("Woof!");
    }
}

public class Cat extends Animal {
    @Override
    void speak() {
        System.out.println("Meow!");
    }
}
```

```java
Animal a1 = new Dog("D", "x");
Animal a2 = new Cat("C");

a1.speak();   // Woof!
a2.speak();   // Meow!
```

Even though both variables are type `Animal`, Java calls the **actual object's** `speak()`.

### 7.4 Polymorphism with arrays / collections

```java
Animal[] zoo = {
    new Dog("Bruno", "Lab"),
    new Cat("Whiskers"),
    new Dog("Max", "Beagle")
};

for (Animal a : zoo) {
    a.speak();   // each animal speaks in its own way
}
```

### 7.5 Abstract classes

When a parent should **not** be instantiated directly, mark it `abstract`:

```java
public abstract class Operation {
    protected String symbol;

    public abstract double calculate(double a, double b);

    public String getSymbol() {
        return symbol;
    }
}
```

- Abstract class can have fields, constructors, and concrete methods
- Subclass **must** implement all `abstract` methods (or be abstract too)

```java
public class Addition extends Operation {
    public Addition() {
        symbol = "+";
    }

    @Override
    public double calculate(double a, double b) {
        return a + b;
    }
}
```

### 7.6 Interfaces (brief)

An **interface** is a contract — only method signatures (Java 8+ can have `default` methods):

```java
public interface Drawable {
    void draw();
}

public class Circle implements Drawable {
    @Override
    public void draw() {
        System.out.println("Drawing circle");
    }
}
```

Use `implements` for interfaces, `extends` for classes (and for extending interfaces).

### Exercise

**Task:** Given `Animal` with `void speak()`, create `Dog` and `Cat` that override `speak()`. Store both in an `Animal[]` and call `speak()` in a loop.

**Solution:**

```java
Animal[] animals = { new Dog("D1", "Lab"), new Cat("C1") };

for (Animal a : animals) {
    a.speak();
}
```

Expected output:

```
Woof!
Meow!
```

---

## 8. Mini project: OOP Calculator

Build a calculator that uses **classes**, **objects**, **inheritance**, and **polymorphism**.

### 8.1 Requirements

- User enters two numbers and an operator (`+`, `-`, `*`, `/`)
- Program prints the result
- Each operation is its own class
- All operations share a common parent (`Operation`)
- `Calculator` picks the right operation **polymorphically**

### 8.2 Project structure

```
OOP-Calculator/
├── build.gradle
├── settings.gradle
└── src/main/java/org/fullstack/
    ├── Main.java
    ├── Calculator.java
    └── operations/
        ├── Operation.java      (abstract parent)
        ├── Addition.java
        ├── Subtraction.java
        ├── Multiplication.java
        └── Division.java
```

Open `OOP-Calculator` in IntelliJ (same steps as [setup.md](./setup.md)).

### 8.3 Class diagram

```
                    ┌─────────────────────┐
                    │   <<abstract>>      │
                    │     Operation       │
                    ├─────────────────────┤
                    │ # symbol: String    │
                    ├─────────────────────┤
                    │ + calculate(a,b)    │
                    │ + getSymbol()       │
                    └──────────┬──────────┘
                               │ extends
         ┌─────────────────────┼─────────────────────┐
         │                     │                     │
   ┌─────▼─────┐         ┌─────▼─────┐         ┌─────▼─────┐
   │ Addition  │         │Subtraction│         │  Division │
   └───────────┘         └───────────┘   ...   └───────────┘

   ┌──────────────────────────────────────────┐
   │              Calculator                   │
   ├──────────────────────────────────────────┤
   │ - operations: Operation[]                 │
   ├──────────────────────────────────────────┤
   │ + compute(a, b, operator): double         │
   └──────────────────────────────────────────┘
```

### 8.4 Abstract parent — `Operation`

```java
package org.fullstack.operations;

public abstract class Operation {
    protected String symbol;

    public abstract double calculate(double a, double b);

    public String getSymbol() {
        return symbol;
    }
}
```

**OOP used:** abstraction — every operation **must** implement `calculate`, but details differ per child.

### 8.5 Child classes (inheritance + overriding)

```java
package org.fullstack.operations;

public class Addition extends Operation {
    public Addition() {
        symbol = "+";
    }

    @Override
    public double calculate(double a, double b) {
        return a + b;
    }
}
```

```java
public class Subtraction extends Operation {
    public Subtraction() { symbol = "-"; }

    @Override
    public double calculate(double a, double b) {
        return a - b;
    }
}
```

```java
public class Multiplication extends Operation {
    public Multiplication() { symbol = "*"; }

    @Override
    public double calculate(double a, double b) {
        return a * b;
    }
}
```

```java
public class Division extends Operation {
    public Division() { symbol = "/"; }

    @Override
    public double calculate(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return a / b;
    }
}
```

**OOP used:** inheritance (`extends Operation`), polymorphism (`@Override calculate`).

### 8.6 `Calculator` class (objects + polymorphism)

```java
package org.fullstack;

import org.fullstack.operations.*;

public class Calculator {
    private final Operation[] operations;

    public Calculator() {
        operations = new Operation[] {
            new Addition(),
            new Subtraction(),
            new Multiplication(),
            new Division()
        };
    }

    public double compute(double a, double b, String operator) {
        for (Operation op : operations) {
            if (op.getSymbol().equals(operator)) {
                return op.calculate(a, b);   // runtime polymorphism
            }
        }
        throw new IllegalArgumentException("Invalid operator: " + operator);
    }
}
```

| OOP concept | Where |
|-------------|--------|
| **Class / Object** | `Calculator`, `Addition`, … |
| **Encapsulation** | `operations` is `private` |
| **Inheritance** | `Addition extends Operation` |
| **Polymorphism** | `Operation op = new Addition(); op.calculate(...)` |

### 8.7 `Main` — entry point

```java
package org.fullstack;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Calculator calculator = new Calculator();

        System.out.print("First number: ");
        double a = sc.nextDouble();

        System.out.print("Second number: ");
        double b = sc.nextDouble();

        System.out.print("Operator (+, -, *, /): ");
        String operator = sc.next();

        try {
            double result = calculator.compute(a, b, operator);
            System.out.println("Result: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }
}
```

### 8.8 Sample run

```
First number: 20
Second number: 4
Operator (+, -, *, /): /
Result: 5.0
```

```
First number: 10
Second number: 0
Operator (+, -, *, /): /
Error: Cannot divide by zero
```

### 8.9 Run from terminal

```bash
cd OOP-Calculator
./gradlew run
```

Or run `Main` from IntelliJ (green play button).

### 8.10 How each pillar appears

| Pillar | In this project |
|--------|-----------------|
| **Encapsulation** | `Calculator` hides the `operations` array; callers only use `compute()` |
| **Inheritance** | `Addition`, `Subtraction`, … extend `Operation` |
| **Polymorphism** | Loop uses `Operation` references; JVM calls the correct `calculate()` |

### 8.11 Stretch exercises

1. **Modulo** — Add `Modulo extends Operation` with symbol `%`.
2. **History** — Add a `List<String>` in `Calculator` to store each expression and result.
3. **Interface** — Extract `calculate` into an interface `Calculable` and have `Operation` implement it.
4. **Scientific** — Add `Power extends Operation` using `Math.pow(a, b)`.

**Task (stretch):** Add `Modulo` and register it in `Calculator`'s constructor array.

**Hint:**

```java
public class Modulo extends Operation {
    public Modulo() { symbol = "%"; }

    @Override
    public double calculate(double a, double b) {
        if (b == 0) throw new IllegalArgumentException("Cannot modulo by zero");
        return a % b;
    }
}
```

---

## Quick reference

```java
// class & object
Student s = new Student("Riya", 101);

// inheritance
class Dog extends Animal { super(name); }

// overriding
@Override
void speak() { ... }

// polymorphism
Animal a = new Dog(...);
a.speak();   // Dog's version runs

// abstract
public abstract class Operation {
    public abstract double calculate(double a, double b);
}
```

---

**Next steps:** interfaces in depth, `abstract` vs `interface`, packages, and design patterns (Factory, Strategy).
