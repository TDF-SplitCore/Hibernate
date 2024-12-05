package org.example;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.example.converter.BirthDayConverter;
import org.example.model.Birthday;
import org.example.model.Role;
import org.example.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.SQLException;
import java.time.LocalDate;

public class HibernateRunner {

    public static void main(String[] args) throws SQLException {
        Configuration configuration = new Configuration();
        configuration.addAttributeConverter(new BirthDayConverter(), true);
        configuration.registerTypeOverride(new JsonBinaryType());
        configuration.configure();
        try(
                SessionFactory sessionFactory = configuration.buildSessionFactory();
                Session session = sessionFactory.openSession()
        ){
            session.beginTransaction();
/*            User user = new User(
                    "user1",
                    "user",
                    "user",
                    Role.ADMIN,
                    new Birthday(LocalDate.of(2000,1,1)),
                    """
                            {
                                "name" : "ivan",
                                "id" : 25
                            }
                            """
                    );
            session.save(user);*/
            User user1 = session.get(User.class, "user1");
            System.out.println(user1);
            session.getTransaction().commit();
            System.out.println("OK");
        };
    }
}
