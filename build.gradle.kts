import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("jacoco")
	id("org.springframework.boot") version "2.4.1"
	id("io.spring.dependency-management") version "1.0.10.RELEASE"
	id("org.sonarqube") version "3.0"
	id("org.jmailen.kotlinter") version "3.3.0"
	id("com.gorylenko.gradle-git-properties") version "2.2.4"
	kotlin("jvm") version "1.4.21"
	kotlin("plugin.spring") version "1.4.21"
	kotlin("kapt") version "1.4.20"
}

group = "com.tul.fintech"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
	jcenter()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("org.springframework.cloud:spring-cloud-starter-config")
	implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
	implementation("org.springframework.kafka:spring-kafka:2.6.2")
	implementation ("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.kafka:spring-kafka-test:2.6.4")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("com.h2database:h2")
	implementation("org.hibernate:hibernate-envers")
	implementation("org.postgresql:postgresql:42.2.18")
	implementation("commons-codec:commons-codec:1.15")
	implementation("io.springfox:springfox-boot-starter:3.0.0")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("com.sipios:spring-search:0.2.4")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("net.minidev:json-smart:2.4.7")
	// implementation("org.liquibase:liquibase-core:4.3.1")
	compileOnly("io.springfox:springfox-swagger-ui:3.0.0")
	api("org.mapstruct:mapstruct:1.4.1.Final")
	kapt("org.mapstruct:mapstruct-processor:1.4.1.Final")
	kapt("org.springframework.boot:spring-boot-configuration-processor")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
	testImplementation("com.github.tomakehurst:wiremock-jre8:2.25.1")
	testImplementation("com.github.javafaker:javafaker:0.14") {
		exclude(module = "org.yaml")
	}
	implementation("io.sentry:sentry-spring-boot-starter:4.3.0")
	implementation("io.sentry:sentry-logback:4.3.0")
	implementation("dev.turingcomplete:kotlin-onetimepassword:2.1.0")
}

extra["springCloudVersion"] = "2020.0.1"

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
		allWarningsAsErrors = true
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.jacocoTestReport{
	reports {
		xml.isEnabled = true
	}
}

sonarqube {
	properties {
		property("sonar.sources", "src/main/kotlin")
		property("sonar.tests", "src/test/kotlin")
		property("sonar.verbose", "true")
		property("sonar.qualitygate.wait", "true")
		property("sonar.coverage.jacoco.xmlReportPaths", "build/reports/jacoco/test/jacocoTestReport.xml")
	}
}
