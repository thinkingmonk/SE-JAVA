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

**Object-Oriented Programming (OOP)** models real-world things as **objects** that bundle **data** (fields) and **behavior** (methods). Instead of one long `main` method that holds every variable, you design small types that match how the problem actually works.

| Pillar | Idea | Java keyword / feature |
|--------|------|-------------------------|
| **Encapsulation** | Hide internal data; expose safe methods | `private`, getters/setters |
| **Inheritance** | Reuse and extend a parent class | `extends` |
| **Polymorphism** | One interface, many implementations | method overriding, parent-type references |

Until now you wrote everything inside `Main`. With OOP you split code into **multiple classes**, each with a clear responsibility.

**Scenario — college ID card vs a sticky note**

Imagine the college office tracks students with sticky notes: name on one note, roll number on another, CGPA on a third. Anyone can scribble on any note, mix them up, or lose one. That is “everything in `main`”: data is scattered and easy to break.

An ID card is better: one student’s details live together, and the office has rules (you cannot change CGPA by scribbling on the card). OOP is that ID-card style: a `Student` object owns its data and the actions that belong to a student.

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

If you later add 200 students, you do not invent 200 sets of variables. You create 200 `Student` objects from the same class.

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

Think of a class as the **form** the college prints for every student. The form has blanks (fields) and instructions (methods). Filling the form later creates an object.

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

**Scenario — hostel room vs campus notice board**

- `private` is a locked hostel cupboard: only that room’s occupant (the class) can open it.
- Default (no modifier) is a notice on the hostel corridor: other rooms in the **same building** (package) can read it; other buildings cannot.
- `protected` is a family recipe: the family (class + subclasses) and the same kitchen (package) can use it; strangers cannot.
- `public` is a poster on the college gate: anyone can see it.

You would not put a bank balance on the college gate (`public`). You keep it in the cupboard (`private`) and only change it through a teller (`deposit` / `withdraw`).

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

**Scenario — roll number vs college name**

Each student has their own `rollNo` (instance). The college name `"TCET"` is shared by everyone; you do not store it 4,000 times. A `static` field is that shared college name (or a running count of how many students were ever registered). Changing `Counter.totalObjects` is like updating the admission counter at the gate — one number for the whole college, not one per student.

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

An **object** is a concrete instance of a class — created with `new`. The class is the recipe; the object is the cooked dish sitting on the table.

```java
// syntax: ClassName objectName = new ClassName();
Student s1 = new Student();
Student s2 = new Student();
```

