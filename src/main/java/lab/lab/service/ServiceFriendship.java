package lab.lab.service;

import lab.lab.domain.Friendship;
import lab.lab.domain.StatusFriendship;
import lab.lab.domain.User;
import lab.lab.repo.RepoGeneric;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

public class ServiceFriendship  {
    RepoGeneric<Friendship> friendshipRepoGeneric;
    ServiceUser su;

    public ServiceFriendship(RepoGeneric<Friendship> friendshipRepoGeneric, ServiceUser su) {
        this.friendshipRepoGeneric = friendshipRepoGeneric;
        this.su = su;
    }

    void sendRequest(int idUser1,int idUser2){
        this.friendshipRepoGeneric.create(new Friendship(idUser1, idUser2, LocalDate.now(), StatusFriendship.Pending));
    }

    void acceptRequest(int idUser1,int idUser2){
        for(Friendship fr:this.friendshipRepoGeneric.getAll())
            if(fr.getIdUser1() == Math.min(idUser1, idUser2) &&  fr.getIdUser2() == Math.max(idUser1, idUser2)) {
                fr.setStatus(StatusFriendship.Active);
                fr.setData(LocalDate.now());
                friendshipRepoGeneric.update(fr.getId(),fr);
                return;
            }
    }

    void declineRequst(int idUser1,int idUser2){
        for(Friendship fr:this.friendshipRepoGeneric.getAll())
            if(fr.getIdUser1() == Math.min(idUser1,idUser2) && fr.getIdUser2() == Math.max(idUser1,idUser2)){
                fr.setStatus(StatusFriendship.Blocked);
                friendshipRepoGeneric.update(fr.getId(),fr);
                return;
            }
    }

    Collection<Friendship> getAllFriendships(){
        return friendshipRepoGeneric.getAll();
    }

    void deleteFriendshipByID(int idFriendship){
        friendshipRepoGeneric.delete(idFriendship);
    }

    void deleteFriendship(int idUser1,int idUser2){
        for(Friendship fr:friendshipRepoGeneric.getAll()){
            if(fr.getIdUser1() == Math.min(idUser1,idUser2) && fr.getIdUser2() == Math.max(idUser1,idUser2) )
            {
                friendshipRepoGeneric.delete(fr.getId());
                return;
            }
        }
    }

    Collection<String> getAllFriends(int idUser){
        ArrayList<String> listaPrieteni = new ArrayList<>();
        for (Friendship friendship:friendshipRepoGeneric.getAll()){

            if (friendship.getIdUser1() == idUser)
            {
                User u = su.getUser(friendship.getIdUser2());
                listaPrieteni.add(u.getName());
            }
            if (friendship.getIdUser2() == idUser)
            {
                User u = su.getUser(friendship.getIdUser1());
                listaPrieteni.add(u.getName());
            }
        }
        return listaPrieteni;
    }
}
