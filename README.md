## Meteorological Data 

#### Содержание:
<a name="zeroDot"></a>

* [Начало работы](#start)
* [Работа модуля](#work)
* [Настройка подключения](#set)
* [Настройка станции](#station)
* [Добавление widget на главный экран](#widget)
* [Координаты модуля](#coord)

#### Начало работы
<a name="start"></a>

Данный модуль служит для интеграции данных, получаемных от метеостацнии Vaisala WXT520.

<small>[в начало](#zeroDot)</small>

#### Работа модуля
<a name="work"></a> 

При запуске приложения, создается назначенная задача с параметрами выполнения один раз в минуту(на старте задача
не активна). Для изменения интервала
обновления данных, необходимо изменить его на экране `Настройки устройства`. На этом же экране меняется [COM порт](#set).
По умолчанию выбран `COM3`, из логики что первый и второй занимает мышка и клавиатура. В виджете актуальными данными,
для отображения, считаются данные последнего обновления по назначенной задаче.

**Важно:**

Для astra linux, COM port указывает в формате `/dev/ttyUSB0`

<small>[в начало](#zeroDot)</small>

#### Выбор COM порта
<a name="set"></a>

На экране `Настройки устройства`, необходимо выбрать нужный COM порт, к которому подключена станция.

<small>[в начало](#zeroDot)</small>

#### Настройка станции
<a name="station"></a>

---

*** **ВАЖНО** ***

Перед настройкой станции необходимо деактивировать назначенную задачу.

---

На вкладке `Настройки устройства`, доступны настройки датчиков станции:

* Датчик ветра
* Датчик осадков
* Датчик температуры и давления
* Датчик супервизор
    
Для каждого из такого датчика есть свой столбец с различными настройками.

<small>[в начало](#zeroDot)</small>

#### Добавление widget на главный экран
<a name="widget"></a>

Что бы добавить виджет на главный экран, необходимо в xml добавить фрагмент с виджетом:

 `<fragment screen="meteorologicaldata_Statisticmeteodata"/>`
  
<small>[в начало](#zeroDot)</small>
 
 ------------------------
 
 
 #### Координаты модуля
 <a name="coord"></a>
 
 Координаты модуля: `ru.getmanenko.meteorologicaldata:meteostation-global:4.4-SNAPSHOT`
 
 <small>[в начало](#zeroDot)</small>
