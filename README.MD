Проект "Сервис регистрации домашних животных"
Для запуска проекта:
1) запустить gradle build для создание jar-файлов модулей(сервисов)
2) запустить docker-compose.yml

Модули:
1) name-generator - сервис для генерации кличек исходя из таких параметров, как вид и пол животного
2) user-service - сервис для сбора статистики действий, совершаемый пользователем
3) pet-registration-service - сервис регистрирует в базе данных домашних животных

Дле всех эндпоинтов генерируется swagger ui "http:host:port/swagger-ui/index.html#"