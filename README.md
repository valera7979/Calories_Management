##Задание HW03

- 1 Понять, почему перестали работать SpringMain, InMemoryAdminRestControllerTest, InMemoryAdminRestControllerSpringTest
- 2 Дополнить скрипты создания и инициализации базы таблицой MEALS. Запустить скрипты на вашу базу (через Run). Порядок таблиц при DROP и DELETE важен, если они связаны fk. Проверьте, что ваши скрипты работают
- 3 Реализовать через Spring JDBC Template JdbcMealRepositoryImpl
- - 3.1. сделать каждый метод за один SQL запрос
- - 3.2. userId в класс Meal вставлять НЕ надо (для UI и REST это лишние данные, userId это id залогиненного пользователя)
- - 3.3. JbdcTemplate работает через сеттеры. Вместе с конструктором по умолчанию их нужно добавить в Meal
- - 3.4. Cписок еды должен быть отсортирован (тогда мы его сможем сравнивать с тестовыми данными). Кроме того это требуется для UI и API: последняя еда наверху.
- 4 Проверить работу MealServlet, запустив приложение

###Optional

- 5 Сделать MealServiceTest из MealService (Ctrl+Shift+T и выбрать JUnit4) и реализовать тесты.
- - 5.1 Сделать тестовые данные MealTestData, АНАЛОГИЧНЫЕ пропопулированным в populateDB.sql. Сравниваем данные через MealTestData.MATCHER
- - 5.2 Сделать тесты на чужую еду (delete, get, update) с тем чтобы получить NotFoundException.
* 6 Предложить решение, как почнинить SpringMain, InMemory*Test. InMemory*Test должны использовать реализацию в памяти
- 7 Сделать индексы к таблице Meals. Индекс на pk (id) postgres создает автоматически: Postgres and Indexes on Foreign Keys and Primary Keys
Postgres Guide: Indexes