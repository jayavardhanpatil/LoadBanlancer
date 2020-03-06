package com.lb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by jayavardhanpatil on 03/06/20
 */

public class LoadBalancer {

    private ArrayList<String> totalServersAsPerWeightage;
    private Integer position;

    LoadBalancer(){
        position = 0;
        totalServersAsPerWeightage = new ArrayList<>();
    }

    /* Method : getAssignedServerName_RandomWeight
    *  Paramater : HashMap -> Servers and their weights;
    *  Description : Randomly distributes the jobs to the servers depending on weights,
    *                Distribution is approximately correct;
    *  return : String ServerName.
    * */

    public String getAssignedServerName_RandomWeight(HashMap<String, Integer> serversWeightMap){

        if(serversWeightMap == null || serversWeightMap.size() == 0){
            return "No Server Available to distribute";
        }

        totalServersAsPerWeightage = serveraList(serversWeightMap);
        return assigntoRandomWeightedServer(totalServersAsPerWeightage);
    }

    /* Method : getAssignedServerName_RandomWeight
     *  Paramater : HashMap -> Servers and their weights;
     *  Description : Distributes the jobs to the servers in round robbin way depending on weights,
     *                correctly distributes to all servers as per weights
     *  return : String ServerName.
     * */
    public String getAssignedServerName_RoundRobinWeight(HashMap<String, Integer> serversWeightMap){
        if(serversWeightMap == null || serversWeightMap.size() == 0){
            return "No Server Available to distribute";
        }
        totalServersAsPerWeightage = serveraList(serversWeightMap);
        return assignWeightedServerRoundRobin(totalServersAsPerWeightage);
    }


    //If Server weights are in order of 10 : 30 : 50 : 20 it can be optimized by finding GCF(Greatest Common factor) and
    // divide each weight by the GCF so that we can reduce the size of Servers being added to the list.
    private ArrayList<String> serveraList(HashMap<String, Integer> serversWeightMap){
        ArrayList<String> servers = new ArrayList<>();
        for(Map.Entry<String, Integer> data: serversWeightMap.entrySet()){
            for(int i =0;i<data.getValue();i++) {
                servers.add(data.getKey());
            }
        }
        return servers;
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

