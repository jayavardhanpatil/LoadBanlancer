package com.lb;

import java.util.ArrayList;
import java.util.Random;

public class DistributeLoad {

    private Integer position;
    DistributeLoad(){
        position = 0;
    }

    /* Method : assigntoRandomWeightedServer
     *  Paramater : ArrayList -> List of Servers;
     *  Description : Randomly distributes the jobs to the servers depending on weights,
     *                Distribution is approximately correct;
     *  return : String ServerName.
     * */
    public String assigntoRandomWeightedServer(ArrayList<String> totalServersAsPerWeightage){
        Random random = new Random();
        return totalServersAsPerWeightage.get(random.nextInt(totalServersAsPerWeightage.size()));
    }


    /* Method : assignWeightedServerRoundRobin
     *  Paramater : ArrayList -> List of Servers;
     *  Description : Distributes the jobs to the servers in round robbin way depending on weights,
     *                correctly distributes to all servers as per weights
     *  return : String ServerName.
     * */
    public String assignWeightedServerRoundRobin(ArrayList<String> totalServersAsPerWeightage){
        synchronized (position) {
            if (position > totalServersAsPerWeightage.size()-1) {
                position = 0;
            }
            String target = totalServersAsPerWeightage.get(position);
            position++;
            return target;
        }
    }

}
