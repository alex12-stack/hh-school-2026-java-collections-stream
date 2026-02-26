package tasks;

import common.Person;
import common.PersonService;

import java.util.*;
import java.util.stream.Collectors;

/*
Задача 1
Метод на входе принимает List<Integer> id людей, ходит за ними в сервис
(он выдает несортированный Set<Person>, внутренняя работа сервиса неизвестна)
нужно их отсортировать в том же порядке, что и переданные id.
Оценить асимптотику работы
 */
public class Task1 {

  private final PersonService personService;

  public Task1(PersonService personService) {
    this.personService = personService;
  }

  public List<Person> findOrderedPersons(List<Integer> personIds) {
    Set<Person> persons = personService.findPersons(personIds);

    Map<Integer,Person> id_person = persons.stream()
            .collect(Collectors.toMap(Person::id,p -> p));

    return personIds.stream()
            .map(id -> id_person.get(id))
            .toList();
    // Асимптотика: создали сет персон O(n), создали мап O(m), прошлись по персонам O(n)
    // Итого O(n)
    // сет хранит m элементов, мап так же m элементов, возвращаем список в худшем случае n элементов
    // Итого O(n)
    // Итог: O(n) по времени и памяти
  }
}
