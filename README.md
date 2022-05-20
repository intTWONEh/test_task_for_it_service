# Решение тестового задания

## Задание:
Есть две задачи (во вложении), их нужно реализовать отдельными классами и написать пользовательский интерфейс(GUI) к ним.

GUI:
· Поле ввода условий,
· Поле вывода результата,
· Combobox с выбором задачи,
· Кнопки: посчитать, сохранить, экспортировать, загрузить, импортировать.
 
При нажатии на «сохранить»: сохраняет в БД данные из поля ввода и тип задачи.
При нажатии на «экспортировать»: сохраняет в текстовый файл данные из поля ввода и тип задачи.
При нажатии на «загрузить»: вызывает диалоговое окно с выбором данных из БД (например, по дате, по типу задачи, но на ваше усмотрение) и при выборе загружает данные и считает.
При нажатии на «импортировать»: вызывает диалоговое окно с выбором файла и при выборе, загружает данные и считает.
 
В качестве СУБД, предлагаю использовать любую встраиваемую, например, HSQLDB. Также, ожидается, что будут добавлены скрипты для инициализации.

В качестве фреймворка, предлагаю использовать: Cuba Platform (jmix), Vaadin, Spring (React, ThymeLeaf, Freemarker).
В ответном письме ожидается ссылка на github и ссылка на собранный проект (war, jar) в любом облаке.

Перед отправкой, советую протестировать на стороннем сервере приложений и/или залить на https://www.heroku.com/

Если не знакомы ни с каким, из предложенных мной фреймворков, то предлагаю использовать Cuba Platform (https://www.cuba-platform.ru/)(https://www.cuba-platform.ru/learn/quickstart/studio/), но на ваше усмотрение.

(Файл с задачами task.txt)
