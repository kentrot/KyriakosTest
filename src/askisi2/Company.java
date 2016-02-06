package askisi2;

// Εισαγωγή βιβλιοθηκών της java

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


/**
 *
 * @author kyriakos
 */
public class Company {
    
    private static EntityManager em;    
    
    public EntityManager getEm() {
        return em;
    }
    
    //Δημιουργία κατασκευαστή
    public Company(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("askisi2PU");
        // Δημιουργία του Entity Manager
        em = emf.createEntityManager();
    }
    private void clearDB(){
        em.getTransaction().begin();
        try {
            Query q=em.createQuery("DELETE FROM Workpermit");
            q.executeUpdate();                        
            q=em.createQuery("DELETE FROM Availableworkpermit");
            q.executeUpdate();            
            q=em.createQuery("DELETE FROM Workpermittype");
            q.executeUpdate();                                    
            q=em.createQuery("DELETE FROM Employee");
            q.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }
        
    //Δημιουργία κλάσης main
    
    public static void main(String[] args) {
        Company cmp = new Company();
        //Καθαρισμός βάσης δεδομένων
        cmp.clearDB();
        em.getTransaction().begin();
        try {
            // 1. Δημιουργία των τριών εργαζομένων
            
            //Υπάλληλος Γεώργιος ΑΝΑΣΤΑΣΙΟΥ
            Employee employee1 = new Employee(null, "Γεώργιος", "ΑΝΑΣΤΑΣΙΟΥ", 
                    "g.anastasiou@company.gr");
            em.persist(employee1);
            em.flush();
            
            //Υπάλληλος Μαρία ΠΑΠΑΔΗΜΗΤΡΙΟΥ
            Employee employee2 = new Employee(null, "Μαρία", "ΠΑΠΑΔΗΜΗΤΡΙΟΥ", 
                    "m.papadimitriou@company.gr");
            employee2.setManagerId(employee1);
            em.persist(employee2);
            em.flush();
        
            //Υπάλληλος Δημήτριος ΚΑΡΑΓΕΩΡΓΙΟΥ
            Employee employee3 = new Employee(null, "Δημήτριος", "ΚΑΡΑΓΕΩΡΓΙΟΥ", 
                    "d.karageorgiou@company.gr");
            employee3.setManagerId(employee1);
            em.persist(employee3);
            em.flush();
            
            // 2. Δημιουργία των 2 τύπων αδειών
            Workpermittype officialPermit = new Workpermittype(null, 
                    "ΚΑΝΟΝΙΚΗ ΑΔΕΙΑ");
            em.persist(officialPermit);
            em.flush();

            Workpermittype maternityPermit = new Workpermittype(null, 
                    "ΑΔΕΙΑ ΜΗΤΡΟΤΗΤΑΣ");
            em.persist(maternityPermit);
            em.flush();
            
            // 3.α Ανάθεση κανονικών αδειών στους εργαζόμενους
            
            //Κανονική Άδεια στον Γεώργιο ΑΝΑΣΤΑΣΙΟΥ
            Availableworkpermit awp1 = new Availableworkpermit(null, 25);
            awp1.setEmployeeId(employee1);
            employee1.getAvailableworkpermitList().add(awp1);
            awp1.setWorkPermitTypeId(officialPermit);
            officialPermit.getAvailableworkpermitList().add(awp1);
            em.persist(awp1);
            em.flush();
            
            //Κανονική Άδεια στην Μαρία ΠΑΠΑΔΗΜΗΤΡΙΟΥ
            Availableworkpermit awp2 = new Availableworkpermit(null, 25);
            awp2.setEmployeeId(employee2);
            employee2.getAvailableworkpermitList().add(awp2);
            awp2.setWorkPermitTypeId(officialPermit);
            officialPermit.getAvailableworkpermitList().add(awp2);
            em.persist(awp2);
            em.flush();

            //Κανονική Άδεια στον Δημήτριο ΚΑΡΑΓΕΩΡΓΙΟΥ
            Availableworkpermit awp3 = new Availableworkpermit(null, 25);
            awp3.setEmployeeId(employee3);
            employee3.getAvailableworkpermitList().add(awp3);
            awp3.setWorkPermitTypeId(officialPermit);
            officialPermit.getAvailableworkpermitList().add(awp3);
            em.persist(awp3);
            em.flush();

            // 3.β Ανάθεση μητρικής άδειας στην Μαρία ΠΑΠΑΔΗΜΗΤΡΙΟΥ
            Availableworkpermit awp4 = new Availableworkpermit(null, 58);
            awp4.setEmployeeId(employee2);
            employee2.getAvailableworkpermitList().add(awp4);
            awp4.setWorkPermitTypeId(maternityPermit);
            maternityPermit.getAvailableworkpermitList().add(awp4);
            em.persist(awp4);
            em.flush();

            // 4. Εισαγωγή κανονικών αδειών.
            Calendar calendar = Calendar.getInstance();
            calendar.clear();
            Date from, to;

            // 4.α Στον ΚΑΡΑΓΕΩΡΓΙΟΥ από 1/7/2015 έως 20/7/2015, 14 ημέρες.
            calendar.set(2015, 6, 1);
            from = calendar.getTime();
            calendar.set(2015, 6, 20);
            to = calendar.getTime();
            Workpermit wp1 = new Workpermit(null, from, to, 14);
            wp1.setEmployeeId(employee3);
            employee3.getWorkpermitList().add(wp1);
            wp1.setWorkPermitTypeId(officialPermit);
            officialPermit.getWorkpermitList().add(wp1);
            em.persist(wp1);
            em.flush();

            // 4.β Στον ΚΑΡΑΓΕΩΡΓΙΟΥ απο 5/9/2015 έως 10/9/2015, 4 ημέρες.
            calendar.set(2015, 8, 5);
            from = calendar.getTime();
            calendar.set(2015, 8, 10);
            to = calendar.getTime();
            Workpermit wp2 = new Workpermit(null, from, to, 4);
            wp2.setEmployeeId(employee3);
            employee3.getWorkpermitList().add(wp2);
            wp2.setWorkPermitTypeId(officialPermit);
            officialPermit.getWorkpermitList().add(wp2);
            em.persist(wp2);
            em.flush();

            // 4.γ Στην ΠΑΠΑΔΗΜΗΤΡΙΟΥ απο 5/7/2015 έως 18/7/2015, 10 ημέρες.
            calendar.set(2015, 6, 5);
            from = calendar.getTime();
            calendar.set(2015, 6, 18);
            to = calendar.getTime();
            Workpermit wp3 = new Workpermit(null, from, to, 10);
            wp3.setEmployeeId(employee2);
            employee2.getWorkpermitList().add(wp3);
            wp3.setWorkPermitTypeId(officialPermit);
            officialPermit.getWorkpermitList().add(wp3);
            em.persist(wp3);
            em.flush();
        
            // 4.δ Στον ΑΝΑΣΤΑΣΙΟΥ απο 25/7/2015 έως 5/8/2015, 8 ημέρες.
            calendar.set(2015, 6, 25);
            from = calendar.getTime();
            calendar.set(2015, 7, 5);
            to = calendar.getTime();
            Workpermit wp4 = new Workpermit(null, from, to, 8);
            wp4.setEmployeeId(employee1);
            employee1.getWorkpermitList().add(wp4);
            wp4.setWorkPermitTypeId(officialPermit);
            officialPermit.getWorkpermitList().add(wp4);
            em.persist(wp4);
            em.flush();
        
            // 5. Εισαγωγή αδειών μητρότητας στην ΠΑΠΑΔΗΜΗΤΡΙΟΥ
            // Από 1/7/2015 έως 20/7/2015, 14 ημέρες.
            calendar.set(2015, 6, 1);
            from = calendar.getTime();
            calendar.set(2015, 6, 20);
            to = calendar.getTime();
            Workpermit wp5 = new Workpermit(null, from, to, 14);
            wp5.setEmployeeId(employee2);
            employee2.getWorkpermitList().add(wp5);
            wp5.setWorkPermitTypeId(maternityPermit);
            maternityPermit.getWorkpermitList().add(wp5);
            em.persist(wp5);
            em.flush();
        
             // Από 2/11/2015 έως 3/11/2015, 2 ημέρες.
            calendar.set(2015, 10, 2);
            from = calendar.getTime();
            calendar.set(2015, 10, 3);
            to = calendar.getTime();
            Workpermit wp6 = new Workpermit(null, from, to, 2);
            wp6.setEmployeeId(employee2);
            employee2.getWorkpermitList().add(wp6);
            wp6.setWorkPermitTypeId(maternityPermit);
            maternityPermit.getWorkpermitList().add(wp6);
            em.persist(wp6);
            em.flush();
            
            // 6. Εμφάνιση των στοιχείων του ΑΝΑΣΤΑΣΙΟΥ
            System.out.println();
            System.out.println("Βήμα 6 εμφάνιση των στοιχείων του εργαζομένου ΑΝΑΣΤΑΣΙΟΥ");
            System.out.println();
            System.out.println("Στοιχεία Εργαζομένου");
            System.out.println("====================");
            System.out.println("Ονοματεπώνυμο: " + employee1.getLname() + " "
                    + employee1.getFname());
            System.out.println("eMail " + employee1.getEmail() );
            System.out.println();
            
            // 7. Εμφάνιση των δικαιούμενων τύπων άδειας της ΠΑΠΑΔΗΜΗΤΡΙΟΥ
            System.out.println();
            System.out.println("Βήμα 7 εμφάνιση των δικαιούμενων τύπων άδειας της ΠΑΠΑΔΗΜΗΤΡΙΟΥ");
            System.out.println();
            System.out.println("Δικαιούμενες άδειες υπαλλήλου " + 
                    employee2.getLname() + " " + employee2.getFname());
            System.out.println("============================================"
                    + "=====");
            for (Availableworkpermit awp : employee2.getAvailableworkpermitList())
            {
            System.out.println("Είδος: " + awp.getWorkPermitTypeId().getWorkPermitTypeText()
                    + ", Διάρκεια: " + awp.getAvailableDays());
            System.out.println();
        }
        
            // 8. Εμφάνιση κανονικών αδειών όλων των εργαζομένων
            //  ΑΝΑΣΤΑΣΙΟΥ Γεώργιος
            System.out.println();
            System.out.println("Βήμα 8 εμφάνιση στοιχείων των κανονικών αδειών και των τριών εργαζομένων");
            System.out.println();
            System.out.println("Κανονικές άδειες για: " + employee1.getLname() 
                    + " " + employee1.getFname());
            System.out.println("============================================="
                    + "========");            
            for (Iterator<Workpermit> it = employee1.getWorkpermitList().iterator();
                    it.hasNext();) {
            Workpermit w = it.next();
            }

            //  ΠΑΠΑΔΗΜΗΤΡΙΟΥ Μαρία
            System.out.println("Κανονικές άδειες για: " + employee2.getLname() 
                    + " " + employee2.getFname());
            System.out.println("============================================="
                    + "========");            
            for (Iterator<Workpermit> it = employee2.getWorkpermitList().iterator();
                    it.hasNext();) {
            Workpermit w = it.next();
            }
            
            // ΚΑΡΑΓΕΩΡΓΙΟΥ Δημήτριος
            System.out.println("Κανονικές άδειες για: " + employee3.getLname() 
                    + " " + employee3.getFname());
            System.out.println("============================================="
                    + "========");            
            for (Iterator<Workpermit> it = employee3.getWorkpermitList().iterator();
                    it.hasNext();) {
            Workpermit w = it.next();
            }
            // Τερματισμός της δοσοληψίας
            em.getTransaction().commit();
            // Καταστροφή του EntityManager και του EntityManagerFactory
            em.close();
            //emf.close();            
            }  catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
               }
        }
} 