package com.javarush.task.task33.task3312;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/* 
Сериализация зоопарка
*/
public class Solution {
    public static void main(String[] args) throws JsonProcessingException {
        Zoo.Dog dog = new Zoo.Dog("doggy");
        Zoo.Cat cat = new Zoo.Cat("catty");
        Zoo zoo = new Zoo();
        zoo.animals.add(dog);
        zoo.animals.add(cat);

        String result = new ObjectMapper().writeValueAsString(zoo);

        System.out.println(result);
    }
}

/*
1. Из лекции "Во-первых, выделяют некоторое поле, которое используется для того, чтобы отличать один тип от другого.
Если его нет – его заводят." Поэтому пишем в начале класса Zoo:

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")

Т.е. эту аннотацию не обязательно писать перед классом Animal, она к нему не относится. Данная аннотация говорит
"при сериализации добавь поле type ко всем вложенным объектам", а "при десериализации использую поле type как
уникальный id объекта".
После добавления только этой строки вывод будет такой:

{"animals":[{"type":"Zoo$Dog","name":"doggy","barkVolume":0.0},{"type":"Zoo$Cat","name":"catty","lives":0}]}

Как видим у объектов коллекции animals появилось поле Type значение которого NAME (имя класса).

2. Как видим поле type заполняется точным именем класса (Zoo$Dog). Нас это не устраивает, поэтому мы маркируем классы
упрощенной "биркой"

@JsonTypeName("dog")

После добавления "бирок" вывод будет таким:

{"animals":[{"type":"dog","name":"doggy","barkVolume":0.0},{"type":"cat","name":"catty","lives":0}]}

Такой вывод нас устраивает, но валидацию он не пройдёт, так как мы не использовали @JsonSubTypes

3. Аннотация @JsonSubTypes, как я понял, нужна не для сериализации, а для десериализации. При десериализации мы пишем

Zoo zoo1 = new ObjectMapper().readValue(result, Zoo.class);

Программа начинает заполнять новый объект класса Zoo. Находит описание коллекции animals, видит по аннотациям класса
Zoo что поле type это id объекта, но не знает где взять класс для создания нового объекта. Вот тут и приходит на
помощь аннотация @JsonSubTypes.

@JsonSubTypes({
        @JsonSubTypes.Type(Dog.class),
        @JsonSubTypes.Type(Cat.class)
})


Данная аннотация описывает вложенные объекты класса Zoo.
Если попытаться десериализовать Zoo без этой аннотации, то получим InvalidTypeIdException, а после добавления уже
будет другая ошибка :) MismatchedInputException. Которая появляется из-за того, что наши классы создаются не с
конструктором по умолчанию. Чтобы обойти эту ошибку перед контруктором каждого вложенного класса пишем @JsonCreator, а
в параметрах конструктора @JsonProperty("name"). После чего наш Zoo отлично десериализуется :) Но процесс
десериализации к этой задаче не относится. :)
 */