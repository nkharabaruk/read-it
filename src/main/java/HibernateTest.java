import com.readit.entity.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;



/**
 * Created by nataliia on 27.01.16.
 */
public class HibernateTest {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Book book = new Book("author","name",1999,"description");
        book.setId("id");
        session.save(book);
        session.getTransaction().commit();
        System.out.println("OK");

    }
}

class HibernateUtil {
    private static SessionFactory sessionFactory = buildSessionFactory();
    private static SessionFactory buildSessionFactory(){
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        return new MetadataSources( registry ).buildMetadata().buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
