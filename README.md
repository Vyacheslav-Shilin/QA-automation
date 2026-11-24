# QA Automation

Учебный проект автоматизации тестирования на Java.

## Инструменты

- Java 17
- Maven
- TestNG
- REST Assured
- Jackson
- Allure Report 3

## Структура проекта

- clients — методы отправки HTTP-запросов
- models — классы данных запросов и ответов
- config — загрузка настроек
- tests — тесты и общая настройка API

Адреса API указаны в src/test/resources/config.properties.

## Запуск тестов

В IntelliJ IDEA нажмите правой кнопкой на пакет
src/test/java/com/example/qa/tests и выберите Run 'Tests in…'.

API-тестам требуется подключение к интернету.

## Просмотр Allure-отчёта

После запуска тестов выполните в терминале из корня проекта:

```powershell
allure.cmd generate target/allure-results -o target/allure-report --clean
allure.cmd open target/allure-report
```

Для этих команд требуется установленный Allure Report 3.

Чтобы сформировать отчёт только по новому запуску,
удалите target/allure-results перед запуском тестов.

Для остановки сервера отчёта нажмите Ctrl+C.