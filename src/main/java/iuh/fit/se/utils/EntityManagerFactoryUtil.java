package iuh.fit.se.utils;

public class EntityManagerFactoryUtil {
    private static final jakarta.persistence.EntityManagerFactory entityManageFactory;

    static {
        try {
            entityManageFactory = jakarta.persistence.Persistence.createEntityManagerFactory("user-management");
        } catch (Throwable ex) {
            System.err.println("Initial EntityManagerFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static jakarta.persistence.EntityManager getEntityManager() {
        return entityManageFactory.createEntityManager();
    }

    public static void close() {
        if (entityManageFactory.isOpen()) {
            entityManageFactory.close();
        }
    }
}
