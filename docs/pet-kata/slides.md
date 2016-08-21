
Eclipse Collections Pet Kata
============================

Learning by exercises

* **space key:** go to next page
* **down arrow key:** go to next page in the current section
* **right arrow key:** go to next section



Pet Kata domain
===============
 * Eclipse Collections Pet Kata uses `Person`, `Pet`, `PetType`
 * You can find domain objects are initialized in `PetDomainForKata` class
 * Data is available to you through `this.people` which has some person and pet object set for you


<img src="pet-domain.png" alt="Kata Domain" style="width: 55%;"/>



Ex1
===

* **Go down:** Learn new concepts in Ex1
* **Go right:** Learn Ex1 solutions


Collect Pattern
---------------
 * _Collect_ Pattern (a.k.a. _map_ or _transform_).
 * Return a new element where each element has been transformed.
   * e.g. collect each pet's name.
 * __Function__ is the type that takes an object and returns an object of a different type.
   * a.k.a. _Transformer_.


Collect Pattern (legacy for loop)
---------------------------------

```
List<Pet> pets = someCodeToGetPets();
List<String> petNames = new ArrayList<String>();
for (Pet pet : pets)
{
petNames.add(pet.getName());
}
```


Collect Pattern (Eclipse Collections)
-------------------------------------

Using Java 8 lambda expression
```
MutableList<Pet> pets = someCodeToGetPets();
MutableList<String> petNames = pets.collect(pet -> pet.getName());
```

Or using method reference
```
MutableList<Pet> pets = someCodeToGetPets();
MutableList<String> petNames = pets.collect(Pet::getName);
```


Select Pattern
--------------
 * _Select_ Pattern (a.k.a. _filter_).
 * Return the elements of collections that satisfy some condition
   * e.g. select only those people who have a pet.
 * __Predicate__ is the type that takes an object and returns a boolean


Select Pattern (Eclipse Collections)
------------------------------------

```
MutableList<Person> people = someCodeToGetPeople();
MutableList<Person> petPeople 
        = people.select(person -> person.isPetPerson());
```


Exercise 1
----------
 * Fix `Exercise1Test`; they have failures.
 * Figure out how to get the tests to pass using what you have seen so far.



Ex1 solutions
=============

* **Go down:** Learn Ex1 solutions
* **Go right:** Learn Ex2


Get first names of people
-------------------------
```
    @Test
    public void getFirstNamesOfAllPeople()
    {
        MutableList<Person> people = this.people;
        MutableList<String> firstNames =
            people.collect(Person::getFirstName);
        MutableList<String> expectedFirstNames =
            Lists.mutable.with("Mary", "Bob", "Ted", "Jake", "Barry", "Terry", "Harry", "John");
        Assert.assertEquals(expectedFirstNames, firstNames);
    }
```


Get names of Mary Smith's Pets
------------------------------
```
    @Test
    public void getNamesOfMarySmithsPets()
    {
        Person person = this.getPersonNamed("Mary Smith");
        MutableList<Pet> pets = person.getPets();
        MutableList<String> names =
            pets.collect(eachPet -> eachPet.getName());
        Assert.assertEquals("Tabby", names.makeString());
    }
```


Get people with cats
--------------------
```
    @Test
    public void getPeopleWithCats()
    {
        MutableList<Person> people = this.people;
        MutableList<Person> peopleWithCats =
            people.select(person -> person.hasPet(PetType.CAT));
        Verify.assertSize(2, peopleWithCats);
    }
```


Get people without cats
-----------------------
```
    @Test
    public void getPeopleWithoutCats()
    {
        MutableList<Person> people = this.people;
        MutableList<Person> peopleWithoutCats =
            people.reject(person -> person.hasPet(PetType.CAT));
        Verify.assertSize(6, peopleWithoutCats);
    }
```



Ex2
===

* **Go down:** Learn new concepts in Ex2
* **Go right:** Learn Ex2 solutions


TestUtils
---------
 * Eclipse Collections distribution includes `eclipse-collections-testutils.jar`.
   * Includes helpful utity for writing unit tests.
   * Collection specific.
   * Implemented as an extention of JUnit.
   * Better error messages.
   * Most important class is called `Verify`.


