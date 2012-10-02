package com.readytalk.gradle.publishers

import com.readytalk.gradle.tasks.PublishTask
import org.gradle.api.Project
import org.gradle.api.Plugin
import org.gradle.api.DefaultTask
import org.gradle.api.Task

class OpenSourcePublisherPlugin implements Plugin<Project> {

  private Project project
  private static String snapshotName = 'oss_sonatype_ivy_snapshots'
  private String snapshotUrl = 'https://oss.sonatype.org/content/repositories/snapshots'
  private String username, password
  private Closure credentials

  void apply(Project project) {
    this.project = project

    addResolverRepo()

    if(setupProperties()) {
      addSnapshotRepo()
      addTask()
    } else {
      project.logger.warn('Publishing repositories and tasks not added! Set up your credentials!')
    }
  }

  void addResolverRepo() {
    project.repositories {
      mavenCentral()
    }
  }

  boolean setupProperties() {
    if(project.has('oss_sonatype_username') &&
       project.has('oss_sonatype_password')) {

      username = project.'oss_sonatype_username'
      password = project.'oss_sonatype_password'

      credentials = {
        setUsername username
        setPassword password
      }

      return true
    }
    return false
  }

  void addTask() {
   project.tasks.add(type: PublishTask, name: 'publish') {
      description 'Install project into "Maven Central"'
      group 'publish'
      dependsOn 'build'
    }
  }

  void addSnapshotRepo() {

    project.repositories {
      ivy {
        credentials {
          setUsername this.username
          setPassword this.password
        }
        name snapshotName
        url snapshotUrl
        layout 'maven'
      }
    }
  }

  static String getSnapshotRepoName() {
    return snapshotName
  }

}