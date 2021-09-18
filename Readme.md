# Home-Core

Система умного дома. Структура проекта включает в себя:

## Структура проекта
Проект состоит из POM модулей следующих типов:
- Api
- Feature
- Core

### Api
Api - обычные Java приложения, выставляющие API для взаимодействия
с каким-либо инструментом, например Яндекс.Станция

### Feature
Feature - фича. Представляет собой Spring Starter, который может быть подключен
через `pom.xml` в основной проект. Фича реализует одну функциональную задачу,
полезную для пользователя на основе Api. Ни одна фича не должна зависеть от другой,
они должны быть независимы

### Core
Core - ядро. Spring'овое приложение включающее в себя набор фич. Каждая фича подключается отдельно через `pom.xml`,
таким образом контролируется набор необходимых фич пользователю. 