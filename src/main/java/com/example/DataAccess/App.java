package com.example.DataAccess;

import com.example.DataAccess.POGO.Person;
import com.example.DataAccess.daoClasses.PersonDao;

public class App 
{
    public static void main( String[] args ) throws ClassNotFoundException
    {
        PersonDao personDao = new PersonDao();
        Person person = personDao.getById((long) 1);
        System.out.println(person.getId()+ ". "+person.getFirstName()+" "+person.getLastName() + ", age: " + person.getAge());
      
    }
}
