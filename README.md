# Rama Sai Pampana — DevOps Portfolio

A responsive portfolio website built with HTML/CSS/JavaScript and a Spring Boot REST API. Maven packages the application; Docker creates the runtime image; Jenkins builds/tests the app and optionally publishes the image; Kubernetes manifests deploy it.

## Requirements
- Java 17+
- Maven 3.9+ (or use the included Docker multi-stage build)
- Docker for container build
- Kubernetes cluster only for deployment

## Run locally
```bash
mvn spring-boot:run
```
Open http://localhost:8080. API endpoints:
- `GET /api/profile`
- `GET /api/skills`
- `GET /api/health`
- `GET /actuator/health`

Build and test:
```bash
mvn clean verify
```

## Profile photo
The provided photo is included as `src/main/resources/static/profile.jpeg`. Replace that file to update the displayed photo.

## Docker
```bash
docker build -t devops-portfolio:local .
docker run --rm -p 8080:8080 devops-portfolio:local
```
Open http://localhost:8080.

## Jenkins setup
1. Create a Pipeline job connected to this Git repository.
2. Configure Jenkins tools named exactly `Maven3` and `JDK17`, or update the Jenkinsfile names to match your tool configuration.
3. Install/configure Docker CLI and Docker daemon access on the agent.
4. Add a Jenkins username/password credential with ID `dockerhub-credentials` for Docker Hub.
5. Set `REGISTRY` or image naming to match your registry. The pipeline publishes only on `main`/`master`.

**Security note:** Do not put credentials in source control. Restrict Docker socket access on Jenkins agents.

## Kubernetes
1. Update `k8s/deployment.yaml` image to your published image.
2. Deploy:
```bash
kubectl apply -f k8s/deployment.yaml
kubectl get pods,svc
kubectl port-forward svc/devops-portfolio 8081:80
```
Open http://localhost:8081. For external access, configure your cluster's ingress controller and update `k8s/ingress.yaml` host.

## Project structure
```text
pom.xml
Jenkinsfile
Dockerfile
src/main/java/com/ramasai/portfolio/
  PortfolioApplication.java
  ProfileController.java
src/main/resources/
  application.properties
  static/
    index.html
    styles.css
    app.js
    profile.jpeg
k8s/
  deployment.yaml
  ingress.yaml
```

## Before publishing
- Verify personal information and dates.
- Consider removing the full residential address and date of birth from a public portfolio for privacy.
- Confirm all employer/client details and performance metrics are approved for public disclosure.
- Set the actual Docker Hub username, Jenkins credentials, Kubernetes image and domain.
