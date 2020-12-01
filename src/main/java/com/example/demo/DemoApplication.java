package com.example.demo;

import com.example.demo.server.repositories.MedicationPRepository;
import com.example.demo.server.service.MedicationPlanImpl;
import com.example.demo.server.service.MedicationPlanInterface;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.remoting.rmi.RmiServiceExporter;

import java.util.TimeZone;

@SpringBootApplication(scanBasePackages={
       "com.example.demo.server"
})
@EnableJpaRepositories(basePackageClasses = MedicationPRepository.class)
public class DemoApplication  {

    @Value("${server.port}")
    private Integer userBucketPath;

    @Bean
    MedicationPlanInterface planService() {
        return new MedicationPlanImpl();
    }

    @Bean
    RmiServiceExporter exporter(MedicationPlanInterface implementation) {

        Class<MedicationPlanInterface> serviceInterface = MedicationPlanInterface.class;
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setServiceInterface(serviceInterface);
        exporter.setService(implementation);
        exporter.setServiceName(serviceInterface.getSimpleName());
        exporter.setRegistryPort(userBucketPath);
        System.out.println(userBucketPath);
        return exporter;
    }

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplication.run(DemoApplication.class, args);
    }

}




