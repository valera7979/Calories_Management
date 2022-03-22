[![Codacy Badge](https://api.codacy.com/project/badge/Grade/7e11826fd02f43f1b6e92450370ef89d)](https://www.codacy.com/app/valera7979/Calories_Management?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=valera7979/Calories_Management&amp;utm_campaign=Badge_Grade)
[![Dependency Status](https://dependencyci.com/github/valera7979/Calories_Management/badge)](https://dependencyci.com/github/valera7979/Calories_Management)
[![Build Status](https://travis-ci.org/valera7979/Calories_Management.svg?branch=master)](https://travis-ci.org/valera7979/Calories_Management)

# <a href="http://mealmng.herokuapp.com/login">Calories_Management*</a>

Java Enterprise project with registration / authorization and role based interface (USER, ADMIN). The administrator can create / edit / delete / users, and the user can manage his profile and data (day, food, calories) through the UI (via AJAX) and through the REST interface with established authorization. It is possible to filter data by date and time, while the color of the food table entry depends on whether the amount of calories per day is large (an editable parameter in the user profile). All REST is covered by JUnit tests using Spring MVC Test and Spring Security Test.
https://mealmng.herokuapp.com/login - to start application wait 1 min while it starts and rerun if required (due to free plan on herokuapp.com inactive apps are in sleep condition)

Java Enterprise проект с регистрацией/авторизацией и интерфейсом на основе ролей (USER, ADMIN). Администратор может создавать/редактировать/удалять/пользователей, а пользователь - управлять своим профилем и данными (день, еда, калории) через UI (по AJAX) и по REST интерфейсу с базовой авторизацией. Возможна фильтрация данных по датам и времени, при этом цвет записи таблицы еды зависит от того, превышает ли сумма калорий за день норму (редактируемый параметр в профиле пользователя). Весь REST интерфейс покрывается JUnit тестами, используя Spring MVC Test и Spring Security Test.

used tools/ libraries/ frameworks:

используемые инструменты/ библиотеки/ фреймворки:
                <a href="http://maven.apache.org/">Maven</a>,
                <a href="http://projects.spring.io/spring-security/">Spring Security</a>,
            <a href="http://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html">Spring MVC</a>,
                <a href="http://projects.spring.io/spring-data-jpa/">Spring Data JPA</a>,
                <a href="http://spring.io/blog/2014/05/07/preview-spring-security-test-method-security">Spring Security
                    Test</a>,
                <a href="http://hibernate.org/orm/">Hibernate ORM</a>,
                <a href="http://hibernate.org/validator/">Hibernate Validator</a>,
                <a href="http://www.slf4j.org/">SLF4J</a>,
                <a href="https://github.com/FasterXML/jackson">Json Jackson</a>,
                <a href="http://ru.wikipedia.org/wiki/JSP">JSP</a>,
                <a href="http://en.wikipedia.org/wiki/JavaServer_Pages_Standard_Tag_Library">JSTL</a>,
                <a href="http://tomcat.apache.org/">Apache Tomcat</a>,
                <a href="http://www.webjars.org/">WebJars</a>,
                <a href="http://ehcache.org">Ehcache</a>,
                <a href="http://www.postgresql.org/">PostgreSQL</a>,
                <a href="http://junit.org/">JUnit</a>,
                <a href="http://hamcrest.org/JavaHamcrest/">Hamcrest</a>,
                <a href="http://jquery.com/">jQuery</a>,
                <a href="https://datatables.net/">jQuery Datatable</a>,
                <a href="http://ned.im/noty/">jQuery notification</a>,
                <a href="http://getbootstrap.com/">Bootstrap</a>.
                
                (*-приложение стартует через 1 минуту - по условию бесплатного обслуживания на herokuapp.com неактивные приложения засыпают)

