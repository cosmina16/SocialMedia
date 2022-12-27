package lab.lab.controller;

import lab.lab.service.ServiceFriendship;
import lab.lab.service.ServiceUser;

public abstract class AbstractController {
    protected ServiceUser su;
    protected ServiceFriendship sf;

    public void setSu(ServiceUser su) {
        this.su = su;
    }

    public void setSf(ServiceFriendship sf) {
        this.sf = sf;
    }

}