TestUtils Code Example
----------------------

Example from previous solution
```
Verify.assertSize(2, peopleWithCats);
```

Instead of
```
Assert.assertEquals(2, peopleWithCats.size());
```


Flat Collect Pattern
--------------------
* `flatCollect()` is a special case for `collect()`
* With `collect()`, when the `Function` returns a collection, the result is collection of collections.

collect code example
```
MutableList<Person> people = ...;
Function<Person, Iterable<PetType>> function =
        person -> person.getPetTypes();
MutableList<MutableList<PetType>> pets =
        people.collect(function);
```


Flat Collect Pattern
--------------------
* `flatCollect()` outputs a single **flattened** collection instead of a collection of collections.
* The signature of `flatCollect()` is similar to `collect()`, expect that the `Function` parameter must map to an `Iterable` type.

```
flatCollect(Function<? super T, ? extends Iterable<V>> function);
```

Code Example
```
MutableList<Person> people = ...;
MutableList<PetType> pets =
        people.flatCollect(person -> person.getPetTypes());
```


More Iteration Pattern
----------------------
* Other Pattern that use `Predicate`.
  * **Select:** returns the element of a collection that satisfy the `Predicate`
  * **Reject:** returns the element of a collection that do not satisfy the `Predicate`
  * **Count:** returns the number of elements that satisfy the `Predicate`


More Iteration Pattern
----------------------
* Short circuit pattern that use `Predicate`.
  * **Detect:** finds the first element that satisfies the `Predicate`
  * **Any Satisfy:** returns true if any element satisfies the `Predicate`
  * **All Satisfy:** returns true if all elements satisfy the `Predicate`
  * **None Satisfy:** returns true if no element satisfies the `Predicate`


Inheritance Hierarchy (List)
----------------------------
* `MutableList` extends `List`
* `FastList` is a drop-in replacement for `ArrayList`


<img src="../shared/inheritance-hierarchy-list.png" alt="Inheritance Hierarchy (List)" style="width: 45%;"/>


Inheritance Hierarchy (Set)
----------------------------
* `MutableSet` extends `Set`
* `UnifiedSet` is a drop-in replacement for `HashSet`


<img src="../shared/inheritance-hierarchy-set.png" alt="Inheritance Hierarchy (Set)" style="width: 45%;"/>


With Methods
------------
* `collectWith()`, `selectWith()` and `rejectWith()` are alternate forms of `collect()`, `select()` and `reject()`.
* Original forms all take a single parameter, a code block which takes a single parameter.
* What if we want to find groups of people at different ages?

Code Example
```
MutableList<Person> voters = people.select(
    person -> person.getAge() > 18;
);
```


With Methods
------------
* `...With()` forms take two parameters:
  * A code block which takes two parameters,
  * An object that gets passed as the second argument of the code block.
* Store the two-argument block in a consistent to avoid object creation

Code Example

```
Predicate2<Person, Integer> age =
        (person, age) -> person.getAge() > age;
MutableList<Person> drivers = people.selectWith(age, 17);
MutableList<Person> voters = people.selectWith(age, 18);
MutableList<Person> drinkers = people.selectWith(age, 21);
MutableList<Person> sunsetRobotSquad = people.selectWith(age, 160);
```


With Methods
------------
* For almost every common iteration pattern, there is an equivalent "With" method.
  * selectWith()
  * detectWith()
  * rejectWith()
  * anySatisfyWith()
  * ...etc


With Methods
------------
* These are useful when you want to use method references rather than lambdas

```
MutableList<Person> peopleWithCatsLambda =
    this.people.select(Person::hasPet(PetType.CAT)); //does not compile!

MutableList<Person> peopleWithCatMethodReference =
    this.people.selectWith(Person::hasPet, PetType.CAT);
```


Exercise 2
----------
 * Fix `Exercise2Test`; they have failures.
 * Use the other iteration patterns that take a `Predicate`.
 * Use the `flatCollect` pattern.



Ex2 solutions
=============

* **Go down:** Learn Ex2 solutions
* **Go right:** Learn Ex3


Do any people have cats
-----------------------
```
    @Test
    public void doAnyPeopleHaveCats()
    {
        Predicate<Person> predicate = person -> person.hasPet(PetType.CAT);
        Assert.assertTrue(this.people.anySatisfy(predicate));
    }
```


