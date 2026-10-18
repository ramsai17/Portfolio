package com.ramasai.portfolio;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
public class ProfileController {
    @GetMapping("/api/profile")
    public Map<String, Object> profile() {
        return Map.of(
            "name", "Rama Sai Pampana",
            "role", "DevOps Engineer",
            "mobile", "+91 6304390224",
            "email", "ramasaidevops333@gmail.com",
            "location", "Hyderabad, Telangana, India"
        );
    }

    @GetMapping("/api/health")
    public Map<String, String> health() {
        return Map.of("status", "UP", "application", "devops-portfolio");
    }

    @GetMapping("/api/skills")
    public List<Map<String, String>> skills() {
        return List.of(
            Map.of("category", "Operating Systems", "items", "Linux, Windows"),
            Map.of("category", "Version Control", "items", "Git, GitHub"),
            Map.of("category", "Build Tools", "items", "Maven"),
            Map.of("category", "CI/CD Tools", "items", "Jenkins"),
            Map.of("category", "Containerization", "items", "Docker"),
            Map.of("category", "Code Quality", "items", "SonarQube"),
            Map.of("category", "Cloud Platforms", "items", "AWS"),
            Map.of("category", "Orchestration", "items", "Kubernetes, OpenShift, Helm, Argo CD"),
            Map.of("category", "Issue Tracking & ITSM", "items", "Jira, ServiceNow"),
            Map.of("category", "Monitoring", "items", "ELK Kibana, Grafana, CloudWatch"),
            Map.of("category", "Languages", "items", "Prolog")
        );
    }
}
