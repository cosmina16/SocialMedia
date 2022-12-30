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

    public void sendRequest(int sendingUser,int receivingUser){
        this.friendshipRepoGeneric.create(new Friendship(sendingUser, receivingUser, LocalDate.now(), StatusFriendship.Pending));
    }

    public void acceptRequest(int receivingUser,int sendingUser){
        for(Friendship fr:this.friendshipRepoGeneric.getAll())
            if(fr.getIdUser1() == sendingUser &&  fr.getIdUser2() == receivingUser){
                fr.setStatus(StatusFriendship.Active);
                fr.setData(LocalDate.now());
                friendshipRepoGeneric.update(fr.getId(),fr);
                return;
            }
        System.out.println("aici");
    }

    public void declineRequst(int receivingUser,int sendingUser){
        for(Friendship fr:this.friendshipRepoGeneric.getAll())
            if(fr.getIdUser1() == sendingUser &&  fr.getIdUser2() == receivingUser){
                fr.setStatus(StatusFriendship.Blocked);
                friendshipRepoGeneric.update(fr.getId(),fr);
                return;
            }
    }

    public Collection<Friendship> getAllFriendships(){
        return friendshipRepoGeneric.getAll();
    }

    public void deleteFriendshipByID(int idFriendship){
        friendshipRepoGeneric.delete(idFriendship);
    }

    public void deleteFriendship(int idUser1,int idUser2){
        for(Friendship fr:friendshipRepoGeneric.getAll()){
            if(Math.min(fr.getIdUser1(), fr.getIdUser2()) == Math.min(idUser1,idUser2) && Math.max(fr.getIdUser1(), fr.getIdUser2()) == Math.max(idUser1,idUser2) )
            {
                friendshipRepoGeneric.delete(fr.getId());
                return;
            }
        }
    }

    public Collection<String> getAllFriends(int idUser){
        ArrayList<String> listaPrieteni = new ArrayList<>();
        for (Friendship friendship:friendshipRepoGeneric.getAll()){
            if(!friendship.getStatus().equals(StatusFriendship.Active))
                continue;

            if (friendship.getIdUser1() == idUser)
            {
                User u = su.getUser(friendship.getIdUser2());
                listaPrieteni.add(u.getName());
            }
            else if (friendship.getIdUser2() == idUser)
            {
                User u = su.getUser(friendship.getIdUser1());
                listaPrieteni.add(u.getName());
            }
        }
        return listaPrieteni;
    }

    public Friendship getFriendship(int id1,int id2) {
        for (Friendship fr : friendshipRepoGeneric.getAll()) {
            if (Math.min(fr.getIdUser1(), fr.getIdUser2()) == Math.min(id1, id2) && Math.max(fr.getIdUser1(), fr.getIdUser2()) == Math.max(id1, id2)) {
                return fr;
            }
        }
        return null;
    }

}