Do all people have pets
-----------------------
```
    @Test
    public void doAllPeopleHavePets()
    {
        Predicate<Person> predicate = person -> person.isPetPerson();
        boolean result = this.people.allSatisfy(predicate);
        Assert.assertFalse(result);
    }
```


How many people have cats
-------------------------
```
    @Test
    public void howManyPeopleHaveCats()
    {
        int count = this.people.count(person -> person.hasPet(PetType.CAT));
        Assert.assertEquals(2, count);
    }
```


Find Mary Smith
---------------
```
    @Test
    public void findMarySmith()
    {
        Person result = this.people.detect(person -> person.named("Mary Smith"));
        Assert.assertEquals("Mary", result.getFirstName());
        Assert.assertEquals("Smith", result.getLastName());
    }
```


Get People With Pets
--------------------
```
    @Test
    public void getPeopleWithPets()
    {
        // Replace with only the pet owners.
        MutableList<Person> petPeople = this.people.select(person -> person.isPetPerson());
        Verify.assertSize(7, petPeople);
    }
```


Get all pets of all people
--------------------------
```
    @Test
    public void getAllPetsOfAllPeople()
    {
        Function<Person, Iterable<PetType>> function = person -> person.getPetTypes();
        MutableSet<PetType> petTypes = this.people.flatCollect(function, Sets.mutable.empty());
        Assert.assertEquals(
                Sets.mutable.with(PetType.CAT, PetType.DOG, PetType.TURTLE, PetType.HAMSTER, PetType.BIRD, PetType.SNAKE),
                petTypes);
    }
```


Get first name of all people
----------------------------
```
    @Test
    public void getFirstNamesOfAllPeople()
    {
        MutableList<String> firstNames = this.people.collect(Person::getFirstName);
        Assert.assertEquals(
                Lists.mutable.with("Mary", "Bob", "Ted", "Jake", "Barry", "Terry", "Harry", "John"),
                firstNames);
    }
```


Do any people have cats (refactor)
----------------------------------
```
    @Test
    public void doAnyPeopleHaveCatsRefactor()
    {
        boolean peopleHaveCatsLambda = this.people.anySatisfy(person -> person.hasPet(PetType.CAT));
        Assert.assertTrue(peopleHaveCatsLambda);

        // Use method reference, NOT lambdas, to solve the problem below.
        boolean peopleHaveCatsMethodRef = this.people.anySatisfyWith(Person::hasPet, PetType.CAT);
        Assert.assertTrue(peopleHaveCatsMethodRef);
    }
```


Do all people have cats (refactor)
----------------------------------
```
    @Test
    public void doAllPeopleHaveCatsRefactor()
    {
        boolean peopleHaveCatsLambda = this.people.allSatisfy(person -> person.hasPet(PetType.CAT));
        Assert.assertFalse(peopleHaveCatsLambda);

        // Use method reference, NOT lambdas, to solve the problem below.
        boolean peopleHaveCatsMethodRef = this.people.allSatisfyWith(Person::hasPet, PetType.CAT);
        Assert.assertFalse(peopleHaveCatsMethodRef);
    }
```


Get people with cats (refactor)
-------------------------------
```
    @Test
    public void getPeopleWithCatsRefator()
    {
        // Use method reference, NOT lambdas, to solve the problem below.
        MutableList<Person> peopleWithCatsMethodRef = this.people.selectWith(Person::hasPet, PetType.CAT);
        Verify.assertSize(2, peopleWithCatsMethodRef);
    }
```


Get people without cats (refactor)
-------------------------------
```
    @Test
    public void getPeopleWithoutCatsRefactor()
    {
        // Use method reference, NOT lambdas, to solve the problem below.
        MutableList<Person> peopleWithoutCatsMethodRef = this.people.rejectWith(Person::hasPet, PetType.CAT);
        Verify.assertSize(6, peopleWithoutCatsMethodRef);
    }
```



Ex3
===

* **Go down:** Learn new concepts in Ex3
* **Go right:** Learn Ex3 solutions


Bag
===


Bag
---

