package com.readytalk.gradle.repos

import org.gradle.api.Project
import org.gradle.api.Plugin

class OpenSourceReposPlugin implements ReposConvention {

  Project project

  void apply(Project project) {
    this.project = project

    assert project.has('oss_sonatype_username')
    assert project.has('oss_sonatype_password')

    addLocal()
    addMainRepo()
    addSnapshotPublishRepo()
  }

  void addLocal() {
    def local = new LocalRepo()
    local.apply(project)
  }

  void addMainRepo() {
    project.repositories {
      mavenCentral()
    }
  }

  void addSnapshotPublishRepo() {
    project.uploadArchives.repositories {
      ivy {
        credentials {
          username project.'oss_sonatype_username'
          password project.'oss_sonatype_password'
        }
        name 'oss_sonatype_ivy_snapshots'
        url 'https://oss.sonatype.org/content/repositories/snapshots'
        layout 'maven'
      }
    }
  }

}