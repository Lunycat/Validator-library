# **Java Validator Library**
[![Actions Status](https://github.com/Lunycat/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/Lunycat/java-project-78/actions)
[![Maintainability](https://api.codeclimate.com/v1/badges/b424b5d9541be7f43429/maintainability)](https://codeclimate.com/github/Lunycat/java-project-78/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/b424b5d9541be7f43429/test_coverage)](https://codeclimate.com/github/Lunycat/java-project-78/test_coverage)
[![Java CI](https://github.com/Lunycat/java-project-78/actions/workflows/main.yaml/badge.svg)](https://github.com/Lunycat/java-project-78/actions/workflows/main.yaml)

## **Описание**
**Валидатор данных**– библиотека, с помощью которой можно проверять корректность данных типа **String, Integer, Map**. Библиотека создавалась на основе структурного шаблона **Fluent Interface**, что обеспечивает возможность последовательного вызова методов объекта без необходимости повторного обращения к нему.

### **Как пользоваться:**
Просто импортируйте библиотеку Validator. В зависимости от типа проверяемых данных можно воспользоваться следующими фабричными методами:
+ **.number()** - создаётся **NumberSchema** для валидации числовых значений
+ **.string()** - создаётся **StringSchema** для валидации строчных значений
+ **.map()** - создаётся **MapSchema** для валидации пар

### Каждая схема имеет следующие возможности:
```java
Validator validator = new Validator(); // Создаём Validator

NumberSchema numberSchema = validator.number(); // Создаём NumberSchema
StringSchema stringSchema = validator.string(); // Создаём StringSchema
MapSchema mapSchema = validator.map(); // Создаём MapSchema

// Для схем устанавливаем следующие правила
/*
* required() - значение не может быть null
* positive() - число должно быть положительным, начиная от 1
* range(1, 5) - число должно быть от 1 до 5 (включительно)
*/
numberSchema.required().positive().range(1, 5);
/*
* required() - строчка не может быть пустой или null
* minLength(5) - строчка должна иметь размер не меньше 5
* contains("llo!") - строчка должна содержать "llo!"
*/
stringSchema.required().minLength(5).contains("llo!");
/*
* required() - значение не может быть null
* sizeof(1) - Map'а должна содержать в себе 1 пару
* shape() - создаём конкретное правило для пары, в данном примере имя должно быть длинной не меньше 3 букв
*/
mapSchema.required().sizeof(1).shape(Map.of("firstName", validator.string().required().minLength(3)));

numberSchema.isValid(10); //false
numberSchema.isValid(5); // true

stringSchema.isValid("Dog"); //false
stringSchema.isValid("Hello!"); // true

mapSchema.isValid(Map.of("k1", "v1", "k2", "v2")); //false
mapSchema.isValid(Map.of("firstName", "Vladislav")); // true
```