# Operator
База данных
PostgreSql с использованием JpaRepository, ORM Hibernate
- Клиент
    + long id;
    + String firstName;
    + String lastName;
    + String number;
    + Tariff tariff;
- Тариф
    + long id;
    + Set<Client> clients;
    + int priceSMS;//Цена за смс
    + int period;//Через какой период цена изменится(в минутах)
    + int priceInc;//На сколько цена увеличитс
    + Date dateLastUpdate;//Дата последенего обновления 
    
Взаимодействие

- /client
    + /getAll
    + /get/{id}
    + /save? id = & fn = & last = & num = & tar =
    + /new? fn = & last = & num = & tar =
    + /delete/{id}
- /tariff
    + /getAll
    + /get/{id}
    + /new? price = & period = & inc =
    + /save? id = & price = & period = & inc =
    + /delete/{id} 
- /test
    
Все ответы в формате Json

Обновление\
Класс компонент UpdatePriceComponent с Scheduled функцией reportCurrentTime, которая запускается каждую минуту.\
Для каждого Тарифа вычисляется (Текущее время - Время последнего обновления)\
Если это время больше или равно периода то увеличиваем цену и для каждого ползователя этого тарифа отправляем сообщение через SMSNotificationService.
В него передаём клиента и тариф.

Перед запуском нужно сконфигурировать db.properties,hibernate.properties.

Главное hibernate.hbm2ddl.auto = create для создания таблицы.

При следующем запуске hibernate.hbm2ddl.auto = validate для проверки базы данных

Ввод вывод требует тестирования(в разработке)

