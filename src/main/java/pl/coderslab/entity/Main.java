package pl.coderslab.entity;

import java.util.Arrays;

public class Main {

    public static final String createTable = " CREATE TABLE users(id int(11) not null auto_increment, email varchar(255) not null unique ,\n" +
            "username varchar(255) not null ,password varchar(60) not null, PRIMARY KEY (id)); ";


    public static void main(String[] args) {
        User user = new User("bartek1@gmail.com","bartek","bartekhaslo");
        UserDao userDao = new UserDao();
        //System.out.println("Dodawanie uzytkownika 1");
       // userDao.create(user);

        User user1 = new User("asia@gmail.com","asia","asia haslo");
       // userDao.create(user1);

        User user2 = new User("wojtek@gmail.com","wojtek","wojtekmail");
        //userDao.create(user2);

        User user3 = new User("dousuniecia","dousuniecia","dousuniecia");
        //userDao.create(user3);

        System.out.println("Metoda read()");
        userDao.read(2);

        System.out.println("Metoda update()");
        User update = userDao.read(2);
        update.setUserName("proba metody update");
        userDao.update(update);

        System.out.println();
        System.out.println("Metoda delete");
        userDao.delete(4);

        System.out.println();
        System.out.println("Metoda findALl()");


        User[] users = userDao.findAll();
        for (User currentUser : users){
            System.out.println("ID: "+currentUser.getId()+"\nUsername: "+currentUser.getUserName()+"\nPassword: "+currentUser.getPassword()+"\nEmail: "+ currentUser.getEmail());
        }



    }
}
