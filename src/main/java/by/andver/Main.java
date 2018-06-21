package by.andver;

import by.andver.interfaces.UserDAO;
import by.andver.objects.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
//        User user1=new User("Полесьежилстрой","г. Брест, ул. Кижеватова, д. 60","80162456987", null);
//        User user2=new User("Брестпроект","г. Брест, ул. Пушкинская, д. 16","80162569879", null);
//
//        User user3=new User("УКС г.Бреста","г. Брест, ул. Гоголя, д. 9","80162356547", null);
//
//        Project project1=new Project("Десткий сад на 350 мест в г. Кобрин",user3,100000,2,new Date());
//
//        List<Participant> participantList=new LinkedList<Participant>();
//
//        Tender tender=new Tender(project1,null, new Date(),true, null);
//
//        Participant participant1=new Participant(user1,tender,90000);
//        Participant participant2=new Participant(user2,tender,95000);
//
//        participantList.add(participant1);
//        participantList.add(participant2);
//
//        tender.setParticipantList(participantList);
//
//        System.out.println(user1);
//        System.out.println(user2);
//        System.out.println(user3);
//        System.out.println(project1);
//        System.out.println("--------------");
//        System.out.println(participant1);
//        System.out.println(participant2);
//        System.out.println(tender);


        ApplicationContext context=new ClassPathXmlApplicationContext("file:web/WEB-INF/applicationContext.xml");
        UserDAO userDAO=(UserDAO) context.getBean("userDAOImpl") ;
        User user1=new User("Полесьежилстрой","г. Брест, ул. Кижеватова, д. 60","80162456987", "asasas");
        userDAO.saveUser(user1);

        System.out.println(userDAO.findById(1));


    }
}
