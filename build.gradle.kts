plugins {
	java
	id("org.springframework.boot") version "3.5.10"
	id("io.spring.dependency-management") version "1.1.7"
	id ("org.openapi.generator") version "7.17.0"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
description = "Demo project for Spring Boot"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

extra["springCloudVersion"] = "2025.0.1"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-amqp")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	//implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
	compileOnly("org.projectlombok:lombok")
	runtimeOnly("org.postgresql:postgresql")
	annotationProcessor("org.projectlombok:lombok")
	implementation("org.mapstruct:mapstruct:1.6.3")
	annotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.15")
}

openApiGenerate {
	generatorName.set("spring")
	inputSpec.set("$rootDir/src/main/resources/openapi.yaml")
	outputDir.set("$buildDir/generated/sources/openapi")
	apiPackage.set("com.example.catalog_service.openapi.api")
	modelPackage.set("com.example.catalog_service.openapi.model")
	configOptions = buildMap {
		put("useSpringBoot3", "true")
		put("interfaceOnly", "true")
		put("requestMappingMode", "api_interface")
		put("skipDefaultInterface", "true")
		put("dateLibrary", "java8")
		put("documentationProvider", "none")
		put("annotationLibrary", "none")
		put("openApiNullable", "false")
	}
}

tasks.compileJava {
	dependsOn(tasks.openApiGenerate)
}

sourceSets {
	main {
		java {
			srcDir("$buildDir/generated/sources/openapi/src/main/java")
		}
	}
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
