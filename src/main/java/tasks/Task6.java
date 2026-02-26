package tasks;

import common.Area;
import common.Person;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
Имеются
- коллекция персон Collection<Person>
- словарь Map<Integer, Set<Integer>>, сопоставляющий каждой персоне множество id регионов
- коллекция всех регионов Collection<Area>
На выходе хочется получить множество строк вида "Имя - регион". Если у персон регионов несколько, таких строк так же будет несколько
 */
public class Task6 {

  public static Set<String> getPersonDescriptions(Collection<Person> persons,
                                                  Map<Integer, Set<Integer>> personAreaIds,
                                                  Collection<Area> areas) {

    HashMap<Integer,String> id_to_region = new HashMap();
    for (Area i: areas){
      id_to_region.put(i.getId(),i.getName());
    }

    return persons.stream()
            .flatMap(p -> personAreaIds.get(p.id()).stream()
                    .map(a -> Stream.of(p.firstName(),id_to_region.get(a))
                    .collect(Collectors.joining(" - ")))
            ).collect(Collectors.toSet());
  }
}
