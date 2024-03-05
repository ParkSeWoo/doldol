plugins {
	java
	id("org.springframework.boot") version "3.2.3"
	id("io.spring.dependency-management") version "1.1.4"
	id("org.graalvm.buildtools.native") version "0.9.28"
}

group = "io.sw"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
	compileOnly("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

/* sub direct 생성 */
tasks.register("createSubProjects") {
	doLast {
		val subProjects = listOf("domain", "admin-api", "client-api")

		subProjects.forEach { projectName ->
			val projectDir = project.projectDir.resolve(projectName)
			if (!projectDir.exists()) {
				projectDir.mkdirs()
				println("Created directory: ${projectDir.path}")

				// 자바 프로젝트를 위한 기본 소스 및 테스트 디렉토리 구조 생성
				val srcMainJava = projectDir.resolve("src/main/java")
				val srcMainResources = projectDir.resolve("src/main/resources")
				val srcTestJava = projectDir.resolve("src/test/java")
				val srcTestResources = projectDir.resolve("src/test/resources")

				listOf(srcMainJava, srcMainResources, srcTestJava, srcTestResources).forEach { dir ->
					if (!dir.exists()) {
						dir.mkdirs()
						println("Created directory: ${dir.path}")
					}
				}

				// 각 서브 프로젝트의 build.gradle.kts 파일 생성
				val buildFile = projectDir.resolve("build.gradle.kts")
				if (!buildFile.exists()) {
					buildFile.writeText("""
                        plugins {
                            java
                            id("org.springframework.boot") version "3.2.0"
                            id("io.spring.dependency-management") version "1.0.11.RELEASE"
                        }

                        dependencies {
                            // 자바 프로젝트에 필요한 의존성을 여기에 추가하세요.
                        }
                    """.trimIndent())
					println("Created build file: ${buildFile.path}")
				}
			}
		}
	}
}
