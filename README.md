## ![hw](https://cloud.githubusercontent.com/assets/13649199/13672719/09593080-e6e7-11e5-81d1-5cb629c438ca.png) Задание HW4

- 1: Сделать из `Meal` Hibernate entity
- 2: Имплементировать и протестировать `JpaMealRepositoryImpl`
  -  IDEA не понимает в `@NamedQuery` `..  m.dateTime BETWEEN ..`. На функциональность это не влияет.
  -  работа с LocalDate/Time уже включена `hibernate-core` 5.2.x:  
     
#### Optional

- 3: Добавить в тесты `MealServiceTest` функциональность `@Rule`:
  - 3.1: проверку Exception
  - 3.2: вывод в лог времени выполнения каждого теста 
  - 3.3: вывод сводки в конце класса: имя теста - время выполнения 
