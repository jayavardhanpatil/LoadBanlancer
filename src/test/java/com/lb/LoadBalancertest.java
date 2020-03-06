package com.lb;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

/**
 * Created by jayavardhanpatil on 03/06/20
 */

public class LoadBalancertest {

    private HashMap<String, Integer> map;
    private LoadBalancer loadBalancer;
    @Before
    public void setUp(){
        map = new HashMap<>();
        map.put("A", 3);
        map.put("B", 3);
        map.put("C", 4);
        map.put("D", 4);
        map.put("E", 1);
        loadBalancer = new LoadBalancer();
    }

    @Test
    public void getRandomAssignedServer(){
        System.out.println("Server : " + loadBalancer.getAssignedServerName_RandomWeight(map));
    }

    @Test
    public void getRoundRobinAssignedServer(){
        System.out.println("Server : " + loadBalancer.getAssignedServerName_RoundRobinWeight(map));
    }

    //test Random assigned server for the load of 1000 jobs
    @Test
    public void getRandomAssignedServersForAgivenLoad(){
        for(int i=0;i<1000;i++) {
            System.out.println("Server : " + loadBalancer.getAssignedServerName_RandomWeight(map));
        }
    }


    //test Round robin assigned servers on the load of 1000 jobs
    @Test
    public void getRoundrobinAssignedServersForAgivenLoad(){
        for(int i=0;i<1000;i++) {
            System.out.println("Server : " + loadBalancer.getAssignedServerName_RoundRobinWeight(map));
        }
    }

    @Test()
    public void getRandomServers(){
        HashMap<String, Integer> serversWeights = new HashMap<>();
        serversWeights.put("X", 1);
        serversWeights.put("Y", 3);
        for(int i=0;i<1000;i++) {
            System.out.println("Server : " + loadBalancer.getAssignedServerName_RandomWeight(serversWeights));
        }
    }

    @Test()
    public void test_Null_servers(){
        HashMap<String, Integer> serversWeights = null;
        Assert.assertEquals("No Server Available to distribute", loadBalancer.getAssignedServerName_RandomWeight(serversWeights));
    }
}
