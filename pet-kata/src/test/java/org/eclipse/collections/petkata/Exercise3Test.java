/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.petkata;

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.Maps;
import org.eclipse.collections.impl.factory.Sets;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

public class Exercise3Test extends PetDomainForKata
{
    @Test
    public void getCountsByPetType()
    {
        MutableList<PetType> petTypes = this.people.flatCollect(Person::getPets).collect(Pet::getType);
        // Try to replace MutableMap<PetType, Integer> with a Bag<PetType>
        MutableMap<PetType, Integer> petTypeCounts = Maps.mutable.empty();
        for (PetType petType : petTypes)
        {
            Integer count = petTypeCounts.get(petType);
            if (count == null)
            {
                count = 0;
            }
            petTypeCounts.put(petType, count + 1);
        }

        Assert.assertEquals(Integer.valueOf(2), petTypeCounts.get(PetType.CAT));
        Assert.assertEquals(Integer.valueOf(2), petTypeCounts.get(PetType.DOG));
        Assert.assertEquals(Integer.valueOf(2), petTypeCounts.get(PetType.HAMSTER));
        Assert.assertEquals(Integer.valueOf(1), petTypeCounts.get(PetType.SNAKE));
        Assert.assertEquals(Integer.valueOf(1), petTypeCounts.get(PetType.TURTLE));
        Assert.assertEquals(Integer.valueOf(1), petTypeCounts.get(PetType.BIRD));

        Assert.fail("Optimize this test by using a Bag with variable name 'counts'");
        /*
        Assert.assertEquals(2, counts.occurrencesOf(PetType.CAT));
        Assert.assertEquals(2, counts.occurrencesOf(PetType.DOG));
        Assert.assertEquals(2, counts.occurrencesOf(PetType.HAMSTER));
        Assert.assertEquals(1, counts.occurrencesOf(PetType.SNAKE));
        Assert.assertEquals(1, counts.occurrencesOf(PetType.TURTLE));
        Assert.assertEquals(1, counts.occurrencesOf(PetType.BIRD));
        */
    }

    @Test
    public void getPeopleByLastName()
    {
        // Try to replace MutableMap<String, MutableList<Person> with a Multimap
        // Hint: use the groupBy method
        MutableMap<String, MutableList<Person>> lastNamesToPeople = Maps.mutable.empty();
        for (Person person : this.people)
        {
            String lastName = person.getLastName();
            MutableList<Person> peopleWithLastName = lastNamesToPeople.get(lastName);
            if (peopleWithLastName == null)
            {
                peopleWithLastName = Lists.mutable.empty();
                lastNamesToPeople.put(lastName, peopleWithLastName);
            }
            peopleWithLastName.add(person);
        }
        Verify.assertIterableSize(3, lastNamesToPeople.get("Smith"));
        Assert.fail("Optimize this test by using a Multimap");

        //replace assertion with the below
        //Verify.assertIterableSize(3, byLastNameMultimap.get("Smith"));
    }

    @Test
    public void getPeopleByTheirPets()
    {
        // Hint: Use a target collection to go from a List to MutableSetMultimap<PetType, Person>
        MutableMap<PetType, MutableSet<Person>> peopleByPetType = Maps.mutable.empty();

        for (Person person : this.people)
        {
            MutableList<Pet> pets = person.getPets();
            for (Pet pet : pets)
            {
                PetType petType = pet.getType();
                MutableSet<Person> peopleWithPetType = peopleByPetType.get(petType);
                if (peopleWithPetType == null)
                {
                    peopleWithPetType = Sets.mutable.empty();
                    peopleByPetType.put(petType, peopleWithPetType);
                }
                peopleWithPetType.add(person);
            }
        }

        Verify.assertIterableSize(2, peopleByPetType.get(PetType.CAT));
        Verify.assertIterableSize(2, peopleByPetType.get(PetType.DOG));
        Verify.assertIterableSize(1, peopleByPetType.get(PetType.HAMSTER));
        Verify.assertIterableSize(1, peopleByPetType.get(PetType.TURTLE));
        Verify.assertIterableSize(1, peopleByPetType.get(PetType.BIRD));
        Verify.assertIterableSize(1, peopleByPetType.get(PetType.SNAKE));

        Assert.fail("Optimize this test by using a Multimap");
    }
}
