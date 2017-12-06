<!--
  ~ Copyright (c) 2017 BNY Mellon and others.
  ~ All rights reserved. This program and the accompanying materials
  ~ are made available under the terms of the Eclipse Public License v1.0
  ~ and Eclipse Distribution License v. 1.0 which accompany this distribution.
  ~ The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
  ~ and the Eclipse Distribution License is available at
  ~ http://www.eclipse.org/org/documents/edl-v10.php.
  -->
<img src="../shared/eclipse-collections-logo.png" alt="Eclipse Collections" style="width: 50%;background-color:aliceblue"/>
API Design: Eclipse Collections
-------------------------------

* **space:** next page
* **down arrow:** next page in the current section
* **right arrow:** next section


Agenda
------
 * What is Eclipse Collections?
 * Design Goals
 * Eclipse Collections 9.0
 * Strategies  for evolving a Collections API
 * How did we get here?
 * Symmetric Sympathy
 * The Past and the Future



What is Eclipse Collections?
----------------------------
* An open source Java Collections Framework
  * Functional, Fluent, Friendly and Fun
  * A mature library with an evolutionary design
  * 12+ years of development
* A Brief History
  * Caramel (2005)
    * Internal library developed at Goldman Sachs
  * GS Collections (2012)
    * First open source project from Goldman Sachs
  * [Eclipse Collections](https://www.eclipse.org/collections/) (2015)
    * Moved to the Eclipse Foundation


Project Leads and Committers
----------------------------
 * Nikhil Nanivadekar – Project Lead (Active) / Committer
 * Donald Raab – Project Lead / Committer 
 * Hiroshi Ito – Project Lead / Committer
 * Craig Motlin – Committer
 * Moh Rezaei – Committer


Eclipse Collections Evangelism
------------------------------
* *Donald Raab* - JavaOne, Devoxx US, JCrete, EclipseCon, JVMLS, GIDS, LJC, NY Java SIG, NY JUG, Pittsburgh JUG  
* *Nikhil Nanivadekar* - JavaOne, JavaOne4Kids, Devoxx US, Devoxx4Kids, JCrete, JCrete4Kids, GIDS, Utah JUG, Dublin JUG, Belfast JUG, Edinburgh JUG, Manchester JC, West Midlands JUG 
* *Craig Motlin* - JavaOne, QCon NY, GOTO Chicago, ScalaDays, NY Java SIG
* *Hiroshi Ito* - JavaOne, JavaDay Tokyo, JJUG CCC
* *Kristen O’Leary* - EclipseCon, QCon NY, NY Java SIG
* *Bhavana Hindupur* - Bangalore JUG, Delaware JUG
* *Alex Iliev* - EclipseCon Europe, ECOOP(Curry-on)
* *Chandra Guntur* - NY Java SIG 



Design Goals
---------------
* Rich “Functional, fluent and fun” API
  * Eager, Lazy, Serial, Parallel
* Memory efficient data structures
* Optimized algorithms
* Java Interoperability
  * Java container types, Streams 
* Immutability
* Missing Types
  * Intervals, Stacks, Bags, Multimaps, BiMaps
* Primitive Containers

[Symmetric Sympathy](https://medium.com/@donraab/symmetric-sympathy-2c59d4541d60)


Eclipse Collections Today
-------------------------
* Open for contribution at the Eclipse Foundation
* Symmetry drives the design
* Interoperability w/ Java 8 and Java 9

<img src="symmetry.png" alt="Symmetry" style="width: 100%;"/>


Symmetry: Protocols and Interfaces
----------------------------------

<img src="selectreject.png" alt="Select/Reject" style="width: 100%;background-color:aliceblue"/>


Memory Efficiency -  Sets
-------------------------

<img src="costsets.png" alt="Select/Reject" style="width: 100%;background-color:aliceblue"/>


Memory Efficiency -  Immutable
------------------------------

<img src="costimmutable.png" alt="Select/Reject" style="width: 100%;background-color:aliceblue"/>


Memory Efficiency -  Primitives
-------------------------------

<img src="costprimitives.png" alt="Select/Reject" style="width: 100%;background-color:aliceblue"/>


Object vs. Primitive Performance
--------------------------------
| *Benchmark*               | *Operations per Second* |
| ------------------------- | ------------------- |
| filterECPrimitive         | 88.674              |
| filterJDKBoxed            | 58.126              |
| filterJDKBoxedParallel    | 297.705             |
---
* Use case: Filter evens into new List
* Machine: 12-Core Intel Xeon E5, 2.7GHz, 64 GB RAM
* Data: 1 Million Ints
* Larger numbers are better


Object vs. Primitive Performance
--------------------------------
| *Benchmark*               | *Operations per Second* |
| ------------------------- | ------------------- |
| sumECPrimitive            | 1465.254            |
| sumJDKBoxed               | 156.609             |
| sumJDKBoxedParallel       | 2607.731            |
---
* Use case: Sum all of the numbers
* Machine: 12-Core Intel Xeon E5, 2.7GHz, 64 GB RAM
* Data: 1 Million Ints
* Larger numbers are better


Object vs. Primitive Performance
--------------------------------
| *Benchmark*               | *Operations per Second* |
| ------------------------- | ------------------- |
| transformECPrimitive      | 652.465             |
| transformJDKBoxed         | 55.098              |
| transformJDKBoxedParallel | 180.775             |
---
* Use case: Multiply each x 2 and return result in new List
* Machine: 12-Core Intel Xeon E5, 2.7GHz, 64 GB RAM
* Data: 1 Million Ints
* Larger numbers are better



New in Eclipse Collections 9.0
------------------------------
* Java 9
  * Automatic Module Naming
  * Searchable Javadocs (finally!!!!)
* New Features
  * CountBy
  * DistinctBy
  * Factories for Primitive Streams
  * Stream on Immutable Collections


CountBy
-------
```java
// Eclipse Collections 7.x - 8.x

MutableBag<String> countsOld =
        this.company.getCustomers()
                .asLazy().collect(Customer::getCity).toBag();

```
---
```java
// Eclipse Collections 9.x

MutableBag<String> countsNew =
        this.company.getCustomers()
                .countBy(Customer::getCity);

```


DistinctBy
----------
```java
// Eclipse Collections 7.x - 8.x

MutableList<Customer> distinctOld =
        this.company.getCustomers()
                .distinct(HashingStrategies
                              .fromFunction(Customer::getCity));

```
---
```java
// Eclipse Collections 9.x

MutableList<Customer> distinctNew =
        this.company.getCustomers()
                .distinctBy(Customer::getCity);

```


Factories for Primitive Streams
-------------------------------
```java
// Eclipse Collections 7.x - 8.x

MutableIntList listOld =
        IntStream.rangeClosed(1, 100)
                .collect(IntLists.mutable::empty,
                         MutableIntList::add,
                         MutableIntList::withAll);

```
---
```java
// Eclipse Collections 9.x

MutableIntList listNew =
        IntLists.mutable.withAll(
                IntStream.rangeClosed(1, 100));   

```


Stream on Immutable Collections
-------------------------------
```java
// Eclipse Collections 7.x - 8.x

boolean result =
        Lists.immutable.with(1, 2, 3)
                .castToList()
                .stream()
                .anyMatch(i -> i % 2 == 0);

```
---
```java
// Eclipse Collections 9.x

boolean result =
        Lists.immutable.with(1, 2, 3)
                .stream()
                .anyMatch(i -> i % 2 == 0);

```



API Evolution Strategies
------------------------
| *Strategy*   | *Cost* | *Complexity* |
| ---------- | ---- | ---------- |
| Static Utility | Low   | Low |
| Default methods | Low - Med  | Medium |
| Lazy API | Med - High  | Medium |
| Parallel Utility | Medium  | Med - High |
| Eager API | High  | Medium |
| New Data Type (Object) | High  | Med - High |
| Parallel Lazy API | High  | High |
| New Data Type (Primitive) | High  | High |



How did we get here?
--------------------
* Once upon a time...


An old dude who knows Smalltalk
-------------------------------
* Clipper / CA-Visual Objects (‘90 –‘95)
  * Arrays
  * Code Blocks
    * { |each| expression }
* IBM VisualAge Smalltalk (‘94 – ‘00)
  * Collections
  * Code Blocks
    * [ :each | expression ]


Smalltalk-80 Collection Hierarchy
---------------------------------

<img src="smalltalkhierarchy.png" alt="Smalltalk Collections" style="width: 100%;background-color:aliceblue"/>

"It's turtles all the way down"


Smalltalk Collection Factories
------------------------------
```smalltalk
|set list bag array string map interval|

set := Set with: 1 with: 2 with: 3 with: 4.
list := OrderedCollection with: 1 with: 2 with: 3 with: 4. 
bag := Bag with: 1 with: 2 with: 3 with: 4.

array := #( 1 2 3 4 ).

string := ‘The Quick Brown Fox jumps over a lazy dog.’.

map := Dictionary newFromPairs: #( 1 ‘1’ 2 ‘2’ 3 ‘3’ 4 ‘4’).

interval := 1 to: 4.
```


Smalltalk Protocols (Eager)
---------------------------
```smalltalk
set select: [ :each | each odd ].         // Set(1 3)
list reject: [ :each | each even].        // OrderedCollection(1 3)
array collect: [ :each | each asString].  // #(‘1’ ‘2’ ‘3’ ‘4’)

string detect: [ :c | c isLowercase].     // $h
map inject: '' into: [ :r :s | r, s].     // ‘1234’

string anySatisfy: [ :c | c = $e].        // true
set allSatisfy: [ :each | each < 5 ].     // true
map noneSatisfy: [ :s | s isString ].     // false

interval asSet.                           // Set(1 2 3 4)
map asBag.                                // Bag(‘1’ ‘2’ ‘3’ ‘4’) 
list asArray.                             // #(1 2 3 4)
```


Smalltalk + Java = Eclipse Collections
--------------------------------------
| *Smalltalk Influenced*   | *Java Influenced* |
| ---------- | ---- | ---------- |
| Symmetry | Basic Collection Types |
| Blocks (Lambdas) | Primitive Collections |
| Internal Iterators | Lazy Iterables |
| Protocol Naming | Parallelism |
| Eager APIs | Immutability |
| Bag, Interval | Multimap, BiMap |
| Factory Methods |  |


Designing API Names
-------------------
* "There are only two hard things in Computer Science"
  * Cache Invalidation
  * Naming of things" -Phil Karlton (From [Martin Fowler's Bliki](https://martinfowler.com/bliki/TwoHardThings.html))
* Choose a naming influence style
  * I chose Smalltalk as my influence
* Find patterns of implementation and name them
  * Does your name communicate well?
  * Get opinions of others
* Stick to your patterns and names - be consistent!


A rose by any other name...
---------------------------
| *Smalltalk*   | *Eclipse Collections* | *Java 8 Streams* |
| ---------- | ---- | ---------- |
| select: | [select](https://www.eclipse.org/collections/javadoc/9.0.0/org/eclipse/collections/api/RichIterable.html#select-org.eclipse.collections.api.block.predicate.Predicate-)   | filter |
| reject: | [reject](https://www.eclipse.org/collections/javadoc/9.0.0/org/eclipse/collections/api/RichIterable.html#reject-org.eclipse.collections.api.block.predicate.Predicate-)  | filter (!) |
| collect: | [collect](https://www.eclipse.org/collections/javadoc/9.0.0/org/eclipse/collections/api/RichIterable.html#collect-org.eclipse.collections.api.block.function.Function-)  | map |
| flatCollect: | [flatCollect](https://www.eclipse.org/collections/javadoc/9.0.0/org/eclipse/collections/api/RichIterable.html#flatCollect-org.eclipse.collections.api.block.function.Function-) | flatMap |
| detect: | [detect](https://www.eclipse.org/collections/javadoc/9.0.0/org/eclipse/collections/api/RichIterable.html#detect-org.eclipse.collections.api.block.predicate.Predicate-)  | filter().findFirst().get() |
| inject:into: | [injectInto](https://www.eclipse.org/collections/javadoc/9.0.0/org/eclipse/collections/api/RichIterable.html#injectInto-IV-org.eclipse.collections.api.block.function.Function2-) | reduce |
| any/all/noneSatisfy: | [any](https://www.eclipse.org/collections/javadoc/9.0.0/org/eclipse/collections/api/RichIterable.html#anySatisfy-org.eclipse.collections.api.block.predicate.Predicate-)/[all](https://www.eclipse.org/collections/javadoc/9.0.0/org/eclipse/collections/api/RichIterable.html#allSatisfy-org.eclipse.collections.api.block.predicate.Predicate-)/[noneSatisfy](https://www.eclipse.org/collections/javadoc/9.0.0/org/eclipse/collections/api/RichIterable.html#noneSatisfy-org.eclipse.collections.api.block.predicate.Predicate-) | any/all/noneMatch |


Eclipse Collections Factories
-----------------------------
```java
MutableSet<Integer> set = Sets.mutable.with(1, 2, 3, 4);
MutableList<Integer> list = Lists.mutable.with(1, 2, 3, 4); 
MutableBag<Integer> bag = Bags.mutable.with(1, 2, 3, 4);

MutableList<Integer> array = ArrayAdapter.adapt(1, 2, 3, 4);

CharAdapter string = 
    CharAdapter.adapt("The Quick Brown Fox jumps over a lazy dog.");

MutableMap<Integer, String> map := 
    Maps.mutable.with(1, "1", 2, "2", 3, "3", 4, "4").

Interval interval = Interval.oneTo(4);
```


Eclipse Collections (Eager)
---------------------------
```java
set.select(each -> each % 2 == 1);         // Set(1 3)
list.reject(each -> each % 2 == 0);        // List(1 3)
array.collect(Object::toString);           // List("1" "2" "3" "4")

bag.detect(each -> each < 2);              // Integer.valueOf(1)
map.injectInto("", (r, s) -> r + s);       // "1234"

string.anySatisfy(c -> c == 'e');          // true
set.allSatisfy(each -> each < 5);          // true
map.noneSatisfy(String.class::isInstance); // false

interval.toSet();                          // Set(1 2 3 4)
map.toBag();                               // Bag("1" "2" "3" "4")
set.toList();                              // List(1 2 3 4)
```


Eclipse Collections (Lazy)
--------------------------
```java
set.asLazy().select(each -> each % 2 == 1);    // Set(1 3)
list.asLazy().reject(each -> each % 2 == 0);   // List(1 3)
array.asLazy().collect(Object::toString);      // List("1" "2" "3" "4")

bag.asLazy().detect(each -> each < 2);         // Integer.valueOf(1)
map.asLazy().injectInto("", (r, s) -> r + s);  // "1234"

string.asLazy().anySatisfy(c -> c == 'e');          // true
set.asLazy().allSatisfy(each -> each < 5);          // true
map.asLazy().noneSatisfy(String.class::isInstance); // false

interval.asLazy().toSet();                     // Set(1 2 3 4)
map.asLazy().toBag();                          // Bag("1" "2" "3" "4")
set.asLazy().toList();                         // List(1 2 3 4)
```



Symmetric Sympathy
------------------

* Blogs about Symmetry
  * [Symmetric Sympathy](https://medium.com/@donraab/symmetric-sympathy-2c59d4541d60)
  * [As a matter of Factory - Part 1](https://medium.com/@donraab/as-a-matter-of-factory-part-1-mutable-75cc2c5d72d9)
  * [As a matter of Factory - Part 2](https://medium.com/@donraab/as-a-matter-of-factory-part-2-immutable-8cb72ff897ee)
  * [Zip Symmetry](https://medium.com/@donraab/zip-symmetry-a857a934ee26)
  * [A rose by any other name...](https://medium.com/@donraab/a-rose-by-any-other-name-e15060d4c98e)
  * [Lazy and Inexhaustible](https://medium.com/@donraab/lazy-and-inexhaustible-f41ffda857dc)
  * [Preposition Preference](https://medium.com/@donraab/preposition-preference-1f1c709b098b)
  * [By yourself some time](https://medium.com/@donraab/preposition-preference-1f1c709b098b)


#### Symmetric Sympathy - Part 1
| *API* | *Eager* | *Lazy* | *Parallel* | *Eager(p)* | *Lazy(p)* |
| -------- | ------ | ------ | -------- | --------- | --------- |
| select | Co* | Lazy | Co* | Co* | Lazy |
| reject | Co* | Lazy | Co* | Co* | Lazy |
| collect | Co* | Lazy | Co* | Co* | Lazy |
| detect | T | T | T | Primitive | Primitive |
| injectInto | R | R | ~~N/A~~ | R | R |
| any/all/none | boolean | boolean | boolean | boolean | boolean |
| toSet | mSet | mSet | mSet | m(p)Set | m(p)Set |
| toBag | mBag | mBag | mSet | m(p)Set | m(p)Set |
| toList | mList | mList | mList | m(p)List | m(p)List |


#### Symmetric Sympathy - Part 2
| *API* | *Eager* | *Lazy* | *Parallel* | *Eager(p)* | *Lazy(p)* |
| -------- | ------ | ------ | -------- | --------- | --------- |
| flatCollect | Co* | Lazy | Co* | R | Lazy |
| groupBy | Co* | Multimap | Co* | ~~N/A~~ | ~~N/A~~ |
| partition | Co* | RI | ~~N/A~~ | ~~N/A~~ | ~~N/A~~ |
| chunk | RI | RI | ~~N/A~~ | ~~N/A~~ | ~~N/A~~ |
| zip | Co* | Co* | ~~N/A~~ | Co* | ~~N/A~~ |
| makeString | String | String | String | String | String |
| appendString | void | void | void | void | void |
| count | int | int | int | int | int |
| min/max | T | T | T | (p) | (p) |



The Past
--------
* Memory efficient Small Collections (2004)
* Java 5 - Generics (2005)
* Open Source GS Collections (2012)
  * [7 Major Releases](https://github.com/goldmansachs/gs-collections/releases) 
* Java 8 - Lambdas (2014)
* Eclipse Collections (2015) 
  * [3 Major Releases](https://github.com/eclipse/eclipse-collections/releases) 


The Future
----------
* Modularization
* Improve Symmetry
  * Object / Primitive
* New Data Structures
  * More Primitive, Ordered, Trees, Persistent Structures, Off-heap
* Continued Java integration and inter-op
* Integrate with Eclipse Build Train



Appendix
========


Static Utility - [Iterate](https://www.eclipse.org/collections/javadoc/9.0.0/org/eclipse/collections/impl/utility/Iterate.html) (Eager)
--------------------------------
```java
Iterate.select(Iterable, Predicate);              // Collection
Iterate.reject(Iterable, Predicate);              // Collection
Iterate.collect(Iterable, Function);              // Collection

Iterate.detect(Iterable, Predicate);              // Object
Iterate.injectInto(Iterable, Object, Function2);  // Object

Iterate.anySatisfy(Iterable, Predicate);          // boolean
Iterate.allSatisfy(Iterable, Predicate);          // boolean
Iterate.noneSatisfy(Iterable, Predicate);         // boolean

Iterate.toArray(Iterable);                        // Object[]
Iterate.toMap(Iterable, Function, Function);      // Map
```


Static Utility - [ListIterate](https://www.eclipse.org/collections/javadoc/9.0.0/org/eclipse/collections/impl/utility/ListIterate.html) (Eager)
------------------------------------
```java
ListIterate.select(List, Predicate);              // MutableList
ListIterate.reject(List, Predicate);              // MutableList
ListIterate.collect(List, Function);              // MutableList

ListIterate.detect(List, Predicate);              // Object
ListIterate.injectInto(List, Object, Function2);  // Object

ListIterate.anySatisfy(List, Predicate);          // boolean
ListIterate.allSatisfy(List, Predicate);          // boolean
ListIterate.noneSatisfy(List, Predicate);         // boolean

ListIterate.toArray(List);                        // Object[]
ListIterate.toMap(List, Function, Function);      // Map
```


Eclipse Collections Primitive (Eager)
-------------------------------------
```java
set.select(each -> each % 2 == 1);         // IntSet(1 3)
list.reject(each -> each % 2 == 0);        // IntList(1 3)
bag.collect(Object::toString);             // Bag("1" "2" "3" "4")

string.detect(Character::isLowerCase);     // 'h'
map.injectInto("", (r, s) -> r + s);       // "1234"

string.anySatisfy(c -> c == 'e');          // true
set.allSatisfy(each -> each < 5);          // true
map.noneSatisfy(String.class::isInstance); // false

interval.toSet();                          // IntSet(1 2 3 4)
map.toBag();                               // IntBag(1 2 3 4)
set.toList();                              // IntList(1 2 3 4)
```


Eclipse Collections Primitive (Lazy)
------------------------------------
```java
set.asLazy().select(each -> each % 2 == 1);  // LazyIntIterable
list.asLazy().reject(each -> each % 2 == 0); // LazyIntIterable
bag.asLazy().collect(Object::toString);      // LazyIterable

string.asLazy().detect(Character::isLowerCase); // 'h'
map.asLazy().injectInto("", (r, s) -> r + s);   // "1234"

string.asLazy().anySatisfy(c -> c == 'e');          // true
set.asLazy().allSatisfy(each -> each < 5);          // true
map.asLazy().noneSatisfy(String.class::isInstance); // false

interval.asLazy().toSet();                    // IntSet(1 2 3 4)
map.asLazy().toBag();                         // IntBag(1 2 3 4)
set.asLazy().toList();                        // IntList(1 2 3 4)
```


Eclipse Collections (Streams-4-Free)
------------------------------------
```java
set.stream().filter(each -> each % 2 == 1);     // Stream
list.stream().filter(each -> each % 2 != 0);    // Stream
array.stream().map(Object::toString);           // Stream

bag.stream().filter(each -> each < 2).findFirst(); // Optional(1)
map.stream().reduce("", (r, s) -> r + s);          // "1234"

string.chars().anyMatch(c -> c == 'e');            // true
set.stream().allMatch(each -> each < 5);           // true
map.stream().noneMatch(String.class::isInstance);  // false

interval.stream().collect(Collectors.toSet());  // Set(1 2 3 4)
map.stream().collect(Collectors2.toBag());      // Bag(1 2 3 4)
set.stream().collect(Collectors2.toList());     // List(1 2 3 4)
```
