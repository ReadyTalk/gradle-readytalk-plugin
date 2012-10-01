package com.readytalk.gradle.repos

import org.gradle.api.Project
import org.gradle.api.Plugin

class LocalRepo implements Plugin<Project> {

  Project project
  static String localName = 'readytalk_local'

  String userHome = System.getProperty("user.home")

  void apply(Project project) {
    this.project = project

    addLocal()
  }

  void addLocal() {
    project.repositories {
      ivy {
        name localName
        url "${userHome}/.ivy2/local"
        layout "maven"
      }
    }

    project.uploadArchives.repositories {
      add project.repositories.getByName(localName)
    }
  }

}