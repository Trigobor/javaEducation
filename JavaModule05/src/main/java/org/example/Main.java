package org.website;

import jakarta.persistence.EntityManager;
import org.website.models.Role;
import org.website.models.User;
import org.website.services.RolesService;
import org.website.services.UsersService;
import org.website.utils.DataBaseWatcher;

import java.util.Set;

public class Main {
    public static void main(String[] args) {

        //Test test = new Test(null, "basdf");
        //session.persist(test);

        /*User broodmother = new User(null,"broodmother");
        Role woodman = new Role(null,"woodman");

        broodmother.addRole(woodman);
        session.persist(broodmother);
        session.persist(woodman);

        transaction.commit();
        session.close();*/

        /*RolesService rolesService = new RolesService();
        UsersService usersService = new UsersService();

        User techies = usersService.createUser("techies");

        Role halfSup = rolesService.createRole("half support");
        Role fullSup = rolesService.createRole("full support");
        Role lateCarry = rolesService.createRole("late carry");
        Role samiCarry = rolesService.createRole("sami carry");
        Role ganker = rolesService.createRole("ganker");
        Role pusher = rolesService.createRole("pusher");

        usersService.addRoleToUser(techies, halfSup);
        usersService.addRoleToUser(techies, fullSup);

        User windranger = usersService.createUser("windranger");
        User rubuck = usersService.createUser("rubuck");
        User legionCommander = usersService.createUser("legion commander");
        User nagaSiren = usersService.createUser("naga siren");
        User medusa = usersService.createUser("medusa");

        usersService.addRoleToUser(windranger, halfSup);
        usersService.addRoleToUser(windranger, samiCarry);
        usersService.addRoleToUser(windranger, ganker);
        usersService.addRoleToUser(windranger, pusher);

        usersService.updateUser(windranger);
        usersService.updateUser(techies);

        Set<Role> roles = usersService.getRolesByUser(windranger);
        for (Role role : roles){
            System.out.println("Вот имя роли " + role.getName());
        }

        rolesService.deleteRole(pusher);*/

        EntityManager entityManager = DataBaseWatcher.getWatcher().getEntityManagerFactory().createEntityManager();
        EntityManager entityManager1 = DataBaseWatcher.getWatcher().getEntityManagerFactory().createEntityManager();
        EntityManager entityManager2 = DataBaseWatcher.getWatcher().getEntityManagerFactory().createEntityManager();
        EntityManager entityManager3 = DataBaseWatcher.getWatcher().getEntityManagerFactory().createEntityManager();
        EntityManager entityManager4 = DataBaseWatcher.getWatcher().getEntityManagerFactory().createEntityManager();
        EntityManager entityManager5 = DataBaseWatcher.getWatcher().getEntityManagerFactory().createEntityManager();
        EntityManager entityManager6 = DataBaseWatcher.getWatcher().getEntityManagerFactory().createEntityManager();
        EntityManager entityManager7 = DataBaseWatcher.getWatcher().getEntityManagerFactory().createEntityManager();
        EntityManager entityManager8 = DataBaseWatcher.getWatcher().getEntityManagerFactory().createEntityManager();
        EntityManager entityManager9 = DataBaseWatcher.getWatcher().getEntityManagerFactory().createEntityManager();

    }
}