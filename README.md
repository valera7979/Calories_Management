## ![hw](https://cloud.githubusercontent.com/assets/13649199/13672719/09593080-e6e7-11e5-81d1-5cb629c438ca.png) Задание HW0
```
Реализовать метод UserMealsUtil.getFilteredWithExceeded:
-  должны возвращаться только записи между startTime и endTime 
-  поле UserMealWithExceed.exceed должно показывать, 
                                     превышает ли сумма калорий за весь день параметра метода caloriesPerDay  
        
Т.е UserMealWithExceed - это запись одной еды, но поле exceeded будет одинаково для всех записей за этот день.
    
- Проверте результат выполнения ДЗ (можно проверить логику в http://topjava.herokuapp.com , список еды)
- Оцените Time complexity вашего алгоритма, если он O(N*N)- попробуйте сделать O(N).
```

#### Optional (Java 8 Stream API)
```
Сделать реализация через Java 8 Stream API. Заменяйте forEach оператором stream.map(..)
```
