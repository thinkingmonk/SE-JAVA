# Java Fundamentals

Reference course: [Java Tutorial for Beginners | Learn Java in 2 Hours](https://www.youtube.com/watch?v=UmnCZ7-9yDY) (Apna College).  
Local setup: see [setup.md](./setup.md) (JDK + IntelliJ IDEA).

---

## Table of contents

1. [Program structure](#1-program-structure)
2. [Comments](#2-comments)
3. [Variables](#3-variables)
4. [Data types](#4-data-types)
5. [Strings](#5-strings)
6. [Arrays](#6-arrays)
7. [Type casting](#7-type-casting)
8. [Constants (`final`)](#8-constants-final)
9. [Operators](#9-operators)
10. [`Math` class](#10-math-class)
11. [User input (`Scanner`)](#11-user-input-scanner)
12. [Comparison operators](#12-comparison-operators)
13. [Conditional statements (`if` / `else`)](#13-conditional-statements-if--else)
14. [Logical operators](#14-logical-operators)
15. [`switch`](#15-switch)
16. [Loops](#16-loops)
17. [`break` and `continue`](#17-break-and-continue)
18. [Exception handling (`try` / `catch`)](#18-exception-handling-try--catch)
19. [Methods (functions)](#19-methods-functions)

---

## 1. Program structure

Every Java program lives inside a **class**. The JVM starts execution from **`main`**.

```java
package org.fullstack;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

| Part | Meaning |
|------|--------|
| `package org.fullstack;` | Folder/package where this file belongs |
| `public class Main` | Class name (usually matches filename `Main.java`) |
| `public static void main(String[] args)` | Entry point; JVM calls this first |
| `System.out.println(...)` | Print text and move to the next line |
| `;` | End of a statement (like a full stop in English) |

**`println` vs `print`**

```java
System.out.print("Hello");
System.out.println("Java");   // HelloJava, then newline
System.out.print("Again");    // on same line after Java
```

IntelliJ shortcut: type `sout` + Tab → `System.out.println`.

### Exercise

**Task:** Print `Hello` and `Java` on the **same line** (no space between them), then print `TCET` on the next line.

**Solution:**

```java
public static void main(String[] args) {
    System.out.print("Hello");
    System.out.println("Java");
    System.out.println("TCET");
}
```

Expected output:

```
HelloJava
TCET
```

---

## 2. Comments

Comments are ignored by the compiler. Use them for notes to humans.

```java
// Single-line comment

/*
   Multi-line
   comment
*/

/** Documentation comment (often used above classes/methods) */
```

### Exercise

**Task:** Below the `main` method’s opening brace, add a single-line comment describing what the program does, then print `42` (the comment must not affect the output).

**Solution:**

```java
public static void main(String[] args) {
    // Prints the answer to demonstrate comments
    System.out.println(42);
}
```

---

## 3. Variables

A **variable** is a named box in memory that holds a value.

```java
String name = "Aman";
String friend = "Shravan";
int age = 22;
float cgpa = 9.5f;
```

**Reassigning**

```java
int a = 10;
a = 20;           // a is now 20

int b = a;        // b gets a copy of a's value (10 or 20)
```

**Naming rules**

- Letters, digits, `_`, `$` (cannot start with a digit)
- Case-sensitive: `age` and `Age` are different
- Use camelCase for variables: `studentName`, `finalPrice`

### Exercise

**Task:** Create variables `studentName` (String), `rollNo` (int), and `cgpa` (float). Print one line: `Roll 101: Riya, CGPA 8.5` using your variables.

**Solution:**

```java
String studentName = "Riya";
int rollNo = 101;
float cgpa = 8.5f;

System.out.println("Roll " + rollNo + ": " + studentName + ", CGPA " + cgpa);
```

---

## 4. Data types

### 4.1 Primitive types

Fixed-size types stored directly in memory.

| Type | Size (typical) | Example | Range / notes |
|------|----------------|---------|----------------|
| `byte` | 1 byte | `byte b = 100;` | -128 to 127 |
| `short` | 2 bytes | `short s = 30000;` | -32,768 to 32,767 |
| `int` | 4 bytes | `int n = 1_000_000;` | ~±2.1 billion |
| `long` | 8 bytes | `long phone = 9876543210L;` | large integers; suffix `L` |
| `float` | 4 bytes | `float pi = 3.14f;` | suffix `f` |
| `double` | 8 bytes | `double price = 99.99;` | default for decimals |
| `char` | 2 bytes | `char grade = 'A';` | single character in quotes |
| `boolean` | 1 bit (JVM) | `boolean adult = true;` | `true` or `false` only |

```java
int marks = 95;
long bigNumber = 9876543210L;   // L required — int literal too large otherwise
float gpa = 3.14f;              // f required — otherwise treated as double
double salary = 50000.50;
char section = 'B';
boolean isStudent = true;
```

### 4.2 Reference (non-primitive) types

Objects live on the heap; the variable holds a **reference** (address).

```java
String name = "Aman";
String friend = new String("Shravan");  // explicit object (less common for strings)

System.out.println(name.length());      // 4 — methods on String
```

**Primitive vs reference (quick)**

| | Primitive | Reference (e.g. `String`) |
|--|-----------|---------------------------|
| Storage | Value directly | Reference to object |
| `null` | Not allowed | Allowed |
| Methods | No | Yes (`length()`, etc.) |
| `new` | Not used for primitives | Often used (`new String(...)`) |

Strings assigned with `"..."` without `new` are fine; Java manages them efficiently.

### 4.3 Default values (arrays & fields)

Uninitialized **array elements** and **instance fields** get defaults:

- `int`, `byte`, `short`, `long` → `0`
- `float`, `double` → `0.0`
- `boolean` → `false`
- Reference types → `null`

```java
int[] marks = new int[3];
System.out.println(marks[2]);   // 0 — not “empty”, default int
```

Local variables **must** be assigned before use.

### Exercise

**Task:** Declare `int age = 17`, `boolean hasId = true`, and `char grade = 'B'`. Print whether the student is an adult (`age >= 18`) and print `grade`.

**Solution:**

```java
int age = 17;
boolean hasId = true;   // unused here — ok for practice
char grade = 'B';

System.out.println(age >= 18);   // false
System.out.println(grade);       // B
```

---

## 5. Strings

`String` is immutable: once created, its content cannot change; methods return **new** strings.

```java
String first = "Aman";
String last = "Kumar";
String full = first + " " + last;   // concatenation
System.out.println(full);           // Aman Kumar
```

**Common methods**

```java
String name = "Aman";

name.length();              // 4
name.charAt(0);             // 'A' — index starts at 0
name.charAt(1);             // 'm'

String replaced = name.replace('m', 'n');  // "Aann" — original unchanged
System.out.println(name);                    // still "Aman"

name.substring(0, 3);       // "Ama" — from index 0 up to (not including) 3
name.substring(1);          // "man" — from index 1 to end
```

**Equality**

```java
String a = "Java";
String b = new String("Java");
System.out.println(a == b);         // false — different references
System.out.println(a.equals(b));    // true — same content
```

Always use `.equals()` for string content comparison.

### Exercise

**Task:** For `String course = "Java";`, print its length, the character at index 2, and the substring from index 1 to 3 (exclusive end).

**Solution:**

```java
String course = "Java";

System.out.println(course.length());      // 4
System.out.println(course.charAt(2));   // v
System.out.println(course.substring(1, 3)); // av
```

---

## 6. Arrays

An **array** is a fixed-size list of elements of the same type.

### 6.1 Declaration and access

```java
int[] marks = new int[3];
marks[0] = 95;   // Physics
marks[1] = 98;   // Chemistry
marks[2] = 100;  // English

System.out.println(marks[0]);   // 95
```

Printing the array variable prints something like `[I@...` — use an index or loop to print values.

### 6.2 Initialization in one line

```java
int[] marks = {95, 98, 100};
```

### 6.3 Length and sorting

```java
int[] marks = {95, 98, 100};
System.out.println(marks.length);   // 3 — property, no ()

import java.util.Arrays;

Arrays.sort(marks);                 // ascending: 95, 98, 100
```

### 6.4 2D arrays (matrix)

```java
int[][] finalMarks = {
    {97, 98, 95},   // student 1
    {85, 88, 90}    // student 2
};

System.out.println(finalMarks[0][1]);   // 98 — row 0, column 1
```

### Exercise

**Task:** Create `int[] nums = {40, 10, 30};`, sort it ascending with `Arrays.sort`, then print the smallest and largest values (first and last index).

**Solution:**

```java
import java.util.Arrays;

int[] nums = {40, 10, 30};
Arrays.sort(nums);

System.out.println(nums[0]);              // 10
System.out.println(nums[nums.length - 1]); // 30
```

---

## 7. Type casting

Converting one numeric type to another.

### 7.1 Implicit (widening)

Smaller type → larger type automatically (safe).

```java
int a = 8;
double b = a;        // 8.0 — int fits in double
```

### 7.2 Explicit (narrowing)

Larger type → smaller type — may lose data; use a cast.

```java
double price = 100.0;
int finalPrice = (int) price;   // 100 — decimals truncated

double withGst = 100.0 * 1.18;
int rounded = (int) withGst;    // 118 if result is 118.0
```

You cannot cast incompatible types (e.g. `String` to `int` without parsing).

### Exercise

**Task:** A `double` variable `amount` is `249.99`. Store its whole rupees in an `int` using a cast and print that integer.

**Solution:**

```java
double amount = 249.99;
int rupees = (int) amount;

System.out.println(rupees);   // 249
```

---

## 8. Constants (`final`)

A value that must not change after assignment.

```java
float PI = 3.14F;
PI = 1.1F;   // OK for normal variable

final float PI = 3.14F;
PI = 1.1F;   // compile error: cannot assign to final variable
```

Convention: `UPPER_SNAKE_CASE` for constants.

```java
final int MAX_STUDENTS = 60;
```

### Exercise

**Task:** Declare `final int MAX_MARKS = 100` and `int scored = 86`. Print the percentage as an integer: `(scored * 100) / MAX_MARKS`.

**Solution:**

```java
final int MAX_MARKS = 100;
int scored = 86;

int percentage = (scored * 100) / MAX_MARKS;
System.out.println(percentage);   // 86
```

---

## 9. Operators

### 9.1 Arithmetic

```java
int a = 10, b = 3;

System.out.println(a + b);   // 13
System.out.println(a - b);   // 7
System.out.println(a * b);   // 30
System.out.println(a / b);   // 3 — integer division truncates
System.out.println(a % b);   // 1 — remainder (modulo)

double x = 10, y = 3;
System.out.println(x / y);   // 3.333...
```

### 9.2 Assignment

```java
int n = 10;
n = 20;      // =
n += 5;      // n = n + 5  → 25
n -= 2;
n *= 2;
n /= 4;
```

### 9.3 Increment / decrement

```java
int number = 1;

number++;           // postfix: use then increment → prints 1, then number is 2
++number;           // prefix: increment then use

int a = 1;
System.out.println(a++);   // 1
System.out.println(a);     // 2

int b = 1;
System.out.println(++b);   // 2
System.out.println(b);     // 2
```

### Exercise

**Task:** Given `int a = 17` and `int b = 5`, print the sum, integer quotient (`a / b`), and remainder (`a % b`) on three lines.

**Solution:**

```java
int a = 17, b = 5;

System.out.println(a + b);   // 22
System.out.println(a / b);   // 3
System.out.println(a % b);   // 2
```

---

## 10. `Math` class

Static helpers in `java.lang.Math` (no import needed).

```java
System.out.println(Math.max(5, 6));    // 6
System.out.println(Math.min(5, 6));    // 5
System.out.println(Math.sqrt(64));     // 8.0
System.out.println(Math.pow(2, 3));    // 8.0

// Random in [0.0, 1.0)
double r = Math.random();

// Random int from 1 to 100 (common pattern)
int dice = (int) (Math.random() * 100) + 1;
```

### Exercise

**Task:** Without input, use `Math` to print the larger of `12` and `19`, and the square root of `81`.

**Solution:**

```java
System.out.println(Math.max(12, 19));   // 19
System.out.println(Math.sqrt(81));      // 9.0
```

---

## 11. User input (`Scanner`)

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter age: ");
        int age = sc.nextInt();

        System.out.print("Enter price: ");
        double price = sc.nextDouble();

        System.out.print("Enter name (one word): ");
        String word = sc.next();

        sc.nextLine();   // consume leftover newline after next()
        System.out.print("Enter full line: ");
        String line = sc.nextLine();

        System.out.println("Age: " + age);

        sc.close();
    }
}
```

| Method | Reads |
|--------|--------|
| `nextInt()` | `int` |
| `nextDouble()` | `double` |
| `nextFloat()` | `float` |
| `next()` | one word (stops at whitespace) |
| `nextLine()` | entire line including spaces |

### Exercise

**Task:** Read two integers from the user and print their product. (Assume valid integers.)

**Solution:**

```java
import java.util.Scanner;

Scanner sc = new Scanner(System.in);

System.out.print("First number: ");
int a = sc.nextInt();

System.out.print("Second number: ");
int b = sc.nextInt();

System.out.println("Product: " + (a * b));

sc.close();
```

---

## 12. Comparison operators

Return `boolean`.

| Operator | Meaning |
|----------|---------|
| `==` | equal |
| `!=` | not equal |
| `>` | greater |
| `<` | less |
| `>=` | greater or equal |
| `<=` | less or equal |

```java
int a = 10, b = 20;
System.out.println(a == b);   // false
System.out.println(a != b);   // true
System.out.println(a < b);    // true
```

Use `.equals()` for `String` content, not `==`.

### Exercise

**Task:** Given `int x = 15` and `int y = 15`, print three lines: whether `x == y`, whether `x != y`, and whether `x >= 10`.

**Solution:**

```java
int x = 15, y = 15;

System.out.println(x == y);   // true
System.out.println(x != y);   // false
System.out.println(x >= 10);  // true
```

---

## 13. Conditional statements (`if` / `else`)

```java
boolean isAdult = true;

if (isAdult) {
    System.out.println("adult");
} else {
    System.out.println("not adult");
}

int age = 20;
if (age >= 18) {
    System.out.println("can vote");
} else {
    System.out.println("cannot vote");
}
```

**`else if` ladder**

```java
int cash = 45;
int pen = 10, notebook = 40;

if (cash < pen) {
    System.out.println("cannot buy anything");
} else if (cash < pen + notebook) {
    System.out.println("can get one item");
} else {
    System.out.println("can get both");
}
```

Braces `{ }` are required when a block has **more than one** statement.

### Exercise

**Task:** Given `int marks = 72`, print `Pass` if marks are 40 or above, otherwise print `Fail`.

**Solution:**

```java
int marks = 72;

if (marks >= 40) {
    System.out.println("Pass");
} else {
    System.out.println("Fail");
}
```

---

## 14. Logical operators

Combine boolean conditions.

| Operator | Meaning | True when |
|----------|---------|-----------|
| `&&` | AND | both true |
| `\|\|` | OR | at least one true |
| `!` | NOT | flips true/false |

```java
int a = 30, b = 40;

if (a < 50 && b < 50) {
    System.out.println("both less than 50");
}

if (a < 50 || b < 50) {
    System.out.println("at least one less than 50");   // prints — b is 40
}

boolean isAdult = false;
if (!isAdult) {
    System.out.println("not adult");
}
```

In `if (condition)`, Java treats the condition as “is this true?” — writing `if (x == true)` is redundant; `if (x)` is enough.

### Exercise

**Task:** Given `int temp = 35`, print `Hot` if temp &gt; 30, else `Warm` if temp is between 20 and 30 inclusive, else `Cool`. Use `&&` where needed.

**Solution:**

```java
int temp = 35;

if (temp > 30) {
    System.out.println("Hot");
} else if (temp >= 20 && temp <= 30) {
    System.out.println("Warm");
} else {
    System.out.println("Cool");
}
```

---

## 15. `switch`

Match one value against many cases (often `int`, `char`, `String`).

```java
int day = 2;

switch (day) {
    case 1:
        System.out.println("Monday");
        break;
    case 2:
        System.out.println("Tuesday");
        break;
    default:
        System.out.println("Other day");
}
```

Without `break`, execution **falls through** to the next cases. Use `break` when you want only one case to run.

### Exercise

**Task:** `int day = 6;` — use `switch` to print `Weekday` for days 1–5 and `Weekend` for days 6–7. Use `default` for any other value.

**Solution:**

```java
int day = 6;

switch (day) {
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
        System.out.println("Weekday");
        break;
    case 6:
    case 7:
        System.out.println("Weekend");
        break;
    default:
        System.out.println("Invalid day");
}
```

---

## 16. Loops

Repeat code while a condition holds.

### 16.1 `for` loop

```java
// Print 7 to 12
for (int i = 7; i <= 12; i++) {
    System.out.println(i);
}

// Countdown 100 to 1
for (int i = 100; i >= 1; i--) {
    System.out.println(i);
}
```

Structure: `for (init; condition; update) { body }`

Variables declared in `for (...)` exist **only inside the loop**.

### 16.2 `while` loop

Condition checked **before** each iteration.

```java
int i = 100;
while (i >= 1) {
    System.out.println(i);
    i--;
}
```

### 16.3 `do-while` loop

Body runs **at least once**; condition checked after.

```java
int i = 1;
do {
    System.out.println(i);
    i++;
} while (i <= 5);
```

### 16.4 Example: read numbers until negative

```java
Scanner sc = new Scanner(System.in);
int number;

System.out.print("Enter number: ");
number = sc.nextInt();

while (number >= 0) {
    System.out.println("You entered: " + number);
    System.out.print("Enter number: ");
    number = sc.nextInt();
}
System.out.println("Done");
```

Declare `number` **outside** the loop if you use it in the `while` condition after reading inside the loop.

### Exercise

**Task:** Use a `for` loop to print the sum of integers from 1 to 10 (expected sum: 55).

**Solution:**

```java
int sum = 0;
for (int i = 1; i <= 10; i++) {
    sum += i;
}
System.out.println(sum);   // 55
```

---

## 17. `break` and `continue`

**`break`** — exit the innermost loop (or `switch`) immediately.

```java
int i = 0;
while (true) {
    System.out.println(i);
    i++;
    if (i > 5) {
        break;   // leave the while loop
    }
}
```

**`continue`** — skip the rest of the current iteration and go to the next.

```java
for (int i = 0; i <= 5; i++) {
    if (i == 3) {
        continue;   // skip printing 3
    }
    System.out.println(i);   // 0, 1, 2, 4, 5
}
```

### Exercise

**Task:** Print all integers from 1 to 10 except 7 (use `continue`).

**Solution:**

```java
for (int i = 1; i <= 10; i++) {
    if (i == 7) {
        continue;
    }
    System.out.println(i);
}
```

---

## 18. Exception handling (`try` / `catch`)

When something goes wrong at runtime (e.g. invalid array index), Java throws an **exception**. Uncaught exceptions stop the program.

```java
int[] marks = {95, 98, 100};

// marks[5] = 50;   // ArrayIndexOutOfBoundsException — crashes if uncaught

try {
  System.out.println(marks[5]);
} catch (Exception e) {
  // handle error — program can continue
}

System.out.println("Program continues...");
```

Put only code that **might** fail inside `try`. Handle or log in `catch`. More specific catches (e.g. `ArrayIndexOutOfBoundsException`) are possible as you learn OOP.

**Why use it?** One bad operation (e.g. database read) should not always shut down the entire application.

### Exercise

**Task:** For `int[] data = {10, 20, 30};`, try to print `data[5]` inside `try`. In `catch`, print `Invalid index` and then print `Done` after the `try-catch` block.

**Solution:**

```java
int[] data = {10, 20, 30};

try {
    System.out.println(data[5]);
} catch (Exception e) {
    System.out.println("Invalid index");
}

System.out.println("Done");
```

---

## 19. Methods (functions)

A **method** is a reusable block that can take input (**parameters**) and optionally return a value.

### 19.1 Syntax

```java
accessModifier static? returnType methodName(parameters) {
    // body
}
```

For now, use `public static` methods inside `Main`.

### 19.2 No return value (`void`)

```java
public static void printHello() {
    System.out.println("Hello Java");
}

public static void main(String[] args) {
    printHello();
    printHello();
}
```

### 19.3 Parameters

```java
public static void printName(String name) {
    System.out.println(name);
}

public static void main(String[] args) {
    printName("Aman");
    printName("Shravan");
}
```

### 19.4 Multiple parameters

```java
public static void printSum(int a, int b) {
    int sum = a + b;
    System.out.println(sum);
}

public static void main(String[] args) {
    printSum(1, 6);   // 7
}
```

### 19.5 Returning a value

```java
public static int add(int a, int b) {
    return a + b;
}

public static void main(String[] args) {
    int result = add(3, 5);
    System.out.println(result);   // 8
}
```

Change `void` to the return type (`int`, `double`, `String`, etc.) and use `return` to send a value back.

### 19.6 `main` method

```java
public static void main(String[] args) { ... }
```

- `args` — command-line arguments as an array of strings
- JVM calls `main` when you run the class

### Exercise

**Task:** Write `public static boolean isEven(int n)` that returns `true` if `n` is even. In `main`, print the result for `4` and `7`.

**Solution:**

```java
public static boolean isEven(int n) {
    return n % 2 == 0;
}

public static void main(String[] args) {
    System.out.println(isEven(4));   // true
    System.out.println(isEven(7));   // false
}
```
