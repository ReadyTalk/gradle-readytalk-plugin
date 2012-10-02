package com.readytalk.gradle.publishers

import com.readytalk.gradle.tasks.InstallTask
import org.gradle.api.Project
import org.gradle.api.Plugin
import org.gradle.api.DefaultTask
import org.gradle.api.Task

class LocalPublisherPlugin implements Plugin<Project> {

  Project project

  private static String name = 'local'
  private String url, userHome

  void apply(Project project) {
    this.project = project

    userHome = System.getProperty('user.home')
    url = "${userHome}/.ivy2/local"

    addRepo()
    addTask()
  }

  void addTask() {
    project.tasks.add(name: 'install', type: InstallTask) { 
      description "Install project into the local Ivy repository (${url})"
      group 'publish'
      dependsOn 'build'
    }
  }

  String addRepo() {

    project.repositories {
      ivy {
        name getName()
        url this.url
        layout "maven"
      }
    }

    return getName()
  }

  static String getName() {
    return name
  }

}