package com.readytalk.gradle.plugins

import org.gradle.api.Project
import org.gradle.api.Plugin
import org.gradle.api.InvalidUserDataException

class OpenSonatypePublisher implements Plugin<Project> {

  private Project project

  private String username, password

  void apply(Project project) {
    this.project = project

    project.apply {
      plugin 'properties'
    }

    loadCredentials()
    addResolverRepo()

    project.apply {
      plugin 'maven'
      plugin 'signing'
    }

    project.signing {
      sign project.configurations.archives
    }

    project.uploadArchives.repositories.mavenDeployer {
      name = 'Sonatype OSS'
      repository(url:'https://oss.sonatype.org/service/local/staging/deploy/maven2') {
        authentication(userName: this.username, password: this.password)
      }
      snapshotRepository(url:'https://oss.sonatype.org/content/repositories/snapshots') {
        authentication(userName: this.username, password: this.password)
      }

      beforeDeployment { project.signing.signPom it }

      pom.project {
        name project.name
        description project.description
      }
    }

    addPublishTask()
  }

  private void addResolverRepo() {
    project.repositories {
      mavenCentral()
    }
  }

  private void loadCredentials() {
    if(project.has('oss_sonatype_username') &&
       project.has('oss_sonatype_password')) {

      username = project.'oss_sonatype_username'
      password = project.'oss_sonatype_password'
    } else {
      throw new InvalidUserDataException('You need to supply "oss_sonatype_username" and "oss_sonatype_password" to use the Sonatype publisher plugin')
    }
  }

  private void addPublishTask() {
    project.tasks.add(name: 'publish') {
      dependsOn 'build', 'uploadArchives'
    }
  }
}