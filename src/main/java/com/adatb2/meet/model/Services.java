package com.adatb2.meet.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Services")
public class Services {

    @Id
    @Column(name = "service_id")
    private String serviceId;

    @Column(name = "service_name")
    private String serviceName;

    @ManyToMany(mappedBy = "servicesList")
    private List<Escort> escorts;

    public Services() {
    }

    public Services(String serviceId, String serviceName) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public List<Escort> getEscorts() {
        return escorts;
    }

    public void setEscorts(List<Escort> escorts) {
        this.escorts = escorts;
    }

    @Override
    public String toString() {
        return "Services{" +
                "serviceId='" + serviceId + '\'' +
                ", serviceName='" + serviceName + '\'' +
                '}';
    }
}
