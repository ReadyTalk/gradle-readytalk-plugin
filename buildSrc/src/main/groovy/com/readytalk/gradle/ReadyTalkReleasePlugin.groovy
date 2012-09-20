package com.readytalk.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import com.readytalk.gradle.tasks.InstallTask
import org.gradle.api.DefaultTask

class ReadyTalkReleasePlugin implements Plugin<Project> {
  Project project

  void apply(Project project) {
    this.project = project

    project.apply {
      plugin 'artifactory'
    }

    configureInstall()
    configurePublish()
  }

  void configureInstall() {
  	InstallTask install = project.tasks.add('install', InstallTask.class)
  	install.setDescription 'Install project into the local Ivy repository'
  	install.setGroup 'publish'
    install.dependsOn 'build'
  }

  void configurePublish() {
    DefaultTask publish = project.tasks.add('publish', DefaultTask.class)
    publish.setDescription 'Install project into corporate Artifactory'
    publish.setGroup 'publish'
    publish.dependsOn 'artifactoryPublish'
  }

}