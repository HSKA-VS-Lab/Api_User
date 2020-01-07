package de.hska.iwi.vslab.Api_User.ConsumingREST;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;


public class UrlBuilder {

    private String baseUrl_core_user;
    private String baseUrl_comp_user_role;

    String getBaseUrl_core_user(){
        return baseUrl_core_user;
    }
    String getBaseUrl_comp_user_role(){
        return baseUrl_comp_user_role;
    }

    String getUserUrl(){return baseUrl_core_user+"/user";};
    String getRegisterUrl(){return baseUrl_comp_user_role + "/comp_user_role/user";}

    public UrlBuilder(){
        LoadBalancerClient loadBalancer = BeanUtil.getBean(LoadBalancerClient.class);
        ServiceInstance si_core_user = loadBalancer.choose("core_user");
        ServiceInstance si_comp_user_role = loadBalancer.choose("comp_user_role");
        this.baseUrl_core_user =  si_core_user.getUri().toString();
        this.baseUrl_comp_user_role = si_comp_user_role.getUri().toString();
    }

    String getInputUrl(String input){
        return baseUrl_core_user+"/user/"+input;
    }

    String getSlashURL_core(){
        return baseUrl_core_user+"/";
    }

    String getSlashURL_comp(){
        return baseUrl_comp_user_role+"/";
    }

    String getUrlWithId_core(int id){
        return getUserUrl()+"/"+id;
    }
    String getUrlWithId_comp(int id){
        return baseUrl_comp_user_role+"/"+id;
    }

}
