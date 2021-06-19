package controller;

import model.*;
import operations.Operations;
import service.impl.ServiceImpl;

import java.time.LocalDate;
import java.util.*;

public class Starter {

    private ServiceImpl service = new ServiceImpl();

    public void runApps() {

        System.out.println("Что вам требуется сделать? \n" +
                "1. Создать клиента - 'create user' \n" +
                "2. Создать заказ - 'create order' \n" +
                "3. Удалить клиента - 'delete user' \n" +
                "4. Удалить заказ - 'delete order' \n" +
                "5. Посмотреть информацию о заказе - 'read order' \n" +
                "6. Посмотреть информацию о клиенте - 'read user' \n" +
                "7. Посмотреть информацию о номере - 'read apart' \n" +
                "8. Изменить клиента - 'update user' \n" +
                "9. Изменить заказ - 'update order' \n" +
                "10. Получить весь список клиентов - 'get all users' \n" +
                "11. Получить весь список заказов - 'get all orders' \n" +
                "12. Получить весь список номеров - 'get all aparts' \n" +
                "13. Внести изменения в бронирование - 'get chosen' \n" +
                "14. Выйти из программы - 'exit' \n");


        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {

            String next = scanner.nextLine();

            if (next.equals("create user")) {

                System.out.println("Введите имя клиента: ");
                String name = scanner.next();
                System.out.println("Фамилия: ");
                String lastName = scanner.next();
                System.out.println("Серия и номер паспорта: ");
                long passportData = scanner.nextLong();
                System.out.println("Email: ");
                String email = scanner.next();
                System.out.println("Номер телефона: ");
                long phoneNumber = scanner.nextLong();

                User user = User.builder()
                        .name(name)
                        .phoneNumber(phoneNumber)
                        .passportData(passportData)
                        .lastName(lastName)
                        .email(email)
                        .build();

                service.createUser(user);

            }

            if (next.equals("create order")) {

                 System.out.println("Введите id клиента");
                 int userId = scanner.nextInt();
                 System.out.println("Какой номер хотите забронировать? " +
                         "1. 'LUXE' " +
                         "2. 'STANDART' " +
                         "3. 'SUITE' ");
                 Comfort comfort = Comfort.valueOf(scanner.next());
                 System.out.println("Есть! На какое количество человек? " +
                         "1. 'SINGLE' " +
                         "2. 'DOUBLE' " +
                         "3. 'TRIPLE' ");
                 Accomodation accomodation = Accomodation.valueOf(scanner.next());
                 System.out.println("Введите желаемую дату заезда");
                 LocalDate dateIn = LocalDate.parse(scanner.next());
                 System.out.println("Введите желаемую дату выезда");
                 LocalDate dateOut = LocalDate.parse(scanner.next());
                 System.out.println("Номер успешно забронирован");

                 Apartment apartment = Apartment.builder()
                         .comfort(comfort)
                         .accomodation(accomodation)
                         .build();

                 Order order = Order.builder()
                         .userId(userId)
                         .dateIn(dateIn)
                         .dateOut(dateOut)
                         .build();

                 service.createOrder(order);

            }

            if (next.equals("delete user")) {

                System.out.println("Введите id клиента: ");
                int id = scanner.nextInt();
                System.out.println("Клиент удален");

                service.deleteUser(id);

            }

            if (next.equals("delete order")) {

                System.out.println("Введите id заказа: ");
                int id1 = scanner.nextInt();
                System.out.println("Заказ удален");

                service.deleteOrder(id1);

            }

            if (next.equals("read order")) {

                System.out.println("Введите id заказа");
                int id2 = scanner.nextInt();

                System.out.println(service.readOrder(id2));

            }

            if (next.equals("read user")) {

                System.out.println("Введите id клиента");
                int id3 = scanner.nextInt();

                System.out.println(service.readUser(id3));

            }

            if (next.equals("read apart")) {

                System.out.println("Введите id номера");
                int id4 = scanner.nextInt();

                System.out.println(service.readApart(id4));

            }

            if (next.equals("update user")) {

                System.out.println("Введите id клиента: ");
                int id5 = scanner.nextInt();
                System.out.println("Введите имя клиента: ");
                String name1 = scanner.next();
                System.out.println("Фамилия: ");
                String lastName1 = scanner.next();
                System.out.println("Паспортные данные: ");
                Long passportData1 = scanner.nextLong();
                System.out.println("Email: ");
                String email1 = scanner.next();
                System.out.println("Номер телефона: ");
                Long phoneNumber1 = scanner.nextLong();
                System.out.println("Клиент обновлен.");

                User user1 = User.builder()
                        .userId(id5)
                        .name(name1)
                        .lastName(lastName1)
                        .passportData(passportData1)
                        .email(email1)
                        .phoneNumber(phoneNumber1)
                        .build();

                service.updateUser(user1);
                System.out.println("Юзер обновлен");

            }

            if (next.equals("update order")) {

                System.out.println("Введите id заказа: ");
                int id6 = scanner.nextInt();
                System.out.println("Введите id клиента: ");
                int userId1 = scanner.nextInt();
                System.out.println("Введите id номера: ");
                int apartId = scanner.nextInt();
                System.out.println("Планируемая дата заезда: ");
                LocalDate dateIn1 = LocalDate.parse(scanner.next());
                System.out.println("Планируемая дата выезда: ");
                LocalDate dateOut1 = LocalDate.parse(scanner.next());


                Order order1 = Order.builder()
                        .id(id6)
                        .userId(userId1)
                        .aptId(apartId)
                        .dateIn(dateIn1)
                        .dateOut(dateOut1)
                        .build();

                System.out.println(service.updateOrder(order1));
                System.out.println("Заказ обновлен");

            }

            if (next.equals("get all users")) {

                System.out.println("Полный список клиентов");
                System.out.println(service.getAllUser());

            }

            if (next.equals("get all orders")) {

                System.out.println("Полный список заказов ");
                System.out.println(service.getAllOrders());

            }

            if (next.equals("get all aparts")) {

                System.out.println("Полный список номеров ");
                System.out.println(service.getAllApartment());

            }

            if (next.equals("get chosen")) {

                System.out.println("Введите id заказов");

                while (scanner.hasNext()) {

                    int id7 = scanner.nextInt();

                    System.out.println(service.getChosenOrders(id7));
                }

            }

            if (next.equals("exit")) {

                System.out.println("До свидания");
                break;

            }

            }
        }
    }



