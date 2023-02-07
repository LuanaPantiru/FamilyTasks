# FamilyTasks

## Diagrama Generala
![diagrama_generala-user_diagram](https://github.com/LuanaPantiru/FamilyTasks/blob/master/diagrame/diagrama_generala-user_diagram.jpg)

## Reprezentarea sub forma de diagrama a realizarii unui cont si autentificarea unui user
Diagramele sunt realizate de Pantiru Luana
### Diagrama structurala
![Pantiru_Luana-class_diagram](https://github.com/LuanaPantiru/FamilyTasks/blob/master/diagrame/Pantiru%20Luana-class_diagram.jpg)
### Diagrama comportamentala
![Pantiru_Luana-activity_diagram](https://github.com/LuanaPantiru/FamilyTasks/blob/master/diagrame/Pantiru%20Luana-activity_diagram.png)

## Reprezentarea sub forma de diagrama a relatiei dintre user, membru si family group
Diagramele sunt realizate de Zambitchi Alexandra
### Diagrama structurala
![Zambitchi_Alexandra-class_diagram](https://github.com/LuanaPantiru/FamilyTasks/blob/master/diagrame/Zambitchi%20Alexandra-class_diagram.jpg)
### Diagrama comportamentala
![Zambitchi_Alexandra-activity_diagram](https://github.com/LuanaPantiru/FamilyTasks/blob/master/diagrame/Zambitchi%20Alexandra-activity_diagram.png)

## Reprezentarea sub forma de diagrama a relatiei dintre membru si task
Diagramele sunt realizate de Cinca Adrian
### Diagrama structurala
![Cinca Adrian-class_diagram](https://github.com/LuanaPantiru/FamilyTasks/blob/master/diagrame/Cinca%20Adrian-class_diagram.jpg)
### Diagrama comportamentala
![Cinca Adrian-user_diagram](https://github.com/LuanaPantiru/FamilyTasks/blob/master/diagrame/Cianca%20Adrian-user_diagram.jpeg)

## Designed pattern
1. Singleton - pentru a se realiza conectarea la baza de date; clasa este ApplicationController si este folosita in clasele de tip repository
2. Template Method - este reprezentata de clasa abstracta Member, care este extinsa de clasele AdminMember si NormalMember
3. Bridge - este realizata intre clasa Task si interfata Status; interfata Status este implementata de catre clasele ToDoStatus, InProgressStatus si FinishedStatus