`new` allocates memory and calls the constructor (see [§4](#4-constructors)).

**Scenario — two students, same form**

The admission office uses one `Student` form. Riya fills a copy; Aman fills another copy. Two objects, same class, different data. Calling `s1.display()` is like asking Riya to show her ID — Aman’s card is not affected.

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

Changing Aman’s name does not rename Riya, because their fields live in different memory.

### 3.2 Reference variables

```java
Student s1 = new Student();
Student s2 = s1;      // s2 points to the SAME object as s1

s2.name = "Changed";
System.out.println(s1.name);   // Changed
```

`s1` and `s2` are **references** (addresses), not copies of the whole object.

**Scenario — two names for the same locker**

`s1` is a key to locker 12. `Student s2 = s1` does not build a second locker; it copies the key. If Aman uses `s2` to put a new name on the locker, Riya still sees that name when she uses `s1`. To get a second locker you must use `new` again.

### 3.3 `null`

```java
Student s = null;
// s.display();   // NullPointerException — no object to call on
```

Always ensure a reference points to a real object before using it.

**Scenario — a library card with no account**

`null` is an empty sleeve: you have a variable that *could* hold a student, but nobody is registered yet. Calling `display()` is like swiping a blank card at the library gate — the system crashes (`NullPointerException`) because there is no person behind the card.

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

A **constructor** runs automatically when you write `new ClassName()`. It initializes the object so it is never born “half empty” unless you choose that.

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

**Scenario — filling the admission form at the counter**

When a student is admitted, the clerk does not create a blank file and hope someone fills it later. They write name and roll number **while opening the file**. `new Student("Riya", 101)` is that moment: the object exists only after those values are set. `this.name` means “this file’s name column,” not the clerk’s parameter named `name`.

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

**Scenario — walk-in vs full registration**

Some visitors get a guest pass (`new Student()` → name `"Unknown"`). Regular students get a full form (`new Student("Riya", 101)`). Both constructors still open a real file; the no-arg one just fills defaults by calling the other constructor with `this(...)`.

If you add any constructor yourself, Java **stops** providing the empty default. Then `new Student()` will not compile unless you write a no-arg constructor.

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

**Encapsulation** = keep fields **private** and control access through **public methods** (getters/setters). The object decides *how* its data may change; callers do not poke the insides.

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

**Scenario — ATM, not an open cash drawer**

If `balance` were `public`, anyone could write `acc.balance = -500` or `acc.balance = 99999` — like reaching into the bank vault. An ATM never lets you edit the number directly. You press Deposit or Withdraw; the machine checks the amount, then updates the ledger.

Same idea for CGPA: a student should not set `cgpa = 15` from `main`. A method like `updateCgpa` can reject impossible values. Later you could store paise instead of rupees inside `BankAccount`; as long as `getBalance()` still returns rupees, old code keeps working.

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

**Inheritance** lets a **child class** reuse and extend a **parent class** (superclass). Shared fields and methods live once in the parent; the child adds only what is special.

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

**Scenario — college person, then student / faculty**

Everyone on campus is a **Person**: name, ID, can `enterCampus()`. A **Student** is a Person who also has a roll number and can `submitAssignment()`. A **Faculty** is a Person who has a department and can `markAttendance()`. You do not copy `enterCampus()` three times. You put it on `Person` and write `Student extends Person`.

`super(name)` is the clerk first filling the **Person** part of the file, then filling the student-only columns.

### 6.1 `is-a` relationship

A `Dog` **is an** `Animal`. Inheritance models that relationship.

```
        Animal
          |
          Dog
```

Use inheritance when the sentence “X is a Y” is true in the domain. A `Car` **is a** `Vehicle`. A `Library` is **not** a `Book` — it *has* books (composition), so do not `extend Book`.

**Scenario — wrong inheritance**

`class Circle extends Point` looks tempting (a circle has a centre). A circle is **not** a point; it **has** a point. If you inherit, a circle could be passed anywhere a point is expected and break geometry. Prefer a `Point center` field inside `Circle`.

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

**Scenario — same instruction, different people**

The parent says “introduce yourself.” A generic `Person` might print a name. A `Student` override prints name **and** branch. The action has the same name (`speak` / `introduce`); the child customizes the script. If you misspell the method and skip `@Override`, you accidentally add a *new* method and the parent version still runs.

### 6.3 Single inheritance

Java allows **one direct parent** per class:

```java
public class Dog extends Animal { }   // OK
// public class Dog extends Animal, Pet { }   // NOT allowed
```

**Scenario — one official parent on the form**

Java’s family tree is a single line of `extends`. If a class needs two “roles” (printable + comparable), you keep one parent class and add extra **interfaces** (see [§7.6](#76-interfaces-brief)).

### 6.4 `Object` — root of all classes

Every class implicitly extends `java.lang.Object`:

```java
public class Student { }   // same as: public class Student extends Object { }
```

Useful inherited methods: `toString()`, `equals()`, `hashCode()`.

**Scenario — every file still has a barcode**

Even a tiny `Student` class already “is an” `Object`. Printing an object without overriding `toString()` shows a default barcode-like string (`Student@1a2b3c`). Override `toString()` when you want `101: Riya` in logs.

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

**Polymorphism** ("many forms") — the same type reference can point to different subclass objects, and the **correct method runs at runtime**. You write one loop or one method against the parent type; each child still behaves as itself.

**Scenario — one announcement, many responses**

The hostel warden says: “Everyone, introduce yourself.” Students, faculty, and security all hear the same command (`speak()`), but each group answers differently. The warden does not write a separate speech for each person; they speak to a list of `Person`. That is runtime polymorphism.

### 7.1 Compile-time vs runtime

| Type | Example |
|------|---------|
| **Compile-time** (overloading) | Two methods named `add` with different parameters |
| **Runtime** (overriding) | Parent reference calls child's overridden method |

This section focuses on **runtime polymorphism** via inheritance.

**Scenario — same word, different menus**

**Overloading** is a canteen counter with two `order` windows: `order(String item)` vs `order(String item, int qty)`. The compiler picks which method you meant from the **arguments you wrote**.

**Overriding** is the same window for every customer (`Person.introduce()`), but a student and a professor give different speeches when the JVM looks at **who is actually standing there**.

### 7.2 Upcasting

```java
Animal a = new Dog("Bruno", "Lab");   // Dog IS-A Animal — valid
a.eat();                              // Animal method

// a.bark();   // compile error — reference type is Animal
```

The **reference type** decides what you can call at compile time. The **actual object** decides which overridden method runs.

**Scenario — treating a dog as “an animal” at the clinic**

The vet’s clipboard says `Animal`. You can still bring Bruno (a `Dog`). The clinic will feed any animal (`eat()`). It will not assume every animal can `bark()` — a cat on the same clipboard cannot. So the compiler only allows methods declared on `Animal`. Bruno is still a dog in memory; you just promised to treat him as an animal in this variable.

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

**Scenario — remote vs the device it points at**

`Animal a1` is a universal remote labelled “Animal.” You aim it at a dog or a cat. The button is always `speak()`. The sound comes from the **device**, not from the label on the remote. The compiler only checks that animals have a `speak` button; the JVM, at run time, presses the dog’s or cat’s version.

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

**Scenario — morning attendance in one loop**

A teacher does not write `if student then … else if faculty then …` for 60 people. They keep an `Animal[]` (or `Person[]`) and call `speak()` once per slot. Add a `Bird` later: put it in the array; the loop stays the same. That is why the calculator stores `Operation[]` instead of four separate `if` branches for `+ - * /` (see [§8](#8-mini-project-oop-calculator)).

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

**Scenario — “shape” on a worksheet, not a real drawing**

You can say “every shape has an area” (`abstract double area()`), but you cannot draw a generic Shape with no sides. `new Operation()` would be a calculator button with no meaning. `Addition` is a real button. Shared details (`symbol`, `getSymbol()`) stay on the abstract parent; the missing piece (`calculate`) is filled by each child.

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

**Scenario — college ID vs job description**

A **class** (`Student`) is *what you are*. An **interface** (`Drawable`, `Borrowable`) is *what you can do*. A book and a laptop can both `implements Borrowable` even if they do not share a parent class. The library only cares that the item can be borrowed, not whether it is paper or plastic.

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

**Scenario — four buttons, one engine**

A cheap program would write:

```text
if (op is +) add
else if (op is -) subtract
...
```

Every new operator (`%`, `^`) means editing that `if` chain and risking mistakes. An OOP calculator treats each operator as a **small object** that knows its symbol and how to compute. The engine holds a tray of operations and asks: “Who owns this symbol?” Then it calls `calculate` — the same call for every button, different math inside.

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

**Scenario — remote labelled Operation, devices labelled + − × ÷**

`Calculator` only talks to `Operation`. It never writes `if (op instanceof Addition)`. That is the point of the diagram: add `Modulo` later by creating a class and putting it in the array — the loop does not change.

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

**Scenario — blank exam template**

`Operation` is the question paper header: “symbol + a method named calculate.” You cannot submit the blank template (`new Operation()`). `Addition` fills in the answers. `protected symbol` is like a field students in the same family of classes can set in their constructor.

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

**Scenario — divide by zero is this button’s job**

Only division cares about `b == 0`. Encapsulation + inheritance together: that rule lives in `Division`, not in `Main` or a giant `switch`. The user still types `/`; the `Division` object refuses the illegal case.

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

**Scenario — receptionist, not four separate offices**

`main` does not call `new Addition().calculate(...)`. It talks to one receptionist (`Calculator.compute`). The receptionist walks the tray of operation objects (the `private` array — outsiders cannot swap the tray). When the symbol matches, `op.calculate` is one line; JVM dispatches to `Addition` or `Division`.

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

**Scenario — front desk vs accounts**

`Main` is the front desk: read two numbers and a symbol, print the result or an error. It does not know how division works. If input is invalid (`/` with `0`, or operator `%` before you add Modulo), `compute` throws; `try/catch` turns that into a message instead of a crash.

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

First run: `Division.calculate(20, 4)` returns `5.0`.  
Second run: same class, same method, different data — the guard inside `Division` fires.

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

Walk one keystroke: user types `*`. The loop finds the `Multiplication` object (`getSymbol()` is `"*"`). The variable type is still `Operation`. `calculate(3, 4)` runs `Multiplication`’s body and returns `12`. Same walk for `/`, except the body includes the zero check.

### 8.11 Stretch exercises

1. **Modulo** — Add `Modulo extends Operation` with symbol `%`.
2. **History** — Add a `List<String>` in `Calculator` to store each expression and result.
3. **Interface** — Extract `calculate` into an interface `Calculable` and have `Operation` implement it.
4. **Scientific** — Add `Power extends Operation` using `Math.pow(a, b)`.

**Task (stretch):** Add `Modulo` and register it in `Calculator`'s constructor array.

**Scenario for stretch 1:** `%` is a new button on the same tray. You do not rewrite `compute`. You write one class and add `new Modulo()` to the array — that is the payoff of polymorphism.

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

| Idea | Everyday picture |
|------|------------------|
| Class | Admission form (blanks + rules) |
| Object | One filled form (Riya vs Aman) |
| Reference | Two keys to the same locker |
| Constructor | Clerk filling the file when it is opened |
| Encapsulation | ATM, not an open cash drawer |
| Inheritance | Student **is a** Person |
| Polymorphism | One `speak()` call; dog woofs, cat meows |

---

**Next steps:** interfaces in depth, `abstract` vs `interface`, packages, and design patterns (Factory, Strategy).
