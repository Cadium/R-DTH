package com.steve.entity;

import com.steve.entity.Servicessubscriptions;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-04-23T15:42:23")
@StaticMetamodel(Customers.class)
public class Customers_ { 

    public static volatile SingularAttribute<Customers, String> password;
    public static volatile SingularAttribute<Customers, Servicessubscriptions> serviceSubscribeId;
    public static volatile SingularAttribute<Customers, Integer> customerid;
    public static volatile SingularAttribute<Customers, String> emailaddress;
    public static volatile SingularAttribute<Customers, String> cardnumber;
    public static volatile SingularAttribute<Customers, String> customername;

}