/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package trygetprivatfieldanother;

import java.util.ArrayList;

/**
 *
 * @author POURRI
 */
public class TryGetPrivatFieldAnother {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Entity me = new Entity("my Live");
        me.addFriend(new Entity("his Live"));
        System.out.println(me.isCanKhowFriendPrivateLive());
    }
    
}

class Entity {
    
    private String privateLive;
    
    private ArrayList<Entity> friendList = new ArrayList<>();

    public Entity(String live) {
        privateLive = live;
    }
    
    public void addFriend(Entity friend) {
        friendList.add(friend);
    }
    
    public boolean isCanKhowFriendPrivateLive() {
        boolean isCanKhowFriendPrivateLive = false; 
        try {
            for (Entity entity : friendList) {
                System.out.println(entity.privateLive);
            }
            isCanKhowFriendPrivateLive = true;
        } catch (Exception e) {}
        
        return isCanKhowFriendPrivateLive;
    }
    
}