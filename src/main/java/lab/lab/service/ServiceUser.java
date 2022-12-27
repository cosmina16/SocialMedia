package lab.lab.service;

import lab.lab.domain.Friendship;
import lab.lab.domain.StatusFriendship;
import lab.lab.domain.User;
import lab.lab.repo.RepoGeneric;
import lab.lab.repo.RepoMemory;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

public class ServiceUser {
    private RepoGeneric<User> repoUser ;
    private ServiceFriendship sf;

    private User userCurent= null;

    public User getUserCurent() {
        return userCurent;
    }

    public void setUserCurent(User userCurent) {
        this.userCurent = userCurent;
    }

    public ServiceUser(RepoGeneric<User> repoUser, ServiceFriendship sf) {
        this.repoUser = repoUser;
        this.sf = sf;
    }

    public void createUser(String nume,int varsta,String password ){
        User u = new User(nume,varsta,String.valueOf(password.hashCode()));
        repoUser.create(u);
    }

    public void updateUser(int id, String nume, int varsta, String password){
        User u = new User(nume,varsta,String.valueOf(password.hashCode()));
        repoUser.update(id, u);
    }

    public Collection<User> getAllUsers(){
        return repoUser.getAll();
    }

    public User getUser(int id){
        return repoUser.get(id);
    }

    public void deleteUser(int id){
        ArrayList<Integer> lst = new ArrayList<>();

        for(Friendship friendship : sf.getAllFriendships())
            if(friendship.getIdUser2() == id || friendship.getIdUser1() == id)
                lst.add(friendship.getId());

        for(Integer idDelete : lst)
            sf.deleteFriendshipByID(idDelete);

        repoUser.delete(id);
    }

    public Collection<String> getAllFriends(int idUser){
        return sf.getAllFriends(idUser);

    }
}
