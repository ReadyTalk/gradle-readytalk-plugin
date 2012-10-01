package com.readytalk.gradle.publishers

import com.readytalk.gradle.tasks.InstallTask
import org.gradle.api.Project
import org.gradle.api.Plugin
import org.gradle.api.DefaultTask
import org.gradle.api.Task

class LocalPublisherPlugin implements Plugin<Project> {

  Project project

  void apply(Project project) {
    this.project = project

    addInstallTask(InstallTask.class)
  }

  void addInstallTask(Class<Task> task) {
    InstallTask install = project.tasks.add('install', task)
    install.setDescription 'Install project into the local Ivy repository'
    install.setGroup 'publish'
    install.dependsOn 'build'
  }

}