* Useful when you would otherwise use a map with integer value like below.
  * `Map<K, Integer>`
* For example, ﬁnd the number of each pet type.

Code Example
```
MutableList<Person> people = ...;
MutableList<PetType> pets =
    people.flatCollect(Person::getPetTypes);
MutableMap<PetType, Integer> petTypeCounts =
    UnifiedMap.newMap();
...

int cats = petTypeCounts.get(PetType.CAT);
```


Bag
---

* Problem with `Map<K, Integer>`
* Lots of boilerplate code to deal with uninitialized counts.

Code Example
```
MutableMap<PetType, Integer> petTypeCounts =
    UnifiedMap.newMap();
for (PetType petType : pets) {
    Integer count = petTypeCounts.get(petType);
    if (count == null) {
        count = 0;
    }
    petTypeCounts.put(petType, count + 1);
}
```


Bag
---

* Bag is implemented as a map of key to count.
* Like a List, but unordered.
* Like a Set, but allows duplicates.

Code Example
```
MutableBag<PetType> petTypeCounts = pets.toBag();

int cat = petTypeCounts.occurrencesOf(PetType.CAT);
```


Bag
---

|Methods|Inherited from|
|:------|--------------|
|`select()`, `collect()`, etc. |`RichIterable`|
|`add()`, `remove()`, `iterator()`, `etc.|`MutableCollection` (java.util.Collection)|
|`occurencesOf()`, `forEachWithOccurences()`, `toMapOfItemToCount()`|`Bag`|
|`addOccurences()`, `removeOccurences()`|`MutableBag`|


Bag
---
Code Example

```
MutableBag<String> bag =
    HashBag.newBagWith("one", "two", "two", "three", "three", "three");

Assert.assertEquals(3, bag.occurrencesOf("three"));

bag.add("one");
Assert.assertEquals(2, bag.occurrencesOf("one"));

bag.addOccurrences("one", 4);
Assert.assertEquals(6, bag.occurrencesOf("one"));
```


Multimap
========


Multimap
--------
* Multimap is similar to Map, but associates a key to mul=ple values
* Useful when you would otherwise use a map with integer value like below.
  * `Map<K, Collection<V>>`
* For example, ﬁnd *which* people have the same name.

Code Example
```
MutableList<Person> people = ...;
MutableMap<String, MutableList<Person>> lastNamesToPeople =
    UnifiedMap.newMap();

...

MutableList<Person> smiths =
    lastNamesToPeople.get("Smith");
```


Multimap
--------
* Problem with `Map<K, Collection<V>>`
* Lots of boilerplate code to deal with uninitialized backing collections.

Code Example
```
MutableMap<String, MutableList<Person>> lastNamesToPeople =
    UnifiedMap.newMap();
for (Person person : people) {
    String lastName = person.getLastName();
    MutableList<Person> peopleWithLastName =
lastNamesToPeople.get(lastName);
    if (peopleWithLastName == null) {
        peopleWithLastName = FastList.newList();
        lastNamesToPeople.put(lastName, peopleWithLastName);
    }
    peopleWithLastName.add(person);
}

MutableList<Person> smiths =
    lastNamesToPeople.get("Smith");
```


Multimap
--------

* Using Multimap for the same example

Code Example
```
MutableListMultimap<String, Person> lastNamesToPeople =
    people.groupBy(Person.TO_LAST_NAME);

MutableList<Person> smiths =
    lastNamesToPeople.get("Smith");
```


Multimap
--------
* What happens if you add the same key and value twice?


Multimap
--------
* What happens if you add the same key and value twice?
* Depends on the type of the backing collection.


Multimap
--------
* When backing collection is List

Code Example

```
MutableListMultimap<String, Person> multimap =
    FastListMultimap.newMultimap();
multimap.put("Smith", person);
multimap.put("Smith", person);
MutableList<Person> smiths = multimap.get("Smith");

Verify.assertIterableSize(2, smiths);
```


Multimap
--------
* When backing collection is Set

Code Example

```
MutableSetMultimap<String, Person> multimap =
    UnifiedSetMultimap.newMultimap();
multimap.put("Smith", person);
multimap.put("Smith", person);
MutableSet<Person> smiths = multimap.get("Smith");

