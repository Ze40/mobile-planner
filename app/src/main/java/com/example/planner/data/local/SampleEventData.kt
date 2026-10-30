package com.example.planner.data.local

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters

private val eventTimes = listOf(
    "08:30" to "09:00",
    "10:00" to "11:30",
    "13:00" to "14:00",
    "15:30" to "16:30",
    "18:00" to "19:00"
)

private val eventTemplates = mapOf(
    DayOfWeek.MONDAY to listOf(
        "Планирование недели" to "Проверить задачи и расставить приоритеты",
        "Занятие по алгоритмам" to "Лекция и разбор практических примеров",
        "Обед с командой" to "Перерыв и обсуждение планов",
        "Разработка планировщика" to "Работа над экраном событий",
        "Тренировка" to "Вечерняя тренировка"
    ),
    DayOfWeek.TUESDAY to listOf(
        "Утренняя зарядка" to "Небольшая разминка перед началом дня",
        "Лекция по базам данных" to "Занятие по SQLite и Room",
        "Проверка заданий" to "Разобрать заметки после лекции",
        "Встреча команды" to "Обсудить текущие задачи проекта",
        "Вечерняя прогулка" to "Прогулка на свежем воздухе"
    ),
    DayOfWeek.WEDNESDAY to listOf(
        "План на день" to "Записать главные задачи на сегодня",
        "Практика Kotlin" to "Поработать с коллекциями и функциями",
        "Обеденный перерыв" to "Отдохнуть и пообедать",
        "Работа над событиями" to "Продолжить разработку приложения",
        "Бассейн" to "Вечернее посещение бассейна"
    ),
    DayOfWeek.THURSDAY to listOf(
        "Подготовка к занятию" to "Повторить материал и подготовить вопросы",
        "Лекция по Compose" to "Изучить декларативный интерфейс",
        "Обед" to "Обеденный перерыв",
        "Лабораторная работа" to "Выполнить практическое задание",
        "Звонок семье" to "Позвонить близким вечером"
    ),
    DayOfWeek.FRIDAY to listOf(
        "Планирование задач" to "Подвести итоги недели",
        "Практика по SQL" to "Повторить запросы и сортировку",
        "Обед с одногруппниками" to "Перерыв после занятий",
        "Доработка проекта" to "Исправить замечания и проверить приложение",
        "Встреча с друзьями" to "Вечерняя встреча"
    ),
    DayOfWeek.SATURDAY to listOf(
        "Покупки" to "Купить продукты и необходимые вещи",
        "Уборка квартиры" to "Навести порядок дома",
        "Обед" to "Приготовить и съесть обед",
        "Личное время" to "Заняться любимым делом",
        "Кино" to "Посмотреть фильм вечером"
    ),
    DayOfWeek.SUNDAY to listOf(
        "Завтрак" to "Спокойный завтрак дома",
        "Подготовка к неделе" to "Собрать вещи и проверить расписание",
        "Прогулка" to "Прогуляться в парке",
        "Чтение" to "Выделить время для книги",
        "Отдых" to "Спокойно завершить выходные"
    )
)

internal fun createSampleEventsForCurrentWeek(today: LocalDate = LocalDate.now()): List<EventEntity> {
    val monday = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))

    return (0L..6L).flatMap { dayOffset ->
        val date = monday.plusDays(dayOffset)
        val templates = eventTemplates.getValue(date.dayOfWeek)

        templates.mapIndexed { index, (title, description) ->
            val (startTime, endTime) = eventTimes[index]
            EventEntity(
                title = title,
                date = date.toString(),
                startTime = startTime,
                endTime = endTime,
                description = description,
                categoryId = when (index) {
                    1 -> DefaultCategoryIds.STUDY
                    3 -> DefaultCategoryIds.WORK
                    0 -> DefaultCategoryIds.IMPORTANT
                    else -> DefaultCategoryIds.PERSONAL
                }
            )
        }
    }
}

