package com.steve.entity;

import com.steve.entity.Customers;
import com.steve.entity.Packagedetails;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-04-23T15:42:23")
@StaticMetamodel(Servicessubscriptions.class)
public class Servicessubscriptions_ { 

    public static volatile SingularAttribute<Servicessubscriptions, Date> subscribedate;
    public static volatile SingularAttribute<Servicessubscriptions, Packagedetails> packagedetailPackagedetailid;
    public static volatile CollectionAttribute<Servicessubscriptions, Customers> customersCollection;
    public static volatile SingularAttribute<Servicessubscriptions, Integer> servicesubscribeid;

}