Verify.assertIterableSize(1, smiths);
```


Multimap
--------

* `groupByEach()` is a special case of `groupBy()`.
* Analogous to the diﬀerence between `collect()` and `flatCollect()`.
* Appropriate when the Function returns a collection.
* The return type is the same as `groupBy()`.

Code Example

```
MutableListMultimap<String, Person> lastNamesToPeople =
    people.groupBy(Person::getLastName);

MutableListMultimap<PetType, Person> petsToPeople =
    people.groupByEach(person -> person.getPets().collect(Pet::getType);
```


Target Collection
=================


Target Collection
-----------------

* Let's say we have 3 people: mrSmith, mrsSmith, mrJones.
* The ﬁrst two share the same address.
* What will get printed by the following code?

Code Example

```
MutableSet<Person> people =
    UnifiedSet.newSetWith(mrSmith, mrsSmith, mrJones);

int numAddresses =
    people.collect(addressFunction).size();

System.out.println(numAddresses);
```


Target Collection
-----------------

* `select()`, `collect()`, etc. are deﬁned with covariant return types:
  * `MutableCollection.collect()` returns a `MutableCollection`
  * `MutableList.collect()` returns a `MutableList`
  * `MutableSet.collect()` returns a `MutableSet`
* Alternate forms take target collections.


Target Collection
-----------------

Code Example

```
MutableSet<Person> people =
    UnifiedSet.newSetWith(mrSmith, mrsSmith, mrJones);

MutableList<Address> targetList =
    FastList.<Address>newList();

int numAddresses =
    people.collect(addressFunction, targetList).size();

System.out.println(numAddresses);
```


Exercise 3
----------
 * Fix `Exercise3Test`; they have failures.
 * Use `Bag`, `Multimap`.



Ex3 solutions
=============

* **Go down:** Learn Ex3 solutions
* **Go right:** Learn Ex4


Get counts by pet type
----------------------
```
    @Test
    public void getCountsByPetType()
    {
        MutableList<PetType> petTypes = this.people.flatCollect(Person::getPets).collect(Pet::getType);
        // Try to replace MutableMap<PetType, Integer> with a Bag<PetType>.
        MutableBag<PetType> counts = petTypes.toBag();

        Assert.assertEquals(2, counts.occurrencesOf(PetType.CAT));
        Assert.assertEquals(2, counts.occurrencesOf(PetType.DOG));
        Assert.assertEquals(2, counts.occurrencesOf(PetType.HAMSTER));
        Assert.assertEquals(1, counts.occurrencesOf(PetType.SNAKE));
        Assert.assertEquals(1, counts.occurrencesOf(PetType.TURTLE));
        Assert.assertEquals(1, counts.occurrencesOf(PetType.BIRD));
    }
```


Get people by last name
-----------------------
```
    @Test
    public void getPeopleByLastName()
    {
        // Try to replace MutableMap<String, MutableList<Person> with a Multimap.
        // Hint: use the groupBy method.
        MutableListMultimap<String, Person> lastNamesToPeople = this.people.groupBy(Person::getLastName);

        Verify.assertIterableSize(3, lastNamesToPeople.get("Smith"));
    }
```


Get people by their pets
------------------------
```
    @Test
    public void getPeopleByTheirPets()
    {
        // Hint: Use a target collection to go from a List to MutableSetMultimap<PetType, Person>.
        MutableSetMultimap<PetType, Person> peopleByPetType =
                this.people.groupByEach(person -> person.getPetTypes(), UnifiedSetMultimap.newMultimap());

        Verify.assertIterableSize(2, peopleByPetType.get(PetType.CAT));
        Verify.assertIterableSize(2, peopleByPetType.get(PetType.DOG));
        Verify.assertIterableSize(1, peopleByPetType.get(PetType.HAMSTER));
        Verify.assertIterableSize(1, peopleByPetType.get(PetType.TURTLE));
        Verify.assertIterableSize(1, peopleByPetType.get(PetType.BIRD));
        Verify.assertIterableSize(1, peopleByPetType.get(PetType.SNAKE));
    }
```



Ex4
===

* **Go down:** Learn new concepts in Ex4
* **Go right:** Learn Ex4 solutions


Primitive Collections
=====================


Primitive Collections
---------------------

* Each data structure has an equivalent primitive side
* Eclipse Collections support all primitives
  * int, double, short, long, float, boolean, char, byte


Primitive Collections
---------------------
All 8 primitive collection interfaces

<img src="../shared/primitive-collections1.png" alt="Primitive Collections" style="width: 100%;"/>


Primitive Collections
---------------------
Class Diagram

<img src="../shared/primitive-collections2.png" alt="Primitive Collections" style="width: 75%;"/>


Primitive Collections
---------------------
* There is also the same rich and ﬂuent API that the non primitive side oﬀers.

Code Example

```
MutableIntList intListOfAges =
    this.people
        .flatCollect(Person::getPets)
        .collectInt(Pet::getAge);

MutableIntList selection =
    intListOfAges.select(age -> age > 2);

MutableIntSet intSetOfAges = intListOfAges.toSet();
```


Exercise 4
----------
 * Fix `Exercise4Test`; they have failures.
 * Refactor Java 8 streams to Eclipse Collections.



Ex4 solutions
=============

* **Go down:** Learn Ex4 solutions
* **Go right:** Learn Ex5


Get age statistics of pets
--------------------------
```
    @Test
    public void getAgeStatisticsOfPets()
    {
        // Try to use a MutableIntList here instead.
        // Hints: flatMap = flatCollect, map = collect, mapToInt = collectInt
        IntList agesLazy = this.people.asLazy()
                .flatCollect(Person::getPets)
                .collectInt(Pet::getAge)
                .toList();
        // Try to use an IntSet here instead.
        IntSet uniqueAges = agesLazy.toSet();
        // IntSummaryStatistics is a class in JDK 8 - Try and use it with MutableIntList.forEach().
        IntSummaryStatistics stats = new IntSummaryStatistics();
        agesLazy.forEach(stats::accept);
        // Is a Set<Integer> equal to an IntSet?
        // Hint: Try IntSets instead of Sets as the factory.
        Assert.assertEquals(IntHashSet.newSetWith(1, 2, 3, 4), uniqueAges);
        // Try to leverage min, max, sum, average from the Eclipse Collections Primitive API.
        Assert.assertEquals(stats.getMin(), agesLazy.min());
        Assert.assertEquals(stats.getMax(), agesLazy.max());
        Assert.assertEquals(stats.getSum(), agesLazy.sum());
        Assert.assertEquals(stats.getAverage(), agesLazy.average(), 0.0);
        Assert.assertEquals(stats.getCount(), agesLazy.size());
        // Hint: Match = Satisfy
        Assert.assertTrue(agesLazy.allSatisfy(IntPredicates.greaterThan(0)));
        Assert.assertTrue(agesLazy.allSatisfy(i -> i > 0));
        Assert.assertFalse(agesLazy.anySatisfy(i -> i == 0));
        Assert.assertTrue(agesLazy.noneSatisfy(i -> i < 0));
        Assert.assertEquals(2.0d, agesLazy.median(), 0.0);
    }
```


Stream to EC refactor #1
------------------------
```
    @Test
    public void streamsToECRefactor1()
    {
        // Find Bob Smith.
        Person person = this.people.detect(each -> each.named("Bob Smith"));

        // Get Bob Smith's pets' names
        String names = person.getPets().collect(Pet::getName).makeString(" & ");

        Assert.assertEquals("Dolly & Spot", names);
    }
```


Stream to EC refactor #2
------------------------
```
    @Test
    public void streamsToECRefactor2()
    {
        // Hint: Try to replace the Map<PetType, Long> with a Bag<PetType>.
        MutableBag<PetType> counts =
                this.people
                        .asUnmodifiable()
                        .flatCollect(Person::getPets)
                        .collect(Pet::getType)
                        .toBag();
        Assert.assertEquals(2, counts.occurrencesOf(PetType.CAT));
        Assert.assertEquals(2, counts.occurrencesOf(PetType.DOG));
        Assert.assertEquals(2, counts.occurrencesOf(PetType.HAMSTER));
        Assert.assertEquals(1, counts.occurrencesOf(PetType.SNAKE));
        Assert.assertEquals(1, counts.occurrencesOf(PetType.TURTLE));
        Assert.assertEquals(1, counts.occurrencesOf(PetType.BIRD));
    }
```


Stream to EC refactor #3
------------------------
```
    @Test
    public void streamsToECRefactor3()
    {
        // Hint: The result of groupingBy/counting can almost always be replaced by a Bag.
        // Hint: Look for the API on Bag that might return the top 3 pet types.
        MutableList<ObjectIntPair<PetType>> favorites =
                this.people.asLazy()
                        .flatCollect(Person::getPets)
                        .collect(Pet::getType)
                        .toBag()
                        .topOccurrences(3);
        Verify.assertSize(3, favorites);
        Verify.assertContains(PrimitiveTuples.pair(PetType.CAT, 2), favorites);
        Verify.assertContains(PrimitiveTuples.pair(PetType.DOG, 2), favorites);
        Verify.assertContains(PrimitiveTuples.pair(PetType.HAMSTER, 2), favorites);
    }
```



Ex5
===

* Ex5 is extra-credit.
* Use methods on [RichIterable](https://www.eclipse.org/collections/javadoc/8.0.0/org/eclipse/collections/api/RichIterable.html).
* **Go right:** Learn Ex5 solutions



Partition pet and non pet people
--------------------------------
```
    @Test
    public void partitionPetAndNonPetPeople()
    {
        PartitionMutableList<Person> partitionMutableList = this.people.partition(eachPerson -> eachPerson.isPetPerson());
        Verify.assertSize(7, partitionMutableList.getSelected());
        Verify.assertSize(1, partitionMutableList.getRejected());
    }
```


Get oldest pet
--------------
```
    @Test
    public void getOldestPet()
    {
        Pet oldestPet = this.people.flatCollect(Person::getPets).maxBy(pet -> pet.getAge());
        Assert.assertEquals(PetType.DOG, oldestPet.getType());
        Assert.assertEquals(4, oldestPet.getAge());
    }
```


Get average pet age
-------------------
```
    @Test
    public void getAveragePetAge()
    {
        double averagePetAge = this.people.flatCollect(Person::getPets).collectDouble(Pet::getAge).average();
        Assert.assertEquals(1.8888888888888888, averagePetAge, 0.00001);
    }
```


Add pet ages to existing set
----------------------------
```
    @Test
    public void addPetAgesToExistingSet()
    {
        // Hint: Use petAges as a target collection
        MutableIntSet petAges = IntSets.mutable.with(5);
        this.people.flatCollect(Person::getPets).collectInt(pet -> pet.getAge(), petAges);
        Assert.assertEquals(IntSets.mutable.with(1, 2, 3, 4, 5), petAges);
    }
```


Refactor to Eclipse Collections
-------------------------------
```
    @Test
    public void refactorToEclipseCollections()
    {
        // Replace List and ArrayList with Eclipse Collections types.
        MutableList<Person> people = Lists.mutable.with(
                new Person("Mary", "Smith").addPet(PetType.CAT, "Tabby", 2),
                new Person("Bob", "Smith")
                        .addPet(PetType.CAT, "Dolly", 3)
                        .addPet(PetType.DOG, "Spot", 2),
                new Person("Ted", "Smith").addPet(PetType.DOG, "Spike", 4),
                new Person("Jake", "Snake").addPet(PetType.SNAKE, "Serpy", 1),
                new Person("Barry", "Bird").addPet(PetType.BIRD, "Tweety", 2),
                new Person("Terry", "Turtle").addPet(PetType.TURTLE, "Speedy", 1),
                new Person("Harry", "Hamster")
                        .addPet(PetType.HAMSTER, "Fuzzy", 1)
                        .addPet(PetType.HAMSTER, "Wuzzy", 1),
                new Person("John", "Doe"));

        // Replace Set and HashSet with Eclipse Collections types.
        MutableIntSet petAges = people.flatCollect(Person::getPets).collectInt(Pet::getAge).toSet();

        // Extra bonus - convert to a primitive collection.
        Assert.assertEquals(IntSets.mutable.with(1, 2, 3, 4), petAges);
    }
```


CONGRATULATIONS!
================
You have completed Pet Kata.

Enjoy happy Java development with Eclipse Collections!!